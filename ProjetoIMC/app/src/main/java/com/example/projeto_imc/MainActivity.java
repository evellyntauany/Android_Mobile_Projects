package com.example.projeto_imc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editTextPeso, editTextAltura, editTextNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPeso=findViewById(R.id.edPeso);
        editTextAltura=findViewById(R.id.Altura);
        editTextNome=findViewById(R.id.Nome);
    }

    public void abreSegundaActivity(View v){
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("peso", Double.parseDouble(editTextPeso.getText().toString()));
        intent.putExtra("altura", Double.parseDouble(editTextAltura.getText().toString()));
        intent.putExtra("nome", editTextNome.getText().toString());

        startActivity(intent);

    }
}