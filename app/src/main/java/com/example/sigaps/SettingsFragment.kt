package com.example.sigaps.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sigaps.R

class SettingsFragment : Fragment() {

    private lateinit var switchNotifications: Switch
    private lateinit var switchDarkMode: Switch
    private lateinit var spinnerLanguage: Spinner
    private lateinit var btnResetApp: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        // Initialisation des vues
        switchNotifications = view.findViewById(R.id.switch_notifications)
        switchDarkMode = view.findViewById(R.id.switch_dark_mode)
        spinnerLanguage = view.findViewById(R.id.spinner_language)
        btnResetApp = view.findViewById(R.id.btn_reset_app)

        // Listener pour les notifications
        switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            val status = if (isChecked) "activées" else "désactivées"
            Toast.makeText(requireContext(), "Notifications $status", Toast.LENGTH_SHORT).show()
        }

        // Listener pour le thème sombre
        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            val status = if (isChecked) "activé" else "désactivé"
            Toast.makeText(requireContext(), "Thème sombre $status", Toast.LENGTH_SHORT).show()
            // TODO: Ajouter la logique de changement de thème si nécessaire
        }

        // Listener pour le bouton réinitialiser
        btnResetApp.setOnClickListener {
            // TODO: Ajouter la logique de réinitialisation
            Toast.makeText(requireContext(), "Réinitialisation de l'application", Toast.LENGTH_SHORT).show()
        }

        // Le Spinner peut être initialisé avec un ArrayAdapter si tu veux
        // TODO: Ajouter l’adaptateur et la logique pour changer la langue

        return view
    }
}
