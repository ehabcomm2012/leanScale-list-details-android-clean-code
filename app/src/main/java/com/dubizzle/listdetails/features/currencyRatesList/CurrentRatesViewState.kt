package com.swensonhe.currencyconverter.features.currencyRatesList

import com.swensonhe.currencyconverter.domain.models.CurrencyRatesResponse

sealed class CurrentRatesViewState {
    object  LoadingState : CurrentRatesViewState()
    data class FailureState(val message: String) : CurrentRatesViewState()
    data class SuccessState(val currencyRatesList: CurrencyRatesResponse) : CurrentRatesViewState()
}