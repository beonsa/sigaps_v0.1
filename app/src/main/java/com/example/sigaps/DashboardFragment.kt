package com.example.sigaps

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat

// Imports pour MPAndroidChart
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        // Récupération des TextViews pour les totaux
        val totalElevesTextView: TextView = view.findViewById(R.id.totalElevesTextView)
        val totalClassesTextView: TextView = view.findViewById(R.id.totalClassesTextView)

        // Exemple de mise à jour des données (vous les récupéreriez normalement d'une base de données/API)
        totalElevesTextView.text = "100" // Exemple
        totalClassesTextView.text = "5"   // Exemple

        // Configuration du Graphique à Barres
        val barChart = view.findViewById<BarChart>(R.id.barChartPlaceholder)
        barChart?.let {
            setupBarChart(it)
        }

        // Configuration du Graphique en Secteurs (PieChart)
        val pieChart = view.findViewById<PieChart>(R.id.pieChartPlaceholder)
        pieChart?.let {
            setupPieChart(it)
        }

        // Configuration du Graphique Linéaire (LineChart)
        val lineChart = view.findViewById<LineChart>(R.id.lineChartPlaceholder)
        lineChart?.let {
            setupLineChart(it)
        }

        return view
    }

    private fun setupBarChart(chart: BarChart) {
        val entries = ArrayList<BarEntry>()
        // Exemples de données : Répartition par classe
        entries.add(BarEntry(0f, 8f)) // 6ème A
        entries.add(BarEntry(1f, 6f)) // 6ème B
        entries.add(BarEntry(2f, 7f)) // 5ème A
        entries.add(BarEntry(3f, 4f)) // 5ème B

        val dataSet = BarDataSet(entries, "Répartition par Classe")
        // Couleurs pour chaque barre
        dataSet.setColors(
            ContextCompat.getColor(requireContext(), R.color.green_chart),
            ContextCompat.getColor(requireContext(), R.color.yellow_chart),
            ContextCompat.getColor(requireContext(), R.color.red_chart),
            ContextCompat.getColor(requireContext(), R.color.blue_chart)
        )
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 12f

        val barData = BarData(dataSet)
        barData.barWidth = 0.9f // Largeur des barres

        chart.data = barData
        chart.setFitBars(true)
        chart.description.isEnabled = false
        chart.legend.isEnabled = false

        // Configuration de l'axe X
        val xAxis = chart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(arrayOf("6ème A", "6ème B", "5ème A", "5ème B"))
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(true)

        // Configuration de l'axe Y gauche
        val yAxisLeft = chart.axisLeft
        yAxisLeft.axisMinimum = 0f
        yAxisLeft.setDrawGridLines(true)

        // Configuration de l'axe Y droit
        val yAxisRight = chart.axisRight
        yAxisRight.isEnabled = false

        chart.animateY(1000)
        chart.invalidate()
    }

    private fun setupPieChart(chart: PieChart) {
        val entries = ArrayList<PieEntry>()
        // Exemples de données : Masculin 60%, Féminin 40%
        entries.add(PieEntry(60f, "Masculin"))
        entries.add(PieEntry(40f, "Féminin"))

        val dataSet = PieDataSet(entries, "")

        // Couleurs pour les tranches (celles que vous avez définies pour la légende XML)
        val colors = ArrayList<Int>()
        colors.add(Color.parseColor("#FF4081"))
        colors.add(Color.parseColor("#2196F3"))
        dataSet.colors = colors

        dataSet.valueTextColor = Color.WHITE
        dataSet.valueTextSize = 14f
        dataSet.sliceSpace = 3f

        val pieData = PieData(dataSet)
        // Afficher les valeurs en pourcentage
        pieData.setValueFormatter(com.github.mikephil.charting.formatter.PercentFormatter(chart))
        chart.data = pieData

        chart.description.isEnabled = false
        chart.legend.isEnabled = false

        chart.isDrawHoleEnabled = true // Pour un graphique en "donut"
        chart.setHoleColor(Color.WHITE)
        chart.transparentCircleRadius = 58f // Rayon du cercle transparent autour du trou
        chart.holeRadius = 50f // Rayon du trou central

        chart.setEntryLabelColor(Color.BLACK) // Couleur des labels des tranches (Masculin, Féminin)
        chart.setEntryLabelTextSize(12f)
        chart.setUsePercentValues(true)

        chart.animateY(1000)
        chart.invalidate()
    }

    private fun setupLineChart(chart: LineChart) {
        val entries = ArrayList<Entry>()
        // Exemples de données : collecte sur 6 mois
        entries.add(Entry(0f, 1500f))
        entries.add(Entry(1f, 1800f))
        entries.add(Entry(2f, 1200f))
        entries.add(Entry(3f, 2200f))
        entries.add(Entry(4f, 1900f))
        entries.add(Entry(5f, 2500f))

        val dataSet = LineDataSet(entries, "Collecte Mensuelle")
        dataSet.color = ContextCompat.getColor(requireContext(), R.color.blue_chart)
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 10f
        dataSet.lineWidth = 2f
        dataSet.setCircleColor(ContextCompat.getColor(requireContext(), R.color.blue_chart)) // Couleur des points
        dataSet.circleRadius = 4f
        dataSet.setDrawCircleHole(false) // Pas de trou au centre des points
        dataSet.setDrawValues(true) // Afficher les valeurs sur les points

        // Remplissage sous la ligne (optionnel)
        dataSet.setDrawFilled(true)

        val fillColor = ContextCompat.getColor(requireContext(), R.color.blue_chart)
        dataSet.fillColor = fillColor
        dataSet.fillAlpha = 50

        val lineData = LineData(dataSet)
        chart.data = lineData

        chart.description.isEnabled = false
        chart.legend.isEnabled = true

        // Configuration de l'axe X
        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.setDrawGridLines(false)
        val months = arrayOf("Jan", "Fév", "Mar", "Avr", "Mai", "Jui")
        xAxis.valueFormatter = IndexAxisValueFormatter(months)

        // Configuration de l'axe Y gauche
        val yAxisLeft = chart.axisLeft
        yAxisLeft.axisMinimum = 0f
        yAxisLeft.setDrawGridLines(true)

        // Configuration de l'axe Y droit
        val yAxisRight = chart.axisRight
        yAxisRight.isEnabled = false

        chart.animateX(1000)
        chart.invalidate()
    }
}
