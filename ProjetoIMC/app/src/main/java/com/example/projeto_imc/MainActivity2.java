package com.example.projeto_imc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity2 extends AppCompatActivity {
        TextView textViewPeso, textResult,textNome,textResultAltura;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
            textResultAltura = findViewById(R.id.Altura2);
            textViewPeso = findViewById(R.id.Peso2);
            textResult = findViewById(R.id.resultado);
            textNome = findViewById(R.id.Nome2);


            Double peso = getIntent().getExtras().getDouble("peso");
            Double altura = getIntent().getExtras().getDouble("altura");
            String nome = getIntent().getExtras().getString("nome");


            Double converte = (calculaIMC(peso, altura));
            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.applyPattern("IMC: ##.##");
            textResult.setText((decimalFormat.format(converte)));
            textNome.setText(nome);
            textViewPeso.setText(converte.toString(peso));
            textResultAltura.setText(converte.toString(altura));
        }
        public Double calculaIMC(Double peso, Double altura){

            return peso/(altura*altura);
        }
    }

