package com.example.sigaps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val welcomeTextView: TextView = findViewById(R.id.welcomeDashboardText)
        val logoutButton: Button = findViewById(R.id.logoutButton)

        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userEmail = sharedPreferences.getString("current_user_email", "Utilisateur")

        welcomeTextView.text = "Bienvenue, $userEmail, sur votre Tableau de Bord !"

        logoutButton.setOnClickListener {

            sharedPreferences.edit().putBoolean("is_logged_in", false).apply()
            sharedPreferences.edit().remove("current_user_email").apply() // Supprimer l'email aussi

            val intent = Intent(this, AuthenticationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}