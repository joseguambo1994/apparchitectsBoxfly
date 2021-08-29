package com.example.gnomelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.Collections.emptyList
import java.util.Collections.list

class MainActivity : AppCompatActivity() {

    var gnomes = arrayOf(
        Gnome("name1","http://www.publicdomainpictures.net/pictures/10000/nahled/thinking-monkey-11282237747K8xB.jpg"),
        Gnome("name2", "http://i.imgur.com/tGbaZCY.jpg"),
        Gnome("name2", "https://imgur.com/a/48DpmEb"),
        Gnome("name2", "http://i.imgur.com/DvpvklR.png"),
        Gnome("white-hem","http://www.publicdomainpictures.net/pictures/120000/nahled/white-hen.jpg"),
        Gnome("white-hen https","https://www.publicdomainpictures.net/pictures/120000/nahled/white-hen.jpg"),
        Gnome("name2", "http://i.imgur.com/DvpvklR.png"),
        Gnome("name2", "http://www.publicdomainpictures.net/pictures/10000/nahled/1-1275919724d1Oh.jpg"),
        Gnome("name2", "http://i.imgur.com/DvpvklR.png")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       //pupolateList()
        println("gnomes 0 : "+gnomes[0])
        println("gnomes 1 : "+gnomes[1])
        setContentView(R.layout.activity_main)

        initRecycler()


    }


    fun initRecycler() {
        rvSuperHero.layoutManager = LinearLayoutManager(this)
        val gnomesList: List<Gnome> = gnomes.toList()
        println("list + " + gnomesList)
        val adapter = GnomeAdapter(gnomesList);
        rvSuperHero.adapter=adapter
    }



}