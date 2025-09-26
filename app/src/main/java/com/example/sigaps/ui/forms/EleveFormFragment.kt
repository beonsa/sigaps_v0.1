package com.example.sigaps.ui.forms

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sigaps.data.models.Eleve
import com.example.sigaps.databinding.FragmentFormEleveBinding
import java.text.SimpleDateFormat
import java.util.*

class EleveFormFragment : Fragment() {

    private var _binding: FragmentFormEleveBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormEleveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDatePicker()
        setupDropdowns()
        setupSaveButton()
    }

    private fun setupDatePicker() {
        binding.etDateNaissanceEleve.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                binding.etDateNaissanceEleve.setText(dateFormat.format(selectedDate.time))
            }, year, month, day)
            datePickerDialog.show()
        }
    }

    private fun setupDropdowns() {
        // Pour la classe
        val classes = arrayOf("CP", "CE1", "CE2", "CM1", "CM2", "6ème", "5ème", "4ème", "3ème", "Seconde", "Première", "Terminale")
        val classeAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, classes)
        binding.actvClasseEleve.setAdapter(classeAdapter)

        // Pour l'école (simulation de données)
        val ecoles = arrayOf("École Primaire A", "Collège B", "Lycée C")
        val ecoleAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, ecoles)
        binding.actvEcoleIdEleve.setAdapter(ecoleAdapter)
    }

    private fun setupSaveButton() {
        binding.btnSaveEleve.setOnClickListener {
            val nom = binding.etNomEleve.text.toString().trim()
            val prenom = binding.etPrenomEleve.text.toString().trim()
            val dateNaissance = binding.etDateNaissanceEleve.text.toString().trim()
            val genreId = binding.rgGenreEleve.checkedRadioButtonId
            val genre = if (genreId == binding.rbMasculinEleve.id) "Masculin" else if (genreId == binding.rbFemininEleve.id) "Féminin" else ""
            val classe = binding.actvClasseEleve.text.toString().trim()
            val ecoleId = binding.actvEcoleIdEleve.text.toString().trim() // Pour l'instant, on prend le nom de l'école

            if (nom.isEmpty() || prenom.isEmpty() || dateNaissance.isEmpty() || genre.isEmpty() || classe.isEmpty() || ecoleId.isEmpty()) {
                Toast.makeText(requireContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Ici, vous devrez générer un ID unique pour l'élève.
            // Pour l'exemple, nous allons utiliser un UUID simple.
            val eleve = Eleve(
                id = UUID.randomUUID().toString(),
                nom = nom,
                prenom = prenom,
                dateNaissance = dateNaissance,
                genre = genre,
                classe = classe,
                ecoleId = ecoleId
            )

            // Dans une application réelle, vous enverriez cet objet 'eleve' à votre base de données ou API.
            // Pour l'instant, nous affichons un Toast.
            Toast.makeText(requireContext(), "Élève enregistré: ${eleve.nom} ${eleve.prenom}", Toast.LENGTH_LONG).show()

            // Vous pouvez ajouter une logique pour réinitialiser le formulaire ou naviguer
            // findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}