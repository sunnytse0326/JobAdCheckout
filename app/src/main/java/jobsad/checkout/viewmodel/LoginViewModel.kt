package jobsad.checkout.viewmodel

import android.arch.lifecycle.*
import android.content.Context
import android.util.Log
import com.github.kittinunf.fuel.core.FuelError
import jobsad.checkout.model.Customer
import jobsad.checkout.model.PriceRules
import jobsad.checkout.repository.RequirementRepository

class LoginViewModel(val lifecycle: Lifecycle, private val lifecycleOwner: LifecycleOwner) : ViewModel() {
    val customer: MutableLiveData<Customer> = MutableLiveData()


    fun setCustomerType(type: Customer.Type){
        val newCustomer = Customer(type)
        customer.value = newCustomer
    }

    class VMFactory(private val lifecycle: Lifecycle, private val lifecycleOwner: LifecycleOwner) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = LoginViewModel(lifecycle, lifecycleOwner) as T
    }
}