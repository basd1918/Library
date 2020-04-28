package com.example.parkingapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class dialogAlert extends AppCompatActivity {


    DatabaseHelper myDb;
    private Button buttonDialog_cancel,buttonDialog_imprumutaAcum;
    private TextView textViewDialog_titlu,textViewDialog_autor,textViewDialog_editura;
    private TextView textViewDialog_anPub,textViewDialog_categorie,textViewDialog_nrExempl,textViewDialog_cota,textViewDialog_nrExemplDisponibile;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_alert);
        myDb = new DatabaseHelper(this);

        Intent intent=getIntent();
        final String titlu=intent.getStringExtra("titlu");
        final String autor=intent.getStringExtra("autor");
        final String editura=intent.getStringExtra("editura");
        final String anPubl=intent.getStringExtra("anPubl");
        String nrExempl=intent.getStringExtra("nrExempl");
        final String cota=intent.getStringExtra("cota");
        final String categorie=intent.getStringExtra("categorie");
        String imprumutate=intent.getStringExtra("imprumutate");
        String disponibile=intent.getStringExtra("disponibile");

        textViewDialog_titlu=(TextView) findViewById(R.id.textViewDialog_titlu);
        textViewDialog_titlu.setText(titlu);
        textViewDialog_autor=(TextView) findViewById(R.id.textViewDialog_autor);
        textViewDialog_autor.setText(autor);
        textViewDialog_editura=(TextView) findViewById(R.id.textViewDialog_editura);
        textViewDialog_editura.setText(editura);
        textViewDialog_anPub=(TextView) findViewById(R.id.textViewDialog_anPub);
        textViewDialog_anPub.setText(anPubl);
        textViewDialog_categorie=(TextView) findViewById(R.id.textViewDialog_categorie);
        textViewDialog_categorie.setText(categorie);
        textViewDialog_nrExempl=(TextView) findViewById(R.id.textViewDialog_nrExempl);
        textViewDialog_nrExempl.setText(nrExempl);
        textViewDialog_cota=(TextView) findViewById(R.id.textViewDialog_cota);
        textViewDialog_cota.setText(cota);
        textViewDialog_nrExemplDisponibile=(TextView) findViewById(R.id.textViewDialog_nrExemplDisponibile);
        textViewDialog_nrExemplDisponibile.setText(disponibile);


        buttonDialog_imprumutaAcum=(Button)findViewById(R.id.buttonDialog_imprumutaAcum);
        buttonDialog_imprumutaAcum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nrExTotal=Integer.parseInt(textViewDialog_nrExempl.getText().toString());
                int nrImprumutate=Integer.parseInt(textViewDialog_nrExemplDisponibile.getText().toString());
               // int nrDisponibile=nrExTotal-nrImprumutate;
                int nrDisponibile=Integer.parseInt(textViewDialog_nrExemplDisponibile.getText().toString());
                if(nrDisponibile>0)
                {myDb.imprumutaCarteDupaCota(cota);
                    Toast.makeText(dialogAlert.this,"Succes(borrow book)",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(dialogAlert.this,"Impossible to borrow",Toast.LENGTH_SHORT).show();
                }
                textViewDialog_nrExemplDisponibile.setText(nrDisponibile+"");
                Intent refresh = new Intent(dialogAlert.this,UserActivity.class);
                startActivity(refresh);


            }
        });
        buttonDialog_cancel=(Button)findViewById(R.id.buttonDialog_cancel);
        buttonDialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
