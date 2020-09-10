package com.nekroapps.pokedexxy.Fragments.PokedexBankFragments

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import co.revely.gradient.RevelyGradient
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.GsonBuilder
import com.nekroapps.pokedexxy.Activities.PokedexBank.PokedexBankActivity
import com.nekroapps.pokedexxy.Adapters.FAAdapter
import com.nekroapps.pokedexxy.Adapters.FragmentViewAdapter
import com.nekroapps.pokedexxy.ConnectionData.PokeMoveDetail
import com.nekroapps.pokedexxy.ConnectionData.PokemonInformation
import com.nekroapps.pokedexxy.Fragments.PokemonInformationFragments.PokemonAboutFragment
import com.nekroapps.pokedexxy.Fragments.PokemonInformationFragments.PokemonEvolutionChainFragment
import com.nekroapps.pokedexxy.Fragments.PokemonInformationFragments.PokemonMovesFragment
import com.nekroapps.pokedexxy.Fragments.PokemonInformationFragments.PokemonStatsFragment
import com.nekroapps.pokedexxy.PokeBank.PokeBank
import com.nekroapps.pokedexxy.PokemonObject.Pokemon
import com.nekroapps.pokedexxy.R
import kotlinx.android.synthetic.main.fragment_pokemon_review_canvas.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.io.StringReader

class PokemonReviewCanvasFragment : Fragment(){

    lateinit var myActivity: PokedexBankActivity
    lateinit var pokeMoveDetail: ArrayList<PokeMoveDetail>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_review_canvas, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = PokemonReviewCanvasFragment()
            .apply {  }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myActivity = activity as PokedexBankActivity
        myActivity.currentFragment = this
        pokeMoveDetail = ArrayList()


        showPokemonInformation(myActivity.selectedIDCard)
        apiServicePokemonInformationDemand()
    }

    fun showPokemonInformation(selectedIDCard: Pokemon)
    {
        settingVariables(selectedIDCard)

        //aboutBtn.performClick()
    }


    fun settingVariables(selectedIDCard: Pokemon)
    {
        Glide.with(activity!!).asGif().load(selectedIDCard.pokemon_gifsrc).into(pokemonImageProfile)
        pokemonIndex.text = selectedIDCard.id
        pokemonName1.text = selectedIDCard.name

        RevelyGradient
            .linear()
            .colors(PokeBank.getPokemonIDCardBackgroundColor(selectedIDCard.typeofpokemon))
            .angle(45f)
            .onBackgroundOf(reviewBackground)

//        var fragmentAdapter = FragmentViewAdapter(myActivity.supportFragmentManager)
//        fragmentAdapter.addFragments(PokemonAboutFragment.newInstance(), "About")
//        fragmentAdapter.addFragments(PokemonEvolutionChainFragment.newInstance(), "Evolution")
//        fragmentAdapter.addFragments(PokemonStatsFragment.newInstance(), "Stats")
//        fragmentAdapter.addFragments(PokemonMovesFragment.newInstance(), "Moves")
//
//
//        viewPager.adapter = fragmentAdapter
//
//
//        var parm = viewPager.layoutParams as ViewPager.LayoutParams

//        parm.height = 50
//
//        viewPager.layoutParams = parm


      //  var params = fragmentAdapter.currentFrag.view!!.layoutParams
        //viewPager.layoutParams = params

        val asd : ArrayList<Fragment> = ArrayList()

        asd.add(PokemonAboutFragment.newInstance())
        asd.add(PokemonEvolutionChainFragment.newInstance())
        asd.add(PokemonStatsFragment.newInstance())
        asd.add(PokemonMovesFragment.newInstance())

        val adaptr = FAAdapter(myActivity, asd, myActivity.supportFragmentManager)
        viewPager.adapter = adaptr

        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            tab.text = "Pene"

            //myActivity.supportFragmentManager.beginTransaction().replace(R.id.pokemonInformation2, asd[0]).addToBackStack(null).commit()

            //PokeBank.printThis(tab.position.toString())
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

                PokeBank.printThis(tab!!.position.toString())
                myActivity.supportFragmentManager.beginTransaction().replace(R.id.pokemonInformation, asd[tab.position]).addToBackStack(null).commit()


            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

                //


            }

            override fun onTabReselected(tab: TabLayout.Tab?) {



            }

        })



     //   tabLayout.setupWithViewPager(viewPager)



//        aboutBtn.setOnClickListener {
//            val frag = PokemonAboutFragment.newInstance()
//            activity!!.supportFragmentManager.beginTransaction().replace(R.id.pokemonInformation, frag).addToBackStack(null).commit()
//        }
//
//        evolutionBtn.setOnClickListener {
//            val frag = PokemonEvolutionChainFragment.newInstance()
//            activity!!.supportFragmentManager.beginTransaction().replace(R.id.pokemonInformation, frag).addToBackStack(null).commit()
//        }
//
//        statsBtn.setOnClickListener {
//            val frag = PokemonStatsFragment.newInstance()
//            activity!!.supportFragmentManager.beginTransaction().replace(R.id.pokemonInformation, frag).addToBackStack(null).commit()
//        }
//
//        movesBtn.setOnClickListener {
//            val frag = PokemonMovesFragment.newInstance()
//            activity!!.supportFragmentManager.beginTransaction().replace(R.id.pokemonInformation, frag).addToBackStack(null).commit() }
    }

    fun apiServicePokemonInformationDemand()
    {

        var idNumber = myActivity.selectedIDCard.id.replace("#","")
        idNumber = idNumber.replace("0","")
         val direction = "https://pokeapi.co/api/v2/pokemon/${idNumber}"

        val request = Request.Builder()
            .url(direction)
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : okhttp3.Callback{
            override fun onFailure(call: okhttp3.Call, e: IOException) {
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val strg = StringReader(response.body!!.string())
                val gson = GsonBuilder().create()
                val mpoke = gson.fromJson(strg, PokemonInformation::class.java)



                for (item in mpoke.moves)
                {
                    apiServicePokemonMoveInformationDemand(item.move.url)
                }

            }

        })
    }

    fun apiServicePokemonMoveInformationDemand(urlAddress: String)
    {
        val request = Request.Builder()
            .url(urlAddress)
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : okhttp3.Callback{
            override fun onFailure(call: okhttp3.Call, e: IOException) {
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val strg = StringReader(response.body!!.string())
                val gson = GsonBuilder().create()
                val mpoke = gson.fromJson(strg, PokeMoveDetail::class.java)

                pokeMoveDetail.add(mpoke)

            }

        })

    }


}