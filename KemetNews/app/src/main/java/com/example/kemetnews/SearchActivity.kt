package com.example.kemetnews

import android.annotation.SuppressLint
import com.example.kemetnews.RequestManager.apiRequest
import androidx.appcompat.app.AppCompatActivity
import com.example.kemetnews.Models.Results
import androidx.recyclerview.widget.RecyclerView
import android.widget.ProgressBar
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView

import androidx.recyclerview.widget.LinearLayoutManager

import com.example.kemetnews.Models.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class SearchActivity : AppCompatActivity() {
    var articles: ArrayList<Results>? = null
    var adapter: Adapter? = null
    var recyclerView: RecyclerView? = null
    var progressBar: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val searchView = findViewById<SearchView>(R.id.search_view)
        searchView.clearFocus()
        recyclerView = findViewById(R.id.recycler_main)
        recyclerView?.setHasFixedSize(true)
        articles = ArrayList()
        recyclerView?.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(this@SearchActivity, articles!!)
        recyclerView?.adapter = adapter
        progressBar = findViewById(R.id.progress_bar)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                showNews(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                showNews(newText)
                return true
            }
        })
    }

    private fun showNews(query: String) {
        progressBar!!.visibility = View.VISIBLE
        articles!!.clear()
        apiRequest.getNews(
            this@SearchActivity.getString(R.string.api_key), query,
            this@SearchActivity.getString(R.string.Country)
        )!!.enqueue(object : Callback<ApiResponse?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<ApiResponse?>, response: Response<ApiResponse?>) {
                if (response.isSuccessful) {
                    assert(response.body() != null)
                    progressBar!!.visibility = View.GONE
                    response.body()!!.results?.let { articles!!.addAll(it) }
                    adapter!!.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ApiResponse?>, t: Throwable) {}
        })
    }
}