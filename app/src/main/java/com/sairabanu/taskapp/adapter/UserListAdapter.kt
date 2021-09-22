package com.sairabanu.taskapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sairabanu.taskapp.R
import com.sairabanu.taskapp.databinding.RowUserListBinding
import com.sairabanu.taskapp.model.UserModel

class UserListAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {
    private var items = ArrayList<UserModel>()

    fun setUpdatedData(items: ArrayList<UserModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class MyViewHolder(private val binding: RowUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val item = items[pos]//get item at position
                        listener.onItemClick(item)
                }
            }
        }

        fun bind(data: UserModel) {
            binding.apply {
                tvUserList.text = data.name
                val url = data.image
                Glide.with(ivUserList.context)
                    .applyDefaultRequestOptions(
                        RequestOptions().placeholder(R.drawable.ic_fallback)
                            .error(R.drawable.ic_error)
                            .fallback(R.drawable.ic_fallback)
                    )
                    .load(url)
                    .circleCrop()
                    .into(ivUserList)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(data: UserModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size

    }
}