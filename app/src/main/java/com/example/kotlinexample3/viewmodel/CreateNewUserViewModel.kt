package com.example.kotlinexample3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinexample3.model.User
import com.example.kotlinexample3.model.UserList
import com.example.kotlinexample3.model.UserResponse
import com.example.kotlinexample3.network.APIInterface
import com.example.kotlinexample3.network.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateNewUserViewModel : ViewModel() {

    private lateinit var createNewUserLiveData : MutableLiveData<UserResponse?>
    private lateinit var loadUserLiveData : MutableLiveData<UserResponse?>
    private lateinit var deleteUserLiveData : MutableLiveData<UserResponse?>

    init {
        createNewUserLiveData = MutableLiveData()
        loadUserLiveData = MutableLiveData()
        deleteUserLiveData = MutableLiveData()
    }

    fun getCreateNewUserObservable() : MutableLiveData<UserResponse?> {
        return createNewUserLiveData
    }


    fun getLoadUserObservable() : MutableLiveData<UserResponse?> {
        return loadUserLiveData
    }

    fun getDeleteUserObservable() : MutableLiveData<UserResponse?> {
        return deleteUserLiveData
    }

    fun createNewUser(user : User){

        val retroInstance = RetroInstance.getRetroInstance().create(APIInterface::class.java)
        val call = retroInstance.createUser(user)
        call.enqueue(object : Callback<UserResponse?> {

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {


                if (response.isSuccessful){
                    createNewUserLiveData.postValue(response.body())
                } else{

                    createNewUserLiveData.postValue(null)

                }


            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                createNewUserLiveData.postValue(null)
            }

        })

    }

    fun updateUser(user_id : String, user : User){

        val retroInstance = RetroInstance.getRetroInstance().create(APIInterface::class.java)
        val call = retroInstance.updateUser(user_id , user)
        call.enqueue(object : Callback<UserResponse?> {

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {


                if (response.isSuccessful){
                    createNewUserLiveData.postValue(response.body())
                } else{

                    createNewUserLiveData.postValue(null)

                }


            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                createNewUserLiveData.postValue(null)
            }

        })

    }

    fun deleteUser(user_id : String?){

        val retroInstance = RetroInstance.getRetroInstance().create(APIInterface::class.java)
        val call = retroInstance.deleteUser(user_id!!)
        call.enqueue(object : Callback<UserResponse?> {

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {


                if (response.isSuccessful){
                    deleteUserLiveData.postValue(response.body())
                } else{

                    deleteUserLiveData.postValue(null)

                }


            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                deleteUserLiveData.postValue(null)
            }

        })

    }



    fun getUserData(user_id : String?){

        val retroInstance = RetroInstance.getRetroInstance().create(APIInterface::class.java)
        val call = retroInstance.getUsers(user_id!!)
        call.enqueue(object : Callback<UserResponse?> {

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {


                if (response.isSuccessful){
                    loadUserLiveData.postValue(response.body())
                } else{

                    loadUserLiveData.postValue(null)

                }


            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                loadUserLiveData.postValue(null)
            }

        })

    }


}