package vbn.clean.nation_flag.data.model.response.flag

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "name")
data class Name(
    @ColumnInfo(name = "name")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val common: String ?= null,
    val official: String ?= null,
    @Embedded(prefix = "nativeName_")
    val nativeName: NativeName ?= null
)