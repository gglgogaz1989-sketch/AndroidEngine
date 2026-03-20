package com.androidengine.friends;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.androidengine.R;
import java.util.ArrayList;

public class Friends extends AppCompatActivity {

    private ListView friendsList;
    private ArrayList<String> friendsNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        friendsList = findViewById(R.id.lv_friends);
        friendsNames = new ArrayList<>();

        // Здесь будет загрузка списка друзей из Firebase Database
        friendsNames.add("Разработчик 1");
        friendsNames.add("Мой друг");

        // Код для отображения списка в ListView...
    }
}

