package org.d3if4056.homestaypet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import org.d3if4056.homestaypet.databinding.ActivityMainBinding
import org.d3if4056.homestaypet.model.HasilData

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.kirimButton.setOnClickListener { dataHewan() }
        viewModel.getHasilData().observe(this, { showResult(it) })
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

        // GetInput nama hewan peliharaan
        val nama: String = namaHewan
        val inputNama = getInputNama(nama)
        binding.dataNamaTextView.text = getString(R.string.dataNama, inputNama)

        // getInput berapa lama menginap
        val hari: String = jumlahHari
        val lamaMenginap = getLamaMenginap(hari)
        binding.dataHariTextView.text = getString(R.string.dataHari, lamaMenginap)

        viewModel.hitungHarga(nama, hari.toInt())
    }

    private fun getInputNama(inputNama: String): String {
        println(inputNama)
        return inputNama
    }

    private fun getLamaMenginap(jumlahHari: String): String {
        println(jumlahHari)
        return jumlahHari
    }

    private fun showResult(result: HasilData?) {
        if (result == null) return

        binding.dataNamaTextView.text = getString(R.string.dataNama, result.nama)
        binding.dataHariTextView.text = getString(R.string.dataHari, result.hari.toString())
        binding.dataHargaTextView.text = getString(R.string.dataHarga, result.harga.toString())
    }
}