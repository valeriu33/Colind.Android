package com.valeriu.colindandroid.utils

import androidx.fragment.app.Fragment
import com.valeriu.colindandroid.ColindApplication
import com.valeriu.colindandroid.ViewModelFactory


fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as ColindApplication).taskRepository
    return ViewModelFactory(repository, this)
}
