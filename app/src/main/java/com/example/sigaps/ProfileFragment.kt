package com.example.sigaps.ui.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sigaps.R
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("ProfileFragment", "onCreateView CALLED")
        // auth = FirebaseAuth.getInstance() // Décommentez et initialisez si besoin
        return inflater.inflate(R.layout.fragment_profile, container, false) // Assurez-vous que fragment_profile.xml contient button_logout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("ProfileFragment", "onViewCreated CALLED")

        val buttonLogout = view.findViewById<Button>(R.id.button_logout) // Assurez-vous que cet ID existe dans fragment_profile.xml

        buttonLogout.setOnClickListener {
            Log.d("ProfileFragment", "Bouton Déconnexion cliqué")
            performLogout()
        }
    }

    private fun performLogout() {


        Toast.makeText(context, "Vous avez été déconnecté.", Toast.LENGTH_SHORT).show()

        try {
            findNavController().navigate(R.id.action_profile_to_login)
            Log.d("ProfileFragment", "Navigation vers loginFragment tentée.")
        } catch (e: Exception) {
            Log.e("ProfileFragment", "Erreur lors de la navigation vers loginFragment: ", e)

            Toast.makeText(context, "Erreur de redirection: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }
    }
}
