package org.d3if4056.homestaypet.ui.hitung

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

class HitungViewModel(private val db: PetDao) : ViewModel() {

    private val hasilData = MutableLiveData<HasilData?>()

    fun hitungHarga(nama: String, hari: Int) {

        val dataPet = PetEntity(
            nama = nama,
            hari = hari
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