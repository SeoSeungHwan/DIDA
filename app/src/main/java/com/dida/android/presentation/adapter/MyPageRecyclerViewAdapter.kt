package com.dida.android.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dida.android.databinding.HolderMypageNftBinding
import com.dida.android.domain.model.nav.mypage.MyPageNFTHolderModel

class MyPageRecyclerViewAdapter(private val modelList: List<MyPageNFTHolderModel>): RecyclerView.Adapter<MyPageRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewDataBinding = HolderMypageNftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val holderModel = modelList[position]
        holder.bind(holderModel)
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    inner class ViewHolder(val viewDataBinding: HolderMypageNftBinding): RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(holderModel: MyPageNFTHolderModel) {
            viewDataBinding.holderModel = holderModel

        }
    }
}