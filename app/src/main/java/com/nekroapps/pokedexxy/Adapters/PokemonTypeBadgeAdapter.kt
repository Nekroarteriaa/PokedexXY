package com.nekroapps.pokedexxy.Adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nekroapps.pokedexxy.Interfaces.ITypeBadgeListener
import com.nekroapps.pokedexxy.PokemonObject.Pokemon
import com.nekroapps.pokedexxy.R
import com.robertlevonyan.views.chip.Chip

class PokemonTypeBadgeAdapter (internal var context: Context,
                               internal var badgeList: ArrayList<String>,
                               var clickListener: ITypeBadgeListener
): RecyclerView.Adapter<PokemonTypeBadgeAdapter.MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PokemonTypeBadgeAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_type_badge,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return badgeList.size
    }

    override fun onBindViewHolder(holder: PokemonTypeBadgeAdapter.MyViewHolder, position: Int) {
        holder.typeBadge.text = badgeList[position]
        holder.typeBadge.chipBackgroundColor = holder.gettypeBadgeColor(badgeList[position])

        holder.setTypeBadgeListener(clickListener, holder.typeBadge.text.toString())
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        var typeBadge: Chip = itemView.findViewById(R.id.typeBadge) as Chip
        fun setTypeBadgeListener(action: ITypeBadgeListener, typeBadge: String)
        {
            itemView.setOnClickListener { action.typeBadgeClicked(adapterPosition, typeBadge) }
        }

        fun gettypeBadgeColor(type:String):Int
        {
            when (type) {
                "Normal" -> return Color.parseColor("#A4A27A")
                "Dragon" -> return Color.parseColor("#743BFB")
                "Psychic" -> return Color.parseColor("#F15B85")
                "Electric" -> return Color.parseColor("#E9CA3C")
                "Ground" -> return Color.parseColor("#D9BF6C")
                "Grass" -> return Color.parseColor("#81C85B")
                "Poison" -> return Color.parseColor("#A441A3")
                "Steel" -> return Color.parseColor("#BAB7D2")
                "Fairy" -> return Color.parseColor("#DDA2DF")
                "Fire" -> return Color.parseColor("#F48130")
                "Fight" -> return Color.parseColor("#BE3027")
                "Bug" -> return Color.parseColor("#A8B822")
                "Ghost" -> return Color.parseColor("#705693")
                "Dark" -> return Color.parseColor("#745945")
                "Ice" -> return Color.parseColor("#9BD8D8")
                "Water" -> return Color.parseColor("#658FF1")
                else -> return Color.parseColor("#658FA0")
            }
        }

    }

}