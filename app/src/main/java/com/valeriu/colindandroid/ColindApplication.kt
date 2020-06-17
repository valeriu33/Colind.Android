package com.valeriu.colindandroid

import android.app.Application
import com.valeriu.colindandroid.data.source.ColindRepository

class ColindApplication : Application() {
    val taskRepository: ColindRepository
        get() = ServiceLocator.provideColindRepository(this)
}