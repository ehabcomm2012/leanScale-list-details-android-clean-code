package com.dubizzle.listdetails.features.productList.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dubizzle.listdetails.domain.models.Product
import com.dubizzle.listdetails.domain.models.ProductListResponse
import com.dubizzle.listdetails.domain.usecase.GetProductListUseCase
import com.dubizzle.listdetails.features.productList.ProductListViewState
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

@ExperimentalCoroutinesApi
class ProductListViewModelTest {

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

        val getProductListUseCase = Mockito.mock(GetProductListUseCase::class.java)
        val testResponseSucessState = ProductListViewState.SuccessState(
            ProductListResponse(
                results = listOf(
                    Product(
                        created_at = "2019-02-24 04:04:17",
                        price = "AED 5",
                        name = "Notebook",
                        uid = "4878bf592579410fba52941d00b62f94",
                        image_ids = listOf("4878bf592579410fba52941d00b62f94"),
                        image_urls_thumbnails = listOf("9355183956e3445e89735d877b798689")
                    )
                )
            )
        )
        Mockito.`when`(getProductListUseCase.execute()).thenReturn(flow {
            emit(testResponseSucessState)
        })
        val productListViewModel = ProductListViewModel(getProductListUseCase)

        productListViewModel.getProductList(Dispatchers.Main)

        val viewStateData = productListViewModel.viewState.value
        assertEquals(testResponseSucessState, viewStateData)
    }
}