package com.nekroapps.pokedexxy.Adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nekroapps.pokedexxy.Interfaces.ITypeBadgeListener
import com.nekroapps.pokedexxy.PokeBank.PokeBank
import com.nekroapps.pokedexxy.R

class PokemonTypeSymbolAdapter (internal var context: Context,
                                internal var symbolList: ArrayList<String>//,
//                                var clickListener: ITypeBadgeListener
                                ): RecyclerView.Adapter<PokemonTypeSymbolAdapter.MyViewHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): PokemonTypeSymbolAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_type_symbols,parent,false)
        return MyViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return symbolList.size
    }

    override fun onBindViewHolder(holder: PokemonTypeSymbolAdapter.MyViewHolder, position: Int) {
        val symbolName = symbolList[position]
        val symbolImg = /*holder.getSymbolImage(symbolName)*/ PokeBank.getSymbolImage(symbolName)
        holder.symbol.setImageResource(symbolImg)

    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var symbol: ImageView = itemView.findViewById(R.id.pokemonSymbol)
    }

}