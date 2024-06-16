package com.example.retrofitcompose.Network

import com.example.retrofitcompose.Data.Book
import com.example.retrofitcompose.Network.Model.Books
import retrofit2.http.GET
import retrofit2.http.Query


//4.

interface BookService {

    @GET("volumes")
    suspend fun bookSearch(
        @Query("q") searchQuery: String,
        @Query("maxResults") maxResults: Int
    ): Books

}