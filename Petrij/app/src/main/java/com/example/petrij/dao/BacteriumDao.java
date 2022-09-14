package com.example.petrij.dao;
import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.petrij.Bacterium;

import java.util.List;



@Dao
public interface BacteriumDao {

    @Query("SELECT * FROM bacterium")
    List<Bacterium> getBacteriumList();

    @Query("SELECT * FROM bacterium")
    Cursor getBacteriumListCursor();

    @Query("SELECT * FROM bacterium WHERE _id = :id")        // sve iz tablice
    Cursor findByIdCursor(Integer id);

    @Query("SELECT * FROM bacterium WHERE _id = :id")
    Bacterium findById(Integer id);

    @Insert
    void insertBacterium(Bacterium bacterium);   //

    @Insert
    void insertAll(Bacterium... bacteria);

    @Update
    void updateBacterium(Bacterium bacterium); //klasa je bacterium

    @Delete
    void deleteBacterium(Bacterium bacterium);
}
