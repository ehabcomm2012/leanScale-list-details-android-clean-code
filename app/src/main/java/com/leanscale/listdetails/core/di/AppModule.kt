package com.leanscale.listdetails.core.di

import com.leanscale.listdetails.domain.network.apiServices.ProductListServiceApi
import com.leanscale.listdetails.domain.network.repositories.ProductListRepoImp
import com.leanscale.listdetails.domain.network.repositories.ProductListRepoInterface
import com.leanscale.listdetails.features.productList.data.network.retrofit.RetrofitCurrencyRatesServiceApiImp
import com.leanscale.listdetails.features.productList.data.network.retrofit.RetrofitProductListServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import utils.AppConstants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitClient(okHttpClient :OkHttpClient ,factory : Converter.Factory) : Retrofit {
        return  Retrofit.Builder()
            .baseUrl(AppConstants.BASE_API_URL)
            .client(okHttpClient)
            .addConverterFactory(factory)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory():Converter.Factory {
      return  GsonConverterFactory.create()
    }
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectionSpecs(
                listOf(
                    ConnectionSpec.MODERN_TLS,
                    ConnectionSpec.COMPATIBLE_TLS,
                    ConnectionSpec.CLEARTEXT
                )
            )
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .cache(null)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit : Retrofit): RetrofitProductListServiceApi {
        return retrofit.create(RetrofitProductListServiceApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCurrencyRateServiceApi(retrofitProductListServiceApi:RetrofitProductListServiceApi) : ProductListServiceApi {
        return RetrofitCurrencyRatesServiceApiImp(retrofitProductListServiceApi)
    }

    @Singleton
    @Provides
    fun provideCurrencyRatesRepo(productListServiceApi : ProductListServiceApi): ProductListRepoInterface {
        return ProductListRepoImp(productListServiceApi)
    }
}