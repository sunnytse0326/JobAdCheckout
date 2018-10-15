package jobsad.checkout.model

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.core.Deserializable
import com.github.kittinunf.fuel.core.Response
import jobsad.checkout.utils.asSequence
import org.json.JSONObject
import java.io.Serializable
import java.nio.charset.Charset

open class PriceRules(val jobTypes: MutableList<JobType>, val rules: MutableList<PriceRule>) : Serializable {
    companion object {
        fun init(json: JSONObject): PriceRules {
            val jobTypes: MutableList<JobType> = if(json.has("jobTypes")){ json.getJSONArray("jobTypes").asSequence().map { JobType.init(it as JSONObject) }.toMutableList() } else mutableListOf()
            val rules: MutableList<PriceRule> = if(json.has("rules")){ json.getJSONArray("rules").asSequence().map { PriceRule.init(it as JSONObject) }.toMutableList() } else mutableListOf()
            return PriceRules(jobTypes, rules)
        }
    }

    class Deserializer : Deserializable<PriceRules> {
        override fun deserialize(response: Response): PriceRules {
            val json = Json(response.data.toString(Charset.defaultCharset()))
            return PriceRules.init(json.obj())
        }
    }
}