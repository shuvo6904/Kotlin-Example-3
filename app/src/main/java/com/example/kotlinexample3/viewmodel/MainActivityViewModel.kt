package com.example.kotlinexample3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinexample3.model.UserList
import com.example.kotlinexample3.network.APIInterface
import com.example.kotlinexample3.network.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {

    lateinit var recyclerListData : MutableLiveData<UserList>

    init {
        recyclerListData = MutableLiveData()
    }

    fun getUserListObservable() : MutableLiveData<UserList> {
        return recyclerListData
    }

    fun getUsersList(){
        val retroInstance = RetroInstance.getRetroInstance().create(APIInterface::class.java)
        val call = retroInstance.getUsersList()
        call.enqueue(object : Callback<UserList>{

            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {


                if (response.isSuccessful){
                    recyclerListData.postValue(response.body())
                } else{

                    recyclerListData.postValue(null)

                }


            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                recyclerListData.postValue(null)
            }

        })
    }

    fun searchUser(searchText : String){
        val retroInstance = RetroInstance.getRetroInstance().create(APIInterface::class.java)
        val call = retroInstance.searchUsers(searchText)
        call.enqueue(object : Callback<UserList>{

            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {


                if (response.isSuccessful){
                    recyclerListData.postValue(response.body())
                } else{

                    recyclerListData.postValue(null)

                }


            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                recyclerListData.postValue(null)
            }

        })
    }

}