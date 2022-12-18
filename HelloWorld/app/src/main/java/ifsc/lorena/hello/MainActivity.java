package ifsc.lorena.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text;
    Button botao;
    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.texto);
        botao = findViewById(R.id.button);

    }

    public void clicando(View v) {
        cont++;
        text.setText(Integer.toString(cont));
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d("Ciclo de vida Activity ", "onStart");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("Ciclo de vida Activity ", "onResume");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d("Ciclo de vida Activity ", "onPause");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("Ciclo de vida Activity ", "onRestart");
    }

}