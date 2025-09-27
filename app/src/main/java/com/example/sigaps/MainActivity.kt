package com.example.sigaps

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sigaps.utils.UserPreferences
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialiser UserPreferences
        userPreferences = UserPreferences(this)

        setupNavigation()
    }

    private fun setupNavigation() {
        // Récupération du NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        // Associe la barre d'action avec le NavController
        setupActionBarWithNavController(navController)

        // Associe la BottomNavigationView avec le NavController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Désactive la recoloration des icônes pour conserver les couleurs originales
        bottomNavigationView.itemIconTintList = null

        // Associe la navigation
        bottomNavigationView.setupWithNavController(navController)
    }

    // Créer le menu avec le bouton de déconnexion
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // Gérer les clics sur les items du menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                showLogoutDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Afficher le dialogue de confirmation de déconnexion
    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Déconnexion")
            .setMessage("Êtes-vous sûr de vouloir vous déconnecter ?")
            .setPositiveButton("Oui") { _, _ ->
                logout()
            }
            .setNegativeButton("Non", null)
            .show()
    }

    // Effectuer la déconnexion
    private fun logout() {
        userPreferences.logout()
        redirectToAuth()
    }

    // Rediriger vers l'écran d'authentification
    private fun redirectToAuth() {
        val intent = Intent(this, AuthenticationActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
