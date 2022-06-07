package com.midterm.foodrecipesandconnection.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.midterm.foodrecipesandconnection.View.UserAdapter;
import com.midterm.foodrecipesandconnection.Listeners.UserListener;
import com.midterm.foodrecipesandconnection.Models.User;
import com.midterm.foodrecipesandconnection.Utilities.Constants;
import com.midterm.foodrecipesandconnection.Utilities.PreferenceManager;
import com.midterm.foodrecipesandconnection.databinding.ActivityUsersBinding;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends BaseActivity implements UserListener {

    private ActivityUsersBinding binding;
    private PreferenceManager preferenceManager;
    List<User> users;
    User userFoundedByEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        setListeners();
        getUsers();
        binding.searchViewEmail.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String email) {
                boolean isFound = false;
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).email.equals(email)) {
                        userFoundedByEmail = new User();
                        userFoundedByEmail.name = users.get(i).name;
                        userFoundedByEmail.email = users.get(i).email;
                        userFoundedByEmail.image = users.get(i).image;
                        userFoundedByEmail.token = users.get(i).token;
                        userFoundedByEmail.id = users.get(i).id;
                        users.clear();
                        users.add(userFoundedByEmail);
                        isFound = true;
                        break;
                    }
                }
                if (isFound) {
                    UserAdapter userAdapter = new UserAdapter(users, new UserListener() {
                        @Override
                        public void onUserClicked(User user) {
                            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                            intent.putExtra(Constants.KEY_USER, userFoundedByEmail);
                            startActivity(intent);
                            finish();
                        }
                    });
                    binding.usersRecyclerView.setAdapter(userAdapter);
                    binding.usersRecyclerView.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(UsersActivity.this, "Not Found", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")) {
                    getUsers();
                }
                return false;
            }
        });
    }

    private void setListeners() {
        binding.imageBack.setOnClickListener(v -> onBackPressed());
    }

    private void getUsers() {
        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_USERS)
                .get()
                .addOnCompleteListener(task -> {
                    loading(false);
                    String currentUserId = preferenceManager.getString(Constants.KEY_USER_ID);
                    if (task.isSuccessful() && task.getResult() != null) {
                        users = new ArrayList<>();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                            if (currentUserId.equals(queryDocumentSnapshot.getId())) {
                                continue;
                            }
                            User user = new User();
                            user.name = queryDocumentSnapshot.getString(Constants.KEY_NAME);
                            user.email = queryDocumentSnapshot.getString(Constants.KEY_EMAIL);
                            user.image = queryDocumentSnapshot.getString(Constants.KEY_IMAGE);
                            user.token = queryDocumentSnapshot.getString(Constants.KEY_FCM_TOKEN);
                            user.id = queryDocumentSnapshot.getId();
                            users.add(user);
                        }
                        if (users.size() > 0) {
                            UserAdapter userAdapter = new UserAdapter(users, this);
                            binding.usersRecyclerView.setAdapter(userAdapter);
                            binding.usersRecyclerView.setVisibility(View.VISIBLE);
                        } else {
                            showErrorMessage();
                        }
                    } else {
                        showErrorMessage();
                    }
                });
    }

    private void showErrorMessage() {
        binding.textErrorMessage.setText(String.format("%s", "No user available"));
        binding.textErrorMessage.setVisibility(View.VISIBLE);
    }

    private void loading(Boolean isLoading) {
        if (isLoading) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onUserClicked(User user) {
        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        intent.putExtra(Constants.KEY_USER, user);
        startActivity(intent);
        finish();
    }
}