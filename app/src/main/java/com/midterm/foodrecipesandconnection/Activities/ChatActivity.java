package com.midterm.foodrecipesandconnection.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.midterm.foodrecipesandconnection.Models.User;
import com.midterm.foodrecipesandconnection.R;
import com.midterm.foodrecipesandconnection.Utilities.Constants;
import com.midterm.foodrecipesandconnection.databinding.ActivityChatBinding;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    private User receiverUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
        loadReceiverDetails();
    }

    private void loadReceiverDetails() {
        receiverUser = (User) getIntent().getSerializableExtra(Constants.KEY_USER);
        binding.textName.setText(receiverUser.name);
    }

    private void setListeners() {
        binding.imageBack.setOnClickListener(v -> onBackPressed());
    }
}