package com.phooper.travelhack.ui.global.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.phooper.travelhack.App
import com.phooper.travelhack.R
import kotlinx.android.synthetic.main.item_image_square.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SquareImgAdapter :
    RecyclerView.Adapter<SquareImgAdapter.FavouritePostViewHolder>() {
    var linkList = mutableListOf<String>()
    var onItemClick: ((String) -> Unit)? = null

    init {
        App.daggerComponent.inject(this)
    }

    @Inject
    lateinit var glide: RequestManager

    inner class FavouritePostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(linkList[adapterPosition])
            }
        }

        val image: ImageView = itemView.img
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavouritePostViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_image_square,
                parent,
                false
            )
        )

    override fun getItemCount() = linkList.size

    override fun onBindViewHolder(holder: FavouritePostViewHolder, position: Int) {
        CoroutineScope(Main).launch {
            withContext(CoroutineScope(IO).coroutineContext) {
                glide.load(
                    linkList[position]
                )
            }
                .into(
                    holder.image
                )
        }
    }


fun setData(list: List<String>) {
    linkList.clear()
    linkList.addAll(list)
    notifyDataSetChanged()
}

}