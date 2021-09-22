package com.sairabanu.taskapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sairabanu.taskapp.R
import com.sairabanu.taskapp.adapter.UserListAdapter
import com.sairabanu.taskapp.databinding.FragmentUserListBinding
import com.sairabanu.taskapp.model.UserModel
import com.sairabanu.taskapp.viewmodel.MainActivityViewModel


class UserListFragment : Fragment(), UserListAdapter.OnItemClickListener {
    private lateinit var userListAdapter: UserListAdapter
    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_user_list, container, false)
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        val view = binding.root
        initViewModel()
        initRecyclerView()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.includeUserList.tvToolbar.setText(R.string.Users)
        showProgressBar()
        //binding.includeUserList.tv
    }

    private fun initRecyclerView() {
        val recyclerView = binding.rvUserList
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        userListAdapter = UserListAdapter(this)
        recyclerView.adapter = userListAdapter
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        //it - observer<UserListModel>
        viewModel.getUserListObserver().observe(viewLifecycleOwner, {
            if (it != null) {
                binding.rvUserList.visibility = View.VISIBLE
                binding.pbUserList.visibility = View.GONE
                userListAdapter.setUpdatedData(it.data.users)
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }

    private fun showProgressBar() {
        binding.rvUserList.visibility = View.GONE
        binding.pbUserList.visibility = View.VISIBLE
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            UserListFragment()
    }

    override fun onItemClick(data: UserModel) {
        ///////////call next fragment////////////
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container,EachUserFragment.newInstance(data))
            .addToBackStack(null).commit()
    }
}
