package com.coding.someshwarademo.db;

/*
  Created By Abhishek
  on 13/05/2021
  for assignment in Someshwara
 */

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.coding.someshwarademo.model.DataModel;

import java.util.List;

@Dao
public interface RoomDao {

    @Insert
    void insertPokemon(DataModel pokemon);

    @Query("DELETE FROM favorite_table WHERE name = :pokemonName")
    void deletePokemon(String pokemonName);

    @Query("DELETE FROM favorite_table")
    void deleteAll();

    @Query("SELECT * FROM favorite_table")
    LiveData<List<DataModel>> getFavoritePokemons();
}
