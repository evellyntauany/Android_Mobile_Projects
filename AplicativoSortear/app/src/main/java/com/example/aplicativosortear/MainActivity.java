package com.example.aplicativosortear;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText Minimo, Maximo;
    TextView Sorteio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Maximo = findViewById(R.id.editTextMax);
        Minimo = findViewById(R.id.editTextMin);
        Sorteio = findViewById(R.id.textviewResul);

    }
    public void sortear (View v){
        int min, max;
        min = Integer.parseInt(Minimo.getText().toString());
        max = Integer.parseInt(Maximo.getText().toString());

        int randomNum = new Random().nextInt(max-min)+min;

        Sorteio.setText(Integer.toString(randomNum));
    }

}