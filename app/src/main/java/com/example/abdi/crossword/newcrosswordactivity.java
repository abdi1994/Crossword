package com.example.abdi.crossword;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.abdi.crossword.R.id.spinner1;


public class newcrosswordactivity extends ActionBarActivity {
    static final String STATE_USER = "user";
    private String mUser;
    DatabaseHelper myDb;
    DatabaseHelper myDB;
    Button btnviewAll, btnSave;
    ListView listView ;
    EditText text;
    private GridEngine gridEngine;
    private FrameLayout gridFrame;
    public static final String TEXTFILE = "Crossword.txt";
    public static final String DEBUGTAG = "AM";
    public static final String SAVED = "FileSaved";
    private ItemArrayAdapter itemArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newcrossword_layout);
        myDb = new DatabaseHelper(this);


        btnviewAll = (Button)findViewById(R.id.button_viewAll);
        btnSave = (Button)findViewById(R.id.button_Save);
        text = (EditText)findViewById(R.id.et_simple);



        gridEngine = new GridEngine();
        gridFrame = (FrameLayout)findViewById(R.id.gameframe);
        gridFrame.addView(new GridGraphics(this, gridEngine));
        gridFrame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });

        addSaveButtonListener();

        SharedPreferences pres = getPreferences(MODE_PRIVATE);
        Boolean filesaved = pres.getBoolean(SAVED, false);
        if (filesaved) {
            loadSavedFile();

        }



        viewAll();

        InputStream inputStream = getResources().openRawResource(R.raw.hint);
        CSVReader csv = new CSVReader(inputStream);
        List<String[]> hintList = csv.read();

        Spinner dropdown = (Spinner)findViewById(spinner1);
        String[] items = new String[]{"1", "2", "three"};
        String[] testing = convert(hintList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, testing);
        dropdown.setAdapter(adapter);



        if (savedInstanceState != null) {
            // Restore value of members from saved state
            mUser = savedInstanceState.getString(STATE_USER);
        } else {
            // Probably initialize members with default values for a new instance
            mUser = "NewUser";
        }


    }


    private void loadSavedFile() {

        try {

            FileInputStream fis = openFileInput(TEXTFILE);

            BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(fis)));
            EditText editText = (EditText) findViewById(R.id.et_simple);
            String line;
            while ((line = reader.readLine()) != null) {
                editText.append(line);
                editText.append("");
            }

            fis.close();
        } catch (Exception e) {
            Log.d(DEBUGTAG, "Unable to read file");
        }


    }

    private void addSaveButtonListener() {
        Button saveBtn = (Button) findViewById(R.id.button_Save);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText editText = (EditText) findViewById(R.id.et_simple);

                String text = editText.getText().toString();

                try {
                    Toast.makeText(newcrosswordactivity.this, "Note Saved", Toast.LENGTH_LONG).show();
                    FileOutputStream fos = openFileOutput(TEXTFILE, Context.MODE_PRIVATE);
                    fos.write(text.getBytes());
                    fos.close();

                    SharedPreferences prefs = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean(SAVED, true);
                    editor.commit();
                    Intent intent = new Intent(newcrosswordactivity.this, menuactivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.d(DEBUGTAG, "Unable to save file");

                }

            }
        });
    }


    public void onItemSelected(AdapterView<?> spinner1, View view, int pos, long id)
    {

        //text.setText(spinner1.getSelectedItem());

    }




    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Word :" + res.getString(1) + "\n");
                            buffer.append("Hint :" + res.getString(2) + "\n\n");

                        }

                        // Show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );}





    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(STATE_USER, mUser);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    static String[] convert(List<String[]> from) {
        ArrayList<String> list = new ArrayList<String>();
        for (String[] strings : from) {
            Collections.addAll(list, strings);
        }
        return list.toArray(new String[list.size()]);
    }


}