package com.example.countriesapp.binding_adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.countriesapp.utils.downloadFromUrl
import com.example.countriesapp.utils.placeholderProgressBar

class FlagBinding {

    companion object{
        @BindingAdapter("load_image")
        @JvmStatic
        fun loadImage(imageView : ImageView,flagUrl : String?){
            flagUrl?.let {
                imageView.downloadFromUrl(flagUrl, placeholderProgressBar(imageView.context))
            }

        }
    }

}