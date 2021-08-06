package com.swensonhe.currencyconverter.features.currencyRatesList.data.network.retrofit

import com.swensonhe.currencyconverter.domain.models.CurrencyRatesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitCurrencyRatesServiceApi {
    @GET("latest")
     suspend fun getCurrencyRates(@Query("access_key") accessKey :String): CurrencyRatesResponse?
}