package jobsad.checkout.model

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.core.Deserializable
import com.github.kittinunf.fuel.core.Response
import jobsad.checkout.utils.asSequence
import org.json.JSONObject
import java.io.Serializable
import java.nio.charset.Charset

open class PriceRule(val customer: String, val oneFreeForDeal: OneDealForFree, val discounts: MutableList<Discount>) : Serializable {
    companion object {
        fun init(json: JSONObject): PriceRule {
            val customer = json.getString("customer")
            val oneFreeForDeal: OneDealForFree = if(json.has("oneFreeForDeal")){ OneDealForFree.init(json.getJSONObject("oneFreeForDeal")) } else OneDealForFree("",0)
            val discounts: MutableList<Discount> = if(json.has("discounts")){ json.getJSONArray("discounts").asSequence().map { Discount.init(it as JSONObject) }.toMutableList() } else mutableListOf()
            return PriceRule(customer, oneFreeForDeal, discounts)
        }
    }
}