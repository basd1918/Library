package com.example.parkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editTextTitlu,editTextAutor,editTextEditura,editTextAnPublicatie,editTextCotaCarte,editTextCategorie;
    Button buttonAdaugaCarteNoua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        myDb = new DatabaseHelper(this);
        editTextTitlu=(EditText)findViewById(R.id.editTextTitlu);
        editTextAutor=(EditText)findViewById(R.id.editTextAutor);
        editTextEditura=(EditText)findViewById(R.id.editTextEditura);
        editTextAnPublicatie=(EditText)findViewById(R.id.editTextAnPublicatie);
        editTextCotaCarte=(EditText)findViewById(R.id.editTextCotaCarte);
        editTextCategorie=(EditText)findViewById(R.id.editTextCategorie);
        buttonAdaugaCarteNoua=(Button) findViewById(R.id.buttonAdaugaCarteNoua);


        buttonAdaugaCarteNoua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editTextTitlu.length()==0)
                    editTextTitlu.setError("");
                else if(editTextAutor.length()==0)
                    editTextAutor.setError("");
                else if(editTextEditura.length()==0)
                    editTextEditura.setError("");
                //else if(editTextAnPublicatie.length()==0)
                   // editTextAnPublicatie.setError("");
                else if(editTextCotaCarte.length()==0)
                    editTextCotaCarte.setError("");
               // else if(editTextCategorie.length()==0)
                   // editTextCategorie.setError("");
                else {
                    String titlu = editTextTitlu.getText().toString().trim();
                    String autor = editTextAutor.getText().toString().trim();
                    String editura = editTextEditura.getText().toString().trim();
                    String anPub = editTextAnPublicatie.getText().toString();
                    String categorie = editTextCategorie.getText().toString().trim();
                    String cotaCarte = editTextCotaCarte.getText().toString().trim();

                    long val = myDb.addBook(titlu, autor, editura, anPub, categorie, cotaCarte);
                    if (val > 0) {
                        Toast.makeText(Add.this, "The book was add with Succes", Toast.LENGTH_SHORT).show();
                        Intent refresh = new Intent(Add.this, AdminActivity.class);
                        startActivity(refresh);
                    } else {
                        Toast.makeText(Add.this, "Error(ADD)", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
