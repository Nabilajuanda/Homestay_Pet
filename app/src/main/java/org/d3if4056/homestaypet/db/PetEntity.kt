package org.d3if4056.homestaypet.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pet")
data class PetEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var nama: String,
    var hari: Int,
    var imageId: String
)