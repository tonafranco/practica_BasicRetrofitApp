package com.movilesi.practica_basicretrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.movilesi.practica_basicretrofitapp.remote.PokemonEntry
import com.movilesi.practica_basicretrofitapp.remote.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitBuilder.create().getPokemonById("27")

        retrofit.enqueue(object: Callback<PokemonEntry>{
            override fun onResponse(call: Call<PokemonEntry>, response: Response<PokemonEntry>) {
                Log.d("retrofitresponse", "res: ${response.body()}")
                Log.d("retrofitresponse", "name: ${response.body()?.name}")
                Log.d("retrofitresponse", "sprites: ${response.body()?.sprites}")
                val stats = response.body()?.stats
                if (stats != null){
                    for (stat in stats){
                        Log.d("retrofitresponse", "${stat.stat.stat_value}: ${stat.base_stat}")
                    }
                }
                val typesArray = response.body()?.types
                if (typesArray != null){
                    Log.d("retrofitresponse", "type: ${typesArray[0].type.name}")
                }

            }
            override fun onFailure(call: Call<PokemonEntry>, t:Throwable){
                Log.e("retrofitresponse", "error: ${t.message}")
            }
        })
    }
}