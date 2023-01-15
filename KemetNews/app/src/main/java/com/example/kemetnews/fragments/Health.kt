package com.example.kemetnews.fragments

import android.annotation.SuppressLint
import com.example.kemetnews.RequestManager.apiRequest

import com.example.kemetnews.Models.Results
import android.widget.ProgressBar
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.kemetnews.R
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kemetnews.Adapter

import com.example.kemetnews.Models.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class Health : Fragment() {
    var articles: ArrayList<Results>? = null
    var adapter: Adapter? = null
    var progressBar: ProgressBar? = null
    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.health_fragment, null)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_health)
        articles = ArrayList()
        if (recyclerView != null) {
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
        adapter = Adapter(this.requireContext(), articles!!)
        if (recyclerView != null) {
            recyclerView.adapter = adapter
        }
        progressBar = view?.findViewById(R.id.progress_bar)
        showNews()
        return view
    }

    private fun showNews() {
        progressBar!!.visibility = View.VISIBLE
        articles!!.clear()
        apiRequest.getCategoryNews(
            this.requireContext().getString(R.string.api_key),
            this.requireContext().getString(R.string.Language), this.requireContext().getString(R.string.Health)
        )
            ?.enqueue(object : Callback<ApiResponse?> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<ApiResponse?>,
                    response: Response<ApiResponse?>
                ) {
                    if (response.isSuccessful) {
                        assert(response.body() != null)
                        progressBar!!.visibility = View.GONE
                        articles!!.addAll(response.body()!!.results!!)
                        adapter!!.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<ApiResponse?>, t: Throwable) {}
            })
    }
}