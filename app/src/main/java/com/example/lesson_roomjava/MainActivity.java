package com.example.lesson_roomjava;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson_roomjava.db.user.User;
import com.example.lesson_roomjava.recyclerview.UserListAdapter;
import com.example.lesson_roomjava.viewmodel.UserViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Объявление ViewModel и элементов пользовательского интерфейса
    private UserViewModel userViewModel;
    private EditText editTextUserName, editTextUserId;
    private Button addButton, deleteButton;
    private RecyclerView recyclerView;
    private UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация ViewModel
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Привязка элементов пользовательского интерфейса к переменным
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextUserId = findViewById(R.id.editTextUserId);
        addButton = findViewById(R.id.addButton);
        deleteButton = findViewById(R.id.deleteButton);
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new UserListAdapter(new UserListAdapter.UserDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Поиск элементов пользовательского интерфейса для поиска
        Button searchButton = findViewById(R.id.searchButton);
        EditText editTextSearchUserId = findViewById(R.id.editTextSearchUserId);
        TextView searchResultView = findViewById(R.id.searchResultView);

        // Обработка нажатия кнопки "Добавить"
        addButton.setOnClickListener(view -> {
            String userName = editTextUserName.getText().toString();
            if (!userName.isEmpty()) {
                userViewModel.insert(new User(userName));
            } else {
                Toast.makeText(MainActivity.this, "Пожалуйста, введите имя", Toast.LENGTH_LONG).show();
            }
        });

        // Обработка нажатия кнопки "Поиск"
        searchButton.setOnClickListener(view -> {
            try {
                int userId = Integer.parseInt(editTextSearchUserId.getText().toString());
                userViewModel.findUserById(userId).observe(this, user -> {
                    if (user != null) {
                        searchResultView.setText("Пользователь найден: " + user.getName());
                    } else {
                        searchResultView.setText("Пользователь не найден");
                    }
                });
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Пожалуйста, введите корректный ID", Toast.LENGTH_LONG).show();
            }
        });

        // Обработка нажатия кнопки "Удалить"
        deleteButton.setOnClickListener(view -> {
            try {
                int userId = Integer.parseInt(editTextUserId.getText().toString());
                userViewModel.deleteById(userId);
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Пожалуйста, введите корректный ID", Toast.LENGTH_LONG).show();
            }
        });

        // Наблюдение за LiveData из ViewModel для обновления RecyclerView
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                // Обновление кэша пользователей в адаптере.
                adapter.submitList(users);
            }
        });
    }
}