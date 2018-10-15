package jobsad.checkout.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Customer(val type: Type): Parcelable{
    enum class Type(val id: String) {
        DEFAULT("default"),
        UNILEVER("unilever"),
        APPLE("apple"),
        NIKE("nike"),
        FORD("ford")
    }
}