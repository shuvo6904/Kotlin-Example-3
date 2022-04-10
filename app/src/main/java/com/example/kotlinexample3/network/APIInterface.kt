package com.example.kotlinexample3.network

import com.example.kotlinexample3.model.*
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @GET("users")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getUsersList() : Call<UserList>

    @GET("users")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun searchUsers(@Query("name") searchText : String) : Call<UserList>

    @GET("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getUsers(@Path("user_id") user_id : String) : Call<UserResponse>

    @POST("users")
    @Headers("Accept:application/json", "Content-Type:application/json", "Authorization: Bearer e44a88a2f0d97c0287d6d41579ae59c17863fef25209dd9be7020e1897ec0bff")
    fun createUser(@Body params : User) : Call<UserResponse>

    @PATCH("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json", "Authorization: Bearer e44a88a2f0d97c0287d6d41579ae59c17863fef25209dd9be7020e1897ec0bff")
    fun updateUser(@Path("user_id") user_id : String, @Body params : User) : Call<UserResponse>

    @DELETE("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json", "Authorization: Bearer e44a88a2f0d97c0287d6d41579ae59c17863fef25209dd9be7020e1897ec0bff")
    fun deleteUser(@Path("user_id") user_id : String) : Call<UserResponse>
}