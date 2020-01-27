package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Model.Retrofit.ChangePasswordModel;
import com.example.myapplication.Model.Retrofit.PasswordChangeModel;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {
    Button btn_login;
    EditText oldpassword, newpassword;
    RelativeLayout mainRL;
    String user, password;
    private String userId = "";
    TextView back,titlemenu;
    TextInputLayout name_text_inputs,passwordTL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        btn_login =  findViewById(R.id.btn_login);
        oldpassword =  findViewById(R.id.oldpassword);
        newpassword =  findViewById(R.id.newpassowed);
        titlemenu = findViewById(R.id.titlemenu);
        mainRL = findViewById(R.id.mainRL);
        back = findViewById(R.id.back);
        name_text_inputs = findViewById(R.id.name_text_inputs);
        passwordTL = findViewById(R.id.passwordTL);
        if (getIntent().hasExtra("userId")) {
            userId = getIntent().getStringExtra("userId");
        }
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = oldpassword.getText().toString().trim();
                password = newpassword.getText().toString().trim();


                if (user.isEmpty() || user.length() == 0 || user.equals("") || user == null) {
                    Toast.makeText(getApplicationContext(), getString(R.string.enter_old_pass), Toast.LENGTH_LONG).show();
                    //ed_company.requestFocus();


                    // mPlayer2= MediaPlayer.create(getApplicationContext(), R.raw.alert_tone);
                    // mPlayer2.start();
                    //EditText is empty
                }
                if (password.isEmpty() || password.length() == 0 || password.equals("") || password == null) {
                    Toast.makeText(getApplicationContext(), getString(R.string.enter_new_password), Toast.LENGTH_LONG).show();
                    //ed_company.requestFocus();


                    // mPlayer2= MediaPlayer.create(getApplicationContext(), R.raw.alert_tone);
                    // mPlayer2.start();
                    //EditText is empty
                } else {

                    Login();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePassword.super.onBackPressed();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ChangePassword.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if(languageValue.equalsIgnoreCase("ar"))
        {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titlemenu.setTypeface(custom_font_azab);
            titlemenu.setTextSize(24);
            oldpassword.setTypeface(custom_font_azab);
            newpassword.setTypeface(custom_font_azab);
            btn_login.setTypeface(custom_font_azab);
            passwordTL.setTypeface(custom_font_azab);
            name_text_inputs.setTypeface(custom_font_azab);


        }

        if (languageValue.equalsIgnoreCase("ar")) {
            mainRL.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            name_text_inputs.setTextDirection(View.TEXT_DIRECTION_RTL);
            name_text_inputs.setTextDirection(View.TEXT_DIRECTION_RTL);
            newpassword.setTextDirection(View.TEXT_DIRECTION_RTL);
            oldpassword.setTextDirection(View.TEXT_DIRECTION_RTL);
        } else {
            mainRL.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }

    }


    private void Login() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ChangePassword.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("password Change");
        dialog.show();
        final ChangePasswordModel task = new ChangePasswordModel();
        task.setUserid(userId);
        task.setFormtype("Password");
        task.setLang(languageValue);
        task.setOldpassword(oldpassword.getText().toString().trim());
        task.setNewpassword(newpassword.getText().toString().trim());


        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.ChangePassword(task).enqueue(new Callback<PasswordChangeModel>() {
            @Override
            public void onResponse(Call<PasswordChangeModel> call, Response<PasswordChangeModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    Toast.makeText(ChangePassword.this, getString(R.string.password_changed), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MyAccountHome.class);
                    startActivity(intent); //EditText is not empty

                }
            }

            @Override
            public void onFailure(Call<PasswordChangeModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure Response", Toast.LENGTH_LONG).show();
            }
        });
    }
}

