package vbn.clean.nation_flag.data.model.response.flag

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "official_common_name")
data class OfficialCommonName(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val official: String,
    val common: String
)