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

package com.chandruscm.falselink.ui.website

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.databinding.BindingAdapter
import com.chandruscm.falselink.R
import com.chandruscm.falselink.data.Website
import com.chandruscm.falselink.data.Website.Status.SAFE
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

object WebsiteBindingAdapters {

    /**
     * Set url text color based on the website status.
     */
    @BindingAdapter("verificationStatus")
    @JvmStatic
    fun bindVerificationStatus(view: View, status: Website.Status) {
        with(view) {
            if (status == SAFE) {
                findViewById<MaterialCardView>(R.id.list_item_card).apply {
                    setRippleColorResource(R.color.colorBlueRipple)
                }
                findViewById<TextView>(R.id.website_url).apply {
                    setTextColor(context.getColor(R.color.colorBlue))
                }
                findViewById<MaterialButton>(R.id.move_button).apply {
                    setText(R.string.move_blocked)
                }
            } else {
                findViewById<MaterialCardView>(R.id.list_item_card).apply {
                    setRippleColorResource(R.color.colorRedRipple)
                }
                findViewById<TextView>(R.id.website_url).apply {
                    setTextColor(context.getColor(R.color.colorRed))
                }
                findViewById<MaterialButton>(R.id.move_button).apply {
                    setText(R.string.move_safe)
                }
            }
        }
    }

    @BindingAdapter("goneUnless")
    @JvmStatic
    fun goneUnless(view: Group, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}