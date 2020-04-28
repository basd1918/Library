package com.example.parkingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Restituie extends AppCompatActivity {

    EditText editTextCotaCarteReturn,editTextIdExemplarReturn;
    Button buttonReturnBook;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restituie);
        myDb=new DatabaseHelper(this);
        editTextCotaCarteReturn=(EditText)findViewById(R.id.editTextCotaCarteReturn);
        editTextIdExemplarReturn=(EditText)findViewById(R.id.editTextIdExemplarReturn);
        buttonReturnBook=(Button)findViewById(R.id.buttonReturnBook);
        buttonReturnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               long l= myDb.restituieCarteDupaCota(editTextCotaCarteReturn.getText().toString());
               if(l>0)
                   Toast.makeText(Restituie.this,"Succes(RETURN)",Toast.LENGTH_SHORT).show();
               else Toast.makeText(Restituie.this,"Error(RETURN)",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
