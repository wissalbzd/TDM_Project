package com.example.textjuridique

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(findNavController(R.id.fragment))
        var drawerLayout=findViewById<DrawerLayout>(R.id.drawerLayout)
        var navView=findViewById<NavigationView>(R.id.nav_view)
        toggle= ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.tout -> {
                    findNavController(R.id.fragment).navigate(R.id.homeFragment)
                }
                R.id.favoris -> {
                    findNavController(R.id.fragment).navigate(R.id.listFragment)
                    }
                R.id.historique -> {
                    findNavController(R.id.fragment).navigate(R.id.detailFragment)
                    }
                R.id.parametre -> {
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){return true}
        return super.onOptionsItemSelected(item)
    }
}
