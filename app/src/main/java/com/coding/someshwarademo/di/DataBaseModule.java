package com.coding.someshwarademo.di;

/*
  Created By Abhishek
  on 13/05/2021
  for assignment in Someshwara
 */

import android.app.Application;

import androidx.room.Room;

import com.coding.someshwarademo.db.DataRoomDB;
import com.coding.someshwarademo.db.RoomDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DataBaseModule {

    @Provides
    @Singleton
    public static DataRoomDB providePokemonDB(Application application){
        return Room.databaseBuilder(application,DataRoomDB.class,"Favorite Database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static RoomDao providePokeDao(DataRoomDB pokemonDB){
        return pokemonDB.roomDao();
    }
}
