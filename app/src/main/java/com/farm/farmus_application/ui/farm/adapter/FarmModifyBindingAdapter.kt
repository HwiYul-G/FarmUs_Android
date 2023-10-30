package com.farm.farmus_application.ui.farm.adapter

import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import kotlin.math.roundToInt

object FarmModifyBindingAdapter {

    @JvmStatic
    @BindingAdapter("price")
    fun settingPrice(view: EditText, price: Int) {
        view.setText(price.toString())
    }

    @JvmStatic
    @BindingAdapter("squareMeters")
    fun settingSquareMeters(view: EditText, squareMeters: Int) {
        view.setText(squareMeters.toString())
    }

    @JvmStatic
    @BindingAdapter("pyeong")
    fun settingPyeong(view: EditText, squareMeters: Int) {
        val pyeong = (squareMeters / 3.3).roundToInt().toString()

        view.setText(pyeong)
    }

}