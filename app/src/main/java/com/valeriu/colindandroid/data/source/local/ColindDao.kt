package com.valeriu.colindandroid.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.valeriu.colindandroid.data.models.ColindEntity

@Dao
interface ColindDao {

    @Query("SELECT * FROM colinds")
    fun observeColinds(): LiveData<List<ColindEntity>>

    @Query("SELECT * FROM colinds WHERE id = :colindId")
    fun observeColindById(colindId: Int): LiveData<ColindEntity>

    @Query("SELECT * FROM colinds")
    suspend fun getColinds(): List<ColindEntity>

    @Query("SELECT * FROM colinds WHERE id = :colindId")
    suspend fun getColindById(colindId: Int): ColindEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertColind(colind: ColindEntity)

    @Query("DELETE FROM colinds")
    suspend fun deleteColinds()
}