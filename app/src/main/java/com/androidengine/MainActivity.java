package com.androidengine;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

// Импорты Firebase для проверки аккаунта
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

// Импорты твоих папок
import com.androidengine.registration.Registration;
import com.androidengine.gameeditor.Game_Editor;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Устанавливаем тему (здесь можно будет добавить проверку Системная/Темная/Светлая)
        setContentView(R.layout.activity_main);

        // Инициализируем доступ к аккаунтам Firebase
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        
        // 1. Проверяем, есть ли активная сессия пользователя
        FirebaseUser currentUser = mAuth.getCurrentUser();
        
        if (currentUser != null) {
            // ПОЛЬЗОВАТЕЛЬ ВОШЕЛ: Отправляем в редактор
            Intent intent = new Intent(MainActivity.this, Game_Editor.class);
            startActivity(intent);
        } else {
            // ПОЛЬЗОВАТЕЛЬ НЕ ВОШЕЛ: Отправляем на регистрацию
            Intent intent = new Intent(MainActivity.this, Registration.class);
            startActivity(intent);
        }

        // Закрываем MainActivity, чтобы пользователь не мог вернуться назад к пустому экрану загрузки
        finish();
    }
}

