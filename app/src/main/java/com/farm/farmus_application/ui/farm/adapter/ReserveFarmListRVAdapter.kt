package com.farm.farmus_application.ui.farm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farm.farmus_application.R
import com.farm.farmus_application.databinding.RvGetFarmItemBinding
import com.farm.farmus_application.model.reserve.reserve_list.ReserveListResult
import java.time.LocalDate

class ReserveFarmListRVAdapter() : ListAdapter<ReserveListResult, ReserveFarmListRVAdapter.ReserveFarmListViewHolder>(
    diffUtil
) {

    private var listener: OnClickListener? = null
    private lateinit var binding: RvGetFarmItemBinding

    interface OnClickListener {
        fun onClick(view: View, data: ReserveListResult, pos: Int)
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReserveFarmListViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.rv_get_farm_item, parent, false)
        return ReserveFarmListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReserveFarmListViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ReserveFarmListViewHolder(private val binding: RvGetFarmItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item : ReserveListResult) {
            // TODO: 추후에 databinding으로 수정
            if (item.Picture_url == null) {
                Glide.with(binding.rvItemImg)
                    .load(R.drawable.farm_image_example)
                    .centerCrop()
                    .into(binding.rvItemImg)

            } else {
                Glide.with(binding.rvItemImg)
                    .load(item.Picture_url)
                    .centerCrop()
                    .into(binding.rvItemImg)
            }
            binding.rvItemTitle.text = item.Name
            binding.rvItemStartDay.text =
                LocalDate.parse(item.startAt.substring(0 until 10)).toString()
            binding.rvItemEndDay.text = LocalDate.parse(item.endAt.substring(0 until 10)).toString()

            binding.root.setOnClickListener {
                listener?.onClick(binding.root, item, absoluteAdapterPosition)
            }
        }
    }

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<ReserveListResult>() {
            override fun areItemsTheSame(
                oldItem: ReserveListResult,
                newItem: ReserveListResult
            ): Boolean {
                return oldItem.FarmID == newItem.FarmID
            }

            override fun areContentsTheSame(
                oldItem: ReserveListResult,
                newItem: ReserveListResult
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}

