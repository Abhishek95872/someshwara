package com.coding.someshwarademo.ui;

/*
  Created By Abhishek
  on 13/05/2021
  for assignment in Someshwara
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.coding.someshwarademo.R;
import com.coding.someshwarademo.databinding.ActivityMainBinding;
import com.coding.someshwarademo.ui.fragment.FavoriteFragment;
import com.coding.someshwarademo.ui.fragment.HomeFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private boolean isFavoriteListVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment())
                .commit();

        binding.changeFragment.setOnClickListener(view -> {
            if (isFavoriteListVisible) {
                isFavoriteListVisible = false;
                binding.changeFragment.setText("Favorites");
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment())
                        .commit();
            } else {
                isFavoriteListVisible = true;
                binding.changeFragment.setText("Home");
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new FavoriteFragment())
                        .commit();
            }
        });
    }
}