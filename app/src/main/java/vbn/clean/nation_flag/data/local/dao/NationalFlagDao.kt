package vbn.clean.nation_flag.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import vbn.clean.nation_flag.data.model.response.flag.NationalFlagResponseItem

@Dao
interface NationalFlagDao {
    @Query("SELECT * FROM national_flag_response_item ORDER BY national_flag ASC")
    fun getListNationalFlagsFromLocal(): List<NationalFlagResponseItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: NationalFlagResponseItem): Long

    @Query("DELETE FROM national_flag_response_item")
    suspend fun deleteAll()
}