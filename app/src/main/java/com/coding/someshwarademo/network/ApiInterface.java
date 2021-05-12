package com.coding.someshwarademo.network;

/*
  Created By Abhishek
  on 13/05/2021
  for assignment in Someshwara
 */

import com.coding.someshwarademo.model.DataModelResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("pokemon")
    Observable<DataModelResponse> getPokemons();
}
