package com.leanscale.listdetails.features.gamesList.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.leanscale.listdetails.core.baseUi.BaseViewModel
import com.leanscale.listdetails.domain.models.Game
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
    val viewState: LiveData<GamesListViewState?>
        get() = _viewState
    private val LIST_PAGE_SIZE = 10
    private var pageNumber = 0
    private var numberOfPages = 1
    var lastFirstVisiblePosition : Int?=null
    private val _viewState: MutableLiveData<GamesListViewState?> = MutableLiveData(null)
    var currentGamesList: ArrayList<Game>?=null

    fun getNextGamesListPage(context: CoroutineContext = Dispatchers.IO) {

            if (numberOfPages >= ++pageNumber) {
                val gamesRequest = GamesRequest(
                    AppConstants.API_KEY,
                    LIST_PAGE_SIZE,
                    pageNumber
                )
                getGamesListUseCase.execute(gamesRequest).flowOn(context).onEach {
                    if (it is GamesListViewState.SuccessState) {
                        numberOfPages = Math.ceil(it.totalCount.toDouble() / LIST_PAGE_SIZE).toInt()
                        if(currentGamesList==null)
                            currentGamesList= arrayListOf()
                        currentGamesList?.addAll(it.gamesList)
                        it.gamesList = currentGamesList!!
                    }
                    _viewState.postValue(it)
                }.launchIn(viewModelScope)
            }

    }


}