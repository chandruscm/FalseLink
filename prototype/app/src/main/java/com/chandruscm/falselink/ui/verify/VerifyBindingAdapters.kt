/*
 * Copyright 2019 Chandramohan Sudar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chandruscm.falselink.ui.verify

import android.graphics.Typeface
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
                with(result) {
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
                        text = uri?.toString() ?:
                                view.resources.getString(R.string.caution_header)
                    }
                    /**
                     * Set issue messages based on the protocol and content type.
                     */
                    val messages = arrayOf(
                        getProtocolMessage(view.resources, website.protocol),
                        getContentTypeMessage(view.resources, website.type)
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
