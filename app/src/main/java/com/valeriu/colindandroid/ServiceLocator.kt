package com.valeriu.colindandroid

import android.content.Context
import androidx.room.Room
import com.valeriu.colindandroid.data.source.ColindRepository
import com.valeriu.colindandroid.data.source.DefaultColindRepository
import com.valeriu.colindandroid.data.source.local.ColindDatabase
import com.valeriu.colindandroid.data.source.local.LocalColindDataSource
import com.valeriu.colindandroid.data.source.local.SqlLocalColindDataSource
import com.valeriu.colindandroid.data.source.remote.ApiColindDataSource
import com.valeriu.colindandroid.data.source.remote.ColindApi
import com.valeriu.colindandroid.data.source.remote.RemoteColindDataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ServiceLocator {

    private var database: ColindDatabase? = null
    private var colindApiObj: ColindApi? = null
    var colindRepository: ColindRepository? = null

    fun provideColindRepository(context: Context): ColindRepository {
        val newRepo = colindRepository ?: DefaultColindRepository(
            createLocalColindDataSource(context),
            createRemoteColindDataSource()
        )
        colindRepository = newRepo
        return newRepo
    }

    private fun createLocalColindDataSource(context: Context): LocalColindDataSource {
        val database = database ?: createDatabase(context)
        return SqlLocalColindDataSource(database.colindDao())
    }

    private fun createRemoteColindDataSource(): RemoteColindDataSource {
        val colindApi = colindApiObj ?: createColindApiInstance()
        return ApiColindDataSource(colindApi)
    }

    private fun createDatabase(context: Context): ColindDatabase {
        val result = Room.databaseBuilder(
            context.applicationContext,
            ColindDatabase::class.java, "Tasks.db"
        ).build()
        database = result
        return result
    }

    private fun createColindApiInstance(): ColindApi {
        val retrofit =  Retrofit.Builder()
            .baseUrl("http://161.35.208.211/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val colindApi = retrofit.create(ColindApi::class.java)
        colindApiObj = colindApi
        return colindApi
    }
}