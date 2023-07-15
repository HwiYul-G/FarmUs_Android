package com.example.farmus_application.ui.home.Adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class EmptyDataObserve(rv: RecyclerView?, ev: View?) :
    RecyclerView.AdapterDataObserver() {

    private var emptyView: View? = null
    private var recyclerView: RecyclerView? = null

    init {
        this.emptyView = ev
        this.recyclerView = rv
        checkIfEmpty()
    }

    private fun checkIfEmpty() {
        Log.d("EmptyDataObserve", "checkIfEmpty called")
        if (emptyView != null && recyclerView!!.adapter != null) { // 두 값이 있을 때
            val emptyViewVisible = recyclerView!!.adapter!!.itemCount == 0 // 데이터 0이면 true
            Log.d("EmptyDataObserve", "emptyViewVisible: $emptyViewVisible")
            emptyView!!.visibility = if (emptyViewVisible) View.VISIBLE else View.GONE
            recyclerView!!.visibility = if (emptyViewVisible) View.GONE else View.VISIBLE
        }
    }

    override fun onChanged(){
        super.onChanged()
        Log.d("EmptyDataObserve", "onChanged")
        checkIfEmpty()
    }

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int){
        super.onItemRangeInserted(positionStart, itemCount)
        Log.d("EmptyDataObserve", "onItemRangeInserted: $positionStart, $itemCount")
        checkIfEmpty()
    }


}