package com.dubizzle.listdetails.core.baseUi

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dubizzle.listdetails.R

abstract class BaseFragment : Fragment() {
    abstract val baseViewModel: BaseViewModel
    abstract fun subscribeObservers()
    lateinit var progressDialg: ProgressDialog
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
    }

    fun showFailureNetworkDialog() {
        val dialogBuilder = AlertDialog.Builder(requireActivity())

        // set message of alert dialog
        dialogBuilder.setMessage(R.string.network_down)
            .setCancelable(false)
            .setPositiveButton(R.string.ok, DialogInterface.OnClickListener { dialog, id ->
                requireActivity().finish()

            })
        val alert = dialogBuilder.create()
        alert.setTitle(R.string.warning)
        alert.show()
    }

    @SuppressWarnings("deprecation")
    fun showLoader() {
        if (!this::progressDialg.isInitialized) {
            progressDialg = ProgressDialog(requireActivity())
            progressDialg.setTitle(R.string.loading)
            progressDialg.setCancelable(false)

        }
        if (!progressDialg.isShowing)
            progressDialg.show()

    }

    fun hideLoader() {
        if (this::progressDialg.isInitialized && progressDialg.isShowing)
            progressDialg.dismiss()
    }
}