package com.example.farmus_application

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.DecimalFormat
import kotlin.math.roundToInt

object FarmDetailBindingAdapter {

    @JvmStatic
    @BindingAdapter("locationBig", "locationMid", "locationSmall")
    fun addressString(view: TextView, locationBig: String?, locationMid: String?, locationSmall: String?) {
        if (locationSmall.isNullOrEmpty()) {
            val addressText = locationBig+locationMid
            view.text = addressText
        } else {
            val addressText = locationBig+locationMid+locationSmall
            view.text = addressText
        }
    }

    @JvmStatic
    @BindingAdapter("squareMeters")
    fun changeSquareMeters(view: TextView, squareMeters: Int) {
        val pyeong = (squareMeters / 3.3).roundToInt().toString()
        view.text = view.context.getString(R.string.change_squareMeter, pyeong, squareMeters)
//        view.text = pyeong+"평 "+"("+squareMeters+"m^2)"
    }

    @JvmStatic
    @BindingAdapter("priceFormat")
    fun applyPriceFormat(view: TextView, price: Int) {
        val decimalFormat = DecimalFormat("#,###")
        view.text = view.context.getString(R.string.price_format, decimalFormat.format(price))
    }
}