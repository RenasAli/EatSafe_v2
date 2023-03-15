package com.example.eatsafe_v2

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
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
   private fun safeToEatResult(){
       val safeToEatIntent = Intent(this, SafeToEatActivity::class.java)
       startActivity(safeToEatIntent);
   }
    private fun unSafeToEatResult(){
        val UnsafeToEatIntent = Intent(this, UnsafeActivity::class.java)
        startActivity(UnsafeToEatIntent);
    }

    private fun searchWeb(query: String) {

        val url = query
        val intent = Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url))

        startActivity(intent)

    }

    private fun codeScanner(){
        scannerView = findViewById(R.id.scanner_view)
        codeScanner = CodeScanner(this,scannerView )

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS
            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.SINGLE
            isAutoFocusEnabled = true
            isFlashEnabled = false
            decodeCallback = DecodeCallback {
                runOnUiThread {
                    if (it.text == "6548964631668"){
                    Toast.makeText(applicationContext,"Scan result: ${it.text} it is safe to eat", Toast.LENGTH_LONG).show()
                        safeToEatResult()
                    } else if (it.text == "8949461894984"){
                        Toast.makeText(applicationContext,"Scan result: ${it.text} it is Not safe to eat", Toast.LENGTH_LONG).show()
                        unSafeToEatResult()
                    } else {
                        searchWeb(it.text)

                    }
            }


            }


            scannerView.setOnClickListener {
                codeScanner.startPreview()
            }
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