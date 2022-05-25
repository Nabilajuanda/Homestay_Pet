package org.d3if4056.homestaypet.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4056.homestaypet.db.PetDao

class HistoriViewModel(private val db: PetDao) : ViewModel() {
    val data = db.getLastPet()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}