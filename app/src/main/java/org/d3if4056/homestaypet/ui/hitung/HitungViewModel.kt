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
import org.d3if4056.homestaypet.network.HasilApi

class HitungViewModel(private val db: PetDao) : ViewModel() {

    private val hasilData = MutableLiveData<HasilData?>()

    private val data = MutableLiveData<List<HasilData>>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                data.postValue(HasilApi.service.getData())
            } catch (e: Exception) {
                Log.d("HitungViewModel", "Failure: ${e.message}")
            }
        }
    }

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