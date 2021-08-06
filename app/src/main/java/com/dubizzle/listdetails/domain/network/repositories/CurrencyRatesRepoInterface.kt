package com.swensonhe.currencyconverter.domain.network.repositories

import com.swensonhe.currencyconverter.domain.models.CurrencyRatesResponse

interface CurrencyRatesRepoInterface {
    suspend fun getCurrencyRates(accessKey:String) : CurrencyRatesResponse?
}