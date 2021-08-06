package com.swensonhe.currencyconverter.features.currencyRatesList.data.network.retrofit

import com.swensonhe.currencyconverter.domain.models.CurrencyRatesResponse
import com.swensonhe.currencyconverter.domain.network.apiServices.CurrencyRateServiceApi
import javax.inject.Inject


class RetrofitCurrencyRatesServiceApiImp @Inject constructor(val retrofitCurrencyRatesServiceApi: RetrofitCurrencyRatesServiceApi) : CurrencyRateServiceApi {
    override suspend fun getCurrencyRates(accessKey: String): CurrencyRatesResponse? {
        return retrofitCurrencyRatesServiceApi.getCurrencyRates(accessKey)
    }
}