package jobsad.checkout.activity

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import jobsad.checkout.model.Customer
import jobsad.checkout.uicomponent.LoginUI
import jobsad.checkout.viewmodel.LoginViewModel
import org.jetbrains.anko.setContentView

class LoginActivity : AppCompatActivity() {
    private lateinit var mainUI: LoginUI
    private lateinit var loginViewModel: LoginViewModel
    private val mLifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initView()
    }

    private fun initView(){
        mainUI.setContentView(this)

        mainUI.default.setOnClickListener { loginViewModel.setCustomerType(Customer.Type.DEFAULT) }
        mainUI.unilever.setOnClickListener { loginViewModel.setCustomerType(Customer.Type.UNILEVER) }
        mainUI.apple.setOnClickListener { loginViewModel.setCustomerType(Customer.Type.APPLE) }
        mainUI.nike.setOnClickListener { loginViewModel.setCustomerType(Customer.Type.NIKE) }
        mainUI.ford.setOnClickListener { loginViewModel.setCustomerType(Customer.Type.FORD) }
    }

    private fun initData(){
        loginViewModel = ViewModelProviders.of(this, LoginViewModel.VMFactory(mLifecycleRegistry, this)).get(LoginViewModel::class.java)
        mainUI = LoginUI()

        loginViewModel.customer.observe(this, Observer { customer ->
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            intent.putExtra(HomeActivity.CUSTOMER, customer)
            startActivity(intent)
            finish()
        })
    }

}

