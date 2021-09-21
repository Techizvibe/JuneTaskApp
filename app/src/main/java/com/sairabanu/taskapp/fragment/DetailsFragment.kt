package com.sairabanu.taskapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sairabanu.taskapp.R
import com.sairabanu.taskapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private var imgItemUrl = ""
    private var userName = ""
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.includeDetails.tvToolbar.setText(R.string.details)
        setData()
    }

    private fun setData() {
        Glide.with(binding.ivDetails.context)
            .applyDefaultRequestOptions(
                RequestOptions().placeholder(R.drawable.ic_fallback)
                    .error(R.drawable.ic_error)
                    .fallback(R.drawable.ic_fallback)
            )
            .load(imgItemUrl)
            .into(binding.ivDetails)
        binding.tvDetails.text = userName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(img:String,name:String) =
            DetailsFragment().apply {
                arguments=Bundle().apply {
                    imgItemUrl = img
                    userName = name
                }
            }
    }
}