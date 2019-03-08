package com.katomaran.example.login.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.katomaran.example.login.Activity.util.EspressoIdlingResource
import com.katomaran.example.login.R


class MainActivity : AppCompatActivity() {

    internal lateinit var sharedpreferences: SharedPreferences

    internal var userName: String? = null

    private var collapsingToolbarLayout: CollapsingToolbarLayout? = null
    private var fab: FloatingActionButton? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = ""
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab!!.setOnClickListener {
            EspressoIdlingResource.increment()
            val io = Intent(this@MainActivity, LoginActivity::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val pair1 = Pair.create(fab!!.findViewWithTag<View>("login"), fab!!.transitionName)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity, pair1)
                startActivity(io, options.toBundle())
            } else {
                startActivity(io)
            }
        }

        val custom_font = Typeface.createFromAsset(assets, "fonts/DroidSans.ttf")
        collapsingToolbarLayout = findViewById<View>(R.id.toolbar_layout) as CollapsingToolbarLayout
        toolbarTextAppernce()
        collapsingToolbarLayout!!.setExpandedTitleTypeface(custom_font)
        collapsingToolbarLayout!!.setCollapsedTitleTypeface(custom_font)
    }

    override fun onResume() {
        super.onResume()

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
        if (sharedpreferences.contains(loginUser)) {
            userName = sharedpreferences.getString(loginUser, null)
        } else {
            userName = "LOG IN"
        }
        collapsingToolbarLayout!!.title = userName

    }

    private fun toolbarTextAppernce() {
        collapsingToolbarLayout!!.setCollapsedTitleTextAppearance(R.style.collapsedappbar)
        collapsingToolbarLayout!!.setExpandedTitleTextAppearance(R.style.expandedappbar)
    }

    companion object {
        val MyPREFERENCES = "MyPrefs"
        val loginUser = "loginUserKey"
    }
}