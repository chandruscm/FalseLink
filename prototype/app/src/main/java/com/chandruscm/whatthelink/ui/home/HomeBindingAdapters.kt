package com.chandruscm.whatthelink.ui.home

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.chandruscm.whatthelink.R
import com.chandruscm.whatthelink.data.Website
import com.chandruscm.whatthelink.data.Website.VerificationStatus.*

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
