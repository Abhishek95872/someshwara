package com.coding.someshwarademo.viewModel;

/*
  Created By Abhishek
  on 13/05/2021
  for assignment in Someshwara
 */

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.coding.someshwarademo.model.DataModel;
import com.coding.someshwarademo.model.DataModelResponse;
import com.coding.someshwarademo.repository.Repo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends ViewModel {
    private static final String TAG = "HomeViewModel";

    private Repo repository;
    private MutableLiveData<ArrayList<DataModel>> pokemonList = new MutableLiveData<>();
    private LiveData<List<DataModel>> favoritePokemonList = null;

    @ViewModelInject
    public MainViewModel(Repo repository) {
        this.repository = repository;
        favoritePokemonList = repository.getFavoritePokemon();
    }

    public MutableLiveData<ArrayList<DataModel>> getPokemonList() {
        return pokemonList;
    }

    public void getPokemons(){
        repository.getPokemons()
                .subscribeOn(Schedulers.io())
                .map(new Function<DataModelResponse, ArrayList<DataModel>>() {
                    @Override
                    public ArrayList<DataModel> apply(DataModelResponse pokemonResponse) throws Throwable {
                        ArrayList<DataModel> list = pokemonResponse.getResults();
                        for(DataModel pokemon : list){
                            String url = pokemon.getUrl();
                            String[] pokemonIndex = url.split("/");
                            pokemon.setUrl("https://pokeres.bastionbot.org/images/pokemon/"+pokemonIndex[pokemonIndex.length-1] +".png");
                        }
                        Log.e(TAG, "apply: "+list.get(2).getUrl());
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> pokemonList.setValue(result),
                        error-> Log.e(TAG, "getPokemons: " + error.getMessage() ));
    }

    public void insertPokemon(DataModel pokemon){
        repository.insertPokemon(pokemon);
    }
    public void deletePokemon(String pokemonName){
        repository.deletePokemon(pokemonName);
    }

    public LiveData<List<DataModel>> getFavoritePokemonList() {
        return favoritePokemonList;
    }

    public void getFavoritePokemon(){
        favoritePokemonList = repository.getFavoritePokemon();
    }
}
