package com.example.lesson_roomjava.db.user;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Класс сущности пользователя для базы данных Room
@Entity(tableName = "user_table")
public class User {
    // Поле ID пользователя (генерируется автоматически)
    @PrimaryKey(autoGenerate = true)
    private int id;
    // Поле имени пользователя
    private String name;

    // Конструктор класса
    public User(String name) {
        this.name = name;
    }

    // Метод для получения ID пользователя
    public int getId() {
        return id;
    }

    // Метод для получения имени пользователя
    public String getName() {
        return name;
    }

    // Метод для установки ID пользователя
    public void setId(int id) {
        this.id = id;
    }

    // Метод для установки имени пользователя
    public void setName(String name) {
        this.name = name;
    }
}
