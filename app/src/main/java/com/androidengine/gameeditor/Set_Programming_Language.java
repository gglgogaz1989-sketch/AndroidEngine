package com.androidengine.gameeditor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.androidengine.R;

public class Set_Programming_Language extends AppCompatActivity {

    private RadioGroup radioGroupLanguages;
    private TextView tvDescription;
    private SharedPreferences prefs;
    
    // Ключи для сохранения настроек
    private static final String PREFS_NAME = "ProjectConfig";
    private static final String KEY_LANG = "selected_lang";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_language);

        // Инициализация UI
        radioGroupLanguages = findViewById(R.id.rg_languages);
        tvDescription = findViewById(R.id.tv_lang_description);
        prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Загружаем ранее выбранный язык, чтобы RadioButton сразу стоял где нужно
        loadSavedLanguage();

        // Слушатель изменения выбора
        radioGroupLanguages.setOnCheckedChangeListener((group, checkedId) -> {
            String selectedLang = "Lua";
            String description = "";

            // Проверяем, какая кнопка нажата по ID
            if (checkedId == R.id.rb_lua) {
                selectedLang = "Lua";
                description = "Lua: Легкий и быстрый скриптовый язык. Идеально для логики мобов и триггеров.";
            } else if (checkedId == R.id.rb_python) {
                selectedLang = "Python";
                description = "Python: Понятный код, отлично подходит для сложных систем ИИ.";
            } else if (checkedId == R.id.rb_java) {
                selectedLang = "Java";
                description = "Java: Нативный код. Максимальная производительность для тяжелых вычислений.";
            }

            // Обновляем описание на экране
            tvDescription.setText(description);
            
            // Сохраняем выбор в память устройства
            saveLanguage(selectedLang);
            
            Toast.makeText(this, "Язык изменен на " + selectedLang, Toast.LENGTH_SHORT).show();
        });
    }

    private void saveLanguage(String lang) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_LANG, lang);
        editor.apply(); // Сохраняем асинхронно, чтобы не тормозить приложение
    }

    private void loadSavedLanguage() {
        String savedLang = prefs.getString(KEY_LANG, "Lua"); // По умолчанию Lua

        if (savedLang.equals("Lua")) {
            ((RadioButton) findViewById(R.id.rb_lua)).setChecked(true);
            tvDescription.setText("Lua: Легкий и быстрый скриптовый язык.");
        } else if (savedLang.equals("Python")) {
            ((RadioButton) findViewById(R.id.rb_python)).setChecked(true);
            tvDescription.setText("Python: Понятный код для сложных систем.");
        } else if (savedLang.equals("Java")) {
            ((RadioButton) findViewById(R.id.rb_java)).setChecked(true);
            tvDescription.setText("Java: Нативный код и высокая скорость.");
        }
    }

    // Метод для кнопки "Назад" в интерфейсе (если добавишь её в XML)
    public void onBackClick(View view) {
        finish(); // Закрывает текущее окно и возвращает в редактор
    }
}
