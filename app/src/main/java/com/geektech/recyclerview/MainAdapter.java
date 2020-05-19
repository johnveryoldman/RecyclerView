package com.geektech.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {
    ArrayList<Student> data = new ArrayList<>();
    IStudentClick listener;

    public MainAdapter() {
        for (int i = 0; i < 5; i++) {
            Student student = new Student();
            student.id = i;
            student.name = "Element " + i;
        }
    }

    public int addNewStudent() {
        int position = data.size();
        Student s = new Student();
        s.id = position;
        s.name = "new student " + position;

        data.add(s);

        notifyDataSetChanged();
        return position;
    }

    public void update(Student s) {
        int index = -1;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).id == s.id) {
                index = i;
                break;
            }
        }
        data.set(index, s);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.view_holder_main, parent, false);
        MainViewHolder vh = new MainViewHolder(v);
        vh.listener = listener;
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        Student dataByPosition = data.get(position);
        holder.onBind(dataByPosition);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
