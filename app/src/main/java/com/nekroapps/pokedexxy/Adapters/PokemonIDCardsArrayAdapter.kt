package com.nekroapps.pokedexxy.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
                holder.setIDCardClickListener(clickListener,items[position])


            }

            override fun onError(e: Exception?) {
            }

        })

    }


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        var textViewName: TextView? = null
        var pkmnImage: ImageView? = null

        fun setIDCardClickListener(action: IIDCardClickListener, idCard: Pokemon)
        {
            itemView.setOnClickListener {action!!.showPokemonReview(adapterPosition, idCard) }
        }

        init {
            this.textViewName = itemView.let { it!!.pokemon_name }
            this.pkmnImage = itemView.let { it!!.pokemon_image }
        }
    }

}