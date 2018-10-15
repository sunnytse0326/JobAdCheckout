package jobsad.checkout.repository

import com.github.kittinunf.fuel.core.FuelManager

open class BaseRepository{
    init {
        FuelManager.instance.basePath = "https://sunnytse0326.github.io/MockJson/jobs"
    }
}