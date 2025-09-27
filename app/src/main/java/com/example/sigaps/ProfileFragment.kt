package com.example.sigaps.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.sigaps.R

class ProfileFragment : Fragment() {

    private lateinit var textUserName: TextView
    private lateinit var textUserEmail: TextView
    private lateinit var textUserRole: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("ProfileFragment", "onCreateView CALLED")
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("ProfileFragment", "onViewCreated CALLED")

        // Récupérer les vues
        textUserName = view.findViewById(R.id.text_user_name)
        textUserEmail = view.findViewById(R.id.text_user_email)
        textUserRole = view.findViewById(R.id.text_user_role)

        // Utiliser des valeurs statiques
        textUserName.text = "Nom Prénom"
        textUserEmail.text = "utilisateur@example.com"
        textUserRole.text = "Rôle : Administrateur"
    }
}
