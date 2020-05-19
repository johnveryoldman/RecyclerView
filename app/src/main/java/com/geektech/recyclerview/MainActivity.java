package com.geektech.recyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements IStudentClick {

    static private final int EDIT_CODE = 42;

    MainAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new MainAdapter();
        adapter.listener = this;
        recyclerView.setAdapter(adapter);
    }

    public void onNewElement(View view) {
        int lastPosition = adapter.addNewStudent();
        recyclerView.scrollToPosition(lastPosition);
    }

    @Override
    public void onStudentClick(Student s) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(EditActivity.DATA_KEY, s);
        startActivityForResult(intent, EDIT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_CODE) {
            if (resultCode == RESULT_OK) {
                Student s = (Student) data.getSerializableExtra(EditActivity.RESULT_KEY);
                adapter.update(s);
            } else if (resultCode == RESULT_CANCELED) {

            } else {

            }
        }
    }
}
