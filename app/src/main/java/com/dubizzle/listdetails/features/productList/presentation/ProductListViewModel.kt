package com.dubizzle.listdetails.features.productList.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dubizzle.listdetails.core.baseUi.BaseViewModel
import com.dubizzle.listdetails.domain.usecase.GetProductListUseCase
import com.dubizzle.listdetails.features.productList.ProductListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

import utils.AppConstants
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class ProductListViewModel @Inject constructor(private val getProductListUseCase: GetProductListUseCase) :
    BaseViewModel() {
    val viewState: LiveData<ProductListViewState>
        get() = _viewState

    private val _viewState: MutableLiveData<ProductListViewState> = MutableLiveData()


    fun getCurrencyRates(context: CoroutineContext = Dispatchers.IO) {
        getProductListUseCase.execute().flowOn(context).onEach {
           _viewState.postValue(it)
        }.launchIn(viewModelScope)
    }


}