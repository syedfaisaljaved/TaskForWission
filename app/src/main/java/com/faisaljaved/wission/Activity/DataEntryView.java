package com.faisaljaved.wission.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.faisaljaved.wission.R;

public class DataEntryView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry_view);

        Intent intent = getIntent();
        String entry_name = intent.getStringExtra("ENTRY_NAME");
        String entry_email = intent.getStringExtra("ENTRY_EMAIL");
        String entry_phone = intent.getStringExtra("ENTRY_PHONE");

        TextView name = (TextView) findViewById(R.id.name_view);
        TextView email = (TextView) findViewById(R.id.email_view);
        TextView phone = (TextView) findViewById(R.id.phone_view);

        name.setText(entry_name);
        email.setText(entry_email);
        phone.setText(entry_phone);
    }
}
