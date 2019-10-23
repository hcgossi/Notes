package com.hcgossi.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hcgossi.notes.database.DataBase;

public class CreateNewNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_note);
    }

    public void voltar(View v){
        Intent startNewActivity = new Intent(this, MainActivity.class);
        startActivity(startNewActivity);
    }

    public void criarAnotacao(View v){

        DataBase bancoDeDados = new DataBase(getBaseContext());

        EditText titulo = (EditText)findViewById(R.id.campoTitulo);
        EditText conteudo = (EditText)findViewById(R.id.campoConteudo);

        boolean resultado = bancoDeDados.criarAnotacao(titulo.getText().toString(),
                conteudo.getText().toString());
        if(resultado){
            Toast.makeText(getApplicationContext(), "Anotação criada com sucesso!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Infelizmente ocorreu um erro, tente novamente.", Toast.LENGTH_LONG).show();
        }
        voltar(v);
    }
}
