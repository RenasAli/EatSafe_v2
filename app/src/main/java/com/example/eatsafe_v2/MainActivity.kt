package com.example.eatsafe_v2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var scanner_btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scanner_btn= findViewById(R.id.scanner_btn)
        scanner_btn.setOnClickListener {
            Toast.makeText( this,"Start scanning", Toast.LENGTH_SHORT).show()
            val scannerIntent = Intent(this,ScannerActivity::class.java)
            startActivity(scannerIntent)
        }

    }
}