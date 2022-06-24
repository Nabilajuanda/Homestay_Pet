package org.d3if4056.homestaypet.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if4056.homestaypet.R
import org.d3if4056.homestaypet.databinding.FragmentHitungBinding
import org.d3if4056.homestaypet.db.PetDb
import org.d3if4056.homestaypet.model.HasilData
import org.d3if4056.homestaypet.network.ApiStatus

class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = PetDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.kirimButton.setOnClickListener { dataHewan() }
        binding.bagikanButton.setOnClickListener { shareData() }

        viewModel.getHasilData().observe(requireActivity(), { showResult(it) })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_historiFragment)
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment)
                return true
            }
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

        val imageId: String = namaHewan
        binding.dataNamaTextView.text = getString(R.string.dataNama)

        // Mengambil data dari ViewModel
        viewModel.hitungHarga(
            nama,
            hari.toInt(),
            imageId
        )
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
        binding.bagikanButton.visibility = View.VISIBLE
    }

    private fun shareData() {
        val message = getString(R.string.bagikan_template)

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if(shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }
}