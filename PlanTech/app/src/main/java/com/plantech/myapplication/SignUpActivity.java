package com.plantech.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    EditText inputUsername1, inputEmail, inputPass, inputConfirmPass;
    Button buttonSignUp;
    String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    ProgressDialog progressDialog;
    TextView alreadyHaveAnAccount;

    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inputUsername1 = findViewById(R.id.inputUsername1);
        inputEmail = findViewById(R.id.inputEmail);
        inputPass = findViewById(R.id.inputPass);
        inputConfirmPass = findViewById(R.id.inputConfirmPass);
        alreadyHaveAnAccount = findViewById(R.id.alreadyHaveAnAccount);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuth();
            }
        });

        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });

    }

    private void PerformAuth(){
        String username = inputUsername1.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPass.getText().toString();
        String confirmPassword = inputConfirmPass.getText().toString();

        if(!email.matches(regex))
        {
            inputEmail.setError("Invalid email");
        }
        else if (password.isEmpty() || password.length()<8)
        {
            inputPass.setError("Password must have 8 characters");
        }
        else if (!password.equals(confirmPassword))
        {
            inputConfirmPass.setError("Passwords do not match");
        }
        else
        {
            progressDialog.setTitle("Sign Up");
            progressDialog.setMessage("Please wait...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        openLoginActivity();
                        Toast.makeText(SignUpActivity.this, "Signup Successful!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(SignUpActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void openLoginActivity() {
        Intent login_act = new Intent(SignUpActivity.this,LogInActivity.class);
        login_act.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(login_act);

    }
}
