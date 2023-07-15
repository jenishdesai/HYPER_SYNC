package com.HyperSync.hypersync;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpAdminActivity extends AppCompatActivity {

    Button submit;
    EditText fname,lname,usremail,usrphone,Compcontact,Gst,Designation,Compaddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_admin);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fname = (EditText) findViewById(R.id.txtFirstName);
        lname = (EditText) findViewById(R.id.txtLastName);
        usremail = (EditText) findViewById(R.id.etEmail);
        usrphone = (EditText) findViewById(R.id.etPhoneNumber);
        Compcontact = (EditText) findViewById(R.id.etCompanyContact);
        Gst = (EditText) findViewById(R.id.txtGSTNumber);
        Designation= (EditText) findViewById(R.id.txtDesignation);
        Compaddress = (EditText) findViewById(R.id.etAddress);

        submit = findViewById(R.id.btnContinue);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = fname.getText().toString();
                String lastname = lname.getText().toString();
                String userEmail = usremail.getText().toString();
                String userPhone = usrphone.getText().toString();
                String CompContact = Compcontact.getText().toString();
                String GST = Gst.getText().toString();
                String designation = Designation.getText().toString();
                String CompAddress = Compaddress.getText().toString();

                FirebaseDatabase hypersync = FirebaseDatabase.getInstance();
                DatabaseReference ref = hypersync.getReference("Query");

                Company query = new Company(firstname,lastname,userEmail,userPhone,CompContact,GST,designation,CompAddress);

                ref.child(GST).setValue(query);
                Toast.makeText(getApplicationContext(), "Thank You, Our team will contact you", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SignUpAdminActivity.this,Recorded.class);
                startActivity(intent);
            }
        });


    }
}