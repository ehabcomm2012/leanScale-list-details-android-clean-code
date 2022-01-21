package com.leanscale.listdetails.features.productList.data.network.retrofit


import com.leanscale.listdetails.domain.models.ProductListResponse
import com.leanscale.listdetails.domain.network.apiServices.ProductListServiceApi
import javax.inject.Inject


class RetrofitCurrencyRatesServiceApiImp @Inject constructor(val retrofitProductListServiceApi: RetrofitProductListServiceApi) :
    ProductListServiceApi {
    override suspend fun getProductList(): ProductListResponse? {
        return retrofitProductListServiceApi.getProductList()
    }
}