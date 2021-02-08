package com.vimalnaina.regformwithroom.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.vimalnaina.regformwithroom.R
import com.vimalnaina.regformwithroom.helper.SharedPreference

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.navHost))
    }

    override fun onSupportNavigateUp(): Boolean {

        val navController =findNavController(R.id.navHost)
        val appBarConfiguration = AppBarConfiguration(navController.graph,null)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

}