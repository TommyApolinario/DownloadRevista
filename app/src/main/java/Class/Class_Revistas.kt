package Class


import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Class_Revistas(a: JSONObject) {
    var id: String
    var nombre: String
    var abrev: String
    var urlport: String

    companion object {
        @Throws(JSONException::class)
        fun JsonObjectsBuild(jsonArray: JSONArray): ArrayList<Class_Revistas> {
            val revistas: ArrayList<Class_Revistas> = ArrayList<Class_Revistas>()
            var i = 0
            while (i < jsonArray.length()) {
                revistas.add(Class_Revistas(jsonArray.getJSONObject(i)))
                i++
            }
            return revistas
        }
    }

    init {
        nombre = a.getString("name").toString()
        abrev = a.getString("abbreviation").toString()
        id = a.getString("journal_id").toString()
        urlport = a.getString("portada").toString()
    }
}