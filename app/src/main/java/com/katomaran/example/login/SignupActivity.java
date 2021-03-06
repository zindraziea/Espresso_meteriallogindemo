package com.katomaran.example.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by gopalsamy.k on 20/3/17.
 */

public class SignupActivity extends AppCompatActivity {


    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String username = "usernameKey";
    public static final String password = "passwordKey";
    public static final String email = "emailKey";


    private TextView nametx;
    private TextView statictx;
    private Button signup;
    private TextView termstx;
    private EditText nameed;
    private EditText emailed;
    private EditText passed;
    private TextView emailtx;
    private TextView passtx;
    private TextView closetxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sinup_page);

        initViews();

        closetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameed.getText().toString().length() > 0 && passed.getText().toString().length() > 0) {

                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences pref = getApplicationContext().getSharedPreferences(MyPREFERENCES, 0); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString(username, nameed.getText().toString());
                    editor.putString(password, passed.getText().toString());
                    editor.putString(email, emailed.getText().toString());
                    editor.commit();
                    onBackPressed();

                } else {
                    Toast.makeText(getApplicationContext(), "Enter all fields", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public void initViews() {
        nametx = (TextView) findViewById(R.id.nametxt);
        closetxt = (TextView) findViewById(R.id.closetxt);
        signup = (Button) findViewById(R.id.signup);
        statictx = (TextView) findViewById(R.id.textView);
        termstx = (TextView) findViewById(R.id.textView2);
        nameed = (EditText) findViewById(R.id.nameEdittxt);
        emailtx = (TextView) findViewById(R.id.emailtxt);
        emailed = (EditText) findViewById(R.id.emailEdittxt);
        passtx = (TextView) findViewById(R.id.passtxt);
        passed = (EditText) findViewById(R.id.passEdittxt);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/DroidSans.ttf");
        nametx.setTypeface(custom_font);
        signup.setTypeface(custom_font);
        statictx.setTypeface(custom_font);
        termstx.setTypeface(custom_font);
        nameed.setTypeface(custom_font);
        nameed.getBackground().mutate().setColorFilter(getResources().getColor(R.color.editText), PorterDuff.Mode.SRC_ATOP);
        emailtx.setTypeface(custom_font);
        emailed.setTypeface(custom_font);
        emailed.getBackground().mutate().setColorFilter(getResources().getColor(R.color.editText), PorterDuff.Mode.SRC_ATOP);
        passtx.setTypeface(custom_font);
        passed.setTypeface(custom_font);
        passed.getBackground().mutate().setColorFilter(getResources().getColor(R.color.editText), PorterDuff.Mode.SRC_ATOP);
    }
}
