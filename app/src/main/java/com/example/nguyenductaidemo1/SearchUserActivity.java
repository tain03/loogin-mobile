package com.example.nguyenductaidemo1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchUserActivity extends AppCompatActivity {

    private EditText etSearch;
    private ListView listViewUsers;
    private ArrayAdapter<User> adapter;
    private List<User> allUsers = new ArrayList<>();
    private List<User> filteredUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);

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
            Intent intent = new Intent(SearchUserActivity.this, EditUserActivity.class);
            intent.putExtra("username", selectedUser.getUsername());
            startActivity(intent);
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
}
