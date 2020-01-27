package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.FaqPage.FaqActivtiy;
import com.example.myapplication.Model.Retrofit.Checkoutpage;
import com.example.myapplication.Model.Retrofit.ContactUsModel;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUs extends AppCompatActivity {

    private ImageView imageBack;
    TextInputEditText first;
    EditText last;
    EditText input_email;
    EditText input_phone;
    EditText subject;
    EditText messages;
    Button loginBT;
    TextView titleTV;
    Button link_signup;
    TextView contact_text;
    LinearLayout mainLL;
    TextInputLayout name_text_inputs;
    TextInputLayout name_text_inputss;
    TextInputLayout name_text_input;
    TextInputLayout name_text_inputsss;
    TextInputLayout name_text_inputssss;
    TextInputLayout message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        imageBack = findViewById(R.id.imageBack);
        first = findViewById(R.id.first);
        last = findViewById(R.id.last);
        titleTV = findViewById(R.id.titleTV);
        input_email = findViewById(R.id.input_email);
        input_phone = findViewById(R.id.input_phone);
        messages = findViewById(R.id.messages);
        contact_text = findViewById(R.id.contact_text);
        mainLL = findViewById(R.id.mainLL);
        name_text_inputs = findViewById(R.id.name_text_inputs);
        name_text_inputsss = findViewById(R.id.name_text_inputsss);
        name_text_inputss = findViewById(R.id.name_text_inputss);
        name_text_inputssss = findViewById(R.id.name_text_inputssss);
        message = findViewById(R.id.message);
        name_text_input = findViewById(R.id.name_text_input);
        subject = findViewById(R.id.subject);
        loginBT = findViewById(R.id.btn_login);
        link_signup = findViewById(R.id.link_signup);
        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    contactUsMethod();
                }

            }
        });
        link_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FaqActivtiy.class);
                intent.putExtra("pageFrom", "home");
                startActivity(intent);


            }
        });
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactUs.super.onBackPressed();
            }
        });


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ContactUs.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titleTV.setTypeface(custom_font_azab);
            titleTV.setTextSize(24);
            first.setTypeface(custom_font_azab);
            first.setTypeface(custom_font_azab);
            last.setTypeface(custom_font_azab);
            contact_text.setTypeface(custom_font_azab);
            link_signup.setTypeface(custom_font_azab);
            input_email.setTypeface(custom_font_azab);
            input_phone.setTypeface(custom_font_azab);
            subject.setTypeface(custom_font_azab);
            messages.setTypeface(custom_font_azab);
            loginBT.setTypeface(custom_font_azab);
            name_text_inputs.setTypeface(custom_font_azab);
            name_text_inputss.setTypeface(custom_font_azab);
            name_text_input.setTypeface(custom_font_azab);
            name_text_inputsss.setTypeface(custom_font_azab);
            name_text_inputssss.setTypeface(custom_font_azab);
            contact_text.setTypeface(custom_font_azab);
            message.setTypeface(custom_font_azab);
            first.setTextDirection(View.TEXT_DIRECTION_RTL);
            last.setTextDirection(View.TEXT_DIRECTION_RTL);
            input_email.setTextDirection(View.TEXT_DIRECTION_RTL);
            input_phone.setTextDirection(View.TEXT_DIRECTION_RTL);
            subject.setTextDirection(View.TEXT_DIRECTION_RTL);
            message.setTextDirection(View.TEXT_DIRECTION_RTL);
            /*first.setTextSize(20);
            last.setTextSize(20);
            input_email.setTextSize(20);
            input_phone.setTextSize(20);
            subject.setTextSize(20);
            messages.setTextSize(20);*/
        }

        if (languageValue.equalsIgnoreCase("ar")) {
            mainLL.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        } else {
            mainLL.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }

    }

    private boolean validate() {
        if (first.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.enter_first_name));
            return false;
        } else if (last.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.enter_last_name));
            return false;
        } else if (input_email.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.enter_email));
            return false;
        } else if (input_phone.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.enter_phone_number));
            return false;
        } else if (subject.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.enter_subject));
            return false;
        } else if (messages.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.enter_message));
            return false;
        }
        return true;
    }

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    void contactUsMethod() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.data_loading));
        dialog.show();
        final Checkoutpage task = new Checkoutpage();
        task.setFirstname(first.getText().toString().trim());
        task.setLastname(last.getText().toString().trim());
        task.setPhone(input_phone.getText().toString().trim());
        task.setEmail(input_email.getText().toString().trim());
        task.setMessage(messages.getText().toString().trim());
        task.setSubject(subject.getText().toString().trim());

        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.ContactUsApi(task).enqueue(new Callback<ContactUsModel>() {
            @Override
            public void onResponse(Call<ContactUsModel> call, Response<ContactUsModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    if (response.body().getStatus().equals("200")) {
                        Toast.makeText(ContactUs.this, "Your message has been sent successfully!", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<ContactUsModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure Response", Toast.LENGTH_LONG).show();
            }
        });
    }
}
