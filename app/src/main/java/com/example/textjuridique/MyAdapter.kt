package com.example.textjuridique
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter (context: Context):RecyclerView.Adapter<MyAdapter.ItemHolder>() {
    val parentcontext=context
    var arrayList:ArrayList<TexteItem> = arrayListOf() //ConfigPrefFavorie.readDataFromPref(parentcontext)
     class ItemHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val article=itemView.findViewById<TextView>(R.id.articleNum)
        val journal=itemView.findViewById<TextView>(R.id.journalNum)
        val categorie=itemView.findViewById<TextView>(R.id.categCard)
        val organe=itemView.findViewById<TextView>(R.id.organeCard)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.itemview,parent,false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.article.text=arrayList[position].NumSGG.toString()
        holder.journal.text=arrayList[position].NumJO.toString()
        holder.categorie.text=arrayList[position].TypeTexteFR.toString()
        holder.organe.text=arrayList[position].OrganeFR.toString()
        holder.itemView.setOnClickListener{
            val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(it.rootView.context)
            dialogBuilder.setTitle("Ajouter au favoris")
            dialogBuilder.setMessage("Voulez vous ajouter cet article a la lliste des favoris?")
            dialogBuilder.setNegativeButton("Voir detail",
                    DialogInterface.OnClickListener { dialog, which ->
                        ConfigPrefItem.writeDataInPref(this.parentcontext,this.arrayList[position])
                        dialog.dismiss() })
            dialogBuilder.setPositiveButton("Ajouter", DialogInterface.OnClickListener { dialog, which -> { dialog.dismiss()
            //ConfigPrefFavorie.writeDataInPref(parentcontext,arrayList)
            } })
            fun OnClickListener() {
                dialogBuilder.create().show() }
            OnClickListener()
        }

    }

    override fun getItemCount(): Int {
        return 0
    }
    fun setData(){
        this.arrayList= ConfigPref.readDataFromPref(this.parentcontext)
        notifyDataSetChanged()
    }
}
