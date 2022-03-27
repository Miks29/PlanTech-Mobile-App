package com.plantech.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LogInActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private TextView txtUsername;
    private TextView txtPassword;
    TextView txtSignUp;
    Button btnLogIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


                auth = FirebaseAuth.getInstance();
                btnLogIn = findViewById(R.id.btnLogIn);
                txtUsername = findViewById(R.id.txtUsername);
                txtPassword = findViewById(R.id.txtPassword);
                txtSignUp = findViewById(R.id.txtSignUp);


                btnLogIn.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {

                         login();
                    }
                });

                txtSignUp.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Intent intent = new Intent (LogInActivity.this, SignUpActivity.class);
                         startActivity(intent);
                         finish();
                    }
                });
            }

    private void login() {

        String user = txtUsername.getText().toString().trim();
        String pass = txtPassword.getText().toString().trim();
        if (user.isEmpty()){
            txtUsername.setError("Email cannot be empty");}
        if (pass.isEmpty()){
            txtPassword.setError("Password cannot be empty");}
        else{
            auth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                        Toast.makeText(LogInActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LogInActivity.this , MainActivity.class));}
                    else{
                        Toast.makeText(LogInActivity.this, "Login Failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();}
                        }
            });
        }
    }
}


