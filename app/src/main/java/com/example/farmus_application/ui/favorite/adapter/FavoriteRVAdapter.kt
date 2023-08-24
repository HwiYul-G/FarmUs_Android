package com.example.farmus_application.ui.home.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.farmus_application.R
import com.example.farmus_application.databinding.RvLocalFarmBinding
import com.example.farmus_application.model.favorite.FavoriteFarm
import com.example.farmus_application.repository.UserPrefsStorage
import com.example.farmus_application.utilities.JWTUtils


class FavoriteRVAdapter : ListAdapter<FavoriteFarm, FavoriteRVAdapter.ViewHolder>(diffUtil) {

    private var listener: OnItemClickListener? = null

    val jwtToken = UserPrefsStorage.accessToken
    val email = JWTUtils.decoded(jwtToken.toString())?.tokenBody?.email

    interface OnItemClickListener {
        fun itemClick(farmId: Int)
        fun likeClick(email : String, farmId : Int)
        fun deleteLikeClick(email : String, farmId : Int)
    }

    fun setOnItemClick(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(private val binding: RvLocalFarmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FavoriteFarm) {
            Glide.with(binding.root.context)
                .load(
                    item.PictureUrl ?: R.drawable.farm_image_example
                ).into(binding.rvFarmImage)
            binding.rvFarmName.text = item.Name

            val squaredFeet = item.SquaredMeters * 0.3025
            val itemSize = squaredFeet.toString() + "평 (" + item.SquaredMeters + "㎡)"
            binding.rvFarmSize.text = itemSize

            binding.rvFarmPrice.text = item.Price.toString()

            binding.bookMark.isSelected = true // 좋아요 창이므로 기본적으로 true
            binding.bookMark.setOnClickListener {
                binding.bookMark.isSelected = !binding.bookMark.isSelected
                if(binding.bookMark.isSelected){
                    listener?.likeClick(email.toString(), item.FarmID)
                }else{
                    listener?.deleteLikeClick(email.toString(), item.FarmID)
                }
            }

            binding.root.setOnClickListener {
                listener?.itemClick(item.FarmID)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteRVAdapter.ViewHolder {

        return ViewHolder(
            RvLocalFarmBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteRVAdapter.ViewHolder, position: Int) {

        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<FavoriteFarm>() {
            override fun areItemsTheSame(
                oldItem: FavoriteFarm,
                newItem: FavoriteFarm
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: FavoriteFarm,
                newItem: FavoriteFarm
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}