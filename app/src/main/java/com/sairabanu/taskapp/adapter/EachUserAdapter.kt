package com.sairabanu.taskapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sairabanu.taskapp.R
import com.sairabanu.taskapp.adapter.EachUserAdapter.MyViewHolder
import com.sairabanu.taskapp.databinding.RowEachUserBinding


class EachUserAdapter(
    private val listener: OnClickListener,
    private var imageList: List<String>,
    private var userName:String
) : RecyclerView.Adapter<MyViewHolder>() {
    private var mImageList = imageList
    private var imageItem = ""
    private var name = ""


    inner class MyViewHolder(private val binding: RowEachUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    imageItem = imageList[pos]
                    name = userName
                    listener.onItemClick(imageItem,name)
                }
            }
        }

        fun bind(imageItem: String) {
            binding.apply {
                val url = imageItem
                Glide.with(ivEachUser.context)
                    .applyDefaultRequestOptions(
                        RequestOptions().placeholder(R.drawable.ic_fallback)
                            .error(R.drawable.ic_error)
                            .fallback(R.drawable.ic_fallback)
                    )
                    .load(url)
                    .into(ivEachUser)
            }
        }
    }

    interface OnClickListener {
        fun onItemClick(imageItem: String,name:String)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            RowEachUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //holder.bind(imageList[position])
         if (itemCount % 2 != 0) {
             if (position == 0) {
                 val params: RecyclerView.LayoutParams = RecyclerView.LayoutParams(200, 200)
                 holder.bind(mImageList[position])
                 params.width = ViewGroup.LayoutParams.MATCH_PARENT
                 params.height = 800
                 holder.itemView.setLayoutParams(params)
             } else {
                 holder.bind(mImageList[position])
             }
         } else {
             holder.bind(mImageList[position])
         }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}