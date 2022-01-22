package com.leanscale.listdetails.features.gamesList.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.leanscale.listdetails.core.baseUi.BaseViewModel
import com.leanscale.listdetails.domain.models.GamesRequest
import com.leanscale.listdetails.domain.usecase.GetGamesListUseCase
import com.leanscale.listdetails.features.gamesList.GamesListViewState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import utils.AppConstants


import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class GamesListViewModel @Inject constructor(private val getGamesListUseCase: GetGamesListUseCase) :
    BaseViewModel() {
    val viewState: LiveData<GamesListViewState>
        get() = _viewState
    private val LIST_PAGE_SIZE = 10
    private val pageNumber = 1

    private val _viewState: MutableLiveData<GamesListViewState> = MutableLiveData()


    fun getGamesList(context: CoroutineContext = Dispatchers.IO) {
        val gamesRequest= GamesRequest(
            AppConstants.API_KEY,
        LIST_PAGE_SIZE,
            pageNumber)
        getGamesListUseCase.execute(gamesRequest).flowOn(context).onEach {
           _viewState.postValue(it)
        }.launchIn(viewModelScope)
    }


}