package com.swensonhe.currencyconverter.domain.network.repositories

import com.swensonhe.currencyconverter.domain.models.CurrencyRatesResponse
import com.swensonhe.currencyconverter.domain.network.apiServices.CurrencyRateServiceApi
import javax.inject.Inject


 class CurrencyRatesRepoImp @Inject constructor(val currencyRateServiceApi : CurrencyRateServiceApi) : CurrencyRatesRepoInterface {
    override suspend fun getCurrencyRates(accessKey: String): CurrencyRatesResponse? {
       return currencyRateServiceApi.getCurrencyRates(accessKey)
    }
}