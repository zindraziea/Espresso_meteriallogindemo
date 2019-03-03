package com.katomaran.example.login.Activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.katomaran.example.login.R


/**
 * Created by gopalsamy.k on 20/3/17.
 */

class LoginActivity : AppCompatActivity() {


    internal lateinit var sharedpreferences: SharedPreferences

    private var login: Button? = null
    private var signup: Button? = null
    private var emailtx: TextView? = null
    private var passtx: TextView? = null
    private var emailed: EditText? = null
    private var passed: EditText? = null
    private var fabLogin: FloatingActionButton? = null
    private var login_cardview: CardView? = null
    private var closetxt: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)
        initViews()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupEnterAnimation()
            setupExitAnimation()
        }
        signup!!.setOnClickListener {
            val io = Intent(this@LoginActivity, SignupActivity::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                val pair1 = Pair.create(signup!!.findViewWithTag<View>(resources.getString(R.string.transition_signup)), signup!!.transitionName)
                val pair2 = Pair.create(emailtx!!.findViewWithTag<View>(resources.getString(R.string.emailtxt)), emailtx!!.transitionName)
                val pair3 = Pair.create(emailed!!.findViewWithTag<View>(resources.getString(R.string.email_edittxt)), emailed!!.transitionName)
                val pair4 = Pair.create(passtx!!.findViewWithTag<View>(resources.getString(R.string.passwordtxt)), passtx!!.transitionName)
                val pair5 = Pair.create(passed!!.findViewWithTag<View>(resources.getString(R.string.passwoed_edittxt)), passed!!.transitionName)
                val pair6 = Pair.create(closetxt!!.findViewWithTag<View>(resources.getString(R.string.transition_close)), closetxt!!.transitionName)
                val options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(this@LoginActivity, pair1, pair2, pair3, pair4, pair5, pair6)
                startActivity(io, options.toBundle())
            } else {
                startActivity(io)
            }
        }
        login!!.setOnClickListener {
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
            if (sharedpreferences.contains(username)) {
                val savedEmail = sharedpreferences.getString(email, null)
                val savedPass = sharedpreferences.getString(password, null)
                if (emailed!!.text.toString() == savedEmail && passed!!.text.toString() == savedPass) {
                    val pref = applicationContext.getSharedPreferences(MyPREFERENCES, 0) // 0 - for private mode
                    val editor = pref.edit()
                    editor.putString(loginUser, sharedpreferences.getString(username, null))
                    editor.commit()
                    onBackPressed()
                } else {
                    Toast.makeText(applicationContext, "Credentials not matched", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "Please Sign up", Toast.LENGTH_SHORT).show()
            }
        }
        closetxt!!.setOnClickListener { onBackPressed() }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setupEnterAnimation() {
        val transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.changebounds_with_arcmotion)
        window.sharedElementEnterTransition = transition
        transition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {

            }

            override fun onTransitionEnd(transition: Transition) {
                transition.removeListener(this)
                val myView = login_cardview!!.findViewWithTag<View>(resources.getString(R.string.transition_login))
                val cx = myView.measuredWidth / 2
                val cy = myView.measuredHeight / 2
                val finalRadius = Math.max(myView.width, myView.height) / 2
                val anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0f, finalRadius.toFloat())
                myView.visibility = View.VISIBLE
                anim.start()
            }

            override fun onTransitionCancel(transition: Transition) {

            }

            override fun onTransitionPause(transition: Transition) {

            }

            override fun onTransitionResume(transition: Transition) {

            }
        })
    }

    override fun onBackPressed() {
        val myView = login_cardview!!.findViewWithTag<View>(resources.getString(R.string.transition_login))
        val cx = myView.measuredWidth / 2
        val cy = myView.measuredHeight / 2
        val initialRadius = myView.width / 2
        val anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius.toFloat(), 0f)
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                myView.visibility = View.INVISIBLE
                backPressed()
            }
        })
        anim.start()

    }

    private fun backPressed() {
        super.onBackPressed()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setupExitAnimation() {
        val fade = Fade()
        window.returnTransition = fade
        fade.duration = 300
    }

    fun initViews() {
        closetxt = findViewById<View>(R.id.closetxt) as TextView
        login_cardview = findViewById<View>(R.id.login_cardview) as CardView
        fabLogin = findViewById<View>(R.id.activity_login_fab) as FloatingActionButton
        login = findViewById<View>(R.id.login) as Button
        signup = findViewById<View>(R.id.signup) as Button
        emailtx = findViewById<View>(R.id.emailtxt) as TextView
        emailed = findViewById<View>(R.id.emailEdittxt) as EditText
        passtx = findViewById<View>(R.id.passtxt) as TextView
        passed = findViewById<View>(R.id.passEdittxt) as EditText
        val custom_font = Typeface.createFromAsset(assets, "fonts/DroidSans.ttf")
        login!!.typeface = custom_font
        signup!!.typeface = custom_font
        emailtx!!.typeface = custom_font
        emailed!!.typeface = custom_font
        emailed!!.background.mutate().setColorFilter(resources.getColor(R.color.editText), PorterDuff.Mode.SRC_ATOP)
        passtx!!.typeface = custom_font
        passed!!.typeface = custom_font
        passed!!.background.mutate().setColorFilter(resources.getColor(R.color.editText), PorterDuff.Mode.SRC_ATOP)
    }

    companion object {
        val MyPREFERENCES = "MyPrefs"
        val username = "usernameKey"
        val password = "passwordKey"
        val email = "emailKey"
        val loginUser = "loginUserKey"
    }

}
