package com.coding.someshwarademo.repository;

/*
  Created By Abhishek
  on 13/05/2021
  for assignment in Someshwara
 */

import androidx.lifecycle.LiveData;

import com.coding.someshwarademo.db.RoomDao;
import com.coding.someshwarademo.model.DataModel;
import com.coding.someshwarademo.model.DataModelResponse;
import com.coding.someshwarademo.network.ApiInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Repo {

    private RoomDao pokeDao;
    private ApiInterface apiService;

    @Inject
    public Repo(RoomDao pokeDao, ApiInterface apiService) {
        this.pokeDao = pokeDao;
        this.apiService = apiService;
    }


    public Observable<DataModelResponse> getPokemons(){
        return apiService.getPokemons();
    }

    public void insertPokemon(DataModel pokemon){
        pokeDao.insertPokemon(pokemon);
    }

    public void deletePokemon(String pokemonName){
        pokeDao.deletePokemon(pokemonName);
    }

    public void deleteAll(){
        pokeDao.deleteAll();
    }

    public LiveData<List<DataModel>> getFavoritePokemon(){
        return pokeDao.getFavoritePokemons();
    }
}
