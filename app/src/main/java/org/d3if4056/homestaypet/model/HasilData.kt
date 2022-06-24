package org.d3if4056.homestaypet.model

import org.d3if4056.homestaypet.R

data class HasilData(
    val nama: String,
    val hari: Int,
    val harga: Float,
    val imageResId: Int = R.drawable.anak_kucing
)
