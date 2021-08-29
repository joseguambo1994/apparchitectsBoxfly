package com.example.gnomelist
import Brastlewark
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class GnomeAdapter( val gnomes: List<Brastlewark>): RecyclerView.Adapter<GnomeAdapter.GnomeHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GnomeHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GnomeHolder(layoutInflater.inflate(R.layout.item_gnome,parent, false))
    }

    override fun onBindViewHolder(holder: GnomeHolder, position: Int) {
       // currentBrastlewark = gnomes[position]
        holder.render(gnomes[position])
    }

    override fun getItemCount(): Int {
        return gnomes.size
    }

    inner class GnomeHolder(val view:View): RecyclerView.ViewHolder(view){





        fun  render(brastlewark: Brastlewark){
            view.tvGnome.text = brastlewark.name
//            Picasso.get().load(gnome.imageUrl)
//                .resize(100,100).into(view.ivGnome)
            Glide.with(view)
                .load(brastlewark.thumbnail)
                .into(view.ivGnome)

        }

        init {
            view.setOnClickListener { v:View ->
                val position:Int = absoluteAdapterPosition
                Toast.makeText(view.context,"You clicked on position ${position + 1}",
                Toast.LENGTH_SHORT).show()

                var listOfProfessions = emptyArray<String>()
//                for (i in 0..currentBrastlewark.professions.size - 1) {
//
//
//                    listOfProfessions[i] = currentBrastlewark.professions.get(i)
//                }


                val intent = Intent(v.context, MainActivity2::class.java)
                intent.putExtra("selectedBrastewarkName",gnomes.get(position).name);
                intent.putExtra("selectedBrastewarkThumbnail",gnomes.get(position).thumbnail);
                intent.putStringArrayListExtra("selectedBrastewarkProfessions", ArrayList(gnomes.get(position).professions) );
                v.context.startActivity(intent)

            }


        }


    }


}