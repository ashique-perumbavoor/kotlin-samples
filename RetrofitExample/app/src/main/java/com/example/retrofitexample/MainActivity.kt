package com.example.retrofitexample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var retrofitService: AlbumService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        retrofitService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)
        getRequestWithParameters()
        getRequestWithQueryParameter()
        uploadAlbum()

    }

    @SuppressLint("SetTextI18n")
    private fun getRequestWithQueryParameter() {
        val responseLiveData:LiveData<Response<Albums>> = liveData {
            val response = retrofitService.getAlbumsSorted(1)
            emit(response)
        }
        responseLiveData.observe(this, {
            val albumList = it.body()?.listIterator()
            if (albumList != null) {
                while (albumList.hasNext()) {
                    val albumsItem = albumList.next()
                    text_view.text = text_view.text.toString() + "Album ID: ${albumsItem.id}\n Album Title: ${albumsItem.title}\n Author ID: ${albumsItem.userId}\n\n\n"
                }
            }
        })
    }

    private fun getRequestWithParameters() {
        val pathResponse: LiveData<Response<AlbumsItem>> = liveData {
            val response = retrofitService.getAlbum(3)
            emit(response)
        }
        pathResponse.observe(this, {
            val title = it.body()?.title
            Toast.makeText(this, title, Toast.LENGTH_LONG).show()
        })
    }

    private fun uploadAlbum() {
        val album = AlbumsItem(0, "Test", 3)
        val postResponse: LiveData<Response<AlbumsItem>> = liveData {
            val response = retrofitService.uploadAlbum(album)
            emit(response)
        }

        postResponse.observe(this, {
            val albumsItem = it.body()
            if (albumsItem != null) {
                Log.d("hello", "${albumsItem.id}  ${albumsItem.title}  ${albumsItem.userId}")
            }
        })
    }
}