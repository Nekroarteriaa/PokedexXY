package com.nekroapps.pokedexxy.Interfaces

import com.nekroapps.pokedexxy.PokemonObject.Pokemon

interface IIDCardClickListener {
    fun showPokemonReview(position:Int, iDCard:Pokemon)
}