package org.d3if4056.homestaypet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import org.d3if4056.homestaypet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.kirimButton.setOnClickListener { dataHewan() }
    }

    private fun dataHewan() {
        // Input nama hewan
        val namaHewan = binding.namaEditText.text.toString()
        if (TextUtils.isEmpty(namaHewan)) {
            Toast.makeText(this, R.string.nama_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, R.string.spesies_invalid, Toast.LENGTH_LONG).show()
            return
        }

        // Check radio button kucing atau anjing
        val isCat = selectedId == R.id.kucingButton
        val isDog = selectedId == R.id.anjingButton
        val spesies = "kucing"
        val pilihan = getPilihan(spesies, isCat, isDog)
        binding.dataSpesiesTextView.text = getString(R.string.dataSpesies, pilihan)

        // Input nama hewan peliharaan
        val nama: String = namaHewan
        val inputNama = getInputNama(nama)
        binding.dataNamaTextView.text = getString(R.string.dataNama, inputNama)

        // Input berapa lama menginap


    }

    private fun getInputNama(inputNama: String): String {
        println(inputNama)
        return inputNama
    }

    private fun getPilihan(pilihan: String, isCat: Boolean, isDog: Boolean): String {
        val stringRes = if(isCat) {
            when {
                pilihan == "kucing" -> R.string.kucing
                else -> R.string.anjing
            }
        } else if(isDog) {
            when {
                pilihan == "anjing" -> R.string.anjing
                else -> R.string.notPilihan
            }
        } else {
            R.string.anjing
        }
        return getString(stringRes)
    }
}