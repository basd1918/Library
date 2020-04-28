package com.example.parkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editTextTitlu,editTextAutor,editTextEditura,editTextAnPublicatie,editTextCotaCarte,editTextCategorie;
    Button buttonAdaugaCarteNoua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        myDb = new DatabaseHelper(this);
        editTextTitlu=(EditText)findViewById(R.id.editTextTitlu_update);
        editTextAutor=(EditText)findViewById(R.id.editTextAutor_update);
        editTextEditura=(EditText)findViewById(R.id.editTextEditura_update);
        editTextAnPublicatie=(EditText)findViewById(R.id.editTextAnPublicatie_update);
        editTextCotaCarte=(EditText)findViewById(R.id.editTextCotaCarte_update);
        editTextCategorie=(EditText)findViewById(R.id.editTextCategorie_update);
        buttonAdaugaCarteNoua=(Button) findViewById(R.id.buttonUpdateBook);


        buttonAdaugaCarteNoua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextTitlu.length()==0) editTextTitlu.setError("");
                else if(editTextAutor.length()==0) editTextAutor.setError("");
                else if(editTextEditura.length()==0) editTextEditura.setError("");
                else if(editTextCotaCarte.length()==0) editTextCotaCarte.setError("");
                else {

                    String titlu = editTextTitlu.getText().toString().trim();
                    String autor = editTextAutor.getText().toString().trim();
                    String editura = editTextEditura.getText().toString().trim();
                    String anPub = editTextAnPublicatie.getText().toString();
                    String categorie = editTextCategorie.getText().toString().trim();
                    String cotaCarte = editTextCotaCarte.getText().toString().trim();

                    long val = myDb.updateBook(titlu, autor, editura, anPub, categorie, cotaCarte);
                    if (val > 0) {
                        Toast.makeText(update.this, "Book updated", Toast.LENGTH_SHORT).show();
                        Intent refresh = new Intent(update.this, AdminActivity.class);
                        startActivity(refresh);
                    } else {
                        Toast.makeText(update.this, "Error(UPDATE)", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
