package com.nekroapps.pokedexxy.Activities.PokedexBank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nekroapps.pokedexxy.Fragments.PokedexBankFragments.PokemonListFragment
import com.nekroapps.pokedexxy.Fragments.PokedexBankFragments.PokemonReviewCanvasFragment
import com.nekroapps.pokedexxy.Fragments.PokedexBankFragments.PokemonTypeListFragment
import com.nekroapps.pokedexxy.Interfaces.IIDCardClickListener
import com.nekroapps.pokedexxy.Interfaces.ITypeBadgeListener
import com.nekroapps.pokedexxy.PokemonObject.Pokemon
import com.nekroapps.pokedexxy.R

interface IRestartPokemonList{
    fun onRestartPokeList()
    fun onShowPokemonSearchingResults()
}

class PokedexBankActivity : AppCompatActivity(), IIDCardClickListener, ITypeBadgeListener {

    lateinit var selectedIDCard: Pokemon
    lateinit var currentFragment: Fragment
    var isShowingSearchResults: Boolean = false
    lateinit var fragmentInterface: IRestartPokemonList
    lateinit var selectedBadge: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex_bank)

        val frag = PokemonListFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragment_holder, frag).addToBackStack(null).commit()

    }

    override fun showPokemonReview(position: Int, iDCard: Pokemon) {

        selectedIDCard = iDCard
        val frag = PokemonReviewCanvasFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_holder, frag).addToBackStack(null).commit()


    }

    override fun typeBadgeClicked(position: Int, typeBadge: String) {

        selectedBadge = typeBadge
        val frag = PokemonTypeListFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_holder, frag).addToBackStack(null).commit()
    }

    override fun onBackPressed() {

        if(isShowingSearchResults)
        {
            when(supportFragmentManager.backStackEntryCount)
            {
                1->  fragmentInterface.onRestartPokeList()
                2 -> {super.onBackPressed()
                        fragmentInterface.onShowPokemonSearchingResults()}
                else -> {super.onBackPressed()}
            }

        }

        else if(!isShowingSearchResults)
        {

            when(supportFragmentManager.backStackEntryCount)
            {
                1-> {
                    super.onBackPressed()/*
                    val intent = Intent(this, PokedexMainMenuActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)*/}
                else -> {super.onBackPressed()}
            }
        }

    }

    fun setPokemonListRestarterFragmentObject(interfaceObject: IRestartPokemonList)
    {
        this.fragmentInterface = interfaceObject
    }


}