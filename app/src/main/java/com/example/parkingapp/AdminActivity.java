package com.example.parkingapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    Button BtnAdd;
    Button BtnUpdate;
    Button BtnDelete;
    Button BtnReturn,BtnListaCartiAmin;

    TextView tvId,tvNr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        myDb = new DatabaseHelper(this);

        BtnAdd=(Button)findViewById(R.id.BtnAdd);
        BtnUpdate=(Button)findViewById(R.id.BtnUpdate);
        BtnDelete=(Button)findViewById(R.id.BtnDelete);
        BtnReturn=(Button)findViewById(R.id.BtnReturn);
        BtnListaCartiAmin=(Button) findViewById(R.id.BtnListaCartiAmin);

        tvId=(TextView)findViewById(R.id.identic);
        tvNr=(TextView)findViewById(R.id.nrExempl);

        BtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdd();
            }
        });

        BtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdate();
            }
        });

        BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDelete();
            }
        });

        BtnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //-----------test--------------
                long countNrOfExemplare=myDb.countExemplare("");
                long countNrOfExemplare2=myDb.countIdenticBook("");
                tvId.setText(Long.toString(countNrOfExemplare));
                tvNr.setText(Long.toString(countNrOfExemplare2));
                //---------final test-----------
                openRestituie();
            }
        });
        BtnListaCartiAmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openLista();
            }
        });


    }

    public void openLista(){
        BtnListaCartiAmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res= myDb.getAllData();
                if( res.getCount()==0){
                    showMessage("ERROR","The database is empty");
                    return;
                }
                StringBuffer buffer= new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Title: "+ res.getString(0)+"\n");
                    buffer.append("Author: "+ res.getString(1)+"\n");
                    buffer.append("Publication: "+ res.getString(2)+"\n");
                    buffer.append("Year of Publication: "+ res.getString(3)+"\n");
                    buffer.append("Category: "+ res.getString(4)+"\n");
                    buffer.append("Book number: "+ res.getString(5)+"\n");
                    buffer.append("No of books: "+ res.getString(6)+"\n");
                    buffer.append("No of books borrowed: "+ res.getString(7)+"\n\n");
                }
                showMessage("Books list",buffer.toString());
            }
        });
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    public void openRestituie(){
        Intent intent = new Intent(this,Restituie.class);
        startActivity(intent);
    }
    public void openAdd(){
        Intent intent = new Intent(this,Add.class);
        startActivity(intent);
    }

    public void openUpdate(){
        Intent intent = new Intent(this,update.class);
        startActivity(intent);
    }

    public void openDelete(){
        Intent intent = new Intent(this,delete.class);
        startActivity(intent);
    }

}
