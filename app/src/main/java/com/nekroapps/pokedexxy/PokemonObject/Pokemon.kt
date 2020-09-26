package com.nekroapps.pokedexxy.PokemonObject

import android.util.Log
import com.nekroapps.pokedexxy.PokeBank.PokeBank
import java.io.Serializable

data class Pokemon (var name: String,
                    var id: String,
                    var xdescription: String,
                    var ydescription: String,
                    var height: String,
                    var category: String,
                    var weight: String,
                    var typeofpokemon: ArrayList<String>,
                    var weaknesses: ArrayList<String>,
                    var evolutions: ArrayList<String>,
                    var abilities: ArrayList<String>,
                    var hp: Int,
                    var attack: Int,
                    var defense: Int,
                    var special_attack: Int,
                    var special_defense: Int,
                    var speed: Int,
                    var total: Int,
                    var male_percentage: String,
                    var female_percentage: String,
                    var genderless: Int,
                    var cycles: String,
                    var egg_groups: String,
                    var evolvedfrom: String,
                    var reason: String,
                    var base_exp: String):Serializable
{
    var pokemon_imagesrc: String = ""
    var pokemon_gifsrc: String = ""

    fun getPokemonContent()
    {
        val pokeNumbr = gettingPokemonNumber(id)
        if(pokeNumbr <= 807)
        pokemon_imagesrc = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/ultra-sun-ultra-moon/${pokeNumbr}.png"

        else
        {
            pokemon_imagesrc = if(pokeNumbr == 808) {
                "https://img.pokemondb.net/sprites/home/normal/meltan.png"
            } else {
                "https://img.pokemondb.net/sprites/home/normal/melmetal.png"
            }
        }

        var nameLowercase = name.toLowerCase()
        nameLowercase = nameLowercase.replace("\\s".toRegex(),"")
        nameLowercase = nameLowercase.replace("'","")
        nameLowercase = nameLowercase.replace("♀","f")
        nameLowercase = nameLowercase.replace("♂","")
        nameLowercase = nameLowercase.replace("-","")
        nameLowercase = nameLowercase.replace("é","e")
        nameLowercase = nameLowercase.replace(".","")

        pokemon_gifsrc = "https://play.pokemonshowdown.com/sprites/xyani/${nameLowercase}.gif"
        //pokemon_gifsrc = "https://raw.githubusercontent.com/tdmalone/pokecss-media/master/graphics/pokemon/ani-front/${nameLowercase}.gif"

    }

    fun gettingPokemonNumber(idNmbr:String):Int
    {
        var midNmbr = idNmbr.replace("#","")
        var numberString: Int = Integer.parseInt(midNmbr)

        return numberString
    }

//    fun imagesURL()
//    {
//        val pokeNumbr = gettingPokemonNumber(id)
//        pokemon_imagesrc = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/ultra-sun-ultra-moon/${pokeNumbr}.png"
//
//
//        var nameLowercase = name.toLowerCase()
//        nameLowercase = nameLowercase.replace("\\s".toRegex(),"")
//        nameLowercase = nameLowercase.replace("'","")
//        nameLowercase = nameLowercase.replace("♀","f")
//        nameLowercase = nameLowercase.replace("♂","")
//        nameLowercase = nameLowercase.replace("-","")
//        nameLowercase = nameLowercase.replace("é","e")
//        nameLowercase = nameLowercase.replace(".","")
//
//        pokemon_gifsrc = "https://play.pokemonshowdown.com/sprites/xyani/${nameLowercase}.gif"
//
//        PokeBank.printThis("aqui")
//    }

}


