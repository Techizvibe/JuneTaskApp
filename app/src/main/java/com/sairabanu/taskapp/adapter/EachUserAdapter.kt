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
    private var imageList: List<String>
) : RecyclerView.Adapter<MyViewHolder>() {
    private var mImageList = imageList
    private var imageItem=""

    inner class MyViewHolder(private val binding: RowEachUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    imageItem = imageList[pos]
                    listener.onItemClick(imageItem)
                }
            }
        }
        fun bind(imageItem: String){
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
        fun onItemClick(imageItem:String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowEachUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}