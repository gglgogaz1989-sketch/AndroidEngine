package com.androidengine.registration;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.androidengine.R;
import com.androidengine.gameeditor.Game_Editor;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    private EditText emailField, passwordField;
    private Button btnRegister, btnGoToGoogle;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Инициализируем Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Привязываем элементы интерфейса
        emailField = findViewById(R.id.et_email);
        passwordField = findViewById(R.id.et_password);
        btnRegister = findViewById(R.id.btn_register_submit);
        btnGoToGoogle = findViewById(R.id.btn_login_google);

        // Кнопка обычной регистрации
        btnRegister.setOnClickListener(v -> {
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();

            if (!email.isEmpty() && !password.isEmpty()) {
                createNewUser(email, password);
            } else {
                Toast.makeText(this, "Заполни все поля!", Toast.LENGTH_SHORT).show();
            }
        });

        // Кнопка перехода на вход через Google
        btnGoToGoogle.setOnClickListener(v -> {
            startActivity(new Intent(this, Connect_From_Google.class));
        });
    }

    private void createNewUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    // Ура! Аккаунт создан, идем в редактор
                    Toast.makeText(this, "Регистрация успешна!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Registration.this, Game_Editor.class));
                    finish();
                } else {
                    // Ошибка (например, слабый пароль или почта уже занята)
                    Toast.makeText(this, "Ошибка: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            });
    }
}

