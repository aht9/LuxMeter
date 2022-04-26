package com.example.codeacademy.luxmeter

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {

    var sensor:Sensor? = null
    var sensorManager:SensorManager? = null

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        Log.i("Sensor", event!!.values[0].toString())
        textViewLuxValue.text = event!!.values[0].toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onResume() {
        super.onResume()

        sensorManager!!.registerListener(this,sensor,SensorManager.SENSOR_STATUS_ACCURACY_HIGH)
    }

    override fun onPause() {
        super.onPause()

        sensorManager!!.unregisterListener(this)
    }
}
