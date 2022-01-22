package com.leanscale.listdetails.features.gamesList.presentation

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
import com.leanscale.listdetails.domain.models.ProductListResponse
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
        _binding = FragmentGamesListBinding.inflate(inflater, container, false)
        val view = binding.root
        gamesListViewModel.getProductList()
        return view
    }


    override fun subscribeObservers() {
        gamesListViewModel.viewState.observe(viewLifecycleOwner, Observer {
            setUiState(it)
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
                submitProductListToAdapter(viewState.productList)
            }
            GamesListViewState.EmptyState -> showEmptyState()
        }
    }

    private fun showEmptyState() {
        Toast.makeText(requireContext(), R.string.no_available_games, Toast.LENGTH_LONG).show()
    }

    private fun submitProductListToAdapter(productList: ProductListResponse) {
        gamesListAdapter = GamesListAdapter(productList.results!!) {
            val bundle = Bundle().apply {
                putSerializable(PRODUCT_DETAILS_KEY, it)
            }
            findNavController().navigate(
                R.id.action_gamesListFragment_to_gameDetailsFragment,
                bundle
            )
        }
        _binding?.rvProductList?.layoutManager = LinearLayoutManager(requireContext())
        _binding?.rvProductList?.adapter = gamesListAdapter

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClicks()
    }

    private fun setUpClicks() {
        _binding?.ivBack?.setOnClickListener {
            requireActivity().finish()
        }
    }

    companion object {
        const val PRODUCT_DETAILS_KEY = "productDetailsKey"

        @JvmStatic
        fun newInstance() =
            GamesListFragment().apply {
                arguments = Bundle().apply {
                }
            }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}