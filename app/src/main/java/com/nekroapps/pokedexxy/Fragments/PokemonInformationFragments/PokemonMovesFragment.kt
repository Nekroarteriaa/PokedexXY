package com.nekroapps.pokedexxy.Fragments.PokemonInformationFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.nekroapps.pokedexxy.Activities.PokedexBank.PokedexBankActivity
import com.nekroapps.pokedexxy.Adapters.PokemonMovesAdapter
import com.nekroapps.pokedexxy.Adapters.PokemonTypeSymbolAdapter
import com.nekroapps.pokedexxy.ConnectionData.PokeMoveDetail
import com.nekroapps.pokedexxy.ConnectionData.PokemonInformation
import com.nekroapps.pokedexxy.Fragments.PokedexBankFragments.PokemonReviewCanvasFragment
import com.nekroapps.pokedexxy.Interfaces.ApiService
import com.nekroapps.pokedexxy.PokeBank.PokeBank
import com.nekroapps.pokedexxy.PokemonObject.Pokemon
import com.nekroapps.pokedexxy.R
import kotlinx.android.synthetic.main.fragment_pokemon_moves.*
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.io.StringReader

class PokemonMovesFragment : Fragment() {

    lateinit var myActivity: PokedexBankActivity

    lateinit var pokemonMoveAdapter: PokemonMovesAdapter
    lateinit var recyclerViewMoves: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_moves, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = PokemonMovesFragment()
            .apply {  }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myActivity = activity as PokedexBankActivity

        val parentFragm = myActivity.currentFragment as PokemonReviewCanvasFragment

        recyclerViewMoves = recyclerPokemonMoves
        recyclerViewMoves.hasFixedSize()
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)

        pokemonMoveAdapter = PokemonMovesAdapter(activity!!, parentFragm.pokeMoveDetail)
        recyclerViewMoves.layoutManager = layoutManager
        recyclerViewMoves.adapter = pokemonMoveAdapter
    }



}