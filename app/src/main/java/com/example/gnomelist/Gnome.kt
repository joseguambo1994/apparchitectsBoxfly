package com.example.gnomelist

data class Gnome (
	val id : Int,
	val name : String,
	var thumbnail : String,
	val age : Int,
	val weight : Double,
	val height : Double,
	val hair_color : String,
	val professions : List<String>,
	val friends : List<String>
)