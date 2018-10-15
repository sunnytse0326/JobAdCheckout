package jobsad.checkout.uicomponent

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.CardView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class JobRecyclerViewUI : AnkoComponent<ViewGroup> {
    lateinit var title: TextView
    lateinit var description: TextView
    lateinit var adType: TextView
    lateinit var jobImage: ImageView
    lateinit var cardView: CardView

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        verticalLayout {
            padding = dip(5)
            lparams(matchParent, wrapContent)
            cardView = cardView {
                cardElevation = dip(6).toFloat()
                radius = dip(5).toFloat()
                useCompatPadding = true

                linearLayout {
                    jobImage = imageView {
                        backgroundColor = Color.GRAY
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }.lparams {
                        width = dip(60)
                        height = dip(60)
                        margin = dip(15)
                        gravity = Gravity.CENTER_VERTICAL
                    }
                    verticalLayout {
                        padding = dip(15)
                        adType = textView {
                            textSize = 22f
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams {
                            width = matchParent
                            height = wrapContent
                        }
                        title = textView {
                            textSize = 18f
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams {
                            width = matchParent
                            height = wrapContent
                        }
                        description = textView {
                            textSize = 16f
                        }.lparams {
                            width = matchParent
                            height = wrapContent
                        }
                    }.lparams {
                        width = matchParent
                        height = wrapContent
                    }
                }.lparams {
                    width = matchParent
                    height = wrapContent
                }
            }.lparams {
                width = matchParent
                height = wrapContent
            }
        }
    }
}