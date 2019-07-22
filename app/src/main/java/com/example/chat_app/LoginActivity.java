package com.example.chat_app;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivity extends AppCompatActivity {

    MaterialEditText email,password;
    Button btn_login;

    private Toolbar toolbars;

    private FirebaseAuth auth;
    TextView forget_password;

    CheckBox showpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbars = findViewById(R.id.toolbar);
        setSupportActionBar(toolbars);
        getSupportActionBar().setTitle("Log in");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        btn_login = findViewById(R.id.btn_login);
        forget_password = findViewById(R.id.forgot_password);
        showpassword = findViewById(R.id.showpassword);

        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ResetPasswordActivity.class));
            }
        });


        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());


                }else {

                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                if(TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password)) {
                    Toast.makeText(LoginActivity.this, "All fields are require", Toast.LENGTH_SHORT).show();

                }
                else {
                    auth.signInWithEmailAndPassword(txt_email, txt_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }else{

                                        Toast.makeText(LoginActivity.this,"Authentication failed",Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });


                }

            }
        });
    }
}
