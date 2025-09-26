package com.example.sigaps.ui.forms

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sigaps.data.models.Enseignant
import com.example.sigaps.databinding.FragmentFormEnseignantBinding
import java.text.SimpleDateFormat
import java.util.*

class EnseignantFormFragment : Fragment() {

    private var _binding: FragmentFormEnseignantBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormEnseignantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDatePicker()
        setupDropdowns()
        setupSaveButton()
    }

    private fun setupDatePicker() {
        binding.etDateNaissanceEnseignant.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                binding.etDateNaissanceEnseignant.setText(dateFormat.format(selectedDate.time))
            }, year, month, day)
            datePickerDialog.show()
        }
    }

    private fun setupDropdowns() {
        // Pour la matière
        val matieres = arrayOf("Mathématiques", "Français", "Histoire-Géographie", "Sciences", "Anglais", "Éducation Physique", "Informatique")
        val matiereAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, matieres)
        binding.actvMatiereEnseignant.setAdapter(matiereAdapter)

        // Pour l'école (simulation de données)
        val ecoles = arrayOf("École Primaire A", "Collège B", "Lycée C")
        val ecoleAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, ecoles)
        binding.actvEcoleIdEnseignant.setAdapter(ecoleAdapter)
    }

    private fun setupSaveButton() {
        binding.btnSaveEnseignant.setOnClickListener {
            val nom = binding.etNomEnseignant.text.toString().trim()
            val prenom = binding.etPrenomEnseignant.text.toString().trim()
            val dateNaissance = binding.etDateNaissanceEnseignant.text.toString().trim()
            val genreId = binding.rgGenreEnseignant.checkedRadioButtonId
            val genre = if (genreId == binding.rbMasculinEnseignant.id) "Masculin" else if (genreId == binding.rbFemininEnseignant.id) "Féminin" else ""
            val matiere = binding.actvMatiereEnseignant.text.toString().trim()
            val ecoleId = binding.actvEcoleIdEnseignant.text.toString().trim() // Pour l'instant, on prend le nom de l'école

            if (nom.isEmpty() || prenom.isEmpty() || dateNaissance.isEmpty() || genre.isEmpty() || matiere.isEmpty() || ecoleId.isEmpty()) {
                Toast.makeText(requireContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Ici, vous devrez générer un ID unique pour l'enseignant.
            val enseignant = Enseignant(
                id = UUID.randomUUID().toString(),
                nom = nom,
                prenom = prenom,
                dateNaissance = dateNaissance,
                genre = genre,
                matiere = matiere,
                ecoleId = ecoleId
            )

            // Dans une application réelle, vous enverriez cet objet 'enseignant' à votre base de données ou API.
            Toast.makeText(requireContext(), "Enseignant enregistré: ${enseignant.nom} ${enseignant.prenom}", Toast.LENGTH_LONG).show()
            // findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}