package com.example.newsapp.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.AllNewsHeadlineAdapter
import com.example.newsapp.listener.OnNewsItemSelectedListener
import com.example.newsapp.model.NewsData
import com.example.newsapp.utility.Utils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_fragment.*


class NewsFragment : Fragment() {

    companion object {
        fun newInstance() = NewsFragment()
    }

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var mLayoutManager:LinearLayoutManager
    private lateinit var allNewsHeadlineAdapter: AllNewsHeadlineAdapter
    private var listener: OnFragmentInteractionListener? = null

    /**
     * ************************ handling on attach callback ****************************************
     * */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    /**
     * ************************ handling on detach callback ****************************************
     * */

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        shimmer_view_container.startShimmerAnimation()
        newsViewModel.fetchAllNewsHeadline()
        newsViewModel.allNewsHeadline.observe(this,
            Observer<NewsData> {
                shimmer_view_container.stopShimmerAnimation()
                shimmer_view_container.visibility=View.GONE
                rvAllNewsHeadline.visibility=View.VISIBLE
                swipeRefresh.isRefreshing=false
                setAdapter(it)
            })

        swipeRefresh.setOnRefreshListener {
            newsViewModel.fetchAllNewsHeadline()
        }
    }

    private fun setAdapter(newsData: NewsData?) {

        if(!Utils.internetCheck(context!!)){
            val snackbar = Snackbar.make(rvAllNewsHeadline
                ,
                "No Internet connection",
                Snackbar.LENGTH_LONG
            )
            snackbar.show()
        }
        mLayoutManager = LinearLayoutManager(context)
        rvAllNewsHeadline.layoutManager = mLayoutManager
        allNewsHeadlineAdapter = AllNewsHeadlineAdapter(object : OnNewsItemSelectedListener {
            override fun onNewsItemSelected(uri: String) {
                listener?.onNewsItemSelected(uri)
            }

        }, context!!, newsData?.articles!!)
        rvAllNewsHeadline.adapter = allNewsHeadlineAdapter
    }

    interface OnFragmentInteractionListener {
        fun onNewsItemSelected(uri: String)
    }


}
