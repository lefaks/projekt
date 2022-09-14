package com.example.petrij;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.petrij.database.PetrijDatabase;

import java.util.concurrent.Executors;

public class MicrobiListActivity extends AppCompatActivity {

    private PetrijDatabase petrijDatabase;  //deklariramo tu
    private Cursor bacteriumCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microbi_list);

        /*
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                Bacterium newOne = new Bacterium();
                petrijDatabase.bacteriumDao().insertBacterium(newOne);
                bacteriumCursor =petrijDatabase.bacteriumDao().getBacteriumListCursor();  //dohvaćam kursor na bacteria list

            }
        });*/

        final Handler handler = new Handler();
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                petrijDatabase = PetrijDatabase.getInstance(MicrobiListActivity.this);
                bacteriumCursor =petrijDatabase.bacteriumDao().getBacteriumListCursor();  //dohvaćam kursor na bacteria list
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onGetCursor(bacteriumCursor);
                    }
                });
            }
        });
    }

    public void onClickDoneFAB(View v) {
        Intent intent0 = new Intent(MicrobiListActivity.this, UnosActivity.class);
        startActivity(intent0);

    }


    //što se događa kad dobijem cursor
    private void onGetCursor(Cursor bacteriumCursor) {
        ListView listBacteria = findViewById(R.id.microbiList);  //id je iz layouta -tamo mu je pridodan id  -ovaj id više ne postoji
        listBacteria.setAdapter(new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                bacteriumCursor,
                new String[]{"name"},
                new int[]{android.R.id.text1},
                0
        ));

        //što će se dogodit kad kliknemo na stavku
        listBacteria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MicrobiListActivity.this, BacteriumActivity.class);
                intent.putExtra(BacteriumActivity.EXTRA_BACTERIUMID, (int) id);
                startActivity(intent);
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
