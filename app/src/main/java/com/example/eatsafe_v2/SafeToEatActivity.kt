package com.example.eatsafe_v2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class SafeToEatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_safe_to_eat)
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