package com.example.myapplication;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Model.Retrofit.LoginModel;
import com.example.myapplication.Model.Retrofit.Logins;
import com.example.myapplication.Model.Retrofit.Myprofile;
import com.example.myapplication.Model.Retrofit.PromoResponse;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;
import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;

public class LoginActivity extends AppCompatActivity {


    Button btn_login;
    EditText input_email, input_password;
    TextView link_signup, titlemenu;
    ImageView logo;
    private AnimationDrawable animationDrawable;
    LinearLayout linearLayout;
    String user, password, name, email;
    private TextView back;
    private String token = "";
    Dialog dialog;
    TextInputLayout name_text_input;
    TextInputLayout name_text_inputs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        input_email = findViewById(R.id.input_email);
        input_password = findViewById(R.id.input_password);
        link_signup = findViewById(R.id.link_signup);
        logo = findViewById(R.id.logo);
        back = findViewById(R.id.back);
        name_text_input = findViewById(R.id.name_text_input);
        name_text_inputs = findViewById(R.id.name_text_inputs);
        titlemenu = findViewById(R.id.titlemenu);
        //////////////////////////////////////////////////
        token = FirebaseInstanceId.getInstance().getToken();
        com.example.myapplication.Retrofit.Log.e("MyToken", token);
        AlphaAnimation animation1 = new AlphaAnimation(0.2f, 1.0f);
        animation1.setDuration(1000);
        animation1.setStartOffset(5000);
        animation1.setFillAfter(true);
        //logo.startAnimation(animation1);
        link_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotDialog();

            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = input_email.getText().toString().trim();
                password = input_password.getText().toString().trim();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("userid", user);
                editor.putString("email", user);
                editor.apply();


                if (user.isEmpty() || user.length() == 0 || user.equals("") || user == null) {
                    Toast.makeText(getApplicationContext(), getString(R.string.please_enter_email), Toast.LENGTH_LONG).show();
                    //ed_company.requestFocus();


                    // mPlayer2= MediaPlayer.create(getApplicationContext(), R.raw.alert_tone);
                    // mPlayer2.start();
                    //EditText is empty
                }
                if (password.isEmpty() || password.length() == 0 || password.equals("") || password == null) {
                    Toast.makeText(getApplicationContext(), getString(R.string.please_enter_pass), Toast.LENGTH_LONG).show();
                    //ed_company.requestFocus();


                    // mPlayer2= MediaPlayer.create(getApplicationContext(), R.raw.alert_tone);
                    // mPlayer2.start();
                    //EditText is empty
                } else {

                    Login();
                }
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titlemenu.setTypeface(custom_font_azab);
            titlemenu.setTextSize(24);
            input_email.setTypeface(custom_font_azab);
            input_password.setTypeface(custom_font_azab);
            btn_login.setTypeface(custom_font_azab);
            link_signup.setTypeface(custom_font_azab);
            name_text_inputs.setTypeface(custom_font_azab);
            name_text_input.setTypeface(custom_font_azab);

        }

      /*  if (languageValue.equalsIgnoreCase("ar")) {

            name_text_input.setTextDirection(View.TEXT_DIRECTION_RTL);
            name_text_inputs.setTextDirection(View.TEXT_DIRECTION_RTL);
            input_password.setTextDirection(View.TEXT_DIRECTION_RTL);
        //    name_text_inputs.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        } else {
            name_text_input.setTextDirection(View.TEXT_DIRECTION_LTR);
            name_text_inputs.setTextDirection(View.TEXT_DIRECTION_LTR);
        }
*/
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.super.onBackPressed();
            }
        });

    }


    private void Login() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.login));
        dialog.show();
        final Logins task = new Logins();
        task.setUserid(user);
        task.setPassword(password);
        task.setFirebaseValue(token);


        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetLogin(task).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    String resp = response.body().toString();

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("userid", user);
                    try {
                        editor.putString("userCompanyName", response.body().getData().get(0).getCompany());
                        editor.putString("userFirstName", response.body().getData().get(0).getFirstname());
                        editor.putString("userLastName", response.body().getData().get(0).getLastname());
                        editor.putString("userEmailId", response.body().getData().get(0).getEmail());
                        editor.putString("userFloor", response.body().getData().get(0).getFloor());
                        editor.putString("userSimpleId", response.body().getData().get(0).getId());
                        editor.putString("userOffice", response.body().getData().get(0).getOffice());
                        editor.putString("userPhone", response.body().getData().get(0).getPhone());
                    } catch (Exception ae) {
                        ae.printStackTrace();
                    }

                 /*   editor.putString("email",response.body().getData().get(0).getEmail());
                    editor.putString("name",response.body().getData().get(0).getFirstname());*/
                    editor.apply();
                    // Set Logged In statue to 'true'
                    SaveSharedPreference.setLoggedIn(getApplicationContext(), true);
                    Intent intent = new Intent(getApplicationContext(), MyAccountHome.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);


             /*      Gson gson =  new Gson();
                   gson.fromJson();
                    if(response.body())*/

                    //  Intent intent = new Intent(getApplicationContext(), MyAccountHome.class);
                    // startActivity(intent); //EditText is not empty
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(LoginActivity.this, getString(R.string.enter_correct_number), Toast.LENGTH_SHORT).show();

                //   Toast.makeText(getApplicationContext(), "Failure Response", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void forgotDialog() {

        Button closeBT, forgotBT;
        final EditText emailET;
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.forgot_password_layout);
        emailET = dialog.findViewById(R.id.emailET);
        closeBT = dialog.findViewById(R.id.closeBT);
        forgotBT = dialog.findViewById(R.id.forgotBT);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");

            emailET.setTypeface(custom_font_azab);
            closeBT.setTypeface(custom_font_azab);
            forgotBT.setTypeface(custom_font_azab);
        }


        closeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        forgotBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (emailET.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, getString(R.string.please_enter_email_id), Toast.LENGTH_SHORT).show();
                } else {
                    forgotPassword(emailET.getText().toString());
                }

            }
        });
        dialog.show();


    }


    private void forgotPassword(String email) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.login));
        dialog.show();
        final Myprofile task = new Myprofile();
        task.setEmail(email);


        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.SendForgotEmail(task).enqueue(new Callback<PromoResponse>() {
            @Override
            public void onResponse(Call<PromoResponse> call, Response<PromoResponse> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    String resp = response.body().toString();

                    if (response.body().getStatus().equals("205")) {
                        Toast.makeText(LoginActivity.this, "Please Enter Correct Email", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(LoginActivity.this, getString(R.string.password_sent_to_mail), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<PromoResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(LoginActivity.this, getString(R.string.please_enter_correct_email), Toast.LENGTH_SHORT).show();

                //   Toast.makeText(getApplicationContext(), "Failure Response", Toast.LENGTH_LONG).show();
            }
        });
    }
}
