package com.example.kotlinexample3.ui

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinexample3.R
import com.example.kotlinexample3.databinding.ActivityCreateNewUserBinding
import com.example.kotlinexample3.model.User
import com.example.kotlinexample3.model.UserResponse
import com.example.kotlinexample3.viewmodel.CreateNewUserViewModel

class CreateNewUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateNewUserBinding
    private lateinit var viewModel: CreateNewUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewUserBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val user_id = intent.getStringExtra("user_id")

        initViewModel()
        createUserObservable()

        if (user_id != null){
            loadUserData(user_id)
        }

        binding.createUserButtonId.setOnClickListener {

            create(user_id)
        }

        binding.deleteButtonId.setOnClickListener {
            deleteUser(user_id)
        }


    }

    private fun deleteUser(user_id: String?) {

        viewModel.getDeleteUserObservable().observe(this, Observer<UserResponse?> {
            if (it == null){
                Toast.makeText(this, "Failed to delete user...", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Successfully delete user...", Toast.LENGTH_LONG).show()
                //Toast.makeText(this, it.code.toString(), Toast.LENGTH_LONG).show()

                finish()
            }
        })

        viewModel.deleteUser(user_id)


    }

    private fun loadUserData(user_id : String?) {

        viewModel.getLoadUserObservable().observe(this, Observer<UserResponse?> {
            if (it != null){
                binding.nameEditTextId.setText(it.data?.name)
                binding.emailEditTextId.setText(it.data?.email)
                binding.createUserButtonId.setText("Update")
                binding.deleteButtonId.visibility = View.VISIBLE
            }
        })

        viewModel.getUserData(user_id)



    }

    private fun create(user_id: String?) {
        val user = User( "", binding.nameEditTextId.text.toString(), binding.emailEditTextId.text.toString(), "Active", "Male")

        if (user_id == null){
            viewModel.createNewUser(user)
        }


        else
            viewModel.updateUser(user_id, user)
    }


    private fun initViewModel() {

        viewModel = ViewModelProvider(this).get(CreateNewUserViewModel::class.java)

    }

    private fun createUserObservable() {

        viewModel.getCreateNewUserObservable().observe(this, Observer<UserResponse?> {
            if (it == null){
                Toast.makeText(this, "Failed to create/update new user...", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Successfully created/updated new user...", Toast.LENGTH_LONG).show()
                //Toast.makeText(this, it.code.toString(), Toast.LENGTH_LONG).show()

                finish()
            }
        })
    }

}