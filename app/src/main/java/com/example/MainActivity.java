package com.example;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText mobileNo;
    EditText email;
    EditText url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListeners();

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("mobileNo", mobileNo.getText().toString());
                intent.putExtra("email", email.getText().toString());
                intent.putExtra("url", url.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void setListeners(){

        mobileNo = (EditText) findViewById(R.id.mobileNo);
        email = (EditText) findViewById(R.id.email);
        url = (EditText) findViewById(R.id.url);

    }

}
