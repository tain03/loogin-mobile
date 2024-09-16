package com.example.nguyenductaidemo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private Button btnAddUser;
    private Button btnEditUser;
    private Button btnDeleteUser;
    private Button btnViewUser;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvWelcome = findViewById(R.id.tvWelcome);
        btnAddUser = findViewById(R.id.btnAddUser);
        btnEditUser = findViewById(R.id.btnEditUser);
        btnDeleteUser = findViewById(R.id.btnDeleteUser);
        btnViewUser = findViewById(R.id.btnViewUsers);
        btnLogout = findViewById(R.id.btnLogout);

        String username = getIntent().getStringExtra("username");
        tvWelcome.setText("Welcome, " + username);

        btnAddUser.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AddUserActivity.class);
            startActivity(intent);
        });

        btnEditUser.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SearchUserActivity.class);
            startActivity(intent);
        });

        btnDeleteUser.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, DeleteUserActivity.class);
            startActivity(intent);
        });

        btnViewUser.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, UserListActivity.class);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
