package org.d3if4056.homestaypet.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if4056.homestaypet.model.HasilData

class HitungViewModel : ViewModel() {

    private val hasilData = MutableLiveData<HasilData?>()

    fun hitungHarga(nama: String, hari: Int) {
        val harga = hari * 20000

        hasilData.value = HasilData(nama, hari, harga.toFloat())
    }

    fun getHasilData(): LiveData<HasilData?> = hasilData
}