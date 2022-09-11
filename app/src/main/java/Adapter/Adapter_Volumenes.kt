package Adapter


import Class.Class_Volumenes
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.jeandavid.donwloadrevista.Articulos_Activity
import com.jeandavid.donwloadrevista.R
import com.squareup.picasso.Picasso

class Adapter_Volumenes constructor(context_: Context,
                                            val list: ArrayList<Class_Volumenes>) : RecyclerView.Adapter<Adapter_Volumenes.ViewHolder>() {
    val context: Context = context_

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): Adapter_Volumenes.ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.cardview_volumenes, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: Adapter_Volumenes.ViewHolder, i: Int) {

        viewHolder.volid.text = list[i].id
        viewHolder.voldoi.text="Doi: "+list[i].doi
        viewHolder.volfech.text="Publicado: "+list[i].publicacion
        viewHolder.volnum.text=list[i].nombre
        Picasso.get().load(list[i].urlcov).into(viewHolder.volport);
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        var volid: TextView
        var voldoi: TextView
        var volfech: TextView
        var volnum: TextView
        var volport: ImageView
        init {
            volid=itemView.findViewById(R.id.volumen_id)
            voldoi=itemView.findViewById(R.id.volumen_doi)
            volfech=itemView.findViewById(R.id.volumen_fecha)
            volnum=itemView.findViewById(R.id.volumen_numero)
            volport=itemView.findViewById(R.id.volumen_portada)

            //Datos volumenes
            itemView.setOnClickListener{ v: View ->
                var position: Int = getAdapterPosition()
                Snackbar.make(v, "Item selecccionado $position    ${volnum.text.toString()}" ,  Snackbar.LENGTH_LONG).setAction("Action", null).show()
                var i= Intent(context,Articulos_Activity::class.java)
                i.putExtra("dato_volumen", volid.text).putExtra("name",volnum.text)
                ContextCompat.startActivity(context, i, null)
            }
        }
    }



}