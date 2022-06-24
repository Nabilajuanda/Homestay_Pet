package org.d3if4056.homestaypet.ui.histori

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4056.homestaypet.db.PetDao
import org.d3if4056.homestaypet.model.HasilData
import org.d3if4056.homestaypet.network.ApiStatus
import org.d3if4056.homestaypet.network.HasilApi

class HistoriViewModel(private val db: PetDao) : ViewModel() {
    val data = db.getLastPet()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }

    private val dataApi = MutableLiveData<List<HasilData>>()

    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                dataApi.postValue(HasilApi.service.getData())
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("HitungViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getStatus(): LiveData<ApiStatus> = status
}