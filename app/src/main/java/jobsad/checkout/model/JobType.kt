package jobsad.checkout.model

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.core.Deserializable
import com.github.kittinunf.fuel.core.Response
import jobsad.checkout.utils.asSequence
import org.json.JSONObject
import java.nio.charset.Charset

data class JobType(val id: String = "", val price: String = ""){
    companion object {
        fun init(json: JSONObject): JobType {
            return JobType(json.getString("id"), json.getString("price"))
        }
    }
}