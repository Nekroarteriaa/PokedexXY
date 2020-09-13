package com.nekroapps.pokedexxy.Activities.PokedexBank

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.nekroapps.pokedexxy.Fragments.PokedexBankFragments.PokemonListFragment
import com.nekroapps.pokedexxy.Fragments.PokedexBankFragments.PokemonReviewCanvasFragment
import com.nekroapps.pokedexxy.Fragments.PokedexBankFragments.PokemonTypeListFragment
import com.nekroapps.pokedexxy.Interfaces.IIDCardClickListener
import com.nekroapps.pokedexxy.Interfaces.ITypeBadgeListener
import com.nekroapps.pokedexxy.PokeBank.PokeBank
import com.nekroapps.pokedexxy.PokemonObject.Pokemon
import com.nekroapps.pokedexxy.R
import kotlinx.android.synthetic.main.fragment_pokemon_list.*

interface IRestartPokemonList{
    fun onRestartPokeList()
    fun onShowPokemonSearchingResults()
}

class PokedexBankActivity : AppCompatActivity(), IIDCardClickListener, ITypeBadgeListener {

    lateinit var selectedIDCard: Pokemon
    lateinit var currentFragment: Fragment
    lateinit var fragmentInterface: IRestartPokemonList
    lateinit var selectedBadge: String
    var lastSearchedWords: String = ""

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

        //super.onBackPressed()

        when(supportFragmentManager.backStackEntryCount)
        {
            1->  fragmentInterface.onRestartPokeList()
            2 -> {super.onBackPressed()
                    fragmentInterface.onShowPokemonSearchingResults()}
            else -> {super.onBackPressed()}
        }

    }



    fun setPokemonListRestarterFragmentObject(interfaceObject: IRestartPokemonList)
    {
        this.fragmentInterface = interfaceObject
    }


}