package com.swensonhe.currencyconverter.features.currencyRatesList.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dubizzle.listdetails.R
import com.dubizzle.listdetails.databinding.FragmentProductListBinding
import com.swensonhe.currencyconverter.core.baseUi.BaseFragment
import com.swensonhe.currencyconverter.core.baseUi.BaseViewModel
import com.swensonhe.currencyconverter.domain.models.ProductListResponse
import com.swensonhe.currencyconverter.features.currencyRatesList.ProductListViewState
import com.swensonhe.currencyconverter.features.currencyRatesList.presentation.adapters.ProductListAdapter


class ProductListFragment : BaseFragment() {
    private val viewModel: ProductListViewModel by activityViewModels()
    override val baseViewModel: BaseViewModel
        get() = viewModel
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
        viewModel.getCurrencyRates()
        return view
    }


    override fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
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
                submitRatesListToAdapter(viewState.productList)
            }
        }
    }

    private fun submitRatesListToAdapter(productList: ProductListResponse) {
        productListAdapter = ProductListAdapter(productList.rates!!) {
            val bundle= Bundle().apply {
                putSerializable(CURRENCY_RATE_KEY,it)
            }
            findNavController().navigate(R.id.action_productListFragment_to_productDetailsFragment,bundle)
        }
        _binding?.rvCurrencyRates?.layoutManager = LinearLayoutManager(requireContext())
        _binding?.rvCurrencyRates?.adapter = productListAdapter

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        const val CURRENCY_RATE_KEY = "currencyRateKey"
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