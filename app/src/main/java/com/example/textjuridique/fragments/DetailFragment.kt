package com.example.textjuridique.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.textjuridique.ConfigPref
import com.example.textjuridique.ConfigPrefItem
import com.example.textjuridique.R


class DetailFragment : Fragment() {
    var url="https://www.joradp.dz/FTP/JO-FRANCAIS/ "
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var view= inflater.inflate(R.layout.fragment_detail, container, false)
        ConfigPrefItem.writeDataInPref(this.requireContext(),ConfigPref.readDataFromPref(this.requireContext())[0])
        var item= ConfigPref.readDataFromPref(this.requireContext())[1]
        var text1=view?.findViewById<TextView>(R.id.numSGG)
        var text2=view?.findViewById<TextView>(R.id.numJO)
        var text3=view?.findViewById<TextView>(R.id.organe)
        var text4=view?.findViewById<TextView>(R.id.year)
        var text5=view?.findViewById<TextView>(R.id.datePublication)
        var text6=view?.findViewById<TextView>(R.id.contenuSommaire)
        var btn=view?.findViewById<Button>(R.id.boutton)
        text1?.text=item.NumSGG
        text2?.text=item.NumJO
        text3?.text=item.OrganeFR
        text4?.text=item.AnneeJO
        text5?.text=item.DatepublicationFR
        text6?.text=item.SommaireFR
        url="https://www.joradp.dz/FTP/JO-FRANCAIS/${item.AnneeJO}/${item.F_PDFFileName} "
        btn?.setOnClickListener {
            val uri: Uri = Uri.parse("https://www.joradp.dz/FTP/JO-FRANCAIS/${item.AnneeJO}/${item.F_PDFFileName}") // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        //findNavController().navigate(R.id.action_detailFragment_to_listFragment)
        return view
    }

}