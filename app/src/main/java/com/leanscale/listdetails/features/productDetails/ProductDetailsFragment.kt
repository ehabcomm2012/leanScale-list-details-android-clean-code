package com.leanscale.listdetails.features.productDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.leanscale.listdetails.R
import com.leanscale.listdetails.core.baseUi.BaseFragment
import com.leanscale.listdetails.core.baseUi.BaseViewModel
import com.leanscale.listdetails.databinding.FragmentProductDetailsBinding
import com.leanscale.listdetails.domain.models.Product
import com.leanscale.listdetails.features.productList.presentation.ProductListFragment
import com.leanscale.listdetails.features.productList.presentation.ProductListViewModel


class ProductDetailsFragment : BaseFragment() {

    private val productListViewModel: ProductListViewModel by activityViewModels()
    override val baseViewModel: BaseViewModel
        get() = productListViewModel
    private var productItem: Product? = null
    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productItem =
            arguments?.getSerializable(ProductListFragment.PRODUCT_DETAILS_KEY) as Product?
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
        _binding?.toolbar?.title = productItem?.name
        _binding?.collapsingToolbarLayout?.contentScrim =
            ContextCompat.getDrawable(requireContext(), R.color.teal_200)

        Glide
            .with(requireContext())
            .load(productItem?.image_urls_thumbnails?.get(0))
            .centerCrop()
            .into(_binding?.ivProduct!!)
        _binding?.tvTitle?.text = productItem?.name
        _binding?.tvShortDesc?.text = productItem?.created_at
        _binding?.tvPrice?.text = productItem?.price
        _binding?.tvLotPrice?.text = productItem?.price

    }

    override fun subscribeObservers() {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}