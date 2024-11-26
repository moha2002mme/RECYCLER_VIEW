package com.example.recycler_view_1

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class PaysAadbter(val context: Context, private val paysList: List<Pays>) :
    RecyclerView.Adapter<PaysAadbter.PaysViewHolder>() {

    class PaysViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomPays: TextView = itemView.findViewById(R.id.tvNomPays)
        val nomCap: TextView = itemView.findViewById(R.id.tvCapitale)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaysViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return PaysViewHolder(view)
    }

    override fun getItemCount(): Int = paysList.size

    override fun onBindViewHolder(holder: PaysViewHolder, position: Int) {
        val pays = paysList[position]
        holder.nomPays.text = pays.nom
        holder.nomCap.text = "Capital: ${pays.capitale}"

        holder.itemView.setOnClickListener {
            val dialog = Dialog(context)
            dialog.setContentView(R.layout.custom_dialog)


            val nompays = dialog.findViewById<EditText>(R.id.nomPays)
            val nomCapital = dialog.findViewById<EditText>(R.id.capitlPays)
            val modifierBtn=dialog.findViewById<Button>(R.id.modifierBtn)


            nompays.setText(pays.nom)
            nomCapital.setText(pays.capitale)

            modifierBtn.setOnClickListener {

                val nouveauNom = nompays.text.toString()
                val nouvelleCapitale = nomCapital.text.toString()

                paysList[position].nom = nouveauNom
                paysList[position].capitale = nouvelleCapitale

//                notifyItemChanged(position)
                notifyDataSetChanged()

                holder.nomPays.text = nompays.toString()
                holder.nomCap.text = "Capital: ${nomCapital}"
                dialog.dismiss()

            }
            dialog.show()
        }
    }
}