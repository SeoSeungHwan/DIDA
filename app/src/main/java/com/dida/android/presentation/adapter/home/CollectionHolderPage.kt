package com.dida.android.presentation.adapter.home

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.dida.android.R
import com.dida.android.domain.model.nav.home.Collection


class CollectionHolderPage internal constructor(
    itemView: View,
    private val context: Context,
    private val n_itemClickListener: CollectionAdapter.OnItemClickEventListener
) : RecyclerView.ViewHolder(itemView) {

    private val user_img: ImageView
    private val user_name: TextView
    private val user_detail: TextView
    private val follow_btn: TextView

    var data: Collection? = null
    fun onBind(data: Collection) {
        this.data = data

        user_name.text = data.userName
        user_detail.text = data.userDetail

        if(!data.follow){
            follow_btn.setBackgroundResource(R.drawable.custom_collect_follow)
            follow_btn.setTextColor(ContextCompat.getColor(context,R.color.white))
            follow_btn.text = "팔로우"
        }
        else{
            follow_btn.setBackgroundResource(R.drawable.custom_collect_follow_ok)
            follow_btn.setTextColor(ContextCompat.getColor(context,R.color.black))
            follow_btn.text = "팔로잉"
        }

        // image
        Glide.with(context)
            .load(R.drawable.nft_example_image)
            .transform(CenterCrop(), RoundedCorners(200))
            .into(user_img)
//        Glide.with(context)
//            .load(data.nftImg)
//            .fitCenter()
//            .into(hots_img)
        // image sample

//        hotsContentsMain.setOnClickListener { a_view ->
//            val position = absoluteAdapterPosition
//            if (position != RecyclerView.NO_POSITION) {
//                n_itemClickListener!!.onItemClick(a_view, position)
//            }
//        }
    }

    init {
        user_img = itemView.findViewById(R.id.user_img)
        user_name = itemView.findViewById(R.id.user_name)
        user_detail = itemView.findViewById(R.id.user_detail)
        follow_btn = itemView.findViewById(R.id.follow_btn)
    }
}
