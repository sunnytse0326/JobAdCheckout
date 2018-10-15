package jobsad.checkout.model

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.core.Deserializable
import com.github.kittinunf.fuel.core.Response
import jobsad.checkout.utils.asSequence
import org.json.JSONObject
import java.nio.charset.Charset

data class Discount(val id: String = "", val numberOfDeal: Int, val price: String = ""){
    companion object {
        fun init(json: JSONObject): Discount {
            return Discount(json.getString("id"), json.getInt("numOfDeal"), json.getString("price"))
        }
    }
}