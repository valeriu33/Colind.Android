package com.valeriu.colindandroid.data.source.remote

import androidx.lifecycle.LiveData
import com.valeriu.colindandroid.data.Result
import com.valeriu.colindandroid.data.models.ColindDto
import com.valeriu.colindandroid.data.models.ColindEntity

interface RemoteColindDataSource {

    suspend fun getColinds(): Result<List<ColindDto>>
}