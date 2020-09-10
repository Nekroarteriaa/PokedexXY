package com.nekroapps.pokedexxy.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.nekroapps.pokedexxy.PokeBank.PokeBank
import com.nekroapps.pokedexxy.R

class FragmentViewAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
{
    var fragList: MutableList<Fragment> = ArrayList()
    var fragName: MutableList<String> = ArrayList()
    var currentPosition: Int = 0
    override fun getCount(): Int {
        return fragList.size
    }

    override fun getItem(position: Int): Fragment {
        currentPosition = position

        return fragList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragName[position]
    }

    fun addFragments( fg:Fragment,  name: String)
    {
        fragList.add(fg)
        fragName.add(name)
    }

}


class FAAdapter(var context: Context , var frgmts : ArrayList<Fragment>, var frgmManager: FragmentManager): RecyclerView.Adapter<FAAdapter.MyViewHolder>()
{
    var isShowing: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FAAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.fragment_holder_layout,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FAAdapter.MyViewHolder, position: Int) {



        if(!isShowing)
        {
            frgmManager.beginTransaction().add(R.id.pokemonInformation2, frgmts[position]).addToBackStack(null).commit()
            isShowing = true
        }
            //

    }

    override fun getItemCount(): Int {
        return frgmts.size
    }

    fun activateBaseFragment()
    {

    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        var fragmntCanvasHolder: FrameLayout


        init {
            fragmntCanvasHolder = itemView.let { it!!.findViewById(R.id.pokemonInformation2) }
        }
    }

}