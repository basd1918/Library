package com.example.parkingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends BaseAdapter {
    Context context;
    ArrayList<Book> arrayList;
    public BookAdapter(Context context, ArrayList<Book>arrayList)
    {
       this.context=context;
       this.arrayList=arrayList;
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public Object getItem(int position){
        return arrayList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.mycustomlistview,null);
            TextView textViewTitlu=(TextView)convertView.findViewById(R.id.titluu);
            TextView textViewAutor=(TextView)convertView.findViewById(R.id.autorr);
            TextView textViewEditura=(TextView)convertView.findViewById(R.id.edituraa);
            TextView textViewAn=(TextView)convertView.findViewById(R.id.anPubb);
            TextView textViewCota=(TextView)convertView.findViewById(R.id.cotaa);
            TextView textViewNr=(TextView)convertView.findViewById(R.id.nrExemplarr);
            TextView textViewCategorie=(TextView)convertView.findViewById(R.id.categoriee);
            TextView nrExemplarrDispp=(TextView)convertView.findViewById(R.id.nrExemplarrDispp);
            LinearLayout linearr=(LinearLayout)convertView.findViewById(R.id.linearr);

            Book book=arrayList.get(position);
        textViewTitlu.setText(book.getTitlu());
        textViewAutor.setText(book.getAutor());
        textViewAn.setText(book.getAnPublicatie());
        textViewEditura.setText(book.getEditura());
        textViewCota.setText(book.getCotaCarte());
        textViewCategorie.setText(book.getCategorie());
        nrExemplarrDispp.setText(String.valueOf(book.getNrImprumutate()));
        textViewNr.setText(String.valueOf(book.getNrExemplare()));

        int nrDisp=Integer.parseInt(textViewNr.getText().toString())-Integer.parseInt(nrExemplarrDispp.getText().toString());
        nrExemplarrDispp.setText(nrDisp+"");
        return convertView;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

}
