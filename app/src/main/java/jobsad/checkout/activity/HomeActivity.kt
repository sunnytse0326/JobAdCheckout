package jobsad.checkout.activity

import android.app.Activity
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import jobsad.checkout.R
import jobsad.checkout.adapter.JobsAdapter
import jobsad.checkout.model.Customer
import jobsad.checkout.model.Job
import jobsad.checkout.uicomponent.HomeUI
import jobsad.checkout.viewmodel.HomeViewModel
import org.jetbrains.anko.setContentView
import java.util.*

class HomeActivity : AppCompatActivity() {
    private lateinit var mainUI: HomeUI
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: JobsAdapter
    private val mLifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    companion object {
        val CUSTOMER = "CUSTOMER"
        val JOB = "JOB"
        val JOBACTION = "JOBACTION"
        val ADDJOBRESPONSE = 14564
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initView()
        fetchRequirement()
    }

    private fun initView() {
        mainUI.setContentView(this)
        mainUI.checkoutPriceText.text = "${this.getString(R.string.check_out_price)}: $0"

        mainUI.addNewJobAdBtn.setOnClickListener {
            var intent = Intent(this@HomeActivity, EditJobInfoActivity::class.java)
            intent.putExtra(HomeActivity.JOB, Job(Date().time.toInt(), "", "", "", ""))
            startActivityForResult(intent, ADDJOBRESPONSE)
        }

        mainUI.recyclerView.layoutManager = LinearLayoutManager(this)
        mainUI.recyclerView.adapter = adapter
    }

    private fun initData() {
        homeViewModel = ViewModelProviders.of(this, HomeViewModel.VMFactory(mLifecycleRegistry, this)).get(HomeViewModel::class.java)
        mainUI = HomeUI()
        homeViewModel.setCustomer(this.intent.getParcelableExtra(CUSTOMER)
                ?: Customer(Customer.Type.DEFAULT))
        adapter = JobsAdapter(this, object : JobsAdapter.OnClickListener {
            override fun onBackgroundClicked(position: Int) {
                var intent = Intent(this@HomeActivity, EditJobInfoActivity::class.java)
                intent.putExtra(HomeActivity.JOB, adapter.jobs[position])
                startActivityForResult(intent, ADDJOBRESPONSE)
            }
        }, mutableListOf())
    }

    private fun fetchRequirement() {
        homeViewModel.getRequirement().observe(this, Observer { priceRules ->
            mainUI.loadLty.visibility = View.GONE
        })
        homeViewModel.getErrors().observe(this, Observer { err ->
            mainUI.loadLty.visibility = View.GONE
            Toast.makeText(this@HomeActivity, this@HomeActivity.getString(R.string.error_failed_api), Toast.LENGTH_SHORT).show()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADDJOBRESPONSE && resultCode == Activity.RESULT_OK && data != null) {
            val job: Job = data.getParcelableExtra(JOB)
            val action: String = data.getStringExtra(JOBACTION)

            when(action){
                Job.Action.ADD.name -> {
                    adapter.addJob(job)
                    true
                }
                Job.Action.UPDATE.name -> {
                    adapter.updateJob(job)
                    true
                }
                Job.Action.DELETE.name -> {
                    adapter.deleteJob(job.id)
                    true
                }
            }
            adapter.notifyDataSetChanged()
            mainUI.checkoutPriceText.text = "${this.getString(R.string.check_out_price)}: ${homeViewModel.filterPriceByRequirement(adapter.jobs)}"
        }
    }
}

