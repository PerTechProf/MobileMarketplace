package com.example.nimble

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.nimble.catalog.CatalogFragment
import com.example.nimble.databinding.ActivityMainBinding
import com.example.nimble.user.tokenUser
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        MAIN = this
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main)

        supportActionBar?.hide()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.miSearch, R.id.miAccount, R.id.miShopping))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        if(tokenUser != null){
//            navView.menu.clear()
//            navView.inflateMenu(R.menu.bottom_app_bar_seller)
//        }else{
//            navView.menu.clear()
//            navView.inflateMenu(R.menu.bottom_app_bar)
//        }

    }

}