package com.leanscale.listdetails.features.gamesList.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.leanscale.listdetails.R
import com.leanscale.listdetails.core.baseUi.BaseActivity
import com.leanscale.listdetails.core.baseUi.BaseViewModel
import com.leanscale.listdetails.databinding.FragmentGamesListBinding
import com.leanscale.listdetails.databinding.GamesListActivityLayoutBinding

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GamesListActivity : BaseActivity() {
    override val baseViewModel: BaseViewModel? =null

    private var _binding: GamesListActivityLayoutBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = GamesListActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        if(!findNavController(binding.navHostFragment.id).navigateUp())
            finish()
    }
}