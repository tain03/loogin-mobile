package com.example.nguyenductaidemo1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditUserActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private EditText etFullName;
    private EditText etEmail;
    private Button btnSave;
    private Button btnCancel;
    private String originalUsername;
    private String originalEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        originalUsername = getIntent().getStringExtra("username");
        User user = getUserByUsername(originalUsername);
        if (user != null) {
            etUsername.setText(user.getUsername());
            etPassword.setText(user.getPassword());
            etFullName.setText(user.getFullName());
            etEmail.setText(user.getEmail());
            originalEmail = user.getEmail();
        }

        btnSave.setOnClickListener(v -> saveUser());
        btnCancel.setOnClickListener(v -> finish());
    }

    private void saveUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String fullName = etFullName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra xem username mới có trùng với người khác không
        if (!username.equals(originalUsername) && UserData.isUsernameTaken(username)) {
            Toast.makeText(this, "Username already exists!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra xem email mới có trùng với người khác không
        if (!email.equals(originalEmail) && UserData.isEmailTaken(email)) {
            Toast.makeText(this, "Email already exists!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Cập nhật thông tin người dùng
        User updatedUser = new User(username, password, fullName, email);
        if (UserData.updateUser(updatedUser)) {
            Toast.makeText(this, "User updated successfully!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error updating user!", Toast.LENGTH_SHORT).show();
        }
    }

    private User getUserByUsername(String username) {
        for (User user : UserData.getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
