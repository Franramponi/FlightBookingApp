package com.edu.ort.tp3_belgrano_a_grupo4

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    //Lista de fragmentos se ocultar la bottom bar y action bar
    private val sinActionBar = setOf(
        R.id.profile,
        R.id.pantallaEnDesarrollo

    )
    private val fragmentsWithoutBottomNav = setOf(
        R.id.pantallaEnDesarrollo
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        initViews()
        configToolbar()
        setupDrawerLayout()
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)
        //Observador de cambios de destino, se activa cada vez que cambia el destino de navegación
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            invalidateOptionsMenu() // Cambia de fragment se llama a onPrepareOptionsMenu
            actionBar(destination)// Oculta la actionBar y la bottom navigation en los fragmentos requeridos
            hidenBottomBarFragments(destination)

        }
    }

    private fun initViews() {
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.drawer_nav)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        bottomNavigationView = findViewById(R.id.bottom_navbar)
        toolbar= findViewById(R.id.toolbar)
    }
    private fun configToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val inflaterToolbar = layoutInflater
        val toolbarLayout = inflaterToolbar.inflate(R.layout.custom_home_action_bar, toolbar, false)
        toolbar.addView(toolbarLayout)

    }

    private fun setupDrawerLayout() {
        val navController = navHostFragment.navController
        navigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, _, _ ->
            supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_hamburguesa)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        return false
    }

    private fun actionBar(destination: NavDestination) {
        if (destination.id in sinActionBar) {
            supportActionBar?.hide()
            toolbar.visibility = View.GONE
        } else {
            toolbar.visibility = View.VISIBLE
        }
    }



    private fun hidenBottomBarFragments(destination: NavDestination) {
        if (destination.id in fragmentsWithoutBottomNav) {
            bottomNavigationView.visibility = View.GONE
        } else {
            bottomNavigationView.visibility = View.VISIBLE
        }
    }
}
