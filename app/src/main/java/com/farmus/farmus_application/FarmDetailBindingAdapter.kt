package com.farmus.farmus_application

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
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
    }

    @JvmStatic
    @BindingAdapter("priceFormat")
    fun applyPriceFormat(view: TextView, price: Int) {
        val decimalFormat = DecimalFormat("#,###")
        view.text = view.context.getString(R.string.price_format, decimalFormat.format(price))
    }

    @JvmStatic
    @BindingAdapter("farmerNickname", "farmerName")
    fun settingFarmerNickname(view: TextView, nickname: String?, name: String?) {
        if (nickname.isNullOrEmpty()) {
            view.text = name
        } else {
            view.text = nickname
        }
    }

    @JvmStatic
    @BindingAdapter("farmerImage")
    fun settingFarmerImage(view: ImageView, image: String?) {
        if (image.isNullOrEmpty()) {
            Glide.with(view)
                .load(R.drawable.farmer_default_image)
                .into(view)
        } else {
            Glide.with(view)
                .load(image)
                .into(view)
        }
    }
}