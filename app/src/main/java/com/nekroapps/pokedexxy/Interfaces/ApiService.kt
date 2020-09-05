package com.nekroapps.pokedexxy.Interfaces

import com.nekroapps.pokedexxy.PokemonObject.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("raw/{endPoint}")
    fun getPokemonCollectionFromServer(@Path("endPoint") endPoint: String): Call<ArrayList<Pokemon>>
}