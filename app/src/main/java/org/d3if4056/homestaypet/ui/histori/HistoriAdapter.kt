package org.d3if4056.homestaypet.ui.histori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import org.d3if4056.homestaypet.R
import org.d3if4056.homestaypet.databinding.ItemHistoriBinding
import org.d3if4056.homestaypet.db.PetEntity
import org.d3if4056.homestaypet.model.hitungHarga
import org.d3if4056.homestaypet.network.HasilApi
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter:
    ListAdapter<PetEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<PetEntity>() {
                override fun areItemsTheSame(oldData: PetEntity, newData: PetEntity): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(oldData: PetEntity, newData: PetEntity): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) : ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val dataFormatter = SimpleDateFormat(
            "dd MMMM yyyy",
        Locale("id", "ID")
        )

        fun bind(item: PetEntity) = with(binding) {

            val hasilData = item.hitungHarga()

            Glide.with(imageView.context)
                .load(HasilApi.getGambarUrl(item.imageId))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(imageView)

            tanggalTextView.text = dataFormatter.format(Date(item.tanggal))

            dataNamaTextView.text = root.context.getString(R.string.data_pet,
            hasilData.nama)
            dataHariTextView.text = root.context.getString(R.string.data_hari,
            hasilData.hari.toString())
            dataHargaTextView.text = root.context.getString(R.string.data_harga,
            hasilData.harga)
        }
    }
}