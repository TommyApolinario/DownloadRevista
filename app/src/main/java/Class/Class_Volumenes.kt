package Class

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Class_Volumenes(a: JSONObject) {
    var id: String
    var nombre: String
    var publicacion: String
    var urlcov: String
    var doi:String

    companion object {
        @Throws(JSONException::class)
        fun JsonObjectsBuild(jsonArray: JSONArray): ArrayList<Class_Volumenes> {
            val volumenes: ArrayList<Class_Volumenes> = ArrayList<Class_Volumenes>()
            var i = 0
            while (i < jsonArray.length()) {
                volumenes.add(Class_Volumenes(jsonArray.getJSONObject(i)))
                i++
            }
            return volumenes
        }
    }

    init {
        nombre = "Vol. "+ a.getString("volume").toString()+ " Núm. "+a.getString("number").toString() +": "+a.getString("title").toString()
        publicacion = a.getString("date_published").toString()
        id = a.getString("issue_id").toString()
        urlcov = a.getString("cover").toString()
        doi=a.getString("doi").toString()
    }
}