package com.nekroapps.pokedexxy.PokeBank

import android.util.Log
import com.nekroapps.pokedexxy.PokemonObject.Pokemon

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
                break;
        }
        return gifRUL
    }

    fun getEeveeEvolutions():ArrayList<Pokemon>
    {
        var eevees: ArrayList<Pokemon> = ArrayList()
        var eevee = getPokemon("Eevee")

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
        var pkmnTypeList: ArrayList<Pokemon> = ArrayList()
        for (pokemon in PokemonBank)
        {
           for (type in pokemon.typeofpokemon)
           {
               if(pokemonType == type)
                   pkmnTypeList.add(pokemon)
           }
        }

        return pkmnTypeList!!
    }



    fun printThis(string: String)
    {
        Log.i("myApp", string);
    }

}