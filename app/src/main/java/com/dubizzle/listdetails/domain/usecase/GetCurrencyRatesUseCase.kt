package com.swensonhe.currencyconverter.domain.usecase

import com.swensonhe.currencyconverter.domain.network.repositories.CurrencyRatesRepoInterface
import com.swensonhe.currencyconverter.features.currencyRatesList.CurrentRatesViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

 class GetCurrencyRatesUseCase @Inject constructor(val currencyRatesRepoInterface: CurrencyRatesRepoInterface) :
    BaseUseCase<String, CurrentRatesViewState?>() {
    override fun execute(accessKey: String): Flow<CurrentRatesViewState?> {
        return flow {
            emit(CurrentRatesViewState.LoadingState)
            try {
                val ratesList = currencyRatesRepoInterface.getCurrencyRates(accessKey)

                if (ratesList != null && ratesList.success)
                    emit(CurrentRatesViewState.SuccessState(ratesList))
                else
                    emit(CurrentRatesViewState.FailureState("Failed from the server error"))

            } catch (e: Exception) {
                e.printStackTrace()
                emit(CurrentRatesViewState.FailureState("Failed from a network connection error"))

            }
        }
    }
}