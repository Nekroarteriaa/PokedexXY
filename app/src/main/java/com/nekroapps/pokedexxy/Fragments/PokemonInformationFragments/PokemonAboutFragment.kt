package com.nekroapps.pokedexxy.Fragments.PokemonInformationFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nekroapps.pokedexxy.Activities.PokedexBank.PokedexBankActivity
import com.nekroapps.pokedexxy.Adapters.PokemonTypeSymbolAdapter
import com.nekroapps.pokedexxy.Fragments.PokedexBankFragments.PokemonListFragment
import com.nekroapps.pokedexxy.PokemonObject.Pokemon
import com.nekroapps.pokedexxy.R
import kotlinx.android.synthetic.main.fragment_pokemon_about.*


class PokemonAboutFragment : Fragment() {

    lateinit var myActivity: PokedexBankActivity
    lateinit var pokemonTypeSymbolAdapter: PokemonTypeSymbolAdapter
    lateinit var recyclerViewSymbol: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager

    lateinit var recyclerViewSymbolWeakness: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pokemon_about, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = PokemonAboutFragment()
            .apply {  }

        fun fragmentName():String = "About"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myActivity = activity as PokedexBankActivity

        settingVariables(myActivity.selectedIDCard)

        assigningObjecstToRecyclerViewAdapter(myActivity.selectedIDCard)
    }

    fun settingVariables(selectedID:Pokemon)
    {
        pokemonDescription.text = selectedID.xdescription

        val heightInMeters = convertFeetsToMeters(selectedID)
        val weightInKilos: Float = convertingLibrasToKilos(selectedID)

        pokemonWeightText.text = "Weight: ${weightInKilos.toString()} kg"
        pokemonHeightText.text = "Height: ${heightInMeters.toString()} m"

        pokemonCategory.text = selectedID.category
        pokemonMalePercentage.text = selectedID.male_percentage
        pokemonFemalePercentage.text = selectedID.female_percentage


        recyclerViewSymbol = activity!!.findViewById(R.id.recyclerPokemonSelfType)
        recyclerViewSymbol.hasFixedSize()
        layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

        recyclerViewSymbolWeakness = activity!!.findViewById(R.id.recyclerPokemonTypeWeakness)
        recyclerViewSymbolWeakness.hasFixedSize()

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
        val splitted = selectedIDCard.height.split("'")

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

    fun assigningObjecstToRecyclerViewAdapter(selectedIDCard: Pokemon)
    {
        pokemonTypeSymbolAdapter = PokemonTypeSymbolAdapter(activity!!.baseContext,selectedIDCard.typeofpokemon)
        recyclerViewSymbol.layoutManager = layoutManager
        recyclerViewSymbol.adapter = pokemonTypeSymbolAdapter

        val weakness = PokemonTypeSymbolAdapter(activity!!.baseContext,selectedIDCard.weaknesses)
        recyclerViewSymbolWeakness.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewSymbolWeakness.adapter = weakness
    }

}