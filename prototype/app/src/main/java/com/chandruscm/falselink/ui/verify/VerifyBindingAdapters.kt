package com.chandruscm.falselink.ui.verify

import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.airbnb.lottie.LottieAnimationView
import com.chandruscm.falselink.R
import com.chandruscm.falselink.data.Result
import com.chandruscm.falselink.utils.getContentTypeMessage
import com.chandruscm.falselink.utils.getProtocolMessage

object VerifyBindingAdapters {

    @BindingAdapter("verifyViewModel")
    @JvmStatic
    fun bindVerificationResult(view: View, verificationResult: LiveData<Result<Unit>>) {
        val result = verificationResult.value
        when (result) {
            is Result.Dangerous -> {
                view.findViewById<LottieAnimationView>(R.id.dialog_icon).apply {
                    setAnimation(R.raw.alert_circle)
                    resumeAnimation()
                }
                view.findViewById<Group>(R.id.dialog_verify_detail).apply {
                    isVisible = true
                }
                view.findViewById<TextView>(R.id.dialog_title).apply {
                    text = view.resources.getString(R.string.caution)
                }
                val messages = arrayOf(
                    getProtocolMessage(view.resources, result.website.protocol),
                    getContentTypeMessage(view.resources, result.website.type)
                )
                view.findViewById<TextView>(R.id.issue_text_view).apply {
                    text = view.resources.getString(
                        R.string.issue_holder,
                        TextUtils.join("\n• ", messages))
                }
            }
            is Result.Error -> {

            }
        }
    }
}
