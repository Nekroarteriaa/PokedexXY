package com.nekroapps.pokedexxy.PokeBank

import android.graphics.Color
import android.util.Log
import com.nekroapps.pokedexxy.PokemonObject.Pokemon
import com.nekroapps.pokedexxy.R

object PokeBank {
    lateinit var PokemonBank: ArrayList<Pokemon>

    fun getPokemon(pokemonName: String) : Pokemon
    {
        var pkmn: Pokemon? = null
        for (pokemon in PokemonBank)
        {
            if(pokemon.name == pokemonName)
                pkmn = pokemon
        }

        return pkmn!!
    }

    fun getPokemonbyIDNumber(pokemonID: String) : Pokemon
    {
        var pkmn: Pokemon? = null
        for (pokemon in PokemonBank)
        {
            if(pokemon.id == pokemonID)
                pkmn = pokemon
        }

        return pkmn!!
    }

    fun getPokemonGifUrl(pokemonID: String):String
    {
        var gifRUL: String = ""
        for (pokemon in PokemonBank)
        {
            if(gifRUL.isEmpty())
            {
                if(pokemon.id == pokemonID)
                    gifRUL = pokemon.pokemon_gifsrc
            }

            else
                break
        }
        return gifRUL
    }

    fun getEeveeEvolutions():ArrayList<Pokemon>
    {
        val eevees: ArrayList<Pokemon> = ArrayList()
        val eevee = getPokemon("Eevee")

        eevees.add(eevee)

        for(i in 1 until eevee.evolutions.size step 1)
        {
            val evoluvedEevee = getPokemonbyIDNumber(eevee.evolutions[i])
            eevees.add(evoluvedEevee)
        }

        return eevees

    }

    fun getAllPokemonbyDesiredType(pokemonType: String) : ArrayList<Pokemon>
    {
        val pkmnTypeList: ArrayList<Pokemon> = ArrayList()
        for (pokemon in PokemonBank)
        {
           for (type in pokemon.typeofpokemon)
           {
               if(pokemonType == type)
                   pkmnTypeList.add(pokemon)
           }
        }

        return pkmnTypeList
    }

    fun getSymbolImage(type:String): Int
    {
        when (type) {
            "Normal" -> return R.drawable.normal
            "Dragon" -> return R.drawable.dragon
            "Psychic" -> return R.drawable.psiquico
            "Electric" -> return R.drawable.electrico
            "Ground" -> return R.drawable.tierra
            "Grass" -> return R.drawable.hoja
            "Poison" -> return R.drawable.venenoso
            "Steel" -> return R.drawable.acero
            "Fairy" -> return R.drawable.fairy
            "Fire" -> return R.drawable.fuego
            "Fighting" -> return R.drawable.luchador
            "Flying" -> return R.drawable.volador
            "Bug" -> return R.drawable.bicho
            "Ghost" -> return R.drawable.fantasma
            "Dark" -> return R.drawable.dark
            "Ice" -> return R.drawable.hielo
            "Water" -> return R.drawable.agua
            else -> return R.drawable.roca
        }
    }

    fun getPokemonIDCardBackgroundColor(types: ArrayList<String>) : IntArray
    {
        lateinit var colorAdd: IntArray

        if(types.size > 1)
        {
            val type0 = getPokemonTypeGradient(types[0])
            val type1 = getPokemonTypeGradient(types[1])

            colorAdd = type0 + type1
        }

        else
        {
            colorAdd = getPokemonTypeGradient(types[0])
        }

        return colorAdd
    }

    fun getPokemonTypeGradient(type:String): IntArray
    {
        when (type) {
            "Normal" -> return intArrayOf(Color.parseColor("#E5DFCF"),Color.parseColor("#A09579"),Color.parseColor("#554D3A"))
            "Dragon" -> return intArrayOf(Color.parseColor("#588EC4"),Color.parseColor("#3E1A90"),Color.parseColor("#3A0A74"))
            "Psychic" -> return intArrayOf(Color.parseColor("#ECBAF0"),Color.parseColor("#F094ED"),Color.parseColor("#B113BE"))
            "Electric" -> return intArrayOf(Color.parseColor("#F6DF6C"),Color.parseColor("#F5C368"),Color.parseColor("#FEAE1B"))
            "Ground" -> return intArrayOf(Color.parseColor("#F2EADB"),Color.parseColor("#D5C196"),Color.parseColor("#9D8349"))
            "Grass" -> return intArrayOf(Color.parseColor("#94DE8A"),Color.parseColor("#62C655"),Color.parseColor("#3E7B36"))
            "Poison" -> return intArrayOf(Color.parseColor("#E648FA"),Color.parseColor("#9B20D1"),Color.parseColor("#51087E"))
            "Steel" -> return intArrayOf(Color.parseColor("#EFEFEF"),Color.parseColor("#9A9A9A"),Color.parseColor("#555555"))
            "Fairy" -> return intArrayOf(Color.parseColor("#F8BFF8"),Color.parseColor("#F29D9D"),Color.parseColor("#F77FF7"))
            "Fire" -> return intArrayOf(Color.parseColor("#F77A26"),Color.parseColor("#FB6A3D"),Color.parseColor("#C54216"))
            "Fighting" -> return intArrayOf(Color.parseColor("#F29D9D"),Color.parseColor("#E27575"),Color.parseColor("#960D0D"))
            "Flying" -> return intArrayOf(Color.parseColor("#ECD1FF"),Color.parseColor("#D293FF"),Color.parseColor("#AF44FC"))
            "Bug" -> return intArrayOf(Color.parseColor("#D0F0CB"),Color.parseColor("#94DE8A"),Color.parseColor("#51C342"))
            "Ghost" -> return intArrayOf(Color.parseColor("#4600FF"),Color.parseColor("#3401BC"),Color.parseColor("#100238"))
            "Dark" -> return intArrayOf(Color.parseColor("#D9D9D9"),Color.parseColor("#6F6F6F"),Color.parseColor("#121212"))
            "Ice" -> return intArrayOf(Color.parseColor("#D8FAF8"),Color.parseColor("#77F7ED"),Color.parseColor("#1DD5C6"))
            "Water" -> return intArrayOf(Color.parseColor("#88C6F2"),Color.parseColor("#57ACF4"),Color.parseColor("#0A79D8"))
            else -> return intArrayOf(Color.parseColor("#E6D7B0"),Color.parseColor("#AD9353"),Color.parseColor("#6F5618"))
        }
    }





    fun printThis(string: String)
    {
        Log.i("myApp", string)
    }

}