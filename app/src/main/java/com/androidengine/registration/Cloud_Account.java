package com.androidengine.registration;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class Cloud_Account extends AppCompatActivity {

    private FirebaseStorage storage;
    private StorageReference storageRef;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Инициализация Firebase
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    // Метод для загрузки ZIP-проекта в облако
    public void uploadProjectToCloud(String filePath, String projectName) {
        if (user == null) return;

        Uri file = Uri.fromFile(new File(filePath));
        // Создаем путь в облаке: users/ID_ПОЛЬЗОВАТЕЛЯ/projects/ИМЯ_ПРОЕКТА.zip
        StorageReference projectRef = storageRef.child("users/" + user.getUid() + "/projects/" + projectName + ".zip");

        UploadTask uploadTask = projectRef.putFile(file);

        uploadTask.addOnFailureListener(exception -> {
            Toast.makeText(this, "Ошибка загрузки: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
        }).addOnSuccessListener(taskSnapshot -> {
            Toast.makeText(this, "Проект " + projectName + " сохранен в облаке!", Toast.LENGTH_SHORT).show();
        });
    }

    // Метод для скачивания проекта из облака
    public void downloadProjectFromCloud(String projectName, File localFile) {
        if (user == null) return;

        StorageReference projectRef = storageRef.child("users/" + user.getUid() + "/projects/" + projectName + ".zip");

        projectRef.getFile(localFile).addOnSuccessListener(taskSnapshot -> {
            Toast.makeText(this, "Проект " + projectName + " восстановлен!", Toast.LENGTH_SHORT).show();
            // Тут добавим код для распаковки ZIP
        }).addOnFailureListener(exception -> {
            Toast.makeText(this, "Не удалось скачать проект", Toast.LENGTH_SHORT).show();
        });
    }
}

