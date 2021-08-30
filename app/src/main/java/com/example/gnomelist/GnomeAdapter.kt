package com.example.gnomelist
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_gnome.view.*
import java.util.*
import kotlin.collections.ArrayList

class GnomeAdapter( val gnomes: List<Gnome>): RecyclerView.Adapter<GnomeAdapter.GnomeHolder>(), Filterable {

    var gnomesFiltered : MutableList<Gnome> = ArrayList()

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

        fun  render(gnome: Gnome) {
            view.tvGnome.text = gnome.name
            // Another tested image load library
            // Picasso.get().load(gnome.imageUrl)
            // .resize(100,100).into(view.ivGnome)
            Glide.with(view)
                .load(gnome.thumbnail)
                .override(100, 100)
                .into(view.ivGnome)
        }

        init {
            view.setOnClickListener { v:View ->
                val position:Int = absoluteAdapterPosition

                val intent = Intent(v.context, GnomeDetailActivity::class.java)
                intent.putExtra("selectedGnomeName",gnomesFiltered.get(position).name);
                intent.putExtra("selectedGnomeThumbnail",gnomesFiltered.get(position).thumbnail);
                intent.putStringArrayListExtra("selectedGnomeProfessions", ArrayList(gnomesFiltered.get(position).professions) );
                v.context.startActivity(intent)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                gnomesFiltered = if (charSearch.isEmpty()) {
                    gnomes.toMutableList()
                } else {
                    var resultList : MutableList<Gnome> = ArrayList();
                    for (row in gnomes) {
                        if(row.name.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))){
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = gnomesFiltered
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                gnomesFiltered = results?.values as MutableList<Gnome>
                notifyDataSetChanged()
            }

        }
    }
}