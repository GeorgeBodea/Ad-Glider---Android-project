package com.adsforlife.adglider.Presentation.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.adsforlife.adglider.Presentation.AdGliderViewModel;
import com.adsforlife.adglider.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = "RegisterActivity";
    private FirebaseAuth mAuth;
    private EditText email, password;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        progressBar = findViewById(R.id.progressBar);
    }

    public void goToRegister(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    public void loginToAccount(View view) {
        String emailString = email.getText().toString().trim();
        String passwordString = password.getText().toString();

        if(emailString.isEmpty()) {
            email.setError("Email required");
            email.requestFocus();
            return;
        }

        if(passwordString.isEmpty()) {
            password.setError("Password required");
            password.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailString).matches()) {
            email.setError("Not a valid email");
            email.requestFocus();
            return;
        }

        if(passwordString.length() < 6) {
            password.setError("The password must have more then 6 characters");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(emailString, passwordString)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User successfully logged in");
                            Toast.makeText(LoginActivity.this, "User successfully logged in", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            AdGliderViewModel.setmAuth(mAuth);
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            Log.w(TAG, "Logging failed", task.getException());
                            Toast.makeText(LoginActivity.this, "Logging failed.",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }



}