package com.example.sigaps.ui.forms

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.sigaps.data.models.Ecole
import com.example.sigaps.databinding.FragmentFormEcoleBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*

class EcoleFormFragment : Fragment() {

    private var _binding: FragmentFormEcoleBinding? = null
    private val binding get() = _binding!!

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var currentLatitude: Double = 0.0
    private var currentLongitude: Double = 0.0

    // Lanceur de permission pour la localisation
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                getCurrentLocation()
            } else {
                Toast.makeText(requireContext(), "Permission de localisation refusée.", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormEcoleBinding.inflate(inflater, container, false)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGetLocationEcole.setOnClickListener {
            checkLocationPermissionAndGetLocation()
        }

        binding.btnSaveEcole.setOnClickListener {
            saveEcole()
        }
    }

    private fun checkLocationPermissionAndGetLocation() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                getCurrentLocation()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                // Expliquer pourquoi la permission est nécessaire
                Toast.makeText(requireContext(), "La localisation est nécessaire pour enregistrer l'emplacement de l'école.", Toast.LENGTH_LONG).show()
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        currentLatitude = it.latitude
                        currentLongitude = it.longitude
                        binding.tvLocationEcole.text = "Localisation: Lat ${String.format("%.4f", currentLatitude)}, Lon ${String.format("%.4f", currentLongitude)}"
                        Toast.makeText(requireContext(), "Localisation obtenue.", Toast.LENGTH_SHORT).show()
                    } ?: run {
                        Toast.makeText(requireContext(), "Impossible d'obtenir la localisation actuelle.", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "Erreur lors de l'obtention de la localisation: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun saveEcole() {
        val nom = binding.etNomEcole.text.toString().trim()
        val adresse = binding.etAdresseEcole.text.toString().trim()
        val ville = binding.etVilleEcole.text.toString().trim()
        val telephone = binding.etTelephoneEcole.text.toString().trim()
        val email = binding.etEmailEcole.text.toString().trim()

        if (nom.isEmpty() || adresse.isEmpty() || ville.isEmpty() || telephone.isEmpty() || email.isEmpty()) {
            Toast.makeText(requireContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            return
        }

        if (currentLatitude == 0.0 && currentLongitude == 0.0) {
            Toast.makeText(requireContext(), "Veuillez géolocaliser l'école d'abord", Toast.LENGTH_SHORT).show()
            return
        }

        val ecole = Ecole(
            id = UUID.randomUUID().toString(),
            nom = nom,
            adresse = adresse,
            ville = ville,
            telephone = telephone,
            email = email,
            latitude = currentLatitude,
            longitude = currentLongitude
        )

        Toast.makeText(requireContext(), "École enregistrée: ${ecole.nom}", Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}