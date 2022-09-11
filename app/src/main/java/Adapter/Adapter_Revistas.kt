package Adapter


import Class.Class_Revistas
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.jeandavid.donwloadrevista.R
import com.jeandavid.donwloadrevista.Volumenes_Activity
import com.squareup.picasso.Picasso

class Adapter_Revistas constructor(context_: Context,
                                   intent: Intent,
                                   val list: ArrayList<Class_Revistas>) : RecyclerView.Adapter<Adapter_Revistas.ViewHolder>()
{
    val context: Context = context_

    var dataintent= intent

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): Adapter_Revistas.ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.cardview_revistas, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: Adapter_Revistas.ViewHolder, i: Int) {

        viewHolder.revid.text = list[i].id
        viewHolder.revname.text = list[i].nombre
        viewHolder.revabrev.text=list[i].abrev
        Picasso.get().load(list[i].urlport).into(viewHolder.revport);
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        var revid: TextView
        var revname: TextView
        var revabrev: TextView
        var revport: ImageView

        init {
            revid=itemView.findViewById(R.id.revista_id)
            revname=itemView.findViewById(R.id.revista_name)
            revabrev=itemView.findViewById(R.id.revista_abreviatura)
            revport=itemView.findViewById(R.id.revista_portada)

            //Datos revista
            itemView.setOnClickListener{ view: View ->
                var i=Intent(context,Volumenes_Activity::class.java)
                i.putExtra("dato_revista", revid.text)
                i.putExtra("revista",revname.text)
                startActivity(context,i, null)
            }
        }

    }



}