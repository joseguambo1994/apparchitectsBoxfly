package com.example.gnomelist

import Brastlewark
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val listOfBrastlewark = LinkedList<Brastlewark>()
//    var gnomes = arrayOf(
//        Gnome("name1","https://www.publicdomainpictures.net/pictures/10000/nahled/thinking-monkey-11282237747K8xB.jpg"),
//        Gnome("name2", "https://i.imgur.com/tGbaZCY.jpg"),
//        Gnome("name2", "https://imgur.com/a/48DpmEb"),
//        Gnome("name2", "https://i.imgur.com/DvpvklR.png"),
//        Gnome("white-hem","https://www.publicdomainpictures.net/pictures/120000/nahled/white-hen.jpg"),
//        Gnome("white-hen https","https://www.publicdomainpictures.net/pictures/120000/nahled/white-hen.jpg"),
//        Gnome("name2", "https://i.imgur.com/DvpvklR.png"),
//        Gnome("name2", "https://www.publicdomainpictures.net/pictures/10000/nahled/1-1275919724d1Oh.jpg"),
//        Gnome("name2", "https://i.imgur.com/DvpvklR.png")
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getUsers()
        initRecycler()
        println("listOfBrastlewark " +listOfBrastlewark)
    }

    override fun onResume() {
        super.onResume()
        println("onResume listOfBrastlewark " +listOfBrastlewark)
        getUsers()
        initRecycler()

    }


    fun initRecycler() {
        rvSuperHero.layoutManager = LinearLayoutManager(this)
     //   val gnomesList: List<Gnome> = gnomes.toList()
        println("list + " + listOfBrastlewark)
        getUsers()
        val adapter = GnomeAdapter(listOfBrastlewark.toList());
        rvSuperHero.adapter=adapter
    }

    fun getUsers() {



        val queue = Volley.newRequestQueue(this)
        val url = "https://raw.githubusercontent.com/rrafols/mobile_test/master/data.json"

        val stringReq = StringRequest(Request.Method.GET, url,
            { response ->

                listOfBrastlewark.clear();
                println("size after clear" + listOfBrastlewark.size);
                val jsonObject = JSONObject(response)
                val listOfArray = jsonObject.getJSONArray("Brastlewark")
                println("length "+ listOfArray.length())

                println("listOfArray[2] +" +listOfArray[2])

                println("listOfArray" + listOfArray)

                val oneBrastlewarkFromListOfArray = listOfArray[2].toString()
                val gson = Gson()
                var oneBrastlewarkFromListOfArrayToJson = gson.fromJson(
                    oneBrastlewarkFromListOfArray, Brastlewark::class.java
                )
                println("oneBrastlewarkFromListOfArrayToJson " + oneBrastlewarkFromListOfArrayToJson)

                for (i in 1..(listOfArray.length()-1)) {
                    val oneBrastlewarkFromListOfArray = listOfArray[i].toString()
                    val gson = Gson()
                    var oneBrastlewarkFromListOfArrayToJson = gson.fromJson(
                        oneBrastlewarkFromListOfArray, Brastlewark::class.java
                    )
                    if (oneBrastlewarkFromListOfArrayToJson.age in 150..220) {
                        oneBrastlewarkFromListOfArrayToJson.thumbnail =
                            "http://i.imgur.com/DvpvklR.png";
                        listOfBrastlewark.add(oneBrastlewarkFromListOfArrayToJson)
                    }
                }

                println("mutableList.size + "+listOfBrastlewark.size);
                println("First two items")
                println("mutableList[0]")
                println(listOfBrastlewark.get(0))
                println("mutableList[1]")
                println(listOfBrastlewark.get(1))
                println("mutableList[2]")
                println(listOfBrastlewark.get(2))

            },
            { print("Can't download")})
        queue.add(stringReq)
    }



}