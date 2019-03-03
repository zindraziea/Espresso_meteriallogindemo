package com.katomaran.example.login.Activity

import android.content.Context
import android.content.SharedPreferences
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.katomaran.example.login.R

/**
 * Created by gopalsamy.k on 20/3/17.
 */

class SignupActivity : AppCompatActivity() {


    internal lateinit var sharedpreferences: SharedPreferences

    private var nametx: TextView? = null
    private var statictx: TextView? = null
    private var signup: Button? = null
    private var termstx: TextView? = null
    private var nameed: EditText? = null
    private var emailed: EditText? = null
    private var passed: EditText? = null
    private var emailtx: TextView? = null
    private var passtx: TextView? = null
    private var closetxt: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sinup_page)

        initViews()

        closetxt!!.setOnClickListener { onBackPressed() }
        signup!!.setOnClickListener {
            if (nameed!!.text.toString().length > 0 && passed!!.text.toString().length > 0) {

                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
                val pref = applicationContext.getSharedPreferences(MyPREFERENCES, 0) // 0 - for private mode
                val editor = pref.edit()
                editor.putString(username, nameed!!.text.toString())
                editor.putString(password, passed!!.text.toString())
                editor.putString(email, emailed!!.text.toString())
                editor.commit()
                onBackPressed()

            } else {
                Toast.makeText(applicationContext, "Enter all fields", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

    fun initViews() {
        nametx = findViewById<View>(R.id.nametxt) as TextView
        closetxt = findViewById<View>(R.id.closetxt) as TextView
        signup = findViewById<View>(R.id.signup) as Button
        statictx = findViewById<View>(R.id.textView) as TextView
        termstx = findViewById<View>(R.id.textView2) as TextView
        nameed = findViewById<View>(R.id.nameEdittxt) as EditText
        emailtx = findViewById<View>(R.id.emailtxt) as TextView
        emailed = findViewById<View>(R.id.emailEdittxt) as EditText
        passtx = findViewById<View>(R.id.passtxt) as TextView
        passed = findViewById<View>(R.id.passEdittxt) as EditText
        val custom_font = Typeface.createFromAsset(assets, "fonts/DroidSans.ttf")
        nametx!!.typeface = custom_font
        signup!!.typeface = custom_font
        statictx!!.typeface = custom_font
        termstx!!.typeface = custom_font
        nameed!!.typeface = custom_font
        nameed!!.background.mutate().setColorFilter(resources.getColor(R.color.editText), PorterDuff.Mode.SRC_ATOP)
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
    }
}
