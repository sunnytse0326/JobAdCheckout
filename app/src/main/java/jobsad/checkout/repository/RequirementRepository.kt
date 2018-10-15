package jobsad.checkout.repository

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.rx.rx_object
import com.github.kittinunf.result.Result
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jobsad.checkout.model.PriceRules

class RequirementRepository: BaseRepository() {
    companion object {
        val REQUIREMENT = "requirement.json"
    }
    fun fetchRequirement(): Single<Result<PriceRules, FuelError>>? = Fuel.get("/$REQUIREMENT").rx_object(PriceRules.Deserializer()).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
}