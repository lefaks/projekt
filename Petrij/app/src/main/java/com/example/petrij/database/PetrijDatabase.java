package com.example.petrij.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.petrij.Bacterium;

import java.util.concurrent.Executors;
import com.example.petrij.dao.BacteriumDao;


@Database(entities = {Bacterium.class}, exportSchema = false, version = 1)
public abstract class PetrijDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "db_petrij";
    private static PetrijDatabase petrijDatabase;

    public static synchronized PetrijDatabase getInstance(final Context context) {
        if (petrijDatabase == null) {
            petrijDatabase = Room.databaseBuilder(context.getApplicationContext(), PetrijDatabase.class, DATABASE_NAME)
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);

                            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    String firstRow = String.format("INSERT INTO bacterium(\"name\", \"imageId\", \"gram\",\"waterc\", \"intracellular\", \"shape\",\"aero\",\"agarb\",\"zanimljivo\") VALUES(\"%s\", %d, %b , %b, %b,\"%s\",\"%s\",%b,\"%s\")",
                                            Bacterium.bacteria[0].getName(), Bacterium.bacteria[0].getImageId(), Bacterium.bacteria[0].getGram(), Bacterium.bacteria[0].getWaterc(), Bacterium.bacteria[0].getIntracellular(), Bacterium.bacteria[0].getShape(), Bacterium.bacteria[0].getAero(), Bacterium.bacteria[0].getAgarb(), Bacterium.bacteria[0].getZanimljivo());
                                    String secondRow = String.format("INSERT INTO bacterium(\"name\", \"imageId\", \"gram\",\"waterc\", \"intracellular\", \"shape\",\"aero\",\"agarb\",\"zanimljivo\") VALUES(\"%s\", %d, %b , %b, %b,\"%s\",\"%s\",%b,\"%s\")",
                                            Bacterium.bacteria[1].getName(), Bacterium.bacteria[1].getImageId(), Bacterium.bacteria[1].getGram(), Bacterium.bacteria[1].getWaterc(), Bacterium.bacteria[1].getIntracellular(), Bacterium.bacteria[1].getShape(), Bacterium.bacteria[1].getAero(), Bacterium.bacteria[1].getAgarb(), Bacterium.bacteria[1].getZanimljivo());

                                    String thirdRow = String.format("INSERT INTO bacterium(\"name\", \"imageId\", \"gram\",\"waterc\", \"intracellular\", \"shape\",\"aero\",\"agarb\",\"zanimljivo\") VALUES(\"%s\", %d, %b , %b, %b,\"%s\",\"%s\",%b,\"%s\")",

                                            Bacterium.bacteria[2].getName(), Bacterium.bacteria[2].getImageId(), Bacterium.bacteria[2].getGram(), Bacterium.bacteria[2].getWaterc(), Bacterium.bacteria[2].getIntracellular(), Bacterium.bacteria[2].getShape(), Bacterium.bacteria[2].getAero(), Bacterium.bacteria[2].getAgarb(), Bacterium.bacteria[2].getZanimljivo());

                                    db.execSQL(firstRow);
                                    db.execSQL(secondRow);
                                    db.execSQL(thirdRow);
                                }
                            });
                        }
                    })
                    .build();
        }

            return petrijDatabase;
        }

        public abstract BacteriumDao bacteriumDao ();         //obojo
    }

