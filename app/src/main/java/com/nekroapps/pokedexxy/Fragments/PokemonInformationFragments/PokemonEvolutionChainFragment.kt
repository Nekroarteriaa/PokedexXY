package com.nekroapps.pokedexxy.Fragments.PokemonInformationFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.nekroapps.pokedexxy.Activities.PokedexBank.PokedexBankActivity
import com.nekroapps.pokedexxy.Fragments.PokedexBankFragments.PokemonReviewCanvasFragment
import com.nekroapps.pokedexxy.PokeBank.PokeBank
import com.nekroapps.pokedexxy.R
import kotlinx.android.synthetic.main.fragment_pokemon_evolution_chain.*

class PokemonEvolutionChainFragment : Fragment() {

    lateinit var myActivity: PokedexBankActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_pokemon_evolution_chain, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = PokemonEvolutionChainFragment()
            .apply {  }

        fun fragmentName():String = "Evolves"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myActivity = activity as PokedexBankActivity

        showEvolveChain()
    }

    fun showEvolveChain()
    {

        val layoutArray = choosingLayoutHolder(myActivity.selectedIDCard.evolutions.size-1)
        for (item in layoutArray)
        {


            for (i in 0 until item.childCount step 1)
            {
                val imageHolder = item.getChildAt(i)
                if(imageHolder is ImageView)
                    imageHolder.visibility = View.GONE

                else if(imageHolder is LinearLayout)
                {
                    for (j in 0 until imageHolder.childCount step 1)
                    {
                        val defintiveImageHolder = imageHolder.getChildAt(j)
                        if(defintiveImageHolder is ImageView)
                            defintiveImageHolder.visibility = View.GONE
                    }
                }
            }

            showImageHolder(myActivity.selectedIDCard.evolutions.size - 1, item)
        }

    }

    fun choosingLayoutHolder(evolform:Int) : ArrayList<LinearLayout>
    {
        val arrayHolder: ArrayList<LinearLayout> = ArrayList()
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
                arrayHolder.add(forthEvolutionFamilyLayout)
                firstEvolutionFamilyLayout.visibility = View.GONE
                secondEvolutionFamilyLayout.visibility = View.GONE
                thirdEvolutionFamilyLayout.visibility = View.GONE
            }

            else ->
            {
                firstEvolutionFamilyLayout.visibility = View.VISIBLE
                secondEvolutionFamilyLayout.visibility = View.VISIBLE
                thirdEvolutionFamilyLayout.visibility = View.VISIBLE
                arrayHolder.add(firstEvolutionFamilyLayout)
                arrayHolder.add(secondEvolutionFamilyLayout)
                arrayHolder.add(thirdEvolutionFamilyLayout)
                forthEvolutionFamilyLayout.visibility = View.GONE
            }
        }

        return arrayHolder
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
                val parentFrag = myActivity.currentFragment as PokemonReviewCanvasFragment
                parentFrag.showPokemonInformation(myActivity.selectedIDCard)}

            firstBabyEvolutionFormImage.setOnClickListener { myActivity.selectedIDCard =  eeveeEvolutions[1]
                val parentFrag = myActivity.currentFragment as PokemonReviewCanvasFragment
                parentFrag.showPokemonInformation(myActivity.selectedIDCard)}

            firstTeenEvolutionFormImage.setOnClickListener { myActivity.selectedIDCard =  eeveeEvolutions[2]
                val parentFrag = myActivity.currentFragment as PokemonReviewCanvasFragment
                parentFrag.showPokemonInformation(myActivity.selectedIDCard)}

            firstAdultEvolutionFormImage.setOnClickListener { myActivity.selectedIDCard =  eeveeEvolutions[3]
                val parentFrag = myActivity.currentFragment as PokemonReviewCanvasFragment
                parentFrag.showPokemonInformation(myActivity.selectedIDCard)}

            forthEvolutionFormImage.setOnClickListener { myActivity.selectedIDCard =  eeveeEvolutions[4]
                val parentFrag = myActivity.currentFragment as PokemonReviewCanvasFragment
                parentFrag.showPokemonInformation(myActivity.selectedIDCard)}

            sixthEvolutionFormImage.setOnClickListener { myActivity.selectedIDCard =  eeveeEvolutions[7]
                val parentFrag = myActivity.currentFragment as PokemonReviewCanvasFragment
                parentFrag.showPokemonInformation(myActivity.selectedIDCard)}

            seventhEvolutionFormImage.setOnClickListener { myActivity.selectedIDCard =  eeveeEvolutions[5]
                val parentFrag = myActivity.currentFragment as PokemonReviewCanvasFragment
                parentFrag.showPokemonInformation(myActivity.selectedIDCard)}

            eightEvolutionFormImage.setOnClickListener { myActivity.selectedIDCard =  eeveeEvolutions[6]
                val parentFrag = myActivity.currentFragment as PokemonReviewCanvasFragment
                parentFrag.showPokemonInformation(myActivity.selectedIDCard)}

            ninethEvolutionFormImage.setOnClickListener { myActivity.selectedIDCard =  eeveeEvolutions[8]
                val parentFrag = myActivity.currentFragment as PokemonReviewCanvasFragment
                parentFrag.showPokemonInformation(myActivity.selectedIDCard)}
        }

        else
        {
            for (i in 0 until currentLayout.childCount step 1)
            {
                if(i <= evolForm)
                {
                    val imageHolder = currentLayout.getChildAt(i)
                    if(imageHolder is ImageView)
                    {
                        imageHolder.visibility = View.VISIBLE

                        val pokemonEvolvedForm = PokeBank.getPokemonbyIDNumber(myActivity.selectedIDCard.evolutions[i])
                        Glide.with(activity!!).asGif().load(pokemonEvolvedForm.pokemon_gifsrc).into(imageHolder)

                        imageHolder.setOnClickListener { myActivity.selectedIDCard = pokemonEvolvedForm
                            val parentFrag = myActivity.currentFragment as PokemonReviewCanvasFragment
                            parentFrag.showPokemonInformation(myActivity.selectedIDCard)}
                    }

                    else if(imageHolder is LinearLayout)
                    {
                        val verticalContainer = forthVertical
                        for (j in 0 until imageHolder.childCount step 1)
                        {
                            val defintiveImageHolder = imageHolder.getChildAt(j)
                            if(defintiveImageHolder is ImageView)
                            {
                                defintiveImageHolder.visibility = View.VISIBLE
                                var selection = i + j

                                if(imageHolder == verticalContainer)
                                    selection = i + j + 1

                                val pokemonEvolvedForm = PokeBank.getPokemonbyIDNumber(myActivity.selectedIDCard.evolutions[selection])
                                Glide.with(activity!!).asGif().load(pokemonEvolvedForm.pokemon_gifsrc).into(defintiveImageHolder)

                                defintiveImageHolder.setOnClickListener { myActivity.selectedIDCard = pokemonEvolvedForm
                                    val parentFrag = myActivity.currentFragment as PokemonReviewCanvasFragment
                                    parentFrag.showPokemonInformation(myActivity.selectedIDCard)}
                            }
                        }
                    }
                }
            }
        }


    }
}