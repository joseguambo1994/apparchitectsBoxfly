package com.example.gnomelist
import Brastlewark
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_gnome.*
import kotlinx.android.synthetic.main.item_gnome.view.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.coroutineContext

class GnomeAdapter( val gnomes: List<Brastlewark>): RecyclerView.Adapter<GnomeAdapter.GnomeHolder>(), Filterable {

    var gnomesFiltered : MutableList<Brastlewark> = ArrayList()

    init {
        gnomesFiltered = gnomes.toMutableList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GnomeHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GnomeHolder(layoutInflater.inflate(R.layout.item_gnome,parent, false))
    }

    override fun onBindViewHolder(holder: GnomeHolder, position: Int) {
        holder.render(gnomesFiltered[position])
    }

    override fun getItemCount(): Int {
        return gnomesFiltered.size
    }

    inner class GnomeHolder(val view:View): RecyclerView.ViewHolder(view){

        fun  render(brastlewark: Brastlewark) {
            view.tvGnome.text = brastlewark.name
//            Picasso.get().load(gnome.imageUrl)
//                .resize(100,100).into(view.ivGnome)
            Glide.with(view)
                .load(brastlewark.thumbnail)
                .override(100, 100)
                .into(view.ivGnome)
        }

        init {
            view.setOnClickListener { v:View ->
                val position:Int = absoluteAdapterPosition
                Toast.makeText(view.context,"You clicked on position ${position + 1}",
                Toast.LENGTH_SHORT).show()

                val intent = Intent(v.context, MainActivity2::class.java)
                intent.putExtra("selectedBrastlewarkName",gnomesFiltered.get(position).name);
                intent.putExtra("selectedBrastlewarkThumbnail",gnomesFiltered.get(position).thumbnail);
                intent.putStringArrayListExtra("selectedBrastlewarkProfessions", ArrayList(gnomesFiltered.get(position).professions) );
                v.context.startActivity(intent)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    gnomesFiltered = gnomes.toMutableList()
                } else {
                    var resultList : MutableList<Brastlewark> = ArrayList();
                    for (row in gnomes) {
                        if(row.name.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))){
                            resultList.add(row)
                        }
                        println("row.name.toString()" + row.name)
                    }
                    gnomesFiltered = resultList
                    println("gnomesFiltered.size" + gnomesFiltered.size)

                }
                val filterResults = FilterResults()
                filterResults.values = gnomesFiltered
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                gnomesFiltered = results?.values as MutableList<Brastlewark>
                notifyDataSetChanged()
            }

        }
    }
}