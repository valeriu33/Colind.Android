package com.valeriu.colindandroid.colindsList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.valeriu.colindandroid.R
import com.valeriu.colindandroid.databinding.ActivityColindsBinding

class ColindsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colinds)

//        val navController: NavController = findNavController(R.id.nav_host_fragment)

//        findViewById<NavigationView>(R.id.nav_graph)
//            .setupWithNavController(navController)
    }
}
