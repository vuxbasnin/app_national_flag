package com.base.basemvvm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.base.basemvvm.data.model.response.flag.NationalFlagResponseItem

@Dao
interface NationalFlagDao {
    @Query("SELECT * FROM national_flag_response_item ORDER BY national_flag ASC")
    fun getListNationalFlagsFromLocal(): List<NationalFlagResponseItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: NationalFlagResponseItem): Long

    @Query("DELETE FROM national_flag_response_item")
    suspend fun deleteAll()
}