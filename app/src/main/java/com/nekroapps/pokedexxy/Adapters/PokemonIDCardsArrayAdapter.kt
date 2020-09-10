package com.nekroapps.pokedexxy.Adapters

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.revely.gradient.RevelyGradient
import co.revely.gradient.drawables.Gradient
import com.google.android.material.chip.Chip
import com.nekroapps.pokedexxy.Interfaces.IIDCardClickListener
import com.nekroapps.pokedexxy.PokeBank.PokeBank
import com.nekroapps.pokedexxy.PokemonObject.Pokemon
import com.nekroapps.pokedexxy.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pokemon_id_card.view.*
import java.lang.Exception

class PokemonIDCardsArrayAdapter(internal var context: Context,
                                 internal var items:ArrayList<Pokemon>,
                                 var clickListener: IIDCardClickListener): RecyclerView.Adapter<PokemonIDCardsArrayAdapter.MyViewHolder>()
{




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_id_card,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Picasso.get().load(items[position].pokemon_imagesrc).into(holder.pkmnImage!!, object : Callback
        {
            override fun onSuccess() {
                holder.textViewName!!.text = items[position].name.capitalize()
                holder.textViewNumber!!.text = items[position].id
                holder.setIDCardClickListener(clickListener,items[position])
                holder.badgeID!!.text = items[position].typeofpokemon[0]
                holder.typeSymbol!!.setImageResource(PokeBank.getSymbolImage(items[position].typeofpokemon[0]))

                if(items[position].typeofpokemon.size > 1)
                {
                    holder.secondBadgeID!!.visibility = View.VISIBLE
                    holder.secondTypeSymbol!!.visibility = View.VISIBLE

                    holder.secondBadgeID!!.text = items[position].typeofpokemon[1]
                    holder.secondTypeSymbol!!.setImageResource(PokeBank.getSymbolImage(items[position].typeofpokemon[1]))
                }

                else
                {
                    holder.secondBadgeID!!.visibility = View.GONE
                    holder.secondTypeSymbol!!.visibility = View.GONE
                }

                RevelyGradient
                    .linear()
                    .colors(PokeBank.getPokemonIDCardBackgroundColor(items[position].typeofpokemon))
                    .angle(45f)
                    //.center(1020,30)
                    .onBackgroundOf(holder.pkmnCardBackground!!)
            }

            override fun onError(e: Exception?) {
            }

        })


    }


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        var textViewName: TextView? = null
        var textViewNumber: TextView? = null
        var pkmnImage: ImageView? = null
        var pkmnCardBackground: ImageView? = null

        var badgeID: com.robertlevonyan.views.chip.Chip? = null
        var typeSymbol: ImageView? = null

        var secondBadgeID: com.robertlevonyan.views.chip.Chip? = null
        var secondTypeSymbol: ImageView? = null

        fun setIDCardClickListener(action: IIDCardClickListener, idCard: Pokemon)
        {
            itemView.setOnClickListener {action.showPokemonReview(adapterPosition, idCard) }
        }

        init {
            this.textViewName = itemView.pokemon_name
            this.textViewNumber = itemView.pokemon_numberID
            this.pkmnImage = itemView.pokemon_image
            this.badgeID = itemView.typeBadgeID
            this.typeSymbol = itemView.typeSymbolID
            this.secondBadgeID = itemView.typeTwoBadgeID
            this.secondTypeSymbol = itemView.typeSymbolTwoID
            this.pkmnCardBackground = itemView.pokemon_id_background


        }
    }

}