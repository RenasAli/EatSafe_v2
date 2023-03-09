package com.example.eatsafe_v2

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.ScanMode

private const val CAMERA_REQUEST_CODE= 101

class ScannerActivity : AppCompatActivity() {
    private lateinit var codeScanner: CodeScanner
    private lateinit var scannerView: CodeScannerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)
        setupPermmisions()
       codeScanner()
    }

    private fun codeScanner(){
        scannerView = findViewById(R.id.scanner_view)
        codeScanner = CodeScanner(this,scannerView )

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS
            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            
        }
    }
    override fun onResume(){
        super.onResume()
        codeScanner.startPreview()

    }
    override fun onPause(){
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setupPermmisions(){
        val permission = ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.CAMERA)
        if(permission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }

    }

    private fun  makeRequest(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when(requestCode) {
          CAMERA_REQUEST_CODE -> {
              if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                  Toast.makeText( this, "You need the camera permission to be able to use BareCode Scanner", Toast.LENGTH_SHORT).show()
              }
              else{
                  //Done
              }
          }
      }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)



    }

}