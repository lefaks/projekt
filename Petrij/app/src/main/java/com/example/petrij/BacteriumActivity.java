package com.example.petrij;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.Executors;
import com.example.petrij.database.PetrijDatabase;



public class BacteriumActivity extends AppCompatActivity {

    public static final String EXTRA_BACTERIUMID = "bacteriumId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bacterium);

        int bacteriumId = getIntent().getExtras().getInt(EXTRA_BACTERIUMID); //definiran varijabla kokID

        final Handler handler = new Handler();
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                PetrijDatabase petrijDatabase = PetrijDatabase.getInstance(BacteriumActivity.this);
                final Bacterium bacterium = petrijDatabase.bacteriumDao().findById(bacteriumId);     //poziva se bacteriumDao??
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onGetBacterium(bacterium);
                    }        //šaljem mju varijablu koju sam ranije definirala
                });
            }
        });
    }

    private void onGetBacterium(Bacterium bacterium) {
        TextView nameLabel = findViewById(R.id.name);
        nameLabel.setText(bacterium.getName());

        TextView descLabel = findViewById(R.id.description);
        descLabel.setText(bacterium.getZanimljivo());      //get je iz klase Bacterium logično

        ImageView image = findViewById(R.id.photo);
        image.setImageResource(bacterium.getImageId());
        image.setContentDescription(bacterium.getName());  //metode tog objekta
    }
}