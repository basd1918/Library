package com.example.parkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class delete extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editTextTitlu,editTextAutor,editTextEditura,editTextAnPublicatie,editTextCota_delete;
    EditText editTextBookNumber,editTextExemplarId;
    Button buttonDeleteBook,buttonDeleteExemplar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        myDb = new DatabaseHelper(this);
        editTextTitlu=(EditText)findViewById(R.id.editTextTitlu_delete);
        editTextAutor=(EditText)findViewById(R.id.editTextAutor_delete);
        editTextEditura=(EditText)findViewById(R.id.editTextEditura_delete);
        editTextAnPublicatie=(EditText)findViewById(R.id.editTextAnPublicatie_delete);
        editTextCota_delete=(EditText)findViewById(R.id.editTextCota_delete);
        buttonDeleteBook=(Button) findViewById(R.id.buttonDeleteBook);

        editTextExemplarId=(EditText)findViewById(R.id.editTextExemplarId);
        editTextBookNumber=(EditText)findViewById(R.id.editTextBookNumber);
        buttonDeleteExemplar=(Button)findViewById(R.id.buttonDeleteExemplar);

        buttonDeleteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titlu = editTextTitlu.getText().toString().trim();
                String autor = editTextAutor.getText().toString().trim();
                String editura = editTextEditura.getText().toString().trim();
                String anPub =editTextAnPublicatie.getText().toString();
                String cota=editTextCota_delete.getText().toString();

                /*long val = myDb.deleteBook(titlu,autor,editura,anPub);
                if(val > 0){
                    Toast.makeText(delete.this,"Book was deleted",Toast.LENGTH_SHORT).show();
                    Intent refresh = new Intent(delete.this,AdminActivity.class);
                    startActivity(refresh);
                }
                else{
                    Toast.makeText(delete.this,"ERROR( delete Book)",Toast.LENGTH_SHORT).show();
                }*/

                long val = myDb.deleteBook2(cota,titlu,autor);
                if(val > 0){
                    Toast.makeText(delete.this,"Book was deleted",Toast.LENGTH_SHORT).show();
                    Intent refresh = new Intent(delete.this,AdminActivity.class);
                    startActivity(refresh);
                }
                else{
                    Toast.makeText(delete.this,"Error(delete1)",Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonDeleteExemplar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editTextExemplarId.getText().toString().trim();
                String cota = editTextBookNumber.getText().toString().trim();
                long val = myDb.deleteExemplarBook(id,cota);
                if(val > 0){
                    long nrExemplareRamase=myDb.countExemplare(cota);
                    myDb.updateNrExemplareDupaDelete(cota,(int)nrExemplareRamase);
                    Toast.makeText(delete.this,"Exemplar was deleted",Toast.LENGTH_SHORT).show();
                    Intent refresh = new Intent(delete.this,AdminActivity.class);
                    startActivity(refresh);
                }
                else{
                    Toast.makeText(delete.this,"Error(delete2)",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
