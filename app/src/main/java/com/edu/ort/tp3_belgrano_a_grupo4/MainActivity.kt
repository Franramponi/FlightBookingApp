package com.edu.ort.tp3_belgrano_a_grupo4

import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.edu.ort.tp3_belgrano_a_grupo4.database.AppDatabase
import com.edu.ort.tp3_belgrano_a_grupo4.database.dao.NightModeSettingsDao
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView


    //Lista de fragmentos se ocultar la bottom bar y action bar
    private val sinActionBar = setOf(
        R.id.profile,
        R.id.pantallaEnDesarrollo,
        R.id.splashActivity
    )
    private val fragmentsWithoutBottomNav = setOf(
        R.id.pantallaEnDesarrollo,
        R.id.search_result
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        initViews()
        configActionbar()
        setupDrawerLayout()
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)
        //Observador de cambios de destino, se activa cada vez que cambia el destino de navegación
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            invalidateOptionsMenu() // Cambia de fragment se llama a onPrepareOptionsMenu
            actionBar(destination)// Oculta la actionBar y la bottom navigation en los fragmentos requeridos
            hidenBottomBarFragments(destination)

            updateActionBarLayout(destination)

            updateActionBarIcon(destination)
        }


    }

    private fun initViews() {
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.drawer_nav)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        bottomNavigationView = findViewById(R.id.bottom_navbar)
    }

    private fun configActionbar() {
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowCustomEnabled(true)
            setHamburguerIconColor()
        }
    }

    private fun setupDrawerLayout() {
        val navController = navHostFragment.navController
        navigationView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navHostFragment.navController, drawerLayout)
    }

    private fun actionBar(destination: NavDestination) {
        if (destination.id in sinActionBar) {
            supportActionBar?.hide()
        } else {
            supportActionBar?.show()
        }
    }

    private fun hidenBottomBarFragments(destination: NavDestination) {
        if (destination.id in fragmentsWithoutBottomNav) {
            bottomNavigationView.visibility = View.GONE
        } else {
            bottomNavigationView.visibility = View.VISIBLE
        }
    }

    //Actualiza el icono si esta en el home, se muestra el menu, si no el boton para volver
    private fun updateActionBarIcon(destination: NavDestination) {
        if (destination.id == R.id.explore ) {
            //|| destination.id == R.id.setting
            setHamburguerIconColor()
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        } else {
            supportActionBar?.setHomeAsUpIndicator(null)
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }
    }
    private fun updateActionBarLayout(destination: NavDestination) {
        val layoutResId = when (destination.id) {
            R.id.fragment_search -> R.layout.custom_search_action_bar
            R.id.offers -> R.layout.custom_offert_action_bar
            R.id.search_result -> R.layout.custom_search_result_action_bar
            else -> R.layout.custom_home_action_bar // Default layout
        }

        supportActionBar?.customView = null
        val customView = layoutInflater.inflate(layoutResId, null)
        supportActionBar?.customView = customView
    }
    private fun setHamburguerIconColor() {
        val nightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (nightMode) {
            Configuration.UI_MODE_NIGHT_YES -> {
                supportActionBar?.setHomeAsUpIndicator(R.drawable.menu_claro)
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                supportActionBar?.setHomeAsUpIndicator(R.drawable.menu_oscuro)
                supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.colorActionBarBackgroundLight)))
            }
        }
    }

}

