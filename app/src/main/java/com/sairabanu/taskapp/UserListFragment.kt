package com.sairabanu.taskapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sairabanu.taskapp.adapter.UserListAdapter
import com.sairabanu.taskapp.databinding.FragmentUserListBinding
import com.sairabanu.taskapp.viewmodel.MainActivityViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [UserListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserListFragment : Fragment() {
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
        initViewModel(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.includeUserList.tvToolbar.setText(R.string.Users)
    }

    private fun initViewModel(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_user_list)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        userListAdapter = UserListAdapter()
        recyclerView.adapter = userListAdapter

    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        //it - observer<UserListModel>
        viewModel.getUserListObserver().observe(viewLifecycleOwner, {
            if (it != null) {
                userListAdapter.setUpdatedData(it.data.users)
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            UserListFragment()
    }
}
