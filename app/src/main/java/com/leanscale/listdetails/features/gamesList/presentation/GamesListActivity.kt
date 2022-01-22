package com.leanscale.listdetails.features.gamesList.presentation

import android.os.Bundle
import androidx.activity.viewModels
import com.leanscale.listdetails.R
import com.leanscale.listdetails.core.baseUi.BaseActivity
import com.leanscale.listdetails.core.baseUi.BaseViewModel

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GamesListActivity : BaseActivity() {
    private val viewModel: GamesListViewModel by viewModels()
    override val baseViewModel: BaseViewModel
        get() = viewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.games_list_activity_layout)
    }
}