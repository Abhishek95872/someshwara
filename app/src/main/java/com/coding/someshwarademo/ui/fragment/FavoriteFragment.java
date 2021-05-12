package com.coding.someshwarademo.ui.fragment;


/*
  Created By Abhishek
  on 13/05/2021
  for assignment in Someshwara
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.coding.someshwarademo.databinding.FavoritesBinding;
import com.coding.someshwarademo.model.DataModel;
import com.coding.someshwarademo.ui.adapter.CustomAdapter;
import com.coding.someshwarademo.viewModel.MainViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavoriteFragment extends Fragment {
    private FavoritesBinding binding;
    private MainViewModel viewModel;
    private CustomAdapter adapter;
    private ArrayList<DataModel> pokemonList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FavoritesBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        initRecyclerView();
        setUpItemTouchHelper();
        observeData();
    }

    private void observeData() {
        viewModel.getFavoritePokemonList().observe(getViewLifecycleOwner(), pokemons -> {

            if(pokemons == null || pokemons.size() == 0)
                binding.noFavoritesText.setVisibility(View.VISIBLE);
            else{
                ArrayList<DataModel> list = new ArrayList<>();
                list.addAll(pokemons);
                adapter.updateList(list);
            }
        });
    }

    private void setUpItemTouchHelper() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedPokemonPosition = viewHolder.getAdapterPosition();
                DataModel pokemon = adapter.getPokemonAt(swipedPokemonPosition);
                viewModel.deletePokemon(pokemon.getName());
                Toast.makeText(getContext(),"Pokemon removed from favorites.",Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.favoritesRecyclerView);
    }


    private void initRecyclerView() {
        binding.favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CustomAdapter(getContext(),pokemonList);
        binding.favoritesRecyclerView.setAdapter(adapter);
    }
}
