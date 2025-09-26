package com.example.sigaps.ui.collecte

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

class CollecteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("CollecteFragment", "onCreateView CALLED")

        return inflater.inflate(R.layout.fragment_collecte, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("CollecteFragment", "onViewCreated CALLED")

        val buttonEleve = view.findViewById<Button>(R.id.button_collecte_eleve)
        val buttonEcole = view.findViewById<Button>(R.id.button_collecte_ecole)
        val buttonEnseignant = view.findViewById<Button>(R.id.button_collecte_enseignant)

        buttonEleve.setOnClickListener {
            Log.d("CollecteFragment", "Bouton Élève cliqué")
            try {
                findNavController().navigate(R.id.action_navigationCollecte_to_eleveFormFragment)
                Log.d("CollecteFragment", "Navigation vers EleveFormFragment tentée.")
            } catch (e: Exception) {
                Log.e("CollecteFragment", "Erreur de navigation vers Élève: ", e)
                Toast.makeText(context, "Erreur nav Élève: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        }

        buttonEcole.setOnClickListener {
            Log.d("CollecteFragment", "Bouton École cliqué")
            try {
                findNavController().navigate(R.id.action_navigationCollecte_to_ecoleFormFragment)
                Log.d("CollecteFragment", "Navigation vers EcoleFormFragment tentée.")
            } catch (e: Exception) {
                Log.e("CollecteFragment", "Erreur de navigation vers École: ", e)
                Toast.makeText(context, "Erreur nav École: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        }

        buttonEnseignant.setOnClickListener {
            Log.d("CollecteFragment", "Bouton Enseignant cliqué")
            try {
                findNavController().navigate(R.id.action_navigationCollecte_to_enseignantFormFragment)
                Log.d("CollecteFragment", "Navigation vers EnseignantFormFragment tentée.")
            } catch (e: Exception) {
                Log.e("CollecteFragment", "Erreur de navigation vers Enseignant: ", e)
                Toast.makeText(context, "Erreur nav Enseignant: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
