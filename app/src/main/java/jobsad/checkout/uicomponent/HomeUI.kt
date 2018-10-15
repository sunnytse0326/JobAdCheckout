package jobsad.checkout.uicomponent

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import jobsad.checkout.R
import jobsad.checkout.activity.HomeActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import java.util.*

class HomeUI : AnkoComponent<HomeActivity> {
    lateinit var recyclerView: RecyclerView
    lateinit var loadLty: RelativeLayout
    lateinit var addNewJobAdBtn: Button
    lateinit var checkoutBtn: Button
    lateinit var checkoutPriceText: TextView

    override fun createView(ui: AnkoContext<HomeActivity>) = with(ui) {
        relativeLayout {
            recyclerView = recyclerView {
                clipToPadding = false
                topPadding = dip(5)
                bottomPadding = dip(45)
                id = Date().time.toInt()
            }.lparams {
                width = matchParent
                height = matchParent
                bottomMargin = dip(100)
            }
            verticalLayout {
                linearLayout {
                    addNewJobAdBtn = button(R.string.add_job_ad).lparams {
                        width = 0
                        height = matchParent
                        weight = 0.5F
                        margin = dip(10)
                    }
                    checkoutBtn = button(R.string.checkout).lparams {
                        width = 0
                        height = matchParent
                        weight = 0.5F
                        margin = dip(10)
                    }
                }
                checkoutPriceText = textView(R.string.check_out_price) {
                    textColor = R.color.colorDark
                    textSize = 18F
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams {
                    width = wrapContent
                    height = matchParent
                    rightMargin = dip(15)
                    padding = dip(15)
                    gravity = Gravity.RIGHT
                }
            }.lparams {
                width = matchParent
                height = wrapContent
                alignParentBottom()
            }
            loadLty = relativeLayout {
                backgroundColor = Color.WHITE
                progressBar {
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    gravity = Gravity.CENTER
                }
            }.lparams {
                width = matchParent
                height = matchParent
            }
        }
    }

}