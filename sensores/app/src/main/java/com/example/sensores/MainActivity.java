package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    SensorManager mSensorManager;
    Sensor mSensorLight;
    TextView mTvLight;

    private SensorManager sensorManager;
    private Sensor sensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvLight = findViewById(R.id.tvSensorLigh);

        mSensorManager = (SensorManager) getSystemService(getApplicationContext().SENSOR_SERVICE);
        List<Sensor> listSensor =  mSensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sensorListTxt = new StringBuilder();

        for (Sensor s:listSensor) {
            sensorListTxt.append(s.getName()).append(System.getProperty("line.separator"));
            sensorListTxt.append(s.getResolution()).append(System.getProperty("line.separator"));

        }
       // mTvLight.setText(sensorListTxt);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();

        float currentValue = sensorEvent.values[0];
        mTvLight.setText(Float.toString(currentValue));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}