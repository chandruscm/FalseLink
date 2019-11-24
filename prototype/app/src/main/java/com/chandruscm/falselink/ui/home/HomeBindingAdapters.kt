package com.chandruscm.falselink.ui.home

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.chandruscm.falselink.R
import com.chandruscm.falselink.data.Website
import com.chandruscm.falselink.data.Website.VerificationStatus.*

object HomeBindingAdapters {

    @BindingAdapter("verificationStatus")
    @JvmStatic
    fun bindVerificationStatus(textView: TextView, status: Website.VerificationStatus) {
        with(textView) {
            if (status == WHITE_LISTED) {
                setTextColor(context.getColor(R.color.colorBlue))
            } else {
                setTextColor(context.getColor(R.color.colorRed))
            }
        }
    }

}
