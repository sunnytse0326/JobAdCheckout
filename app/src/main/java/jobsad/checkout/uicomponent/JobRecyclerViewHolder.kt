package jobsad.checkout.uicomponent

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView


class JobRecyclerViewHolder(mainUI: JobRecyclerViewUI, itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView
    var description: TextView
    var adType: TextView
    var cardView: CardView
    var imageView: ImageView

    init {
        title = mainUI.title
        description = mainUI.description
        cardView = mainUI.cardView
        imageView = mainUI.jobImage
        adType = mainUI.adType
    }

}