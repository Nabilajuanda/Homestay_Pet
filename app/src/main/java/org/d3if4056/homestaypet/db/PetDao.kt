package org.d3if4056.homestaypet.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PetDao {

    @Insert
    fun insert(pet: PetEntity)

    @Query("SELECT * FROM pet ORDER BY id DESC")
    fun getLastPet(): LiveData<List<PetEntity>>

    @Query("DELETE FROM Pet")
    fun clearData()
}