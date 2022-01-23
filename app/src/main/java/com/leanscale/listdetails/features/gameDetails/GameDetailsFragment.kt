package com.leanscale.listdetails.features.gameDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.leanscale.listdetails.R
import com.leanscale.listdetails.core.baseUi.BaseFragment
import com.leanscale.listdetails.core.baseUi.BaseViewModel
import com.leanscale.listdetails.databinding.FragmentGameDetailsBinding
import com.leanscale.listdetails.domain.models.Game
import com.leanscale.listdetails.features.gamesList.presentation.GamesListFragment
import com.leanscale.listdetails.features.gamesList.presentation.GamesListViewModel


class GameDetailsFragment : BaseFragment() {

    private val gamesListViewModel: GamesListViewModel by activityViewModels()
    override val baseViewModel: BaseViewModel
        get() = gamesListViewModel
    private var gameItem: Game? = null
    private var _binding: FragmentGameDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameItem =
            arguments?.getSerializable(GamesListFragment.GAME_DETAILS_KEY) as Game?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindData()
    }

    private fun bindData() {
        _binding?.toolbar?.title = gameItem?.name
        _binding?.collapsingToolbarLayout?.contentScrim =
            ContextCompat.getDrawable(requireContext(), R.color.teal_200)

        Glide
            .with(requireContext())
            .load(gameItem?.background_image)
            .centerCrop()
            .into(_binding?.ivGame!!)
        _binding?.tvTitle?.text = gameItem?.name
        _binding?.tvShortDesc?.text = gameItem?.released
        _binding?.tvRating?.text =gameItem?.rating?.toString()
        _binding?.tvLotPrice?.text =  gameItem?.genres?.get(0)?.name

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}