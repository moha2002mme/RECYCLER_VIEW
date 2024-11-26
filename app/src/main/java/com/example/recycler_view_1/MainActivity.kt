package com.example.recycler_view_1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recView: RecyclerView
    private lateinit var paysAadbter: PaysAadbter
    private val listPays = mutableListOf<Pays>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        preparerDonnees()
        var trieBTN=findViewById<ToggleButton>(R.id.trieBTN)
        var recherche=findViewById<EditText>(R.id.recherche)
        recView = findViewById(R.id.recView)
        recView.layoutManager = LinearLayoutManager(this)
        paysAadbter = PaysAadbter(this,listPays)
        recView.adapter = paysAadbter



        trieBTN.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                //
                var paysTrie = listPays.sortedBy { it.nom }
                paysAadbter = PaysAadbter(this,paysTrie)
                recView.adapter = paysAadbter
            } else {
                paysAadbter = PaysAadbter(this,listPays)
                recView.adapter = paysAadbter
            }
        }

        recherche.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int,
            ) {
                //
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                var EP = recherche.text.toString()
                var paysFilter = listPays.filter {
                    it.nom.startsWith(EP.trim(),ignoreCase = true)
                }
                paysAadbter = PaysAadbter(this@MainActivity ,paysFilter)
                recView.adapter = paysAadbter

            }

            override fun afterTextChanged(s: Editable?) {
                //
            }
        })



    }

    private fun preparerDonnees() {
        listPays.apply {
            add(Pays(1, "Palestine", "Al-Quds"))
            add(Pays(2, "Maroc", "Rabat"))
            add(Pays(3, "Espagne", "Madrid"))
            add(Pays(4, "Allemagne", "Berlin"))
            add(Pays(5, "Italie", "Rome"))
            add(Pays(6, "États-Unis", "Washington D.C."))
            add(Pays(7, "Canada", "Ottawa"))
            add(Pays(8, "Chine", "Pékin"))
            add(Pays(9, "Japon", "Tokyo"))
            add(Pays(10, "Brésil", "Brasília"))
        }
    }
}