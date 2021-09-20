package com.sairabanu.taskapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sairabanu.taskapp.R
import com.sairabanu.taskapp.adapter.EachUserAdapter
import com.sairabanu.taskapp.databinding.FragmentEachUserBinding
import com.sairabanu.taskapp.model.UserModel


class EachUserFragment : Fragment(),EachUserAdapter.OnClickListener {
    private var imgUrl = ""
    private var userName = ""
    private var imageList: List<String> = ArrayList()

    private lateinit var eachUserAdapter: EachUserAdapter
    private var _binding: FragmentEachUserBinding? = null
    private val binding get() = _binding!!
    //private var imagesList=List<String>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEachUserBinding.inflate(inflater, container, false)
        val view = binding.root
        setEachUsersAdapter(view)
        return view
    }

    //Load image and user name into toolbar
    private fun setToolbarData() {
        binding.includeEachUser.ivToolbar.visibility = View.VISIBLE
        binding.includeEachUser.tvToolbar.text = userName
        binding.includeEachUser.tvToolbar.textSize = 18F
        Glide.with(binding.includeEachUser.ivToolbar.context)
            .applyDefaultRequestOptions(
                RequestOptions().placeholder(R.drawable.ic_fallback)
                    .error(R.drawable.ic_error)
                    .fallback(R.drawable.ic_fallback)
            )
            .load(imgUrl)
            .circleCrop()
            .into(binding.includeEachUser.ivToolbar)
    }

    private fun setEachUsersAdapter(view: View) {
        val recyclerView = binding.rvEachUser
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
       /* val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)*/

        eachUserAdapter = EachUserAdapter(this, imageList)
        recyclerView.adapter = eachUserAdapter

        //get count of items
        var count= eachUserAdapter.itemCount
        //Log.d("count",count.toString())
        eachUserAdapter.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance(data: UserModel) =
            EachUserFragment().apply {
                arguments = Bundle().apply {
                    imgUrl = data.image
                    userName = data.name
                    imageList = data.items
                    Log.d("images", imageList.toString())
                }
            }
    }

    override fun onItemClick(imageItem: String) {
        Log.d("info",imageItem)
    }


}