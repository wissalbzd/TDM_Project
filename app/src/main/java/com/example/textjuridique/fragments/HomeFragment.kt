package com.example.textjuridique.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.textjuridique.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class HomeFragment : Fragment() {
    var searchString=""
    var array: ArrayList<TexteItem> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_home, container, false)
        Toast.makeText(context,"text",Toast.LENGTH_SHORT).show()
        val btn=view?.findViewById<Button>(R.id.btn)
        getMyData()
        btn?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_listFragment)
           }
            return view
    }
    fun getMyData(){
        var retrofitBuilder= Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        var api: ApiSearch =retrofitBuilder.create(ApiSearch::class.java)
        var call=api.getData()
        var toast:Toast
        call.enqueue(object : Callback<HitsObject?> {
            var jsonResponse: String? =""
            var list: ArrayList<TexteItem> = arrayListOf()
            override fun onResponse(
                    call: Call<HitsObject?>?,
                    response: Response<HitsObject?>) {
                var hitsList = HitsList()
                Toast.makeText(activity, "step1", Toast.LENGTH_SHORT).show()
                try {
                    Log.d(TAG, "onResponse: server response: $response")
                    if (response.isSuccessful) {
                        Toast.makeText(activity, "step2", Toast.LENGTH_SHORT).show()
                        hitsList = response.body()?.hits!!
                    } else {
                        jsonResponse = response.errorBody()!!.string()
                        Toast.makeText(activity, "lemkl,f", Toast.LENGTH_SHORT).show()

                    }
                    Log.d(TAG, "onResponse: hits: $hitsList")
                    for (i in hitsList.postIndex!!.indices) {
                        Log.d(TAG, "onResponse: data: " + hitsList.postIndex!![i].text)
                        array.add(hitsList.postIndex!![0].text!!)
                    }
                    ConfigPref.writeDataInPref(requireContext(),array)
                    Toast.makeText(activity, array.size.toString(), Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "onResponse: size: " + array.size)
                    //setup the list of posts
                } catch (e: NullPointerException) {
                    Log.e(TAG, "onResponse: NullPointerException: " + e.message)
                    Toast.makeText(activity, "22", Toast.LENGTH_SHORT).show()
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "onResponse: IndexOutOfBoundsException: " + e.message)
                    Toast.makeText(activity, "33", Toast.LENGTH_SHORT).show()
                } catch (e: IOException) {
                    Log.e(TAG, "onResponse: IOException: " + e.message)
                    Toast.makeText(activity, "33", Toast.LENGTH_SHORT).show()
                }

            }
            override fun onFailure(call: Call<HitsObject?>?, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.message)
                searchString=t.message.toString()
                Toast.makeText(activity, "failed " + t.message, Toast.LENGTH_LONG).show()
                print(t.message)
            }
        })
    }
}