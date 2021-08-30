package com.example.gnomelist

import Brastlewark
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_main2.*
import org.json.JSONObject
import kotlin.collections.ArrayList
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {

    val listOfBrastlewark = LinkedList<Brastlewark>()

    val listOfImages = arrayOf(
        "https://lumiere-a.akamaihd.net/v1/images/uk_toystory_chi_woody_n_5b5a006f.png?region=0,0,300,300",
        "https://lumiere-a.akamaihd.net/v1/images/open-uri20150422-20810-10n7ovy_9b42e613.jpeg",
        "http://i.imgur.com/DvpvklR.png",
        "http://goo.gl/gEgYUd",
        "https://style.pk/wp-content/uploads/2015/07/omer-Shahzad-performed-umrah-600x548.jpg",
        "http://developer.android.com/images/activity_lifecycle.png"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getGnomes()
        setContentView(R.layout.activity_main)

        initRecycler()
        println("listOfBrastlewark onCreateonCreate " +listOfBrastlewark)

        svBrastlewark.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val adapter = GnomeAdapter(listOfBrastlewark.toList());
                adapter.filter.filter(newText)
                rvSuperHero.adapter=adapter
                return false
            }

        })
    }

    override fun onResume() {
        super.onResume()
        println("onResume listOfBrastlewark " +listOfBrastlewark)
        getGnomes()
        initRecycler()
        clearSearch()
    }

    fun clearSearch(){

        svBrastlewark.setQuery("", false);
        svBrastlewark.clearFocus();
    }

    fun initRecycler() {
        rvSuperHero.layoutManager = LinearLayoutManager(this)
     //   val gnomesList: List<Gnome> = gnomes.toList()
        println("list + " + listOfBrastlewark)
        getGnomes()
        val adapter = GnomeAdapter(listOfBrastlewark.toList());
        rvSuperHero.adapter=adapter
    }

    fun getGnomes() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://raw.githubusercontent.com/rrafols/mobile_test/master/data.json"

        val stringReq = StringRequest(Request.Method.GET, url,
            { response ->
                listOfBrastlewark.clear();
                println("size after clear" + listOfBrastlewark.size);
                val jsonObject = JSONObject(response)
                val listOfArray = jsonObject.getJSONArray("Brastlewark")
                val oneBrastlewarkFromListOfArray = listOfArray[2].toString()
                val gson = Gson()
                var oneBrastlewarkFromListOfArrayToJson = gson.fromJson(
                    oneBrastlewarkFromListOfArray, Brastlewark::class.java
                )

                for (i in 1..(listOfArray.length()-1)) {
                    val oneBrastlewarkFromListOfArray = listOfArray[i].toString()
                    val gson = Gson()
                    var oneBrastlewarkFromListOfArrayToJson = gson.fromJson(
                        oneBrastlewarkFromListOfArray, Brastlewark::class.java
                    )
                    if (oneBrastlewarkFromListOfArrayToJson.age in 150..220) {
                        oneBrastlewarkFromListOfArrayToJson.thumbnail =
                                //                   "http://i.imgur.com/DvpvklR.png";
                            listOfImages[Random.nextInt(0, listOfImages.size - 1)]
                        listOfBrastlewark.add(oneBrastlewarkFromListOfArrayToJson)
                    }
                }
            },
            { print("Can't download")})
        queue.add(stringReq)
    }



}


