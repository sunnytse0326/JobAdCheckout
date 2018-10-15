package jobsad.checkout.model

import org.json.JSONObject

data class OneDealForFree(val id: String = "", val numOfDeal: Int = 0){
    companion object {
        fun init(json: JSONObject): OneDealForFree {
            return OneDealForFree(json.getString("id"), json.getInt("numOfDeal"))
        }
    }
}