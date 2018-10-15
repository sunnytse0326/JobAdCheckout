package jobsad.checkout.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Job(val id: Int, val title: String, val description: String, val imageURL: String, val type: String): Parcelable{
    enum class Action{
        DELETE, ADD, UPDATE
    }
}