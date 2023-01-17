package com.example.farmus_application.ui.home.Adapter

import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.farmus_application.R
import com.example.farmus_application.databinding.RvLocalFarmBinding


class LocalFarmRVAdapter(val items: MutableList<String>): RecyclerView.Adapter<LocalFarmRVAdapter.ViewHolder>() {

    private lateinit var localFarmBinding : RvLocalFarmBinding

    override fun getItemCount(): Int {

        var count = 2

        return count
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalFarmRVAdapter.ViewHolder{

        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_local_farm, parent, false)

        //테두리 둥글게 설정
        localFarmBinding.rvLocalFarmImage.clipToOutline = true

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocalFarmRVAdapter.ViewHolder, position: Int) {
         holder.bindItems(items[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItems(item : String){

        }
    }
}