package com.example.nguyenductaidemo1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteUserActivity extends AppCompatActivity {

    private EditText etSearch;
    private ListView listViewUsers;
    private ArrayAdapter<User> adapter;
    private List<User> allUsers = new ArrayList<>();
    private List<User> filteredUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        etSearch = findViewById(R.id.etSearch);
        listViewUsers = findViewById(R.id.listViewUsers);

        allUsers.addAll(UserData.getUsers()); // Load all users
        filteredUsers.addAll(allUsers);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, android.R.id.text1, filteredUsers);
        listViewUsers.setAdapter(adapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not used
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterUsers(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not used
            }
        });

        listViewUsers.setOnItemClickListener((parent, view, position, id) -> {
            User selectedUser = filteredUsers.get(position);
            showDeleteConfirmationDialog(selectedUser);
        });
    }

    private void filterUsers(String query) {
        filteredUsers.clear();
        if (query.isEmpty()) {
            filteredUsers.addAll(allUsers);
        } else {
            filteredUsers.addAll(allUsers.stream()
                    .filter(user -> user.getUsername().toLowerCase().contains(query.toLowerCase()))
                    .collect(Collectors.toList()));
        }
        adapter.notifyDataSetChanged();
    }

    private void showDeleteConfirmationDialog(User user) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Delete")
                .setMessage("Are you sure you want to delete user: " + user.getUsername() + "?")
                .setPositiveButton("Yes", (dialog, which) -> deleteUser(user))
                .setNegativeButton("No", null)
                .show();
    }

    private void deleteUser(User user) {
        if (UserData.getUsers().remove(user)) {
            Toast.makeText(DeleteUserActivity.this, "User deleted successfully!", Toast.LENGTH_SHORT).show();
            filterUsers(etSearch.getText().toString()); // Refresh the list
        } else {
            Toast.makeText(DeleteUserActivity.this, "User not found!", Toast.LENGTH_SHORT).show();
        }
    }
}
