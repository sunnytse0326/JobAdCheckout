package jobsad.checkout.viewmodel

import android.arch.lifecycle.*
import android.content.Context
import android.util.Log
import com.github.kittinunf.fuel.core.FuelError
import jobsad.checkout.model.Customer
import jobsad.checkout.model.Job
import jobsad.checkout.model.PriceRules
import jobsad.checkout.repository.RequirementRepository
import org.jetbrains.anko.collections.forEachByIndex

class HomeViewModel(val lifecycle: Lifecycle, private val lifecycleOwner: LifecycleOwner) : ViewModel() {
    private val priceRules: MutableLiveData<PriceRules> = MutableLiveData()
    private val requirementRepository: RequirementRepository = RequirementRepository()
    private val error: MutableLiveData<FuelError> = MutableLiveData()
    private var customer: Customer = Customer(Customer.Type.DEFAULT)

    fun getRequirement(): MutableLiveData<PriceRules> {
        requirementRepository.fetchRequirement()?.subscribe { result, _ ->
            val (data, err) = result
            if (err == null) {
                priceRules.value = data
            } else {
                error.value = err
            }
        }
        return priceRules
    }


    fun getErrors(): MutableLiveData<FuelError> {
        return error
    }

    fun filterPriceByRequirement(jobs: MutableList<Job>): Float {
        val priceRules = priceRules.value!!
        var price = 0F
        val adsID: Array<String> = arrayOf("classic", "standout", "premium")
        var adsPriceHandled: Array<Boolean> = arrayOf(false, false, false)

        val classicJobs = jobs.filter { it.type == adsID[0] }
        val standOutJobs = jobs.filter { it.type == adsID[1] }
        val premiumJobs = jobs.filter { it.type == adsID[2] }

        val classicPrice = priceRules.jobTypes.filter { it.id == adsID[0] }[0].price.toFloat()
        val standOutPrice = priceRules.jobTypes.filter { it.id == adsID[1] }[0].price.toFloat()
        val premiumPrice = priceRules.jobTypes.filter { it.id == adsID[2] }[0].price.toFloat()

        priceRules?.rules?.forEachIndexed { index, priceRule ->
            if (customer.type.name == priceRule.customer) {
                if (priceRule.oneFreeForDeal != null && priceRule.oneFreeForDeal.numOfDeal > 0) {
                    when (priceRule.oneFreeForDeal.id) {
                        adsID[0] -> {
                            price = (classicJobs.size / (priceRule.oneFreeForDeal.numOfDeal + 1)) * priceRule.oneFreeForDeal.numOfDeal * classicPrice + (classicJobs.size % (priceRule.oneFreeForDeal.numOfDeal + 1)) * classicPrice
                            adsPriceHandled[0] = true
                            true
                        }
                        adsID[1] -> {
                            price = (standOutJobs.size / (priceRule.oneFreeForDeal.numOfDeal + 1)) * priceRule.oneFreeForDeal.numOfDeal * standOutPrice + (standOutJobs.size % (priceRule.oneFreeForDeal.numOfDeal + 1)) * standOutPrice
                            adsPriceHandled[1] = true
                            true
                        }
                        adsID[2] -> {
                            price = (premiumJobs.size / (priceRule.oneFreeForDeal.numOfDeal + 1)) * priceRule.oneFreeForDeal.numOfDeal * premiumPrice + (premiumJobs.size % (priceRule.oneFreeForDeal.numOfDeal + 1)) * premiumPrice
                            adsPriceHandled[2] = true
                            true
                        }
                    }
                }
                if (priceRule.discounts != null && priceRule.discounts.size > 0) {
                    priceRule.discounts.forEachByIndex {
                        if (it.id == adsID[0] && classicJobs.isNotEmpty()) {
                            adsPriceHandled[0] = true
                            price += (if (classicJobs.size >= it.numberOfDeal) classicJobs.size * it.price.toFloat() else classicJobs.size * classicPrice)
                        } else if (it.id == adsID[1] && standOutJobs.isNotEmpty()) {
                            adsPriceHandled[1] = true
                            price += (if (standOutJobs.size >= it.numberOfDeal) standOutJobs.size * it.price.toFloat() else standOutJobs.size * standOutPrice)
                        } else if (it.id == adsID[2] && premiumJobs.isNotEmpty()) {
                            adsPriceHandled[2] = true
                            price += (if (premiumJobs.size >= it.numberOfDeal) premiumJobs.size * it.price.toFloat() else premiumJobs.size * premiumPrice)
                        }
                    }
                }
                price += (if (!adsPriceHandled[0]) classicJobs.size * classicPrice else 0F) + (if (!adsPriceHandled[1]) standOutJobs.size * standOutPrice else 0F) + (if (!adsPriceHandled[2]) premiumJobs.size * premiumPrice else 0F)
                adsPriceHandled[0] = true
                adsPriceHandled[1] = true
                adsPriceHandled[2] = true
            }
        }
        if (!adsPriceHandled[0] && !adsPriceHandled[1] && !adsPriceHandled[2]) {
            price = classicJobs.size * classicPrice + standOutJobs.size * standOutPrice + premiumJobs.size * premiumPrice
        }

        return price
    }

    fun setCustomer(mCustomer: Customer) {
        customer = mCustomer
    }

    fun getCustomer(): Customer {
        return customer
    }

    fun mockPriceRules(mPriceRules: PriceRules){
        priceRules.value = mPriceRules
    }

    class VMFactory(private val lifecycle: Lifecycle, private val lifecycleOwner: LifecycleOwner) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = HomeViewModel(lifecycle, lifecycleOwner) as T
    }
}