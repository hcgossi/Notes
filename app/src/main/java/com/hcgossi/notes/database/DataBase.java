package com.hcgossi.notes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBase {

    public SQLiteDatabase banco;
    public ManageDataBase manageDataBase;

    public DataBase(Context context){
        manageDataBase = new ManageDataBase(context);
    }

    public boolean criarAnotacao(String titulo, String conteudo){

        banco = manageDataBase.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("titulo", titulo);
        valores.put("conteudo", conteudo);

        long resultado = banco.insert("anotacoes", null, valores);

        banco.close();

        return resultado > 0;
    }

    public Cursor obterAnotacoes(){

        String[] campos = {"_id", "titulo"};

        SQLiteDatabase db = manageDataBase.getReadableDatabase();

        Cursor cursor = db.query("anotacoes", campos, null, null, null, null, "titulo ASC");

        if(cursor!=null){
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

    public void atualizaAnotacao(int id, String titulo, String conteudo) {
        SQLiteDatabase db = manageDataBase.getReadableDatabase();
        String where = "_id = " + id;

        ContentValues valores = new ContentValues();
        valores.put("titulo", titulo);
        valores.put("conteudo", conteudo);

        db.update("anotacoes", valores, where, null);
        db.close();
    }

    public void excluirAnotacao(int id) {
        SQLiteDatabase db = manageDataBase.getReadableDatabase();
        String where = "_id = " + id;

        db.delete("anotacoes", where, null);
        db.close();
    }

    public Cursor consultarAnotacaoPeloId(int id) {
        Cursor cursor;
        String[] campos = {"_id", "titulo", "conteudo"};
        String where = "_id = " + id;
        SQLiteDatabase db = manageDataBase.getReadableDatabase();
        cursor = db.query("anotacoes", campos, where, null, null, null,null,null);

        if (cursor !=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
