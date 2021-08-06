package com.swensonhe.currencyconverter.features.currencyRatesList.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dubizzle.listdetails.R
import com.dubizzle.listdetails.databinding.FragmentCurrencyRatesListBinding
import com.swensonhe.currencyconverter.core.baseUi.BaseFragment
import com.swensonhe.currencyconverter.core.baseUi.BaseViewModel
import com.swensonhe.currencyconverter.domain.models.CurrencyRatesResponse
import com.swensonhe.currencyconverter.features.currencyRatesList.CurrentRatesViewState
import com.swensonhe.currencyconverter.features.currencyRatesList.presentation.adapters.CurrencyRatesAdapter


class CurrencyRatesListFragment : BaseFragment() {
    private val viewModel: CurrencyRatesViewModel by activityViewModels()
    override val baseViewModel: BaseViewModel
        get() = viewModel
    private var _binding: FragmentCurrencyRatesListBinding? = null
    private val binding get() = _binding!!
    private lateinit var currencyRatesAdapter: CurrencyRatesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCurrencyRatesListBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel.getCurrencyRates()
        return view
    }


    override fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            setUiState(it)
        })

    }

    private fun setUiState(viewState: CurrentRatesViewState) {
        when (viewState) {
            is CurrentRatesViewState.FailureState -> {
                hideLoader()
                showFailureNetworkDialog()
            }
            is CurrentRatesViewState.LoadingState -> {
                showLoader()
            }
            is CurrentRatesViewState.SuccessState -> {
                hideLoader()
                submitRatesListToAdapter(viewState.currencyRatesList)
            }
        }
    }

    private fun submitRatesListToAdapter(currencyRatesList: CurrencyRatesResponse) {
        currencyRatesAdapter = CurrencyRatesAdapter(currencyRatesList.rates!!) {
            val bundle= Bundle().apply {
                putSerializable(CURRENCY_RATE_KEY,it)
            }
            findNavController().navigate(R.id.action_currencyRatesListFragment_to_convertCurrencyFragment,bundle)
        }
        _binding?.rvCurrencyRates?.layoutManager = LinearLayoutManager(requireContext())
        _binding?.rvCurrencyRates?.adapter = currencyRatesAdapter

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        const val CURRENCY_RATE_KEY = "currencyRateKey"
        @JvmStatic
        fun newInstance() =
            CurrencyRatesListFragment().apply {
                arguments = Bundle().apply {
                }
            }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}