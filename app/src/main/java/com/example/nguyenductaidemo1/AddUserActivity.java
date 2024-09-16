package com.example.nguyenductaidemo1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddUserActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private EditText etFullName;
    private EditText etEmail;
    private Button btnAdd;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        btnAdd = findViewById(R.id.btnAdd);
        btnReset = findViewById(R.id.btnReset);

        btnAdd.setOnClickListener(v -> addUser());
        btnReset.setOnClickListener(v -> resetFields());
    }

    private void addUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String fullName = etFullName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra username hoặc email đã tồn tại chưa
        if (UserData.isUsernameTaken(username)) {
            Toast.makeText(this, "Username already exists!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (UserData.isEmailTaken(email)) {
            Toast.makeText(this, "Email already exists!", Toast.LENGTH_SHORT).show();
            return;
        }

        User newUser = new User(username, password, fullName, email);
        if (UserData.addUser(newUser)) {
            Toast.makeText(this, "User added successfully!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error adding user!", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetFields() {
        etUsername.setText("");
        etPassword.setText("");
        etFullName.setText("");
        etEmail.setText("");
    }
}
