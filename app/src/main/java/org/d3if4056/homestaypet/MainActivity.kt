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
        val namaHewan = binding.pilihanEditText.text.toString()
        if (TextUtils.isEmpty(namaHewan)) {
            Toast.makeText(this, R.string.nama_invalid, Toast.LENGTH_LONG).show()
            return
        }

        // Check radio button kucing atau anjing
        val selectedId = binding.radioGroup.checkedRadioButtonId
        val isCat = selectedId == R.id.kucingButton
        val hargaKucing = 20000
        val pilihan = getPilihan(hargaKucing, isCat)

        binding.dataNamaTextView.text = getString(R.string.dataNama, pilihan)
    }

    private fun getPilihan(pilihan: Int, isCat: Boolean): String {
        val stringRes = if(isCat) {
            when {
                pilihan == 20000 -> R.string.kucing
                else -> R.string.notPilihan
            }
        } else {
            when {
                pilihan == 25000 -> R.string.anjing
                else -> R.string.notPilihan
            }
        }
        return getString(stringRes)
    }
}