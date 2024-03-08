package com.example.lesson_roomjava.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson_roomjava.R;
import com.example.lesson_roomjava.db.user.User;

public class UserListAdapter extends ListAdapter<User, UserListAdapter.UserViewHolder> {

    // Конструктор класса
    public UserListAdapter(@NonNull DiffUtil.ItemCallback<User> diffCallback) {
        super(diffCallback);
    }

    // Создание ViewHolder
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return UserViewHolder.create(parent);
    }

    // Привязка данных к ViewHolder
    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User current = getItem(position);
        holder.bind(current.getName());
    }

    // ViewHolder для отображения пользовательских данных
    static class UserViewHolder extends RecyclerView.ViewHolder {
        private final TextView userNameItemView;

        private UserViewHolder(View itemView) {
            super(itemView);
            userNameItemView = itemView.findViewById(R.id.textView);
        }

        // Привязка данных к элементам пользовательского интерфейса
        public void bind(String text) {
            userNameItemView.setText(text);
        }

        // Создание ViewHolder
        static UserViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item, parent, false);
            return new UserViewHolder(view);
        }
    }

    // Класс для определения изменений между объектами User
    public static class UserDiff extends DiffUtil.ItemCallback<User> {

        // Проверка, являются ли элементы одинаковыми
        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem == newItem;
        }

        // Проверка, имеют ли элементы одинаковое содержимое
        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }
}