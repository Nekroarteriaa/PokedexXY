package com.nekroapps.pokedexxy.Fragments.PokedexBankFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nekroapps.pokedexxy.Activities.PokedexBank.PokedexBankActivity
import com.nekroapps.pokedexxy.Adapters.PokemonIDCardsArrayAdapter
import com.nekroapps.pokedexxy.PokeBank.PokeBank
import com.nekroapps.pokedexxy.R
import kotlinx.android.synthetic.main.fragment_pokemon_list.*

class PokemonTypeListFragment : Fragment() {

    lateinit var myActivity: PokedexBankActivity
    lateinit var pokemonIDCardsArrayAdapter : PokemonIDCardsArrayAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager

    companion object {
        @JvmStatic
        fun newInstance() = PokemonTypeListFragment()
            .apply {  }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_type_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingVariables()
        showPokedexGrid()
    }

    fun settingVariables()
    {
        myActivity = activity as PokedexBankActivity
        recyclerView = myActivity.findViewById(R.id.pokedexViewCards)
        recyclerView.hasFixedSize()
        layoutManager = GridLayoutManager(activity, 2)

        myActivity.currentFragment = this
    }

    fun showPokedexGrid()
    {
        var pokemonSearchedTypeList = PokeBank.getAllPokemonbyDesiredType(myActivity.selectedBadge)
        pokemonIDCardsArrayAdapter =  PokemonIDCardsArrayAdapter(activity!!.baseContext, pokemonSearchedTypeList, myActivity)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = pokemonIDCardsArrayAdapter


    }


}