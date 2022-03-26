package com.plantech.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;


public class LogInActivity extends AppCompatActivity {

    TextView txtUsername;
    TextView txtLogIn;
    Button btnLogIn;
    TextView txtSignUp;
    TextView txtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


                txtLogIn = findViewById(R.id.txtLogIn);
                btnLogIn = findViewById(R.id.btnLogIn);
                txtUsername = findViewById(R.id.txtUsername);
                txtSignUp = findViewById(R.id.txtSignUp);

            public void checkUsername; {
            boolean isValid = true;
            if (isEmpty(txtUsername)) {
                txtUsername.setError("You must enter username to login!");
                isValid = false;
            } else {
                if (!isEmail(txtUsername)) {
                    txtUsername.setError("Enter valid email!");
                    isValid = false;
                }
            }

            if (isEmpty(txtPassword)) {
                txtPassword.setError("You must enter password to login!");
                isValid = false;
            } else {
                if (txtPassword.getText().toString().length() < 4) {
                    txtPassword.setError("Password must be at least 4 chars long!");
                    isValid = false;
                }
            }

            //check email and password
            //IMPORTANT: here should be call to backend or safer function for local check; For example simple check is cool
            //For example simple check is cool
            if (isValid) {
                String usernameValue = txtUsername.getText().toString();
                String passwordValue = txtPassword.getText().toString();
                if (usernameValue.equals("test@test.com") && passwordValue.equals("password1234")) {
                    //everything checked we open new activity
                    Intent i = new Intent(LogInActivity.this, SignUpActivity.class);
                    startActivity(i);
                    //we close this activity
                    this.finish();
                } else {
                    Toast t = Toast.makeText(this, "Wrong email or password!", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        }
                txtLogIn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent (LogInActivity.this, SignUpActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                btnLogIn.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Intent intent = new Intent (LogInActivity.this, SignUpActivity.class);
                         startActivity(intent);
                         finish();
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
        }




