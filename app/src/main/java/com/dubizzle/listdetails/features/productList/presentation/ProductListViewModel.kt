package com.swensonhe.currencyconverter.features.currencyRatesList.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.swensonhe.currencyconverter.core.baseUi.BaseViewModel
import com.swensonhe.currencyconverter.domain.usecase.GetProductListUseCase
import com.swensonhe.currencyconverter.features.currencyRatesList.ProductListViewState
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
     val currencyRate: MutableLiveData<Pair<String, Float>?> = MutableLiveData()

    val viewState: LiveData<ProductListViewState>
        get() = _viewState

    private val _viewState: MutableLiveData<ProductListViewState> = MutableLiveData()


    fun getCurrencyRates(context: CoroutineContext = Dispatchers.IO) {
        getProductListUseCase.execute(AppConstants.ACCESS_KEY).flowOn(context).onEach {
           _viewState.postValue(it)
        }.launchIn(viewModelScope)
    }

    fun calculateCurrencyRate(inputEuroNumber: Float ,euroRate:Float) {
        currencyRate.postValue(Pair(currencyRate.value!!.first,inputEuroNumber * euroRate))
    }
}