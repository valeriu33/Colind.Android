package com.valeriu.colindandroid.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "colinds")
class ColindEntity constructor(
    @PrimaryKey (autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "text") var text: String = "",
    @ColumnInfo(name = "author") var author: String? = ""
//    @ColumnInfo(name = "tags") var tags: ? TODO add Tags functionality
)