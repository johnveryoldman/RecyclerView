package com.geektech.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    static final String DATA_KEY = "data_key";
    static final String RESULT_KEY = "result_key";

    EditText field;
    Button save;
    Button cancel;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initViews();
        setOnClickListeners();

        student = fetchData();
        if (student != null) {
            setupView(student.name);
        }
    }

    private void initViews() {
        field = findViewById(R.id.edit_field);
        save = findViewById(R.id.save_btn);
        cancel = findViewById(R.id.cancel_btn);
    }

    private void setOnClickListeners() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.name = field.getText().toString();
                returnData(student);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelData();
            }
        });
    }

    private void setupView(@NonNull String text) {
        field.setText(text);
    }

    private Student fetchData() {
        Intent data = getIntent();
        if (data != null) {
            return (Student) data.getSerializableExtra(DATA_KEY);
        }
        return null;
    }

    private void returnData(Student s) {
        if (s != null) {
            Intent intent = new Intent();
            intent.putExtra(RESULT_KEY, s);
            setResult(RESULT_OK, intent);
        } else {
            setResult(RESULT_CANCELED);
        }
        finish();
    }

    private void cancelData() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
