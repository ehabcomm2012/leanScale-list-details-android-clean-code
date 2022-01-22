package com.leanscale.listdetails.features.gamesList.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.leanscale.listdetails.core.baseUi.BaseViewModel
import com.leanscale.listdetails.domain.usecase.GetGamesListUseCase
import com.leanscale.listdetails.features.gamesList.GamesListViewState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class GamesListViewModel @Inject constructor(private val getGamesListUseCase: GetGamesListUseCase) :
    BaseViewModel() {
    val viewState: LiveData<GamesListViewState>
        get() = _viewState

    private val _viewState: MutableLiveData<GamesListViewState> = MutableLiveData()


    fun getProductList(context: CoroutineContext = Dispatchers.IO) {
        getGamesListUseCase.execute().flowOn(context).onEach {
           _viewState.postValue(it)
        }.launchIn(viewModelScope)
    }


}