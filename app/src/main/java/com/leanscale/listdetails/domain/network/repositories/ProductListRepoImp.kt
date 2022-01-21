package com.leanscale.listdetails.domain.network.repositories

import com.leanscale.listdetails.domain.models.ProductListResponse
import com.leanscale.listdetails.domain.network.apiServices.ProductListServiceApi
import javax.inject.Inject


 class ProductListRepoImp @Inject constructor(val productListServiceApi : ProductListServiceApi) : ProductListRepoInterface {
    override suspend fun getProductList(): ProductListResponse? {
       return productListServiceApi.getProductList()
    }
}