package com.dubizzle.listdetails.features.productDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dubizzle.listdetails.core.baseUi.BaseFragment
import com.dubizzle.listdetails.core.baseUi.BaseViewModel
import com.dubizzle.listdetails.databinding.FragmentProductDetailsBinding
import com.dubizzle.listdetails.domain.models.Product
import com.dubizzle.listdetails.features.productList.presentation.ProductListFragment
import com.dubizzle.listdetails.features.productList.presentation.ProductListViewModel


class ProductDetailsFragment : BaseFragment() {

    private val productListViewModel: ProductListViewModel by activityViewModels()
    override val baseViewModel: BaseViewModel
        get() = productListViewModel
    private var productItem: Product? = null
    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productItem = arguments?.getSerializable(ProductListFragment.PRODUCT_DETAILS_KEY) as Product?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindData()
    }

    private fun bindData() {
        _binding?.tvRate?.text = productItem?.name
        _binding?.tvCountry?.text = productItem?.price
    }

    override fun subscribeObservers() {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}