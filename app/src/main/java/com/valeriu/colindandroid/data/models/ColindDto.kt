package com.valeriu.colindandroid.data.models
import androidx.lifecycle.MutableLiveData

data class ColindDto(
    var id: Int,
    var title: String,
    var text: String,
    var author: String? = null,
    var tags: ArrayList<String>? = null)