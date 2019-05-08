package com.example.studybuddy;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import Pages.HomePageActivity;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPassword;
    private TextView signUpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //START: make signUpTextView clickable
        signUpTextView = findViewById(R.id.signUpTextView);
        String text = "Don't have an account? Sign up!";
        SpannableString ss = new SpannableString(text);
        ClickableSpan span = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent toSignUpActivity = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(toSignUpActivity);
            }
        };
        ss.setSpan(span, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signUpTextView.setText(ss);
        signUpTextView.setMovementMethod(LinkMovementMethod.getInstance()); //necessary for TextView link to work
        //END: make signUpTextView clickable

        textInputEmail = findViewById(R.id.text_input_email);
        textInputPassword = findViewById(R.id.text_input_password);
    }

    private boolean validateEmailOrUsername() {
        String emailOrUsernameInput = textInputEmail.getEditText().getText().toString().trim();

        if (emailOrUsernameInput.isEmpty()) {
            textInputEmail.setError("This field cannot be empty."); //sets error message at bottom of layout
            return false;
        } else {
            textInputEmail.setError(null); //removes error message
            textInputEmail.setErrorEnabled(false); //layout shrinks (removes room for error message)
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            textInputPassword.setError("This field cannot be empty.");
            return false;
        } else {
            textInputPassword.setError(null);
            textInputPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void loginOrShowError(View view) { //must pass View object or app will crash
        if (!validateEmailOrUsername() | !validatePassword()) {
            return;
        } else {
            Intent toHomePage = new Intent(LoginActivity.this, HomePageActivity.class);
            startActivity(toHomePage);
        }
    }
}
