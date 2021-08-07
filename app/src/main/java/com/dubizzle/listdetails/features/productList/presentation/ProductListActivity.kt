package com.dubizzle.listdetails.features.productList.presentation

import android.os.Bundle
import androidx.activity.viewModels
import com.dubizzle.listdetails.R
import com.dubizzle.listdetails.core.baseUi.BaseActivity
import com.dubizzle.listdetails.core.baseUi.BaseViewModel

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductListActivity : BaseActivity() {
    private val viewModel: ProductListViewModel by viewModels()
    override val baseViewModel: BaseViewModel
        get() = viewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_list_activity_layout)
    }
}