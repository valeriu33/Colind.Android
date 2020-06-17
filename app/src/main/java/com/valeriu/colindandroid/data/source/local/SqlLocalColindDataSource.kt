package com.valeriu.colindandroid.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.valeriu.colindandroid.data.Result.Error
import com.valeriu.colindandroid.data.Result.Success
import com.valeriu.colindandroid.data.Result
import com.valeriu.colindandroid.data.models.ColindEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class SqlLocalColindDataSource(
    private val colindDao: ColindDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): LocalColindDataSource {

    override fun observeColinds(): LiveData<Result<List<ColindEntity>>> {
        return colindDao.observeColinds().map {
            Success(it)
        }
    }

    override suspend fun getColinds(): Result<List<ColindEntity>> = withContext(ioDispatcher) {
        try{
            Success(colindDao.getColinds())
        } catch (e: Exception) {
            Error(e)
        }
    }

    override fun observeColind(colindId: Int): LiveData<Result<ColindEntity>> {
        return colindDao.observeColindById(colindId).map {
            Success(it)
        }
    }

    override suspend fun getColind(colindId: Int): Result<ColindEntity> = withContext(ioDispatcher) {
        try{
            Success(colindDao.getColindById(colindId))
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun deleteAllColinds() = withContext(ioDispatcher) {
        colindDao.deleteColinds()
    }

    override suspend fun saveColind(colind: ColindEntity) = withContext(ioDispatcher) {
        colindDao.insertColind(colind)
    }
}
