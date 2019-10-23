package com.hcgossi.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hcgossi.notes.database.DataBase;

import javax.crypto.ExemptionMechanism;

public class EditNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        DataBase bancoDeDados = new DataBase(getBaseContext());
        final Cursor cursor = bancoDeDados.consultarAnotacaoPeloId(this.getIntent().getIntExtra("id", 0));
        EditText titulo = (EditText)findViewById(R.id.campoTitulo);
        EditText conteudo = (EditText)findViewById(R.id.campoConteudo);
        titulo.setText(cursor.getString(cursor.getColumnIndexOrThrow("titulo")));
        conteudo.setText(cursor.getString(cursor.getColumnIndexOrThrow("conteudo")));
    }

    public void voltar(View v){
        Intent startNewActivity = new Intent(this, MainActivity.class);
        startActivity(startNewActivity);
    }

    public void autalizarAnotacao(View view) {
        DataBase dataBase = new DataBase(getBaseContext());
        EditText titulo = (EditText) findViewById(R.id.campoTitulo);
        EditText conteudo = (EditText) findViewById(R.id.campoTitulo);

        try{
            dataBase.atualizaAnotacao(this.getIntent().getIntExtra("id", 0), titulo.getText().toString(), conteudo.getText().toString());
            Toast.makeText(getApplicationContext(), "Anotação realizada com sucesso!", Toast.LENGTH_LONG).show();
        }catch(Exception ex){
            Toast.makeText(getApplicationContext(), "Não foi possível atualizar a anotação, por favor tente novamente!", Toast.LENGTH_LONG).show();
        }

        voltar(view);
    }

    public void excluirAnotacao(View view) {
        DataBase dataBase = new DataBase(getBaseContext());
        EditText titulo = (EditText) findViewById(R.id.campoTitulo);
        EditText conteudo = (EditText) findViewById(R.id.campoTitulo);

        try{
            dataBase.excluirAnotacao(this.getIntent().getIntExtra("id", 0));
            Toast.makeText(getApplicationContext(), "Anotação excluida com sucesso!", Toast.LENGTH_LONG).show();
        }catch(Exception ex){
            Toast.makeText(getApplicationContext(), "Não foi possível excluir a anotação, por favor tente novamente!", Toast.LENGTH_LONG).show();
        }

        voltar(view);
    }
}
