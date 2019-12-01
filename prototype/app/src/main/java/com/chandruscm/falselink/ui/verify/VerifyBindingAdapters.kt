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

    /**
     * Show the detail view if the website is dangerous.
     */
    @BindingAdapter("verifyViewModel")
    @JvmStatic
    fun bindVerificationResult(view: View, verificationResult: LiveData<Result<Unit>>) {
        val result = verificationResult.value
        when (result) {
            is Result.Dangerous -> {
                with(result.website) {
                    /**
                     * Replace loading animation with alert animation
                     */
                    view.findViewById<LottieAnimationView>(R.id.dialog_icon).apply {
                        setAnimation(R.raw.alert_circle)
                        resumeAnimation()
                    }
                    /**
                     * Show the detail view to display issue information.
                     */
                    view.findViewById<Group>(R.id.dialog_verify_detail).apply {
                        isVisible = true
                    }
                    view.findViewById<TextView>(R.id.dialog_title).apply {
                        text = view.resources.getString(R.string.caution)
                    }
                    /**
                     * Set issue messages based on the protocol and content type.
                     */
                    val messages = arrayOf(
                        getProtocolMessage(view.resources, protocol),
                        getContentTypeMessage(view.resources, type)
                    )
                    view.findViewById<TextView>(R.id.issue_text_view).apply {
                        text = view.resources.getString(
                            R.string.issue_holder,
                            TextUtils.join("\n• ", messages)
                        )
                    }
                }
            }
            /**
             * TODO: How to handle verification errors? UX or UserSafety?
             */
            is Result.Error -> {

            }
        }
    }
}
