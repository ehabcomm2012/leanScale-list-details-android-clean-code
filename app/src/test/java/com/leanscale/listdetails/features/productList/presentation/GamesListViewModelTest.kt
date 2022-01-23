package com.leanscale.listdetails.features.productList.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.leanscale.listdetails.domain.models.Game
import com.leanscale.listdetails.domain.models.GamesRequest
import com.leanscale.listdetails.domain.usecase.GetGamesListUseCase
import com.leanscale.listdetails.features.gamesList.GamesListViewState
import com.leanscale.listdetails.features.gamesList.presentation.GamesListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import utils.AppConstants

@ExperimentalCoroutinesApi
class GamesListViewModelTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule  //That’s because LiveData is a part of the Android lifecycle, and it needs to access the main looper to proceed
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test get product list when success api request`() = runBlockingTest {

        val getGamesListUseCase = Mockito.mock(GetGamesListUseCase::class.java)
        val testResponseSucessState = GamesListViewState.SuccessState(
            listOf(
                    Game(
                        id="",
                        released = "2019-02-24 04:04:17",
                        name = "Notebook",
                        rating = 4.6f,
                        background_image = "",
                        genres = listOf()
                    )
                )
        ,1000
            )

        val testGamesRequest= GamesRequest(
            AppConstants.API_KEY,
            10,
            1)
        Mockito.`when`(getGamesListUseCase.execute(testGamesRequest)).thenReturn(flow {
            emit(testResponseSucessState)
        })
        val gamesListViewModel = GamesListViewModel(getGamesListUseCase)

        gamesListViewModel.getNextGamesListPage(Dispatchers.Main)

        val viewStateData = gamesListViewModel.viewState.value
        assertEquals(testResponseSucessState, viewStateData)
    }
}