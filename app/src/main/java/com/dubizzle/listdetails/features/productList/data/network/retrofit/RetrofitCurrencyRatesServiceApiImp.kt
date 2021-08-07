package com.swensonhe.currencyconverter.features.currencyRatesList.data.network.retrofit

import com.swensonhe.currencyconverter.domain.models.ProductListResponse
import com.swensonhe.currencyconverter.domain.network.apiServices.ProductListServiceApi
import javax.inject.Inject


class RetrofitCurrencyRatesServiceApiImp @Inject constructor(val retrofitProductListServiceApi: RetrofitProductListServiceApi) : ProductListServiceApi {
    override suspend fun getCurrencyRates(accessKey: String): ProductListResponse? {
        return retrofitProductListServiceApi.getCurrencyRates(accessKey)
    }
}