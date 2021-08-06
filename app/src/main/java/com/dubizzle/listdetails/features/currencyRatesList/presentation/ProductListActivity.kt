package com.dubizzle.listdetails.features.currencyRatesList.presentation

import android.os.Bundle
import androidx.activity.viewModels
import com.dubizzle.listdetails.R
import com.swensonhe.currencyconverter.core.baseUi.BaseActivity
import com.swensonhe.currencyconverter.core.baseUi.BaseViewModel
import com.swensonhe.currencyconverter.features.currencyRatesList.presentation.CurrencyRatesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductListActivity : BaseActivity() {
    private val viewModel: CurrencyRatesViewModel by viewModels()
    override val baseViewModel: BaseViewModel
        get() = viewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.currency_rates_activity_layout)
    }
}