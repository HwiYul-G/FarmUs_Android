package com.example.farmus_application.ui.farm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.farmus_application.databinding.RvGetFarmItemBinding
import com.example.farmus_application.model.reserve.reserve_list.ReserveListResult
import java.time.LocalDate

class ReserveFarmListRVAdapter() : ListAdapter<ReserveListResult, ReserveFarmListRVAdapter.ViewHolder>(diffUtil) {

    private lateinit var binding: RvGetFarmItemBinding

    interface OnClickListener {
        fun onClick(view: View, data: ReserveListResult, pos: Int)
    }

    private var listener: OnClickListener? = null
    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ReserveFarmListRVAdapter.ViewHolder {
        binding = RvGetFarmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.rvItemImg.clipToOutline = true
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }

    inner class ViewHolder( binding: RvGetFarmItemBinding) :RecyclerView.ViewHolder(binding.root) {

        fun bind(item : ReserveListResult) {
            // TODO: 추후에 databinding으로 수정
            Glide.with(binding.rvItemImg)
                .load(item.Picture_url)
                .centerCrop()
                .into(binding.rvItemImg)
            binding.rvItemTitle.text = item.Name
            binding.rvItemStartDay.text = LocalDate.parse(item.startAt.substring(0 until  10)).toString()
            binding.rvItemEndDay.text = LocalDate.parse(item.endAt.substring(0 until  10)).toString()

            binding.root.setOnClickListener {
                listener?.onClick(binding.root, item, absoluteAdapterPosition)
            }
        }
    }
    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<ReserveListResult>(){
            override fun areItemsTheSame(
                oldItem: ReserveListResult,
                newItem: ReserveListResult
            ): Boolean {
                return oldItem.Farmid == newItem.Farmid
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

