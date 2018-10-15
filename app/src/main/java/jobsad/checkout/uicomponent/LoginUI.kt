package jobsad.checkout.uicomponent

import android.graphics.Typeface
import android.widget.Button
import jobsad.checkout.R
import jobsad.checkout.activity.LoginActivity
import org.jetbrains.anko.*

class LoginUI : AnkoComponent<LoginActivity> {
    lateinit var default: Button
    lateinit var unilever: Button
    lateinit var nike: Button
    lateinit var apple: Button
    lateinit var ford: Button

    override fun createView(ui: AnkoContext<LoginActivity>) = with(ui) {
        relativeLayout {
            textView(R.string.customer_select_title){
                padding = dip(10)
                textSize = 18F
                typeface = Typeface.DEFAULT_BOLD
            }
            verticalLayout {
                default = button(R.string.normal_customer).lparams {
                    width = matchParent
                    height = wrapContent
                    topMargin = dip(5)
                    bottomMargin = dip(5)
                    leftMargin = dip(30)
                    rightMargin = dip(30)
                }
                unilever = button(R.string.unilever).lparams {
                    width = matchParent
                    height = wrapContent
                    topMargin = dip(5)
                    bottomMargin = dip(5)
                    leftMargin = dip(30)
                    rightMargin = dip(30)
                }
                nike = button(R.string.nike).lparams {
                    width = matchParent
                    height = wrapContent
                    topMargin = dip(5)
                    bottomMargin = dip(5)
                    leftMargin = dip(30)
                    rightMargin = dip(30)
                }
                apple = button(R.string.apple).lparams {
                    width = matchParent
                    height = wrapContent
                    topMargin = dip(5)
                    bottomMargin = dip(5)
                    leftMargin = dip(30)
                    rightMargin = dip(30)
                }
                ford = button(R.string.ford).lparams {
                    width = matchParent
                    height = wrapContent
                    topMargin = dip(5)
                    bottomMargin = dip(5)
                    leftMargin = dip(30)
                    rightMargin = dip(30)
                }
            }.lparams {
                width = matchParent
                height = wrapContent
                centerInParent()
            }
        }
    }

}