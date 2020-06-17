package com.valeriu.colindandroid.data.source.local

import androidx.lifecycle.LiveData
import com.valeriu.colindandroid.data.Result
import com.valeriu.colindandroid.data.models.ColindEntity

interface LocalColindDataSource {

    fun observeColinds(): LiveData<Result<List<ColindEntity>>>

    suspend fun getColinds(): Result<List<ColindEntity>>

    fun observeColind(colindId: Int): LiveData<Result<ColindEntity>> // observe changed tags

    suspend fun getColind(colindId: Int): Result<ColindEntity>

    suspend fun deleteAllColinds()

    suspend fun saveColind(colind: ColindEntity)
}