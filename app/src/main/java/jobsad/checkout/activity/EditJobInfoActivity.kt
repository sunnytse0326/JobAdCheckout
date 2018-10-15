package jobsad.checkout.activity

import android.app.Activity
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.RadioButton
import jobsad.checkout.R
import jobsad.checkout.model.Job
import jobsad.checkout.uicomponent.EditJobsInfoUI
import jobsad.checkout.viewmodel.EditJobInfoViewModel
import org.jetbrains.anko.setContentView
import android.text.InputFilter
import android.util.Log
import android.widget.Toast
import java.util.*


class EditJobInfoActivity : AppCompatActivity() {
    private lateinit var mainUI: EditJobsInfoUI
    private lateinit var editJobInfoViewModel: EditJobInfoViewModel
    private val mLifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    companion object {
        val JOB = "JOB"
        val JOBACTION = "JOBACTION"
        val SELECTEDIMAGE = 12343
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initView()
    }

    private fun initView() {
        mainUI.setContentView(this)

        mainUI.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            val radioButton: RadioButton = radioGroup.findViewById(i)
            val condition = radioButton.text == this@EditJobInfoActivity.getText(R.string.classic) && radioButton.isChecked
            mainUI.imageUploadLayout.visibility = if (condition) View.GONE else View.VISIBLE
            mainUI.jobDescriptionTitle.text = "${this@EditJobInfoActivity.getString(R.string.edit_job_description)} ${if (condition) "(100 characters)" else "(500 characters)"}"
            mainUI.jobDescriptionET.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(if (condition) 100 else 500))
        }

        val job = this.intent.getParcelableExtra(JOB) ?: Job(
                Date().time.toInt(), "", "", "", "")

        mainUI.deleteJob.visibility = if (job.title.isEmpty()) View.GONE else View.VISIBLE
        mainUI.deleteJob.setOnClickListener {
            val intent = Intent()
            intent.putExtra(JOB, job)
            intent.putExtra(JOBACTION,  Job.Action.DELETE.name)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        mainUI.jobTitleET.setText(if (job.title.isNotEmpty()) job.title else "")
        mainUI.jobDescriptionET.setText(if (job.description.isNotEmpty()) job.description else "")

        if (job.type.isNotEmpty()) {
            if (job.type == "classic") {
                mainUI.classicRB.isChecked = true
            } else if (job.type == "standout") {
                mainUI.standoutRB.isChecked = true
            } else if (job.type == "premium") {
                mainUI.premiumRB.isChecked = true
            }
        }

        mainUI.uploadImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECTEDIMAGE)
        }

        if (job.imageURL.isNotEmpty()) {
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, Uri.parse(job.imageURL))
            mainUI.jobImage.setImageBitmap(bitmap)
        }

        mainUI.addJob.setOnClickListener {
            if (!mainUI.classicRB.isChecked && !mainUI.standoutRB.isChecked && !mainUI.premiumRB.isChecked) {
                Toast.makeText(this@EditJobInfoActivity, this@EditJobInfoActivity.getString(R.string.job_type_needed), Toast.LENGTH_SHORT).show()
            } else if (mainUI.jobTitleET.text.isEmpty()) {
                Toast.makeText(this@EditJobInfoActivity, this@EditJobInfoActivity.getString(R.string.job_title_needed), Toast.LENGTH_SHORT).show()
            } else if (mainUI.jobDescriptionET.text.isEmpty()) {
                Toast.makeText(this@EditJobInfoActivity, this@EditJobInfoActivity.getString(R.string.job_description_needed), Toast.LENGTH_SHORT).show()
            } else {
                editJobInfoViewModel.updateJob(job.id, mainUI.jobTitleET.text.toString(), mainUI.jobDescriptionET.text.toString(), if (mainUI.classicRB.isChecked) "classic" else if (mainUI.standoutRB.isChecked) "standout" else "premium")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SELECTEDIMAGE && resultCode == Activity.RESULT_OK && data?.data != null) {
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data.data)
            mainUI.jobImage.setImageBitmap(bitmap)
            editJobInfoViewModel.setImageURL(data.data.toString())
        }
    }

    private fun initData() {
        mainUI = EditJobsInfoUI()
        editJobInfoViewModel = ViewModelProviders.of(this, EditJobInfoViewModel.VMFactory(mLifecycleRegistry, this)).get(EditJobInfoViewModel::class.java)

        editJobInfoViewModel.jobInfo.observe(this, Observer {
            val intent = Intent()
            intent.putExtra(JOB, it)
            intent.putExtra(JOBACTION, if (mainUI.deleteJob.visibility == View.VISIBLE) Job.Action.UPDATE.name else Job.Action.ADD.name)
            setResult(Activity.RESULT_OK, intent)
            finish()
        })
    }

}

