package com.base.basemvvm.data.model.response.flag

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "native_name")
data class NativeName(
    @ColumnInfo(name = "native_name")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
//    @Embedded(prefix = "names_")
//    val names: Map<String, OfficialCommonName> ?= null
)