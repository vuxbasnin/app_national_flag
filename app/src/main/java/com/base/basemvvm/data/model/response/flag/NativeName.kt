package com.base.basemvvm.data.model.response.flag

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "native_name")
@TypeConverters(MyConverter::class)
data class NativeName(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "names_json")
    val namesJson: String?
) {
    constructor(id: Int) : this(id, null)
}

class MyConverter {
    @TypeConverter
    fun jsonToMap(value: String): Map<String, OfficialCommonName>? {
        val mapType = object : TypeToken<Map<String, OfficialCommonName>?>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun mapToJson(map: Map<String, OfficialCommonName>?): String {
        val gson = Gson()
        return gson.toJson(map)
    }
}