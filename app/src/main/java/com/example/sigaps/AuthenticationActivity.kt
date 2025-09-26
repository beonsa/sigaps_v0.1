package com.example.sigaps

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sigaps.utils.UserPreferences

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        // Initialiser UserPreferences
        userPreferences = UserPreferences(this)

        // Vérifier si l'utilisateur est déjà connecté
        if (userPreferences.isLoggedIn()) {
            navigateToMainActivity()
            return
        }

        setupClickListeners()
    }

    private fun setupClickListeners() {
        val loginButton = findViewById<Button>(R.id.loginButton)
        val registerButton = findViewById<Button>(R.id.registerButton)

        loginButton.setOnClickListener {
            handleLogin()
        }

        registerButton.setOnClickListener {
            handleRegister()
        }
    }

    private fun handleLogin() {
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)

        val username = usernameEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        // Validation des champs
        if (username.isEmpty()) {
            usernameEditText.error = "Le nom d'utilisateur est requis"
            return
        }

        if (password.isEmpty()) {
            passwordEditText.error = "Le mot de passe est requis"
            return
        }

        // Logique d'authentification
        if (authenticateUser(username, password)) {
            // Sauvegarder les informations de connexion
            userPreferences.saveLoginInfo(username)

            Toast.makeText(this, "Connexion réussie !", Toast.LENGTH_SHORT).show()
            navigateToMainActivity()
        } else {
            Toast.makeText(
                this,
                "Nom d'utilisateur ou mot de passe incorrect.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun handleRegister() {
        Toast.makeText(this, "Redirection vers l'inscription...", Toast.LENGTH_SHORT).show()
    }

    private fun authenticateUser(username: String, password: String): Boolean {
        // TODO: Remplacer par votre logique d'authentification réelle
        return username == "user" && password == "password"
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}