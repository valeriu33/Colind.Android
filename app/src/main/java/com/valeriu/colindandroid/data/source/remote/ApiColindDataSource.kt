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

//    override fun getColinds(): Result<List<ColindDto>> {
//        var result: Result<List<ColindDto>>
//        colindApi.getColindList().enqueue(object :Callback<List<ColindDto>>{
//            override fun onFailure(call: Call<List<ColindDto>>, t: Throwable) {
//                result =  Result.Error(Exception())
//            }
//
//            override fun onResponse(call: Call<List<ColindDto>>, response: Response<List<ColindDto>>) {
//                response.body()?.run {
//                    if(response.isSuccessful && (this.isSuccess())){
//                        callback.onSuccess(this.data)
//                    }else{
//                        callback.onError(this.msg)
//                    }
//                }
//            }
//        })
//    }
}