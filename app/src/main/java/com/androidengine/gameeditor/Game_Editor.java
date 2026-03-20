package com.androidengine.gameeditor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.androidengine.R;
import com.androidengine.friends.Friends;

public class Game_Editor extends AppCompatActivity {

    private ImageButton btnSpawn, btnImport, btnBuild, btnPlay, btnStop, btnMenu;
    private String currentLang, gameType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_editor);

        // 1. Инициализация твоих кнопок по ID
        initButtons();

        // 2. Загрузка настроек проекта
        loadProjectSettings();

        // --- ЛОГИКА КНОПОК ---

        // Серый треугольник (Спавн объектов)
        btnSpawn.setOnClickListener(v -> {
            Toast.makeText(this, "Открыть список объектов для спавна", Toast.LENGTH_SHORT).show();
            // Тут будет вызов диалогового окна с выбором (Куб, Спрайт, Свет)
        });

        // Стрелка вверх (Импорт ресурсов)
        btnImport.setOnClickListener(v -> {
            Toast.makeText(this, "Выбор файла: Модель или Спрайт", Toast.LENGTH_SHORT).show();
        });

        // Стрелка вниз (Сборка APK)
        btnBuild.setOnClickListener(v -> {
            Toast.makeText(this, "Начало сборки проекта в APK...", Toast.LENGTH_LONG).show();
        });

        // Зеленая кнопка (Старт)
        btnPlay.setOnClickListener(v -> {
            btnPlay.setVisibility(View.GONE);
            btnStop.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Запуск игры (" + currentLang + " | " + gameType + ")", Toast.LENGTH_SHORT).show();
            // Здесь запускается интерпретатор Lua или Java-движок
        });

        // Красная кнопка (Стоп)
        btnStop.setOnClickListener(v -> {
            btnStop.setVisibility(View.GONE);
            btnPlay.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Игра остановлена", Toast.LENGTH_SHORT).show();
        });

        // Три точки (Настройки и Язык)
        btnMenu.setOnClickListener(v -> {
            startActivity(new Intent(this, Set_Programming_Language.class));
        });
    }

    private void initButtons() {
        btnSpawn = findViewById(R.id.btn_spawn);
        btnImport = findViewById(R.id.btn_import);
        btnBuild = findViewById(R.id.btn_build);
        btnPlay = findViewById(R.id.btn_play);
        btnStop = findViewById(R.id.btn_stop);
        btnMenu = findViewById(R.id.btn_menu_more);
        
        // По умолчанию кнопка "Стоп" скрыта
        btnStop.setVisibility(View.GONE);
    }

    private void loadProjectSettings() {
        SharedPreferences prefs = getSharedPreferences("ProjectConfig", MODE_PRIVATE);
        currentLang = prefs.getString("selected_lang", "Lua");
        gameType = prefs.getString("game_type", "2D");
    }
          }

