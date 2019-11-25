package com.chandruscm.falselink.ui.verify

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.chandruscm.falselink.R
import com.chandruscm.falselink.data.Result
import kotlinx.android.synthetic.main.dialog_verify.view.*

object VerifyBindingAdapters {

    @BindingAdapter("verifyViewModel")
    @JvmStatic
    fun bindVerificationResult(view: View, verificationResult: MutableLiveData<Result<Unit>>) {
        val result = verificationResult.value
        when (result) {
            is Result.Dangerous -> {
                view.dialog_icon.setAnimation(R.raw.alert_circle)
                view.dialog_icon.resumeAnimation()
                view.dialog_verify_detail.isVisible = true
                view.dialog_title.text = view.resources.getString(
                    R.string.caution
                )
                view.issue_text_view.text = view.resources.getString(
                    R.string.issue_holder,
                    result.reason
                )
            }
        }
    }
}