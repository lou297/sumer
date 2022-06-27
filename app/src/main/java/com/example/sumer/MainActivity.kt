package com.example.sumer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.sumer.databinding.ActivityMainBinding
import com.example.sumer.databinding.ActivityMainBinding.inflate

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home_btn -> {
                Toast.makeText(this, "home", Toast.LENGTH_LONG).show()
                true
            }
            R.id.person_btn -> {
                Toast.makeText(this, "person", Toast.LENGTH_LONG).show()
                true
            }
            R.id.setting_btn -> {
                Toast.makeText(this, "setting", Toast.LENGTH_LONG).show()
                true
            }
            else -> {
                false
            }
        }
        return super.onOptionsItemSelected(item)
    }
}