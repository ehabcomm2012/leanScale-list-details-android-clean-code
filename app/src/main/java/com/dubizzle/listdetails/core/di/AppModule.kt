package com.swensonhe.currencyconverter.core.di

import com.swensonhe.currencyconverter.domain.network.apiServices.CurrencyRateServiceApi
import com.swensonhe.currencyconverter.domain.network.repositories.CurrencyRatesRepoImp
import com.swensonhe.currencyconverter.domain.network.repositories.CurrencyRatesRepoInterface
import com.swensonhe.currencyconverter.features.currencyRatesList.data.network.retrofit.RetrofitCurrencyRatesServiceApi
import com.swensonhe.currencyconverter.features.currencyRatesList.data.network.retrofit.RetrofitCurrencyRatesServiceApiImp
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
    fun provideApiService(retrofit : Retrofit): RetrofitCurrencyRatesServiceApi {
        return retrofit.create(RetrofitCurrencyRatesServiceApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCurrencyRateServiceApi(retrofitCurrencyRatesServiceApi:RetrofitCurrencyRatesServiceApi) : CurrencyRateServiceApi {
        return RetrofitCurrencyRatesServiceApiImp(retrofitCurrencyRatesServiceApi)
    }

    @Singleton
    @Provides
    fun provideCurrencyRatesRepo( currencyRateServiceApi : CurrencyRateServiceApi): CurrencyRatesRepoInterface {
        return CurrencyRatesRepoImp(currencyRateServiceApi)
    }
}