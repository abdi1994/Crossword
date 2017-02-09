package com.example.abdi.crossword;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class menuactivity extends AppCompatActivity implements View.OnClickListener {
    //buttons
    Button btnNewCrossword;
    Button btnLoadCrossword;

    //database
    DatabaseHelper myDb;
    DatabaseHelper myDB;

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);

        myDb = new DatabaseHelper(this);


        // button created
        btnNewCrossword = (Button) findViewById(R.id.buttonNewCrossword);
        //btnLoadCrossword
        btnLoadCrossword = (Button) findViewById(R.id.buttonLoadCrossword);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, loginactivity.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        //displaying logged in user name
        textViewUserEmail.setText("Welcome "+user.getEmail());

        //adding listener to button
        buttonLogout.setOnClickListener(this);

        //Adding button functionality
        NewCrossword();
        LoadCrossword();
    }

    @Override
    public void onClick(View view) {
        //if logout is pressed
        if(view == buttonLogout){
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, loginactivity.class));
        }
    }

    public void NewCrossword() {
    btnNewCrossword.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(menuactivity.this, GameActivity.class));
                }
            }
    );


    }

    public void LoadCrossword() {
        btnLoadCrossword.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent load = new Intent(menuactivity.this, GameActivity.class);
                        load.putExtra("load", true);
                        startActivity(load);
                    }
                }
        );


    }






}