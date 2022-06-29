package com.example.textjuridique.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Adapter
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.textjuridique.*
import kotlinx.coroutines.*
import kotlinx.coroutines.android.awaitFrame
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import kotlin.coroutines.EmptyCoroutineContext

const val BASE_URL="http://192.168.43.59:9200/jo_index/"
const val TAG = "SearchFragment"
class ListFragment : Fragment() {
    lateinit var search:EditText
    var array: ArrayList<TexteItem> = arrayListOf()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var searchString :String
        searchString=""
        val view=inflater.inflate(R.layout.fragment_list, container, false)
        search=view?.findViewById(R.id.search)!!

        var adapter= MyAdapter(this.requireContext())
        var btn=view?.findViewById<Button>(R.id.btnOk)
        btn?.setOnClickListener {
            var txt=search?.text.toString()
            searchString=searchString+txt
            getMyData(searchString)
            search.clearComposingText()
            print(searchString)
        }
        Toast.makeText(activity,"Real one"+ConfigPref.readDataFromPref(this.requireContext()).size.toString(),Toast.LENGTH_LONG).show()
        val recyclerView=view?.findViewById<RecyclerView>(R.id.recyclerView)

        val layout=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recyclerView?.layoutManager=layout
        recyclerView?.adapter=adapter

       return view
        }
        fun getMyData(searchString:String){
            var retrofitBuilder= Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            var api:ApiSearch=retrofitBuilder.create(ApiSearch::class.java)
            var call=api.search(searchString)
            var toast:Toast
            call?.enqueue(object : Callback<HitsObject?> {
                var jsonResponse: String? =""
                override fun onResponse(
                        call: Call<HitsObject?>?,
                        response: Response<HitsObject?>) {
                    var hitsList = HitsList()
                    makeText(activity, "step1", Toast.LENGTH_SHORT).show()
                    try {
                        Log.d(TAG, "onResponse: server response: $response")
                        if (response.isSuccessful) {
                            makeText(activity, "step2", Toast.LENGTH_SHORT).show()
                            hitsList = response.body()?.hits!!
                        } else {
                            jsonResponse = response.errorBody()!!.string()
                            makeText(activity, "lemkl,f", Toast.LENGTH_SHORT).show()

                        }
                        Log.d(TAG, "onResponse: hits: $hitsList")
                        for (i in hitsList.postIndex!!.indices) {
                            Log.d(TAG, "onResponse: data: " + hitsList.postIndex!![i].text?.NumSGG)
                            array.add(hitsList.postIndex!![0].text!!)
                        }

                        for(i in array!!){
                            print(i.NumSGG)
                        }
                        makeText(activity, array.size.toString(), Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "onResponse: size: " + array.size)
                        //setup the list of posts
                    } catch (e: NullPointerException) {
                        Log.e(TAG, "onResponse: NullPointerException: " + e.message)
                        makeText(activity, searchString, Toast.LENGTH_SHORT).show()
                    } catch (e: IndexOutOfBoundsException) {
                        Log.e(TAG, "onResponse: IndexOutOfBoundsException: " + e.message)
                        makeText(activity, "33", Toast.LENGTH_SHORT).show()
                    } catch (e: IOException) {
                        Log.e(TAG, "onResponse: IOException: " + e.message)
                        makeText(activity, "33", Toast.LENGTH_SHORT).show()
                    }

                   // contenu?.text =  array.get(0).ATexte.toString()
                    var valeur: String =""
                    var contenu = view?.findViewById<TextView>(R.id.contenu)
                    for (i in array) {
                        // Log.d(TAG, "onResponse: data: " + hitsList.postIndex!![i].text?.NumSGG)
                        valeur = valeur!! + i.SommaireFR.toString() + "\n"
                        contenu?.text =   valeur
                    }


                }
                override fun onFailure(call: Call<HitsObject?>?, t: Throwable) {
                    Log.e(TAG, "onFailure: " + t.message)
                    searchString+t.message.toString()
                    makeText(activity, "failed "+t.message, Toast.LENGTH_LONG).show()
                    print(t.message)
                }
            })
        }
}