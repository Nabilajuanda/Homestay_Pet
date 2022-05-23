package org.d3if4056.homestaypet.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if4056.homestaypet.R
import org.d3if4056.homestaypet.databinding.FragmentHitungBinding
import org.d3if4056.homestaypet.model.HasilData

class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        ViewModelProvider(requireActivity())[HitungViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.kirimButton.setOnClickListener { dataHewan() }
        viewModel.getHasilData().observe(requireActivity(), { showResult(it) })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_hitungFragment_to_aboutFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun dataHewan() {
        // Input nama hewan
        val namaHewan = binding.namaEditText.text.toString()
        if (TextUtils.isEmpty(namaHewan)) {
            Toast.makeText(context, R.string.nama_invalid, Toast.LENGTH_LONG).show()
            return
        }

        // Input berapa lama menginap
        val jumlahHari = binding.berapaHariNumber.text.toString()
        if (TextUtils.isEmpty(jumlahHari)) {
            Toast.makeText(context, R.string.hari_invalid, Toast.LENGTH_LONG).show()
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