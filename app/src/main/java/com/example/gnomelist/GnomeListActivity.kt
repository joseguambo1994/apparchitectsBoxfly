package com.example.gnomelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.gnome_list.*
import java.util.*
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject
import kotlin.random.Random

class GnomeListActivity : AppCompatActivity() {

    val listOfGnome = LinkedList<Gnome>()

    private val listOfImages = arrayOf(
        "https://lumiere-a.akamaihd.net/v1/images/uk_toystory_chi_woody_n_5b5a006f.png?region=0,0,300,300",
        "https://lumiere-a.akamaihd.net/v1/images/open-uri20150422-20810-10n7ovy_9b42e613.jpeg",
        "http://i.imgur.com/DvpvklR.png",
        "http://goo.gl/gEgYUd",
        "https://style.pk/wp-content/uploads/2015/07/omer-Shahzad-performed-umrah-600x548.jpg",
        "http://developer.android.com/images/activity_lifecycle.png"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gnome_list)
        getGnomes()

        svGnome.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val adapter = GnomeAdapter(listOfGnome.toList());
                adapter.filter.filter(newText)
                rvGnomes.adapter=adapter
                return false
            }

        })
    }

    override fun onStop() {
        super.onStop()
        println("onResume is executed")
        getGnomes()
        initRecycler()
        clearSearch()
    }

    private fun clearSearch(){
        svGnome.setQuery("", false);
        svGnome.clearFocus();
    }

    private fun initRecycler() {
        rvGnomes.layoutManager = LinearLayoutManager(this)
        val adapter = GnomeAdapter(listOfGnome.toList());
        rvGnomes.adapter=adapter
    }

    private fun getGnomes() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://raw.githubusercontent.com/rrafols/mobile_test/master/data.json"

        val stringReq = StringRequest(Request.Method.GET, url,
            { response ->
                listOfGnome.clear();
                println("size after clear" + listOfGnome.size);
                val jsonObject = JSONObject(response)
                val listOfArray = jsonObject.getJSONArray("Brastlewark")

                for (i in 1..(listOfArray.length()-1)) {
                    val oneGnomeFromListOfArray = listOfArray[i].toString()
                    val gson = Gson()
                    var oneGnomeFromListOfArrayToJson = gson.fromJson(
                        oneGnomeFromListOfArray, Gnome::class.java
                    )
                    if (oneGnomeFromListOfArrayToJson.age in 150..220) {
                        oneGnomeFromListOfArrayToJson.thumbnail =
                                //                   "http://i.imgur.com/DvpvklR.png";
                            listOfImages[Random.nextInt(0, listOfImages.size - 1)]
                        listOfGnome.add(oneGnomeFromListOfArrayToJson)
                    }
                }
                initRecycler()
            },
            { print("Can't download")})
        queue.add(stringReq)
    }



}


