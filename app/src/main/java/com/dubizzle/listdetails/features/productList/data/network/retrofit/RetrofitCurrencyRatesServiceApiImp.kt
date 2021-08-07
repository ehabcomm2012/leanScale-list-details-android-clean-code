package com.dubizzle.listdetails.features.productList.data.network.retrofit

import com.dubizzle.listdetails.domain.models.ProductListResponse
import com.dubizzle.listdetails.domain.network.apiServices.ProductListServiceApi
import javax.inject.Inject


class RetrofitCurrencyRatesServiceApiImp @Inject constructor(val retrofitProductListServiceApi: RetrofitProductListServiceApi) : ProductListServiceApi {
    override suspend fun getProductList(): ProductListResponse? {
        return retrofitProductListServiceApi.getProductList()
    }
}