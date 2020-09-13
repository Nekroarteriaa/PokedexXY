package com.nekroapps.pokedexxy.Activities.MainMenu

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import co.revely.gradient.RevelyGradient
import com.google.gson.GsonBuilder
import com.nekroapps.pokedexxy.Activities.PokedexBank.PokedexBankActivity
import com.nekroapps.pokedexxy.PokeBank.PokeBank
import com.nekroapps.pokedexxy.PokemonObject.Pokemon
import com.nekroapps.pokedexxy.R
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.io.StringReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule


class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        paintBackground()


        gettingFilledPokemonBank()


    }

    private fun gettingFilledPokemonBank() {

        val request = Request.Builder()
            .url("https://gist.githubusercontent.com/mrcsxsiq/b94dbe9ab67147b642baa9109ce16e44/raw/97811a5df2df7304b5bc4fbb9ee018a0339b8a38")
            .build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("myApp", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val strg = StringReader(response.body!!.string())
                val gson = GsonBuilder().create()
                val mpoke = gson.fromJson(strg, Array<Pokemon>::class.java).toList()


                PokeBank.PokemonBank = ArrayList(mpoke)



               Timer("Begin", false).schedule(1000)
               {
                   goToMainMenu(baseContext)
               }

            }

        })
    }

    fun goToMainMenu(context: Context)
    {
        val intent = Intent(context, PokedexBankActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    fun paintBackground()
    {
        RevelyGradient
            .radial()
            .colors(intArrayOf(Color.parseColor("#F77A26"),Color.parseColor("#FB6A3D"),Color.parseColor("#C54216")))
            .angle(45f)
            //.center(1020,30)
            .onBackgroundOf(splashImage)
    }
}



//
//    fun apiServicePokemonCollectionDemand()
//    {
//        val retrofit : Retrofit = Retrofit.Builder()
//            .baseUrl("https://gist.githubusercontent.com/mrcsxsiq/b94dbe9ab67147b642baa9109ce16e44/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//
//
//        val service : ApiService = retrofit.create<ApiService>(
//            ApiService::class.java)
//
//        service.getPokemonCollectionFromServer("97811a5df2df7304b5bc4fbb9ee018a0339b8a38").enqueue(object :
//            Callback<ArrayList<Pokemon>> {
//            override fun onFailure(call: Call<ArrayList<Pokemon>>, t: Throwable) {
//                Log.e("myApp","Error-> ${t.printStackTrace()}")
//            }
//
//            override fun onResponse(
//                call: Call<ArrayList<Pokemon>>,
//                response: Response<ArrayList<Pokemon>>
//            ) {
//                Log.i("myApp","Aqui")
//            }
//
//
//        })
//    }
//}