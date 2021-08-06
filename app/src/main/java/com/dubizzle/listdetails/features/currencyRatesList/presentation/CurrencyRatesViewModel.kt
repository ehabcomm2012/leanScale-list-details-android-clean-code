package com.swensonhe.currencyconverter.features.currencyRatesList.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.swensonhe.currencyconverter.core.baseUi.BaseViewModel
import com.swensonhe.currencyconverter.domain.usecase.BaseUseCase
import com.swensonhe.currencyconverter.domain.usecase.GetCurrencyRatesUseCase
import com.swensonhe.currencyconverter.features.currencyRatesList.CurrentRatesViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import utils.AppConstants
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class CurrencyRatesViewModel @Inject constructor(private val getCurrencyRatesUseCase: GetCurrencyRatesUseCase) :
    BaseViewModel() {
     val currencyRate: MutableLiveData<Pair<String, Float>?> = MutableLiveData()

    val viewState: LiveData<CurrentRatesViewState>
        get() = _viewState

    private val _viewState: MutableLiveData<CurrentRatesViewState> = MutableLiveData()


    fun getCurrencyRates(context: CoroutineContext = Dispatchers.IO) {
        getCurrencyRatesUseCase.execute(AppConstants.ACCESS_KEY).flowOn(context).onEach {
           _viewState.postValue(it)
        }.launchIn(viewModelScope)
    }

    fun calculateCurrencyRate(inputEuroNumber: Float ,euroRate:Float) {
        currencyRate.postValue(Pair(currencyRate.value!!.first,inputEuroNumber * euroRate))
    }
}