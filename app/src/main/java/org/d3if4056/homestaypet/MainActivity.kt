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

        // Input berapa lama menginap
        val jumlahHari = binding.berapaHariNumber.text.toString()
        if (TextUtils.isEmpty(jumlahHari)) {
            Toast.makeText(this, R.string.hari_invalid, Toast.LENGTH_LONG).show()
            return
        }

        // Check radio button kosong atau tidak
        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, R.string.spesies_invalid, Toast.LENGTH_LONG).show()
            return
        }
        if (selectedId == -1) {
            Toast.makeText(this, R.string.pilihanMakan_invalid, Toast.LENGTH_LONG).show()
            return
        }

        // Check radio button kucing atau anjing
        val isCat = selectedId == R.id.kucingButton
        val isDog = selectedId == R.id.anjingButton
        val spesies = "kucing"
        val pilihan = getPilihan(spesies, isCat, isDog)
        binding.dataSpesiesTextView.text = getString(R.string.dataSpesies, pilihan)

        // Check radio button pilihan makanan
        val isAlone = selectedId == R.id.bawaSendirButton
        val isTogether = selectedId == R.id.pengurusButton
        val data = "Bawa sendiri"
        val hasilData = getData(data, isAlone, isTogether)
        binding.dataPilihanMakanTextView.text = getString(R.string.dataMakan, hasilData)

        // GetInput nama hewan peliharaan
        val nama: String = namaHewan
        val inputNama = getInputNama(nama)
        binding.dataNamaTextView.text = getString(R.string.dataNama, inputNama)

        // getInput berapa lama menginap
        val hari: String = jumlahHari
        val lamaMenginap = getLamaMenginap(hari)
        binding.dataHariTextView.text = getString(R.string.dataHari, lamaMenginap)
    }

    private fun getInputNama(inputNama: String): String {
        println(inputNama)
        return inputNama
    }

    private fun getLamaMenginap(jumlahHari: String): String {
        println(jumlahHari)
        return jumlahHari
    }

    private fun getData(hasilData: String, isAlone: Boolean, isTogether: Boolean): String {
        val stringRes = if(isAlone) {
            when {
                hasilData == "Bawa sendiri" -> R.string.pilihan_Makan1
                else -> R.string.pilihan_Makan2
            }
        } else if (isTogether) {
            when {
                hasilData == "Disediakan oleh pengurus" -> R.string.pilihan_Makan2
                else -> R.string.pilihan_Makan2
            }
        } else {
            R.string.pilihan_Makan2
        }
        return getString(stringRes)
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