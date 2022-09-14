package com.example.petrij;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.petrij.database.PetrijDatabase;

import java.util.concurrent.Executors;

public class UnosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unos);
    }

    private PetrijDatabase petrijDatabase;
    private Cursor bacteriumCursor ;


    public void onClickSubmit(View view) {

        EditText et1 = findViewById(R.id.uno);
        EditText et2 = findViewById(R.id.duo);

        String unos = et1.getText().toString();
        String unos2 = et2.getText().toString();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                Bacterium newOne = new Bacterium(unos, R.drawable.unknown, true, true, true, "unkown", "unknown", false, unos2
                );     //constructor
                petrijDatabase.bacteriumDao().insertBacterium(newOne);
                bacteriumCursor = petrijDatabase.bacteriumDao().getBacteriumListCursor();

            }
        });


    }


        @Override
        protected void onDestroy() {
            super.onDestroy();
            bacteriumCursor.close();
            petrijDatabase.close();



    }






}