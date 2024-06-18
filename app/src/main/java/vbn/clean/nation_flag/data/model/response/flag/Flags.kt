package vbn.clean.nation_flag.data.model.response.flag

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