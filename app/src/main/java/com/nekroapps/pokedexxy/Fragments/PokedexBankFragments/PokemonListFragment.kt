package com.nekroapps.pokedexxy.Fragments.PokedexBankFragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.children
import androidx.core.view.iterator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mancj.materialsearchbar.MaterialSearchBar
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter
import com.nekroapps.pokedexxy.Activities.PokedexBank.IRestartPokemonList
import com.nekroapps.pokedexxy.Activities.PokedexBank.PokedexBankActivity
import com.nekroapps.pokedexxy.Adapters.PokemonIDCardsArrayAdapter
import com.nekroapps.pokedexxy.PokeBank.PokeBank
import com.nekroapps.pokedexxy.PokemonObject.Pokemon
import com.nekroapps.pokedexxy.R
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import kotlin.collections.ArrayList


class PokemonListFragment : Fragment(), IRestartPokemonList {

    lateinit var myActivity: PokedexBankActivity
    lateinit var pokemonIDCardsArrayAdapter : PokemonIDCardsArrayAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var search_adapter: PokemonIDCardsArrayAdapter
    var previousSearch: MutableList<String> = ArrayList()
    lateinit var searchedText:String

    companion object {

        @JvmStatic
        fun newInstance() = PokemonListFragment()
            .apply {  }


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingVariables()

        getPokemonIDCardContent()
        showPokedexGrid()

    }

    private fun settingVariables() {

        myActivity = activity as PokedexBankActivity
        recyclerView = myActivity.findViewById(R.id.pokedexViewCards)
        recyclerView.hasFixedSize()
        layoutManager = LinearLayoutManager(activity!!,LinearLayoutManager.VERTICAL,false) //GridLayoutManager(activity, 2)

        myActivity.currentFragment = this

        myActivity.setPokemonListRestarterFragmentObject(this)




        // Searching bar activator button
        searchBarActivator.setOnClickListener {
            pokemonSearch_bar.visibility = View.VISIBLE
            pokemonSearch_bar.performClick()
            pokemonSearch_bar.showSuggestionsList()
            searchBarActivator.visibility = View.GONE
        }


        // Setting Searching bar
        pokemonSearch_bar.setHint("Enter Pokemon Name, Number or Type")
        pokemonSearch_bar.setCardViewElevation(10)
        pokemonSearch_bar.addTextChangeListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if(!pokemonSearch_bar.isSuggestionsVisible && !pokemonSearch_bar.text.isEmpty())
                    pokemonSearch_bar.showSuggestionsList()

                val suggest = ArrayList<String>()

                for(item in previousSearch)
                {
                    if(item.toLowerCase().contains(pokemonSearch_bar.text.toLowerCase()))
                        suggest.add(item)
                }
                pokemonSearch_bar.lastSuggestions = suggest

                searchedText = pokemonSearch_bar.text

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        pokemonSearch_bar.setOnSearchActionListener(object : MaterialSearchBar.OnSearchActionListener{
            override fun onSearchStateChanged(enabled: Boolean) {
                /*if(!enabled)
                {
                    onRestartPokeList()
                    //recyclerView.adapter = search_adapter
                }*/
            }

            override fun onSearchConfirmed(text: CharSequence?) {
                myActivity.lastSearchedWords = text.toString()
                startSearch(text.toString())
                keyboardshit()

            }

            override fun onButtonClicked(buttonCode: Int) {
            }

        })

        pokemonSearch_bar.setSuggestionsClickListener(object : SuggestionsAdapter.OnItemViewClickListener{
            override fun OnItemClickListener(position: Int, v: View?) {
                val lin = v as LinearLayout
                for (item in lin)
                {
                   if(item is TextView)
                   {
                       showSearchingContent(item)

                   }
                }
            }

            override fun OnItemDeleteListener(position: Int, v: View?) {
            }

        })


        val pokemonSearchBarArrowButton = pokemonSearch_bar.findViewById<AppCompatImageView>(R.id.mt_arrow)
        val pokemonSearchBarCloseButton = pokemonSearch_bar.findViewById<AppCompatImageView>(R.id.mt_clear)
        pokemonSearchBarArrowButton.setOnClickListener {searchBackButtonBehaviour() }
        pokemonSearchBarCloseButton.setOnClickListener { onRestartPokeList() }





    }


    fun showPokedexGrid() {
        pokemonIDCardsArrayAdapter =  PokemonIDCardsArrayAdapter(activity!!.baseContext, PokeBank.PokemonBank, myActivity)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = pokemonIDCardsArrayAdapter

        previousSearch.clear()
        for (item in PokeBank.PokemonBank)
        {
            previousSearch.add(item.name)
        }
        //
        pokemonSearch_bar.lastSuggestions = previousSearch
    }


    fun startSearch(text:String)
    {
        if(PokeBank.PokemonBank.size > 0)
        {
            val searchResult = ArrayList<Pokemon>()

            for(item in PokeBank.PokemonBank)
            {
                if(item.name.toLowerCase().contains(text.toLowerCase()))
                    searchResult.add(item)
            }
            search_adapter = PokemonIDCardsArrayAdapter(activity!!,searchResult, myActivity)
            recyclerView.adapter = search_adapter
        }
    }

    override fun onRestartPokeList() {

        showPokedexGrid()
        pokemonSearch_bar.text=""
        pokemonSearch_bar.visibility = View.GONE
        searchBarActivator.visibility = View.VISIBLE
        myActivity.lastSearchedWords = ""

    }

    override fun onShowPokemonSearchingResults() {


        startSearch(myActivity.lastSearchedWords)
        myActivity.lastSearchedWords = ""

        /* pokemonSearch_bar.onClick(pokemonSearch_bar)
         pokemonSearch_bar.text = searchedText
         startSearch(pokemonSearch_bar.text)
         pokemonSearch_bar.hideSuggestionsList()*/

    }

    private fun showSearchingContent(item: TextView) {
        // pokemonSearch_bar.text = item.text.toString()
        startSearch(item.text.toString())
        pokemonSearch_bar.hideSuggestionsList()
        //pokemonSearch_bar.closeSearch()




    }

    fun searchBackButtonBehaviour()
    {

        myActivity.onBackPressed()
        //onRestartPokeList()
        /*if(myActivity.isShowingSearchResults)
            onRestartPokeList()

        else if(!myActivity.isShowingSearchResults)
        {
            pokemonSearch_bar.hideSuggestionsList()
            val kb = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            kb.hideSoftInputFromWindow(pokemonSearch_bar.windowToken,0)
        }*/

      //  myActivity.onBackPressed()

      //  pokemonSearch_bar.text=""
       // pokemonSearch_bar.hideSuggestionsList()
      //  pokemonSearch_bar.isClickable = false




    }

    fun keyboardshit()
    {
        val view = activity!!.currentFocus
        if(view != null)
        {
            val hideKeyboard = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideKeyboard.hideSoftInputFromWindow(view.windowToken,0)
        }

        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }




}


private fun getPokemonIDCardContent() {
    for (item in PokeBank.PokemonBank) {
        item.getPokemonContent()
    }







}