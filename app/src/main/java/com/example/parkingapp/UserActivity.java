package com.example.parkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    ListView listViewDisplatBooks;
    DatabaseHelper mydb;
    ArrayList<Book> arrayList;
    BookAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mydb=new DatabaseHelper(this);
        listViewDisplatBooks=(ListView)findViewById(R.id.listViewDisplatBooks);
        arrayList=new ArrayList<>();
       loadDataInListView();
    }

    private void loadDataInListView() {
        arrayList=mydb.getAllBooks();
        myAdapter= new BookAdapter(this,arrayList);
        listViewDisplatBooks.setAdapter(myAdapter);
        listViewDisplatBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Intent intent=new Intent(getApplicationContext(),dialogAlert.class);
              String titlu=arrayList.get(position).getTitlu();
              String autor=arrayList.get(position).getAutor();
              String editura=arrayList.get(position).getEditura();
              String anPubl=arrayList.get(position).getAnPublicatie();
              String cota=arrayList.get(position).getCotaCarte();
              String nrExempl=arrayList.get(position).getNrExemplare()+"";
              String categorie=arrayList.get(position).getCategorie();
              String nrImrumutate=arrayList.get(position).getNrImprumutate()+"";
                int nrExTotal=Integer.parseInt(nrExempl);
                int nrImprumutate=Integer.parseInt(nrImrumutate);
                int nrDisponibile=nrExTotal-nrImprumutate;

              intent.putExtra("titlu",titlu);
              intent.putExtra("autor",autor);
              intent.putExtra("editura",editura);
              intent.putExtra("anPubl",anPubl);
              intent.putExtra("cota",cota);
              intent.putExtra("nrExempl",nrExempl);
              intent.putExtra("categorie",categorie);
              intent.putExtra("imprumutate",nrImrumutate);
              intent.putExtra("disponibile",nrDisponibile+"");

              startActivity(intent);
            }
        });

        myAdapter.notifyDataSetChanged();
    }
}
