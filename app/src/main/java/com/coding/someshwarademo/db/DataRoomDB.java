package com.coding.someshwarademo.db;

/*
  Created By Abhishek
  on 13/05/2021
  for assignment in Someshwara
 */

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.coding.someshwarademo.model.DataModel;

@Database(entities = {DataModel.class}, version = 2, exportSchema = false)
public abstract class DataRoomDB extends RoomDatabase {
    public abstract RoomDao roomDao();
}
