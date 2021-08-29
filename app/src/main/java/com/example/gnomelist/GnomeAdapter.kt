package com.example.gnomelist
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_gnome.*
import kotlinx.android.synthetic.main.item_gnome.view.*

class GnomeAdapter( val gnomes: List<Gnome>): RecyclerView.Adapter<GnomeAdapter.GnomeHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GnomeHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GnomeHolder(layoutInflater.inflate(R.layout.item_gnome,parent, false))
    }

    override fun onBindViewHolder(holder: GnomeHolder, position: Int) {
        holder.render(gnomes[position])
    }

    override fun getItemCount(): Int {
        return gnomes.size
    }

    class GnomeHolder(val view:View): RecyclerView.ViewHolder(view){

        fun  render(gnome: Gnome){
            view.tvGnome.text = gnome.name
            Picasso.get().load(gnome.imageUrl)
                .resize(100,100).into(view.ivGnome)
        }
    }


}