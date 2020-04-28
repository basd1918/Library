package com.example.parkingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="biblioteca.db";
    public static final String TABLE_NAME ="utilizatori_table";
    public static final String TABLE_NAME_BOOK ="carti_table";
    public static final String TABLE_NAME_BOOK_EXEMPLAR ="exemplare_table";
    public static final String TABLE_NAME_IMPRUMUTA_RESTITUIE ="imprumuta_restituie_table";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="username";
    public static final String COL_3 ="password";
    public static final String COL_4 ="usertype";
    public static final String COL_5 ="CNP";
    public static final String COL_6 ="nume";
    public static final String COL_7 ="adresa";
    public static final String COL_8 ="telefon";

    public static final String COLO_1 ="titlu";
    public static final String COLO_2 ="autor";
    public static final String COLO_3 ="editura";
    public static final String COLO_4 ="anPublicatie";
    public static final String COLO_5 ="categorie";
    public static final String COLO_6 ="cotaCarte";

    public static final String COLOMN_1 ="titlu";
    public static final String COLOMN_2 ="autor";
    public static final String COLOMN_3 ="editura";
    public static final String COLOMN_4 ="anPublicatie";
    public static final String COLOMN_5 ="categorie";
    public static final String COLOMN_6 ="cotaCarte";
    public static final String COLOMN_7 ="nrExemplare";
    public static final String COLOMN_8 ="nrExemplareImprumutate";


    public static final String COLOM_1 ="cotaCarteIR";
    public static final String COLOM_2 ="nrExemplareDate";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+"  (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, password TEXT, usertype TEXT , CNP TEXT , nume TEXT , adresa TEXT , telefon TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME_BOOK_EXEMPLAR+"  (ID_BOOK INTEGER PRIMARY  KEY AUTOINCREMENT, titlu TEXT, autor TEXT, editura TEXT , anPublicatie TEXT , categorie TEXT , cotaCarte TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME_BOOK+"  (titlu TEXT, autor TEXT, editura TEXT , anPublicatie TEXT , categorie TEXT , cotaCarte TEXT PRIMARY  KEY ,nrExemplare INTEGER,nrExemplareImprumutate INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME_IMPRUMUTA_RESTITUIE+"  (cotaCarteIR TEXT PRIMARY  KEY, nrExemplareDate INTEGER)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME_BOOK);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME_BOOK_EXEMPLAR);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME_IMPRUMUTA_RESTITUIE);
        onCreate(sqLiteDatabase);
    }


    public long addUser(String user, String password, String usertype,String cnp, String adresa, String nume, String telefon){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        contentValues.put("usertype",usertype);
        contentValues.put("CNP",cnp);
        contentValues.put("adresa",adresa);
        contentValues.put("nume",nume);
        contentValues.put("telefon",telefon);
        long res = db.insert(TABLE_NAME,null,contentValues);
        db.close();
        return  res;
    }

    public long countIdenticBook(String cota){
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteStatement s = db.compileStatement( "select count(*) from carti_table where cotaCarte='" + cota + "'; " );
        long count = s.simpleQueryForLong();
        return count;
    }

    public long countExemplare(String cota){
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteStatement s = db.compileStatement( "select count(*) from exemplare_table where cotaCarte='" + cota + "'; " );
        long count = s.simpleQueryForLong();
        return count;
    }

    public int getNrExemplareImprumutate(String cota){
        int nr=0;
        String sql=" SELECT nrExemplareDate FROM " +TABLE_NAME_IMPRUMUTA_RESTITUIE+" where cotaCarteIR="+"'"+cota+"'";
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        if(cursor.getCount()>0){
            cursor.moveToNext();
            nr=cursor.getInt(0);
        }
        cursor.close();
        return nr;
    }


    public int updateNrImprumutate(String cota,int nr){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COLOMN_8,nr);
        String[]whereArgs={cota};
        int count=db.update(DatabaseHelper.TABLE_NAME_BOOK,contentValues,DatabaseHelper.COLOMN_6 + " =? ",whereArgs);
        return count;
    }

    public int updateNrExemplareDupaDelete(String cota,int nr){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COLOMN_7,nr);
        String[]whereArgs={cota};
        int count=db.update(DatabaseHelper.TABLE_NAME_BOOK,contentValues,DatabaseHelper.COLOMN_6 + " =? ",whereArgs);
        return count;
    }


    public long imprumutaCarteDupaCota(String cota){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int nrExemplreDate;
        contentValues.put(COLOM_1,cota);
        contentValues.put(COLOM_2,1);
        long res = db.insert(TABLE_NAME_IMPRUMUTA_RESTITUIE,null,contentValues);
        if(res<0)
        {
            SQLiteDatabase db4 = this.getWritableDatabase();
            nrExemplreDate=getNrExemplareImprumutate(cota);
            contentValues.put(COLOM_1,cota);
            contentValues.put(COLOM_2,nrExemplreDate+1);
            db4.update(TABLE_NAME_IMPRUMUTA_RESTITUIE, contentValues,COLOM_1+" = ?",new String[]{cota});
            db4.close();

        }
        int nr=getNrExemplareImprumutate(cota);
        int update=updateNrImprumutate(cota,nr);
        db.close();
        return  res;
    }

    public long restituieCarteDupaCota(String cota){  //functia returneaza 0 la eroare,sau o vaoare poz daca a fost restituita
        ContentValues contentValues = new ContentValues();
        int nrExemplreDate;
        SQLiteDatabase db4 = this.getWritableDatabase();
        nrExemplreDate=getNrExemplareImprumutate(cota);
        long res;
        if(nrExemplreDate>0)
        {
            contentValues.put(COLOM_1,cota);
            contentValues.put(COLOM_2,nrExemplreDate-1);
            res=db4.update(TABLE_NAME_IMPRUMUTA_RESTITUIE, contentValues,COLOM_1+" = ?",new String[]{cota});
            int update=updateNrImprumutate(cota,nrExemplreDate-1);
            db4.close();
        }
        else res=0;
        return  res;
    }


    public long addBook(String titlu, String autor, String editura,String anPublicatie, String categorie, String cotaCarte){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db2 = this.getWritableDatabase();
        SQLiteDatabase db3 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLO_1,titlu);
        contentValues.put(COLO_2,autor);
        contentValues.put(COLO_3,editura);
        contentValues.put(COLO_4,anPublicatie);
        contentValues.put(COLO_5,categorie);
        contentValues.put(COLO_6,cotaCarte);
        long res = db.insert(TABLE_NAME_BOOK_EXEMPLAR,null,contentValues);
        long countIdenticBooks=countIdenticBook(cotaCarte);
        long countExemplare=countExemplare(cotaCarte);
        int nrExemplare=1;
        int nrExemplareImprumutate;
        if(countIdenticBooks==0)
        {

            ContentValues contentValues2 = new ContentValues();
            contentValues2.put(COLOMN_1,titlu);
            contentValues2.put(COLOMN_2,autor);
            contentValues2.put(COLOMN_3,editura);
            contentValues2.put(COLOMN_4,anPublicatie);
            contentValues2.put(COLOMN_5,categorie);
            contentValues2.put(COLOMN_6,cotaCarte);
            contentValues2.put(COLOMN_7,nrExemplare);
            contentValues2.put(COLOMN_8,0);
            db2.insert(TABLE_NAME_BOOK,null,contentValues2);
            db2.close();
        }
       else
        {
            nrExemplare=(int)countExemplare;
            nrExemplareImprumutate=getNrExemplareImprumutate(cotaCarte);
            SQLiteDatabase db4 = this.getWritableDatabase();
            ContentValues contentValues4= new ContentValues();
            contentValues4.put(COLOMN_1,titlu);
            contentValues4.put(COLOMN_2,autor);
            contentValues4.put(COLOMN_3,editura);
            contentValues4.put(COLOMN_4,anPublicatie);
            contentValues4.put(COLOMN_5,categorie);
            contentValues4.put(COLOMN_6,cotaCarte);
            contentValues4.put(COLOMN_7,nrExemplare);
            contentValues4.put(COLOMN_8,nrExemplareImprumutate);
            db4.update(TABLE_NAME_BOOK, contentValues4,COLOMN_6+" = ?",new String[]{cotaCarte});
            db4.close();
        }
        db.close();
        return  res;
    }

    public int updateBook(String titlu, String autor, String editura,String anPublicatie, String categorie, String cotaCarte){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLO_1,titlu);
        contentValues.put(COLO_2,autor);
        contentValues.put(COLO_3,editura);
        contentValues.put(COLO_4,anPublicatie);
        contentValues.put(COLO_5,categorie);
        contentValues.put(COLO_6,cotaCarte);
        String[]whereArgs={cotaCarte};
        int count=db.update(DatabaseHelper.TABLE_NAME_BOOK,contentValues,DatabaseHelper.COLO_6 + " =? ",whereArgs);
        return count;
    }

    public Integer deleteBook(String titlu, String autor,String editura, String anPub){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_BOOK,"titlu=? and autor=? and editura=? and anPublicatie=?",new String[]{titlu, autor,editura,anPub,});
    }

    public Integer deleteExemplarBook(String id,String cota){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_BOOK_EXEMPLAR,"ID_BOOK=? and cotaCarte=?",new String[]{id, cota});
    }

    public Integer deleteBook2(String cota,String titlu,String autor){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_BOOK,"cotaCarte=? and titlu=? and autor=?",new String[]{cota, titlu, autor});
    }

    public boolean checkUser(String username, String password,String usertype){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?" + " and " + COL_4 + "=?";
        String[] selectionArgs = { username, password ,usertype};
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }

   public ArrayList<Book> getAllBooks()
   {
       ArrayList<Book> arrayList=new ArrayList<>();
       SQLiteDatabase db= this.getReadableDatabase();
       Cursor cursor= db.rawQuery("Select * from carti_table",null);

       while (cursor.moveToNext())
       {
           String titlu=cursor.getString(0);
           String autor=cursor.getString(1);
           String editura=cursor.getString(2);
           String an=cursor.getString(3);
           String categorie=cursor.getString(4);
           String cota=cursor.getString(5);
           int nr=cursor.getInt(6);
           int nrImprumutate=cursor.getInt(7);
           Book book=new Book(titlu,autor,editura,an,categorie,cota,nr,nrImprumutate);
           arrayList.add(book);

       }
       return arrayList;
   }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from " + TABLE_NAME_BOOK,null);
        return res;
    }
}
