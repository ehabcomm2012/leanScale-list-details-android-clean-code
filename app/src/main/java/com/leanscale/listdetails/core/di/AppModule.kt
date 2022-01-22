package com.leanscale.listdetails.core.di

import com.leanscale.listdetails.domain.network.apiServices.GamesListServiceApi
import com.leanscale.listdetails.domain.network.repositories.GamesListRepoImp
import com.leanscale.listdetails.domain.network.repositories.GamesListRepoInterface
import com.leanscale.listdetails.features.gamesList.data.network.retrofit.RetrofitGamesListServiceApiImp
import com.leanscale.listdetails.features.gamesList.data.network.retrofit.RetrofitGamesListServiceApi
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
    fun provideApiService(retrofit : Retrofit): RetrofitGamesListServiceApi {
        return retrofit.create(RetrofitGamesListServiceApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCurrencyRateServiceApi(retrofitGamesListServiceApi:RetrofitGamesListServiceApi) : GamesListServiceApi {
        return RetrofitGamesListServiceApiImp(retrofitGamesListServiceApi)
    }

    @Singleton
    @Provides
    fun provideCurrencyRatesRepo(gamesListServiceApi : GamesListServiceApi): GamesListRepoInterface {
        return GamesListRepoImp(gamesListServiceApi)
    }
}