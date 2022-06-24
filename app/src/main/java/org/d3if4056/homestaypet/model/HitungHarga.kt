package org.d3if4056.homestaypet.model

import org.d3if4056.homestaypet.db.PetEntity

fun PetEntity.hitungHarga(): HasilData {

        val harga = hari * 20000

    return HasilData(nama, hari, harga.toFloat(), imageId)
}