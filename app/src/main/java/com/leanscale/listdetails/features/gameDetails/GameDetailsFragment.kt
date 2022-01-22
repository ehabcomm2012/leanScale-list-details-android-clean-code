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
import com.leanscale.listdetails.domain.models.Games
import com.leanscale.listdetails.features.gamesList.presentation.GamesListFragment
import com.leanscale.listdetails.features.gamesList.presentation.GamesListViewModel


class GameDetailsFragment : BaseFragment() {

    private val gamesListViewModel: GamesListViewModel by activityViewModels()
    override val baseViewModel: BaseViewModel
        get() = gamesListViewModel
    private var gamesItem: Games? = null
    private var _binding: FragmentGameDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gamesItem =
            arguments?.getSerializable(GamesListFragment.PRODUCT_DETAILS_KEY) as Games?
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
        _binding?.toolbar?.title = gamesItem?.name
        _binding?.collapsingToolbarLayout?.contentScrim =
            ContextCompat.getDrawable(requireContext(), R.color.teal_200)

        Glide
            .with(requireContext())
            .load(gamesItem?.background_image)
            .centerCrop()
            .into(_binding?.ivGame!!)
        _binding?.tvTitle?.text = gamesItem?.name
        _binding?.tvShortDesc?.text = gamesItem?.released
        _binding?.tvProductTotalRate?.text =gamesItem?.rating?.toString()
        _binding?.tvLotPrice?.text =  gamesItem?.genres?.get(0)?.name

    }

    override fun subscribeObservers() {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}