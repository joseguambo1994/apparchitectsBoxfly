package com.example.gnomelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_profession.view.*

class ProfessionAdapter(private val professions: ArrayList<String>): RecyclerView.Adapter<ProfessionAdapter.ProfessionHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessionHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProfessionHolder(layoutInflater.inflate(R.layout.item_profession,parent, false))
    }

    override fun onBindViewHolder(holder: ProfessionHolder, position: Int) {
        holder.render(professions[position])
    }

    override fun getItemCount(): Int {
        return professions.size
    }

    inner class ProfessionHolder(private val view: View): RecyclerView.ViewHolder(view){
        fun render(professionName:String){
            view.tvProfession.text= professionName
        }
    }
}