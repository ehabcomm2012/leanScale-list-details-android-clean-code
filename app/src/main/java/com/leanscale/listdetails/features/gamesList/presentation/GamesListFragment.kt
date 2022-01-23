package com.leanscale.listdetails.features.gamesList.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.leanscale.listdetails.R
import com.leanscale.listdetails.core.baseUi.BaseFragment
import com.leanscale.listdetails.core.baseUi.BaseViewModel
import com.leanscale.listdetails.databinding.FragmentGamesListBinding
import com.leanscale.listdetails.domain.models.Game
import com.leanscale.listdetails.features.gamesList.GamesListViewState
import com.leanscale.listdetails.features.gamesList.presentation.adapters.GamesListAdapter


class GamesListFragment : BaseFragment() {
    private val gamesListViewModel: GamesListViewModel by activityViewModels()
    override val baseViewModel: BaseViewModel
        get() = gamesListViewModel
    private var _binding: FragmentGamesListBinding? = null
    private val binding get() = _binding!!
    private lateinit var gamesListAdapter: GamesListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_binding == null) {
            _binding = FragmentGamesListBinding.inflate(inflater, container, false)
            setUpClicks()
            subscribeObservers()
            if(gamesListViewModel.currentGamesList.isNullOrEmpty())
            gamesListViewModel.getNextGamesListPage()
        }
        return binding.root
    }

    fun subscribeObservers() {
        gamesListViewModel.viewState.observe(viewLifecycleOwner, Observer {
            it?.let { gamesListState->
                setUiState(gamesListState)
            }
        })

    }

    private fun setUiState(viewState: GamesListViewState) {
        when (viewState) {
            is GamesListViewState.FailureState -> {
                hideLoader()
                showFailureNetworkDialog()
            }
            is GamesListViewState.LoadingState -> {
                showLoader()
            }
            is GamesListViewState.SuccessState -> {
                hideLoader()
                submitProductListToAdapter(viewState.gamesList)
            }
            GamesListViewState.EmptyState -> showEmptyState()
        }
    }

    private fun showEmptyState() {
        Toast.makeText(requireContext(), R.string.no_available_games, Toast.LENGTH_LONG).show()
    }

    private fun submitProductListToAdapter(gamesList: List<Game>?) {
        if (!this::gamesListAdapter.isInitialized && gamesList?.size!! > 0) {
            gamesListAdapter = GamesListAdapter(
                gamesList!! as ArrayList<Game>, { game ->

                    val bundle = Bundle().apply {
                        putSerializable(GAME_DETAILS_KEY, game)
                    }
                    findNavController().navigate(
                        R.id.action_gamesListFragment_to_gameDetailsFragment,
                        bundle
                    )
                },
                ::onReachlastItem
            )
            _binding?.rvGamesList?.layoutManager = LinearLayoutManager(requireContext())
            _binding?.rvGamesList?.adapter = gamesListAdapter
            if( gamesListViewModel.lastFirstVisiblePosition!=null)
                (_binding?.rvGamesList?.layoutManager as LinearLayoutManager).scrollToPosition(gamesListViewModel.lastFirstVisiblePosition!!)

        } else if (gamesList?.size!! > 0) {
            gamesListAdapter.bindList(gamesList as ArrayList)
        }

    }

    private fun onReachlastItem() {
        gamesListViewModel.getNextGamesListPage()
    }


    private fun setUpClicks() {
        _binding?.ivBack?.setOnClickListener {
            if (requireActivity() is GamesListActivity)
                (requireActivity() as GamesListActivity).onBackPressed()
        }
    }

    companion object {
        const val GAME_DETAILS_KEY = "gameDetailsKey"

        @JvmStatic
        fun newInstance() =
            GamesListFragment().apply {
                arguments = Bundle().apply {
                }
            }

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        gamesListViewModel.lastFirstVisiblePosition=
            (_binding?.rvGamesList?.getLayoutManager() as LinearLayoutManager).findFirstVisibleItemPosition()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}