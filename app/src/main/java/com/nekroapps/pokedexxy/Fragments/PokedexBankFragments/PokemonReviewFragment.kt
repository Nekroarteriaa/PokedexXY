package com.nekroapps.pokedexxy.Fragments.PokedexBankFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import com.nekroapps.pokedexxy.Activities.PokedexBank.PokedexBankActivity
import com.nekroapps.pokedexxy.Adapters.PokemonTypeBadgeAdapter
import com.nekroapps.pokedexxy.PokeBank.PokeBank
import com.nekroapps.pokedexxy.PokemonObject.Pokemon
import com.nekroapps.pokedexxy.R
import kotlinx.android.synthetic.main.fragment_pokemon_review.*
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.io.StringReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule


class PokemonReviewFragment : Fragment() {

    lateinit var myActivity: PokedexBankActivity
    lateinit var pokemonTypeBadgeAdapter: PokemonTypeBadgeAdapter
    lateinit var recyclerViewTypeBadges: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager

    lateinit var recyclerViewWeaknessBadges: RecyclerView

    companion object {

        @JvmStatic
        fun newInstance() = PokemonReviewFragment()
            .apply {  }
    }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View?
        {
            // Inflate the layout for this fragment
            return  inflater.inflate(R.layout.fragment_pokemon_review, container, false)


        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            settingVariables()
            preparingPokemonVizualization()
        }


        private fun settingVariables()
        {
            myActivity = activity as PokedexBankActivity

            recyclerViewTypeBadges = myActivity.findViewById(R.id.recyclerPokemonType)
            recyclerViewTypeBadges.hasFixedSize()
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

            recyclerViewWeaknessBadges = myActivity.findViewById(R.id.recyclerPokemonWeakness)
            recyclerViewWeaknessBadges.hasFixedSize()

            myActivity.currentFragment = this
        }

        fun preparingPokemonVizualization()
        {
            showPokemonGif(myActivity.selectedIDCard)
            fillHeaders(myActivity.selectedIDCard)
        }

        private fun showPokemonGif(selectedIDCard: Pokemon) {
            Glide.with(activity!!).asGif().load(selectedIDCard.pokemon_gifsrc).into(pokemonReviewImage)
        }

        private fun fillHeaders(selectedIDCard: Pokemon)
        {
            pokemonName.text = selectedIDCard.name

            val heightInMeters = convertFeetsToMeters(selectedIDCard)
            pokemonHeight.text = "Height: ${heightInMeters.toString()} m"


            val weightInKilos: Float = convertingLibrasToKilos(selectedIDCard)
            pokemonWeight.text = "Weight: ${weightInKilos.toString()} kg"

            assigningObjecstToRecyclerViewAdapter(selectedIDCard)
        }

    private fun convertingLibrasToKilos(selectedIDCard: Pokemon): Float {
        var lbs = selectedIDCard.weight
        lbs = lbs.replace("\\s".toRegex(), "")
        lbs = lbs.replace("lbs", "")

        val weighttoKilos: Float = lbs.toFloat() / 2.2046f
        val weightInKilos = String.format("%.2f", weighttoKilos).toFloat()
        return weightInKilos
    }

    private fun convertFeetsToMeters(selectedIDCard: Pokemon): Float {
        var splitted = selectedIDCard.height.split("'")

        val feets = splitted[0]
        var inches = splitted[1]

        inches = inches.replace("\\s".toRegex(), "")
        inches = inches.replace("\"".toRegex(), "")

        var feettoMeters: Float = feets.toFloat() / 3.2808f
        val inchestoMeters: Float = inches.toFloat() / 39.370f
        feettoMeters += inchestoMeters
        val heightInMeters = String.format("%.2f", feettoMeters).toFloat()
        return heightInMeters
    }

    private fun assigningObjecstToRecyclerViewAdapter(selectedIDCard: Pokemon) {
        pokemonTypeBadgeAdapter = PokemonTypeBadgeAdapter(activity!!.baseContext, selectedIDCard.typeofpokemon, myActivity)
        recyclerViewTypeBadges.layoutManager = layoutManager
        recyclerViewTypeBadges.adapter = pokemonTypeBadgeAdapter


        val weakness = PokemonTypeBadgeAdapter(activity!!.baseContext, selectedIDCard.weaknesses,myActivity)
        recyclerViewWeaknessBadges.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewWeaknessBadges.adapter = weakness

        showEvolveChain()

        }


