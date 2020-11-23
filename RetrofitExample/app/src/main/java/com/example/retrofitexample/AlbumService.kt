package com.example.retrofitexample

import retrofit2.Response
import retrofit2.http.*

interface AlbumService {

    @GET("/albums")
    suspend fun getAlbums(): Response<Albums>

    @GET("/albums")
    suspend fun getAlbumsSorted(@Query("userId") userId:Int): Response<Albums>

    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id")albumId:Int): Response<AlbumsItem>

    @POST("/albums")
    suspend fun uploadAlbum(@Body albums: AlbumsItem): Response<AlbumsItem>
}