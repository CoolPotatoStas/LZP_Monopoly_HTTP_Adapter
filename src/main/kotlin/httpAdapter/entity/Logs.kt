package httpAdapter.entity

import com.google.gson.Gson

data class Logs(
    var name_app: String,
    var time: String,
    var logs: String
) {

    override fun toString(): String {
        return Gson().toJson(this)
    }
}