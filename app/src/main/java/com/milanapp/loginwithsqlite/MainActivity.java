package com.milanapp.loginwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et_email,et_pass,et_cpass;
    Button reg_btn;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_email = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.et_pass);
        et_cpass = findViewById(R.id.et_conf_pass);
        reg_btn = findViewById(R.id.registration);
        helper = new DatabaseHelper(this);

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = et_email.getText().toString();
                String s2 = et_pass.getText().toString();
                String s3 = et_cpass.getText().toString();

                if (s1.equals("")||s2.equals("")||s3.equals("")){

                    Toast.makeText(MainActivity.this, "Field is empty", Toast.LENGTH_SHORT).show();
                }
                else if (s2.equals(s3)==false){
                    Toast.makeText(MainActivity.this, "Password is not match", Toast.LENGTH_SHORT).show();
                }
                else {

                    if (s2.equals(s3)){
                        Boolean checkmail = helper.checkmail(s1);
                        if (checkmail==true){

                            Boolean insert = helper.insertdata(s1,s2);
                            if (insert==true){
                                Toast.makeText(MainActivity.this, "Registration succcessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Email is already exits", Toast.LENGTH_SHORT).show();
                        }

                    }
                }


            }
        });


    }
}
