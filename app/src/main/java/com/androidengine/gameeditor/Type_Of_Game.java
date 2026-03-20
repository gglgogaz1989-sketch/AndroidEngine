package com.androidengine.gameeditor;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.androidengine.R;

public class Type_Of_Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_game);

        // Кнопка выбора 2D
        findViewById(R.id.btn_select_2d).setOnClickListener(v -> {
            setGameType("2D");
        });

        // Кнопка выбора 3D
        findViewById(R.id.btn_select_3d).setOnClickListener(v -> {
            setGameType("3D");
        });
    }

    private void setGameType(String type) {
        getSharedPreferences("ProjectConfig", MODE_PRIVATE)
            .edit()
            .putString("game_type", type)
            .apply();
            
        finish(); // Возвращаемся в редактор после выбора
    }
}

