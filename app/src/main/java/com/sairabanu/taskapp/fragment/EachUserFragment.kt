package com.sairabanu.taskapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sairabanu.taskapp.R
import com.sairabanu.taskapp.adapter.EachUserAdapter
import com.sairabanu.taskapp.databinding.FragmentEachUserBinding
import com.sairabanu.taskapp.model.UserModel


class EachUserFragment : Fragment(), EachUserAdapter.OnClickListener {
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
        setEachUsersAdapter()
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

    private fun setEachUsersAdapter() {
        val recyclerView = binding.rvEachUser
        val manager = GridLayoutManager(activity, 2)
        if (imageList.size % 2 != 0) {
            manager.spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == 0) 2 else 1
                }
            }
            recyclerView.layoutManager = manager
        } else {
            recyclerView.layoutManager = manager
        }

        eachUserAdapter = EachUserAdapter(this, imageList,userName)
        recyclerView.adapter = eachUserAdapter
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

    override fun onItemClick(imageItem: String,name : String) {
        Log.d("info", imageItem)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container,DetailsFragment.newInstance(imageItem,name))
            .addToBackStack(null).commit()
    }


}