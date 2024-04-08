package com.base.basemvvm.data.model.response.flag

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "national_flag_response_item")
data class NationalFlagResponseItem(
    @ColumnInfo(name = "national_flag")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @Embedded(prefix = "flags_")
    val flags: Flags? = null,
    @Embedded(prefix = "name_")
    val name: Name? = null
)