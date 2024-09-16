package com.example.nguyenductaidemo1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private ListView listViewUsers;
    private Button btnAddUser;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etFullName;
    private EditText etEmail;

    private List<User> users = new ArrayList<>();
    private ArrayAdapter<User> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        listViewUsers = findViewById(R.id.listViewUsers);
        btnAddUser = findViewById(R.id.btnAddUser);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);

        users.addAll(UserData.getUsers()); // Load existing users

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        listViewUsers.setAdapter(adapter);

        btnAddUser.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            String fullName = etFullName.getText().toString();
            String email = etEmail.getText().toString();

            if (!username.isEmpty() && !password.isEmpty()) {
                User newUser = new User(username, password, fullName, email);
                users.add(newUser);
                adapter.notifyDataSetChanged(); // Refresh the list
                clearInputs(); // Clear input fields
            }
        });

        listViewUsers.setOnItemLongClickListener((parent, view, position, id) -> {
            User userToDelete = users.get(position);
            users.remove(userToDelete);
            adapter.notifyDataSetChanged(); // Refresh the list
            return true;
        });
    }

    private void clearInputs() {
        etUsername.setText("");
        etPassword.setText("");
        etFullName.setText("");
        etEmail.setText("");
    }
}
