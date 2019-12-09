package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.api.Keys
import com.example.newsapp.ui.main.NewsFragment
import dagger.android.AndroidInjection
import io.paperdb.Paper

class NewsActivity : AppCompatActivity(), NewsFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        Paper.init(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NewsFragment.newInstance())
                .commitNow()
        }

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.abs_layout)
        applicationContext
    }

    /**
     * ************************* on news item selected *********************************************
     * */
    override
    fun onNewsItemSelected(uri: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra(Keys.Extras.URL, uri)
        startActivity(intent)

    }


}
