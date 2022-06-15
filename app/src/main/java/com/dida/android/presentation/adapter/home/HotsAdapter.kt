package com.dida.android.presentation.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dida.android.R
import com.dida.android.domain.model.nav.home.Hots

class HotsAdapter() :
    RecyclerView.Adapter<HotsHolderPage>(){
    var datas = ArrayList<Hots>()

    private val itemList = ArrayList<Hots>()

    interface OnItemClickEventListener {
        fun onItemClick(a_view: View?, a_position: Int)
    }

    private var nItemClickListener: OnItemClickEventListener? = null

    fun nextItemClickListener(a_listener: OnItemClickEventListener) {
        nItemClickListener = a_listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotsHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.holder_hots, parent, false)
        return HotsHolderPage(view, context, nItemClickListener!!)
    }

    override fun onBindViewHolder(holder: HotsHolderPage, position: Int) {
        if (holder is HotsHolderPage) {
            val viewHolder: HotsHolderPage = holder as HotsHolderPage
            viewHolder.onBind(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: Hots) {
        itemList.add(item)
    }

    fun getItem(position: Int): Hots {
        return itemList[position]
    }

    fun deleteItem(position: Int) {
        itemList.removeAt(position)
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}