    fun showEvolveChain()
    {
        var layoutArray = choosingLayoutHolder(myActivity.selectedIDCard.evolutions.size-1)
       for (item in layoutArray)
       {


           for (i in 0 until item.childCount step 1)
           {
               var imageHolder = item.getChildAt(i)
               if(imageHolder is ImageView)
                   imageHolder.visibility = View.GONE

               else if(imageHolder is LinearLayout)
               {
                   for (j in 0 until imageHolder.childCount step 1)
                   {
                       var defintiveImageHolder = imageHolder.getChildAt(j)
                       if(defintiveImageHolder is ImageView)
                        defintiveImageHolder.visibility = View.GONE
                   }
               }
           }

           showImageHolder(myActivity.selectedIDCard.evolutions.size - 1, item)
       }
//
//
//        var babyImageHolder = choosingImageHolder(0)
//        var teenImageHolder = choosingImageHolder(1)
//        var adultImageHolder = choosingImageHolder(2)
//
//
//        evolveChainImageHolderResponsive(myActivity.selectedIDCard.evolutions.size, teenImageHolder,adultImageHolder)
//
//        for (i in 0 until myActivity.selectedIDCard.evolutions.size step 1)
//        {
//           // val gifsrc = PokeBank.getPokemonGifUrl(myActivity.selectedIDCard.evolutions[i])
//            val pokemonEvolvedForm = PokeBank.getPokemonbyIDNumber(myActivity.selectedIDCard.evolutions[i])
//            var holdername = choosingImageHolder(i)
//            Glide.with(activity!!).asGif().load(pokemonEvolvedForm.pokemon_gifsrc).into(holdername)
//
//            holdername.setOnClickListener { myActivity.selectedIDCard = pokemonEvolvedForm
//                                            preparingPokemonVizualization()}
//        }
//        var children = forthEvolutionFamilyLayout.childCount
//        PokeBank.printThis(children.toString())


    }


    fun choosingLayoutHolder(evolform:Int) : ArrayList<LinearLayout>
    {
        var arrayHolder: ArrayList<LinearLayout> = ArrayList()
        when(evolform)
        {
            0 ->{
                firstEvolutionFamilyLayout.visibility = View.VISIBLE
                arrayHolder.add(firstEvolutionFamilyLayout)
                forthEvolutionFamilyLayout.visibility = View.GONE
                secondEvolutionFamilyLayout.visibility = View.GONE
                thirdEvolutionFamilyLayout.visibility = View.GONE
            }
            1-> {
                firstEvolutionFamilyLayout.visibility = View.VISIBLE
                arrayHolder.add(firstEvolutionFamilyLayout)
                forthEvolutionFamilyLayout.visibility = View.GONE
                secondEvolutionFamilyLayout.visibility = View.GONE
                thirdEvolutionFamilyLayout.visibility = View.GONE
            }
            2 -> {
                firstEvolutionFamilyLayout.visibility = View.VISIBLE
                arrayHolder.add(firstEvolutionFamilyLayout)
                forthEvolutionFamilyLayout.visibility = View.GONE
                secondEvolutionFamilyLayout.visibility = View.GONE
                thirdEvolutionFamilyLayout.visibility = View.GONE
            }
            3 -> {
                forthEvolutionFamilyLayout.visibility = View.VISIBLE
                arrayHolder!!.add(forthEvolutionFamilyLayout)
                firstEvolutionFamilyLayout.visibility = View.GONE
                secondEvolutionFamilyLayout.visibility = View.GONE
                thirdEvolutionFamilyLayout.visibility = View.GONE
            }

            else ->
            {
                firstEvolutionFamilyLayout.visibility = View.VISIBLE
                secondEvolutionFamilyLayout.visibility = View.VISIBLE
                thirdEvolutionFamilyLayout.visibility = View.VISIBLE
                arrayHolder!!.add(firstEvolutionFamilyLayout)
                arrayHolder!!.add(secondEvolutionFamilyLayout)
                arrayHolder!!.add(thirdEvolutionFamilyLayout)
                forthEvolutionFamilyLayout.visibility = View.GONE
            }
        }

        return arrayHolder!!
    }

