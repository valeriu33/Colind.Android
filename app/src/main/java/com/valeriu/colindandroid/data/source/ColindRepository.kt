package com.valeriu.colindandroid.data.source

import androidx.lifecycle.LiveData
import com.valeriu.colindandroid.data.Result
import com.valeriu.colindandroid.data.models.ColindDto
import com.valeriu.colindandroid.data.models.ColindEntity


interface ColindRepository {

    fun observeColinds(): LiveData<Result<List<ColindEntity>>>

    suspend fun getColinds(forceUpdate: Boolean): Result<List<ColindEntity>>

    suspend fun refreshColinds()

    fun observeColind(colindId: Int): LiveData<Result<ColindEntity>>

    suspend fun getColind(colindId: Int): Result<ColindEntity>

    suspend fun refreshColind(colindId: Int)
}