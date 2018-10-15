package jobsad.checkout.uicomponent

import android.view.Gravity
import android.view.View
import android.widget.*
import jobsad.checkout.R
import jobsad.checkout.activity.EditJobInfoActivity
import org.jetbrains.anko.*
import org.w3c.dom.Text

class EditJobsInfoUI : AnkoComponent<EditJobInfoActivity> {
    lateinit var classicRB: RadioButton
    lateinit var standoutRB: RadioButton
    lateinit var premiumRB: RadioButton
    lateinit var radioGroup: RadioGroup
    lateinit var addJob: Button
    lateinit var deleteJob: Button
    lateinit var jobDescriptionTitle: TextView
    lateinit var jobTitleET: EditText
    lateinit var jobDescriptionET: EditText
    lateinit var imageUploadLayout: LinearLayout
    lateinit var jobImage: ImageView
    lateinit var uploadImage: Button

    override fun createView(ui: AnkoContext<EditJobInfoActivity>) = with(ui) {
        relativeLayout {
            verticalLayout {
                textView(R.string.edit_job_type) {
                    textColor = R.color.colorDark
                    textSize = 14F
                }.lparams {
                    width = matchParent
                    height = wrapContent
                    padding = dip(10)
                    gravity = Gravity.RIGHT
                }
                radioGroup = radioGroup {
                    classicRB = radioButton{
                        text = this.context.getString(R.string.classic)
                        textSize = 16F
                    }
                    standoutRB = radioButton{
                        text = this.context.getString(R.string.standout)
                        textSize = 16F
                    }
                    premiumRB = radioButton{
                        text = this.context.getString(R.string.premium)
                        textSize = 16F
                    }
                }.lparams {
                    width = matchParent
                    height = wrapContent
                    bottomMargin = dip(20)
                }
                textView(R.string.edit_job_title) {
                    textColor = R.color.colorDark
                    textSize = 14F
                }.lparams {
                    width = matchParent
                    height = wrapContent
                    padding = dip(10)
                    gravity = Gravity.RIGHT
                }
                jobTitleET = editText {
                    textColor = R.color.colorDark
                    textSize = 18F
                }.lparams {
                    width = matchParent
                    height = wrapContent
                    padding = dip(10)
                    gravity = Gravity.RIGHT
                }
                jobDescriptionTitle = textView(R.string.edit_job_description) {
                    textColor = R.color.colorDark
                    textSize = 14F
                }.lparams {
                    width = matchParent
                    height = wrapContent
                    padding = dip(10)
                    gravity = Gravity.RIGHT
                }
                jobDescriptionET = editText {
                    textColor = R.color.colorDark
                    textSize = 18F
                }.lparams {
                    width = matchParent
                    height = wrapContent
                    padding = dip(10)
                    gravity = Gravity.RIGHT
                }
                imageUploadLayout = verticalLayout{
                    textView(R.string.edit_job_image_upload) {
                        textColor = R.color.colorDark
                        textSize = 14F
                    }.lparams {
                        width = matchParent
                        height = wrapContent
                    }
                    linearLayout {
                        uploadImage = button(R.string.edit_job_image_upload).lparams {
                            width = wrapContent
                            height = wrapContent
                            margin = dip(10)
                        }
                        jobImage = imageView(0).lparams (dip(50), dip(50))
                    }
                    visibility = View.GONE
                }.lparams {
                    width = matchParent
                    height = wrapContent
                }

            }.lparams {
                width = matchParent
                height = wrapContent
                isFocusable = true
                isFocusableInTouchMode = true
            }
            verticalLayout {
                addJob = button(R.string.checkout).lparams {
                    width = matchParent
                    height = wrapContent
                    margin = dip(10)
                }
                deleteJob = button(R.string.delete_job){
                    visibility = View.GONE
                }.lparams {
                    width = matchParent
                    height = wrapContent
                    margin = dip(10)
                }
            }.lparams {
                width = matchParent
                height = wrapContent
                alignParentBottom()
            }

        }
    }

}