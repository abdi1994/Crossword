package com.example.abdi.crossword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void Test() {
        int i = 1 + 1;
        int x = 2 + 2;
        int z = x + i;
    }
}
