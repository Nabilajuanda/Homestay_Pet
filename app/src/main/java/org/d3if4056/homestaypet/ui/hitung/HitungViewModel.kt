package org.d3if4056.homestaypet.ui.hitung

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4056.homestaypet.db.PetDao
import org.d3if4056.homestaypet.db.PetEntity
import org.d3if4056.homestaypet.model.HasilData
import org.d3if4056.homestaypet.model.hitungHarga
import org.d3if4056.homestaypet.network.ApiStatus
import org.d3if4056.homestaypet.network.HasilApi

class HitungViewModel(private val db: PetDao) : ViewModel() {

    private val hasilData = MutableLiveData<HasilData?>()

    fun hitungHarga(nama: String, hari: Int, imageId: String) {

        val dataPet = PetEntity(
            nama = nama,
            hari = hari,
            imageId = imageId
        )
        hasilData.value = dataPet.hitungHarga()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataPet)
            }
        }
    }

    fun getHasilData(): LiveData<HasilData?> = hasilData
}