package com.example.kotlinexample3.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinexample3.adapter.RecyclerViewAdapter
import com.example.kotlinexample3.databinding.ActivityMainBinding
import com.example.kotlinexample3.model.User
import com.example.kotlinexample3.model.UserList
import com.example.kotlinexample3.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerViewAdapter : RecyclerViewAdapter
    private lateinit var viewModel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        initRecyclerView()
        initViewModel()
        searchUser()
        binding.createNewUserButtonId.setOnClickListener {
            startActivity(Intent(this, CreateNewUserActivity::class.java))
        }

    }

    private fun searchUser() {
        binding.searchButtonId.setOnClickListener {

            if (!TextUtils.isEmpty(binding.searchEditTextId.text.toString())){
                viewModel.searchUser(binding.searchEditTextId.text.toString())
            } else{
                viewModel.getUsersList()
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getUserListObservable().observe(this, Observer<UserList> {

            if (it == null){
                Toast.makeText(this, "No result found....",Toast.LENGTH_LONG).show()
            } else{
                recyclerViewAdapter.userList = it.data.toMutableList()
                recyclerViewAdapter.notifyDataSetChanged()
            }

        })

        viewModel.getUsersList()
    }

    private fun initRecyclerView() {

        binding.recyclerId.apply {

            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter = recyclerViewAdapter

        }

    }

    override fun onItemEditClick(user: User) {
        val intent = Intent(this, CreateNewUserActivity::class.java)
        intent.putExtra("user_id", user.id)
        startActivityForResult(intent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 1000){
            viewModel.getUsersList()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}




















