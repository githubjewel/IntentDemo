package com.example;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    TextView mobile;
    TextView email;
    TextView url;
    Button call, sms, send, show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setListener();

        mobile.setText(getIntent().getStringExtra("mobileNo"));
        email.setText(getIntent().getStringExtra("email"));
        url.setText(getIntent().getStringExtra("url"));

        setButtonListener();

    }

    private void setButtonListener() {

        findViewById(R.id.call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String number = "tel: " + mobile.getText().toString().trim();
                    Intent callIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(number));
                    if (ActivityCompat.checkSelfPermission(SecondActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(callIntent);
                    }

                }catch (Exception e){e.printStackTrace();}
            }
        });

        findViewById(R.id.sms).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = mobile.getText().toString().trim();
                Intent sms = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null));
                startActivity(sms);
            }
        });

        findViewById(R.id.sendEmail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, email.getText().toString().trim());
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
                startActivity(Intent.createChooser(intent, "Send Mail"));

                /*
                Intent mail = new Intent(Intent.ACTION_SEND);
                mail.setData(Uri.parse("mailto:"));
                mail.putExtra(Intent.EXTRA_EMAIL, email.getText().toString().trim());
                mail.putExtra(Intent.EXTRA_SUBJECT,"From application");
                mail.putExtra(Intent.EXTRA_TEXT, "Hi,how are you?");
                mail.setType("message/rfc822");
                startActivity(Intent.createChooser(mail,"Send Email"));*/
            }
        });

        findViewById(R.id.showUrl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent webpage = new Intent(Intent.ACTION_VIEW);
                webpage.setData(Uri.parse("http://www.google.com"));
                Intent chooser = Intent.createChooser(webpage,"Open Using...");
                if(webpage.resolveActivity(getPackageManager()) != null)
                    startActivity(chooser);
            }
        });
    }


    private void setListener(){

        mobile = (TextView) findViewById(R.id.mobileNumber);
        email = (TextView) findViewById(R.id.emailAddress);
        url = (TextView) findViewById(R.id.urlField);

    }
}
