package com.base.basemvvm.data.model.response.flag

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flags")
data class Flags(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val png: String ?= null,
    val svg: String ?= null,
    val alt: String ?= null
)