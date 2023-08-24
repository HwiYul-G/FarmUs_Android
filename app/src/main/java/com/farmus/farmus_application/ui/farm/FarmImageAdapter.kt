package com.farmus.farmus_application.ui.farm

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farmus.farmus_application.R
import com.farmus.farmus_application.databinding.ItemFarmDetailImageBinding
import com.farmus.farmus_application.model.farm.detail.DetailResult
import com.farmus.farmus_application.model.farm.list.Pictures

class FarmImageAdapter: RecyclerView.Adapter<FarmImageAdapter.FarmImageViewHolder>() {


    private lateinit var binding: ItemFarmDetailImageBinding
    private var data = mutableListOf<Pictures>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmImageViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_farm_detail_image, parent, false)
        return FarmImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FarmImageViewHolder, position: Int) {
        if (data.size != 0) {
            holder.bind(data[position])
        } else {
            holder.emptyPicture()
        }
    }

    override fun getItemCount(): Int {
        return if (data.size != 0) data.size else 1
    }

    fun setData(data: DetailResult) {
        data.PictureObject?.let {
            this.data = it.toMutableList()
        }
        notifyDataSetChanged()
    }

    inner class FarmImageViewHolder(private val binding: ItemFarmDetailImageBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Pictures) {
            Log.e("FarmImageViewHolderBind","FarmImageViewHolderBind")
            Glide.with(binding.root)
                .load(item.PictureUrl)
                .into(binding.farmImage)
        }
        fun emptyPicture() {
            Glide.with(binding.root)
                .load(R.drawable.farm_image_example)
                .into(binding.farmImage)
        }
    }
}