package com.valeriu.colindandroid.data.source

import androidx.lifecycle.LiveData
import com.valeriu.colindandroid.data.Result
import com.valeriu.colindandroid.data.Result.Success
import com.valeriu.colindandroid.data.Result.Error
import com.valeriu.colindandroid.data.mappers.toEntity
import com.valeriu.colindandroid.data.models.ColindEntity
import com.valeriu.colindandroid.data.source.local.LocalColindDataSource
import com.valeriu.colindandroid.data.source.remote.RemoteColindDataSource

class DefaultColindRepository(
    private val localDataSource: LocalColindDataSource,
    private val remoteDataSource: RemoteColindDataSource
): ColindRepository {

    override fun observeColinds(): LiveData<Result<List<ColindEntity>>> {
        return localDataSource.observeColinds()
    }

    override suspend fun getColinds(forceUpdate: Boolean): Result<List<ColindEntity>> {

        if (forceUpdate) {
            try {
                updateColindsFromRemoteDataSource()
            } catch (ex: Exception) {
                return Error(ex)
            }
        }

        return localDataSource.getColinds()
    }

    override suspend fun refreshColinds() {
        updateColindsFromRemoteDataSource()
    }

    private suspend fun updateColindsFromRemoteDataSource(){
        val remoteColinds = remoteDataSource.getColinds()

        if (remoteColinds is Success) {
            localDataSource.deleteAllColinds()
            remoteColinds.data.forEach { colind ->
                localDataSource.saveColind(colind.toEntity())
            }
        } else if (remoteColinds is Error) {
//            throw remoteColinds.exception
            // TODO add SnackBar
        }
    }

    // TODO add functionality for single Colind
    override fun observeColind(colindId: Int): LiveData<Result<ColindEntity>> {
        return localDataSource.observeColind(colindId)
    }

    override suspend fun getColind(colindId: Int): Result<ColindEntity> {
        return localDataSource.getColind(colindId)
    }

    override suspend fun refreshColind(colindId: Int) {
        updateColindFromRemoteDataSource(colindId)
    }

    private suspend fun updateColindFromRemoteDataSource(colindId: Int){
        val remoteColinds = remoteDataSource.getColinds() //TODO add get single colind method on API

        if (remoteColinds is Success) {
            localDataSource.saveColind(remoteColinds.data[colindId].toEntity())
        }
    }
}