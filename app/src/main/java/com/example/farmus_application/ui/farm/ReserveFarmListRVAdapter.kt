package com.example.farmus_application.ui.farm

import android.content.ClipData.Item
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.farmus_application.databinding.ItemEmptyFarmListBinding
import com.example.farmus_application.databinding.RvGetFarmItemBinding
import com.example.farmus_application.model.reserve.reserve_list.ReserveListResult
import java.time.LocalDate

class ReserveFarmListRVAdapter() : ListAdapter<ReserveListResult, RecyclerView.ViewHolder>(diffUtil) {

    private var listener: OnClickListener? = null

    interface OnClickListener {
        fun onClick(view: View, data: ReserveListResult, pos: Int)
    }
    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        return if (viewType == ITEM) {
            ItemViewHolder(RvGetFarmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        } else {
            EmptyViewHolder(ItemEmptyFarmListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM) {
            (holder as ItemViewHolder).bind(currentList[position])
        } else {
            (holder as EmptyViewHolder)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList.isNotEmpty()) ITEM else EMPTY
    }

    override fun getItemCount(): Int {
        return if (currentList.isNotEmpty()) currentList.size else 1
    }
    inner class ItemViewHolder(private val binding: RvGetFarmItemBinding): RecyclerView.ViewHolder(binding.root) {
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

    inner class EmptyViewHolder(binding: ItemEmptyFarmListBinding): RecyclerView.ViewHolder(binding.root) {}


    companion object{

        private const val ITEM = 1
        private const val EMPTY = 0

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

