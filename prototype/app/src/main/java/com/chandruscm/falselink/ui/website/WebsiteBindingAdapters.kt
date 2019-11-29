package com.chandruscm.falselink.ui.website

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.chandruscm.falselink.R
import com.chandruscm.falselink.data.Website
import com.chandruscm.falselink.data.Website.Status.SAFE

object WebsiteBindingAdapters {

    @BindingAdapter("verificationStatus")
    @JvmStatic
    fun bindVerificationStatus(textView: TextView, status: Website.Status) {
        with(textView) {
            if (status == SAFE) {
                setTextColor(context.getColor(R.color.colorBlue))
            } else {
                setTextColor(context.getColor(R.color.colorRed))
            }
        }
    }
}