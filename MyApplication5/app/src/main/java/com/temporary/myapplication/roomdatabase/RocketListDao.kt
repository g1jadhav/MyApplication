package com.temporary.myapplication.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RocketListDao {

    @Query("SELECT * FROM rocketList")
    suspend fun getAllRocketList(): List<RocketListDataEntity>?

    @Insert
    suspend fun insertRocketList(personData: RocketListDataEntity?)
}