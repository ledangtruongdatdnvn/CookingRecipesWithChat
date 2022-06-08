package com.midterm.foodrecipesandconnection.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;

import com.midterm.foodrecipesandconnection.Models.User;
import com.midterm.foodrecipesandconnection.Utilities.Constants;
import com.midterm.foodrecipesandconnection.databinding.ActivityUserInfoBinding;

public class UserInfoActivity extends AppCompatActivity {
    private User user;
    private ActivityUserInfoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = (User) getIntent().getSerializableExtra(Constants.KEY_USER);
        binding.imageProfile.setImageBitmap(getBitmapFromEncodedString(user.image));
        binding.textViewUserName.setText(user.name);
        binding.textViewUserID.setText(user.id);
    }
    private Bitmap getBitmapFromEncodedString(String encodedImage) {
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}