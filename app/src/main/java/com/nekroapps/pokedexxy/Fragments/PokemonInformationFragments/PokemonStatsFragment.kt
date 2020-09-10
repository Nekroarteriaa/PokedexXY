package com.nekroapps.pokedexxy.Fragments.PokemonInformationFragments

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.nekroapps.pokedexxy.Activities.PokedexBank.PokedexBankActivity
import com.nekroapps.pokedexxy.R
import kotlinx.android.synthetic.main.fragment_pokemon_stats.*

class PokemonStatsFragment : Fragment() {

    lateinit var myActivity : PokedexBankActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_stats, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = PokemonStatsFragment()
            .apply {  }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myActivity = activity as PokedexBankActivity

        hpProgressBar.max = 100
        ObjectAnimator.ofInt(hpProgressBar,"progress", myActivity.selectedIDCard.hp).setDuration(2000).start()

        attackProgressBar.max = 100
        ObjectAnimator.ofInt(attackProgressBar,"progress", myActivity.selectedIDCard.attack).setDuration(2000).start()

        defenseProgressBar.max = 100
        ObjectAnimator.ofInt(defenseProgressBar,"progress", myActivity.selectedIDCard.defense).setDuration(2000).start()

        specialAttackProgressBar.max = 100
        ObjectAnimator.ofInt(specialAttackProgressBar,"progress", myActivity.selectedIDCard.special_attack).setDuration(2000).start()

        specialDefenseProgressBar.max = 100
        ObjectAnimator.ofInt(specialDefenseProgressBar,"progress", myActivity.selectedIDCard.special_defense).setDuration(2000).start()

        speedProgressBar.max = 100
        ObjectAnimator.ofInt(speedProgressBar,"progress", myActivity.selectedIDCard.speed).setDuration(2000).start()

        totalProgressBar.max = 370
        ObjectAnimator.ofInt(totalProgressBar,"progress", myActivity.selectedIDCard.total).setDuration(2000).start()
    }
}