    fun showImageHolder(evolForm: Int, currentLayout: LinearLayout)
    {


        if(evolForm > 4)
        {
            fifthEvolutionFormImage.visibility = View.VISIBLE
            firstBabyEvolutionFormImage.visibility = View.VISIBLE
            firstTeenEvolutionFormImage.visibility = View.VISIBLE
            firstAdultEvolutionFormImage.visibility = View.VISIBLE
            forthEvolutionFormImage.visibility = View.VISIBLE
            sixthEvolutionFormImage.visibility = View.VISIBLE
            seventhEvolutionFormImage.visibility = View.VISIBLE
            eightEvolutionFormImage.visibility = View.VISIBLE
            ninethEvolutionFormImage.visibility = View.VISIBLE

            val eeveeEvolutions = PokeBank.getEeveeEvolutions()
            Glide.with(activity!!).asGif().load(eeveeEvolutions[0].pokemon_gifsrc).into(fifthEvolutionFormImage)
            Glide.with(activity!!).asGif().load(eeveeEvolutions[1].pokemon_gifsrc).into(firstBabyEvolutionFormImage)
            Glide.with(activity!!).asGif().load(eeveeEvolutions[2].pokemon_gifsrc).into(firstTeenEvolutionFormImage)
            Glide.with(activity!!).asGif().load(eeveeEvolutions[3].pokemon_gifsrc).into(firstAdultEvolutionFormImage)
            Glide.with(activity!!).asGif().load(eeveeEvolutions[4].pokemon_gifsrc).into(forthEvolutionFormImage)
            Glide.with(activity!!).asGif().load(eeveeEvolutions[7].pokemon_gifsrc).into(sixthEvolutionFormImage)
            Glide.with(activity!!).asGif().load(eeveeEvolutions[5].pokemon_gifsrc).into(seventhEvolutionFormImage)
            Glide.with(activity!!).asGif().load(eeveeEvolutions[6].pokemon_gifsrc).into(eightEvolutionFormImage)
            Glide.with(activity!!).asGif().load(eeveeEvolutions[8].pokemon_gifsrc).into(ninethEvolutionFormImage)

            fifthEvolutionFormImage.setOnClickListener { myActivity.selectedIDCard =  eeveeEvolutions[0]
                preparingPokemonVizualization()}
            firstBabyEvolutionFormImage.setOnClickListener { myActivity.selectedIDCard =  eeveeEvolutions[1]
                preparingPokemonVizualization()}
            firstTeenEvolutionFormImage.setOnClickListener { myActivity.selectedIDCard =  eeveeEvolutions[2]
                preparingPokemonVizualization()}
            firstAdultEvolutionFormImage.setOnClickListener { myActivity.selectedIDCard =  eeveeEvolutions[3]
                preparingPokemonVizualization()}
            forthEvolutionFormImage.setOnClickListener { myActivity.selectedIDCard =  eeveeEvolutions[4]
                preparingPokemonVizualization()}

            sixthEvolutionFormImage.setOnClickListener { myActivity.selectedIDCard =  eeveeEvolutions[7]
                preparingPokemonVizualization()}
            seventhEvolutionFormImage.setOnClickListener { myActivity.selectedIDCard =  eeveeEvolutions[5]
                preparingPokemonVizualization()}
            eightEvolutionFormImage.setOnClickListener { myActivity.selectedIDCard =  eeveeEvolutions[6]
                preparingPokemonVizualization()}
            ninethEvolutionFormImage.setOnClickListener { myActivity.selectedIDCard =  eeveeEvolutions[8]
                preparingPokemonVizualization()}
        }

        else
        {
            for (i in 0 until currentLayout.childCount step 1)
            {
                if(i <= evolForm)
                {
                    var imageHolder = currentLayout.getChildAt(i)
                    if(imageHolder is ImageView)
                    {
                        imageHolder.visibility = View.VISIBLE

                        val pokemonEvolvedForm = PokeBank.getPokemonbyIDNumber(myActivity.selectedIDCard.evolutions[i])
                        Glide.with(activity!!).asGif().load(pokemonEvolvedForm.pokemon_gifsrc).into(imageHolder)

                        imageHolder.setOnClickListener { myActivity.selectedIDCard = pokemonEvolvedForm
                            preparingPokemonVizualization()}
                    }

                    else if(imageHolder is LinearLayout)
                    {
                        for (j in 0 until imageHolder.childCount step 1)
                        {
                            var defintiveImageHolder = imageHolder.getChildAt(j)
                            if(defintiveImageHolder is ImageView)
                            {
                                defintiveImageHolder.visibility = View.VISIBLE
                                val pokemonEvolvedForm = PokeBank.getPokemonbyIDNumber(myActivity.selectedIDCard.evolutions[i +j])
                                Glide.with(activity!!).asGif().load(pokemonEvolvedForm.pokemon_gifsrc).into(defintiveImageHolder)

                                defintiveImageHolder.setOnClickListener { myActivity.selectedIDCard = pokemonEvolvedForm
                                    preparingPokemonVizualization()}
                            }
                        }
                    }
                }
            }
        }


    }



    fun evolveChainImageHolderResponsive(evolForm: Int, teenHolder:ImageView, adultHolder: ImageView)
    {
        when(evolForm)
        {
            1 -> {teenHolder.visibility = View.GONE
                    adultHolder.visibility = View.GONE}
            2-> {teenHolder.visibility = View.VISIBLE
                adultHolder.visibility = View.GONE}
            else -> {teenHolder.visibility = View.VISIBLE
                adultHolder.visibility = View.VISIBLE}
        }
    }


//    fun choosingImageHolder(evolform:Int) : ImageView
//    {
//        var holder: ImageView? = null
//        when(evolform)
//        {
//            0 -> holder = myActivity.findViewById<ImageView>(R.id.babyEvolutionFormImage)
//            1-> holder =  myActivity.findViewById<ImageView>(R.id.teenEvolutionFormImage)
//            2 -> holder =  myActivity.findViewById<ImageView>(R.id.adultEvolutionFormImage)
//        }
//
//        return holder!!
//    }

    }