package com.example.parkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ListaActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    TextView textViewLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        myDb= new DatabaseHelper(this);
        textViewLista=(TextView)findViewById(R.id.textView_listaAp);

        Intent intent=getIntent();
        String message=intent.getStringExtra("Books list");
        textViewLista.setText(message);
    }
}
