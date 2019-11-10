package com.faisaljaved.wission.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.faisaljaved.wission.db.DBEntry;
import com.faisaljaved.wission.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {

    /**
     * Editext view and button elements
     */
    private EditText name, email, phone;
    private Button add;

    /**
     * DataBase Reference variable for storing values in database
     */
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        databaseReference = FirebaseDatabase.getInstance().getReference("Entries");

        name = (EditText) findViewById(R.id.name_edittext);
        email = (EditText) findViewById(R.id.email_edittext);
        phone = (EditText) findViewById(R.id.phone_edittext);

        add = (Button) findViewById(R.id.add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddEntry();
            }
        });
    }

    private void AddEntry() {

        String name_entry = name.getText().toString().trim();
        String email_entry = email.getText().toString().trim();
        String phone_entry = phone.getText().toString().trim();

        if (!TextUtils.isEmpty(name_entry) || !TextUtils.isEmpty(email_entry) || !TextUtils.isEmpty(phone_entry)){

            String id = databaseReference.push().getKey();

            DBEntry entry = new DBEntry(name_entry,email_entry,phone_entry);

            databaseReference.child(id).setValue(entry);

            Toast.makeText(this, "Entry Added", Toast.LENGTH_SHORT).show();

            finish();

        }else {
            Toast.makeText(this,"Fields can't be left blank", Toast.LENGTH_LONG).show();
        }
    }


}
