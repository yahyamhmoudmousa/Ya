package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSave, btnLoad;
    EditText editTextPhone, editTextMessage;
    TextView textView;
    DatabaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = editTextPhone.getText().toString();
                String message = editTextMessage.getText().toString();
                if(phone.isEmpty() || message.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Data", Toast.LENGTH_SHORT).show();
                }else{
                    User user = new User(phone,message);
                    long rowId = adapter.insertUser(user);
                    Toast.makeText(MainActivity.this, "Row ID: " + rowId, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User[] users = adapter.getAllUsers();
                StringBuilder data = new StringBuilder();
                for (User user : users){
                    data.append(user.toString()).append("\n");
                }
                textView.setText(data);

            }
        });
    }

    private void initComponent() {
        btnLoad = findViewById(R.id.buttonLoad);
        btnSave = findViewById(R.id.buttonSave);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextMessage = findViewById(R.id.editTextMessage);
        textView = findViewById(R.id.textView);
        adapter = new DatabaseAdapter(MainActivity.this);
    }
}