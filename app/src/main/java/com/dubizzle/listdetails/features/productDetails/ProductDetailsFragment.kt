package com.swensonhe.currencyconverter.features.convertCurrencyDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels

import androidx.lifecycle.Observer
import com.dubizzle.listdetails.databinding.FragmentProductDetailsBinding
import com.swensonhe.currencyconverter.core.baseUi.BaseFragment
import com.swensonhe.currencyconverter.core.baseUi.BaseViewModel
import com.swensonhe.currencyconverter.features.currencyRatesList.presentation.ProductListFragment
import com.swensonhe.currencyconverter.features.currencyRatesList.presentation.ProductListViewModel
import java.lang.Exception


class ProductDetailsFragment : BaseFragment() {

    private val viewModel: ProductListViewModel by activityViewModels()
    override val baseViewModel: BaseViewModel
        get() = viewModel
    private var initCurrencyRate: Pair<String, Float>? = null
    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initCurrencyRate =
            arguments?.getSerializable(ProductListFragment.CURRENCY_RATE_KEY) as Pair<String, Float>?
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
        viewModel.currencyRate.value = initCurrencyRate
    }

    private fun bindData(currencyRate: Pair<String, Float>?) {
        _binding?.tvRate?.text = (currencyRate?.second!!).toString()
        _binding?.tvCountry?.text = currencyRate?.first!!
    }

    override fun subscribeObservers() {
        viewModel.currencyRate.observe(viewLifecycleOwner, Observer {
            bindData(it)
        })
        _binding?.edtEuroRate?.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                try {
                    val inputEuroNumber = it.toString().toFloat()
                    viewModel.calculateCurrencyRate(inputEuroNumber, initCurrencyRate?.second!!)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                _binding?.tvRate?.text = (initCurrencyRate?.second!!).toString()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}