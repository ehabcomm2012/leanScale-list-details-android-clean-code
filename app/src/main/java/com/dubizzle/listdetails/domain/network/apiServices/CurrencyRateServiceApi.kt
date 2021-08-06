package com.swensonhe.currencyconverter.domain.network.apiServices

import com.swensonhe.currencyconverter.domain.models.CurrencyRatesResponse

interface CurrencyRateServiceApi {
    suspend fun getCurrencyRates(accessKey:String) : CurrencyRatesResponse?
}