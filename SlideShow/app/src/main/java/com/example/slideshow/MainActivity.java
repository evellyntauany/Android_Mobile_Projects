package com.example.slideshow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Integer imagens[];
    Button esquerdaButton, diretiaButton;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show);
        imagens = new Integer[] {
                R.drawable.cachorro,
                R.drawable.gardem,
                R.drawable.happy,
                R.drawable.porquinho,
                R.drawable.patinho
        };
        imageView=findViewById(R.id.imageView1);
        esquerdaButton=findViewById(R.id.esquerda);
        diretiaButton=findViewById(R.id.direita);

        if(i==0) {
            esquerdaButton.setEnabled(false);
        }
        if(i!=0) {
            esquerdaButton.setEnabled(true);
        }


        esquerdaButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                imageView.setImageResource(imagens[i]);
                i = i-1;
                if(i!=0) {
                    esquerdaButton.setEnabled(true);
                    diretiaButton.setEnabled(true);
                }
                if(i==4){
                    diretiaButton.setEnabled(false);
                }
                if(i==0){
                    esquerdaButton.setEnabled(false);
                }

            }
        });

        diretiaButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                imageView.setImageResource(imagens[i]);
                i = i +1;

                if(i==4){
                    diretiaButton.setEnabled(false);
                }
                else{
                    diretiaButton.setEnabled(true);
                    esquerdaButton.setEnabled(true);
                }
            }
        });

    }


    public void voltaPrimeiro(View v) {
        imageView.setImageResource(imagens[0]);
        diretiaButton.setEnabled(true);
    }

}