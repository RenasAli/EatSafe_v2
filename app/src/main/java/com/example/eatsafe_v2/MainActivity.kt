package com.example.eatsafe_v2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarMenu


class MainActivity : AppCompatActivity() {
    private lateinit var scanner_btn: Button
    private lateinit var navItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scanner_btn= findViewById(R.id.scanner_btn)
        scanner_btn.setOnClickListener {
            Toast.makeText( this,"Start scanning", Toast.LENGTH_SHORT).show()
            val scannerIntent = Intent(this,ScannerActivity::class.java)
            startActivity(scannerIntent)
        }
        bottomNavigationView()



    }
    fun bottomNavigationView(){
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menuScanner -> {
                    val menuScannerIntent = Intent(this,MainActivity::class.java)
                    startActivity(menuScannerIntent)
                    true
                }
                R.id.MenuGroups -> {
                    val menuGroupsIntent = Intent(this,GroupsActivity::class.java)
                    startActivity(menuGroupsIntent)
                    true
                }
                R.id.menuWallet -> {
                    val menuWalletIntent = Intent(this,WalletActivity::class.java)
                    startActivity(menuWalletIntent)
                    true
                }
                else -> false
            }
        }

    }






}