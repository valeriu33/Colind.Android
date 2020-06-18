package com.valeriu.colindandroid.colindsList

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.valeriu.colindandroid.R

@BindingAdapter("app:ornament_color")
fun setOrnament(imageView: ImageView, position: Int?) {
    if ((position ?: 0) % 2 == 0){
        imageView.setImageResource(R.drawable.ornament_point)
    } else {
        imageView.setImageResource(R.drawable.ornament_point_red)
    }
}