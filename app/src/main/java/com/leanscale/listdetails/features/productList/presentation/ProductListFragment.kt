package com.leanscale.listdetails.features.productList.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.leanscale.listdetails.R
import com.leanscale.listdetails.core.baseUi.BaseFragment
import com.leanscale.listdetails.core.baseUi.BaseViewModel
import com.leanscale.listdetails.databinding.FragmentProductListBinding
import com.leanscale.listdetails.domain.models.ProductListResponse
import com.leanscale.listdetails.features.productList.ProductListViewState
import com.leanscale.listdetails.features.productList.presentation.adapters.ProductListAdapter


class ProductListFragment : BaseFragment() {
    private val productListViewModel: ProductListViewModel by activityViewModels()
    override val baseViewModel: BaseViewModel
        get() = productListViewModel
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!
    private lateinit var productListAdapter: ProductListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        val view = binding.root
        productListViewModel.getProductList()
        return view
    }


    override fun subscribeObservers() {
        productListViewModel.viewState.observe(viewLifecycleOwner, Observer {
            setUiState(it)
        })

    }

    private fun setUiState(viewState: ProductListViewState) {
        when (viewState) {
            is ProductListViewState.FailureState -> {
                hideLoader()
                showFailureNetworkDialog()
            }
            is ProductListViewState.LoadingState -> {
                showLoader()
            }
            is ProductListViewState.SuccessState -> {
                hideLoader()
                submitProductListToAdapter(viewState.productList)
            }
            ProductListViewState.EmptyState -> showEmptyState()
        }
    }

    private fun showEmptyState() {
        Toast.makeText(requireContext(), R.string.no_available_products, Toast.LENGTH_LONG).show()
    }

    private fun submitProductListToAdapter(productList: ProductListResponse) {
        productListAdapter = ProductListAdapter(productList.results!!) {
            val bundle = Bundle().apply {
                putSerializable(PRODUCT_DETAILS_KEY, it)
            }
            findNavController().navigate(
                R.id.action_productListFragment_to_productDetailsFragment,
                bundle
            )
        }
        _binding?.rvProductList?.layoutManager = LinearLayoutManager(requireContext())
        _binding?.rvProductList?.adapter = productListAdapter

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClicks()
    }

    private fun setUpClicks() {
        _binding?.ivBack?.setOnClickListener {
            requireActivity().finish()
        }
    }

    companion object {
        const val PRODUCT_DETAILS_KEY = "productDetailsKey"

        @JvmStatic
        fun newInstance() =
            ProductListFragment().apply {
                arguments = Bundle().apply {
                }
            }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}