package com.valeriu.colindandroid.data.source.remote


import com.valeriu.colindandroid.data.Result
import com.valeriu.colindandroid.data.Result.Success
import com.valeriu.colindandroid.data.Result.Error
import com.valeriu.colindandroid.data.models.ColindDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ApiColindDataSource(
    private var colindApi: ColindApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteColindDataSource {

    override suspend fun getColinds(): Result<List<ColindDto>> = withContext(ioDispatcher) {
        try {
            Success(colindApi.getColindList())
        } catch (exception: Exception) {
            Error(exception)
        }
//        Success(colindApi.getColindList()) // TODO Add ERROR HANDLING
    }
}