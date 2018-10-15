package jobsad.checkout

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.Observer
import jobsad.checkout.model.*
import jobsad.checkout.viewmodel.HomeViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mockito.mock

class PriceRuleRequirementUnitTest {
    private val lifecycleOwner = mock(LifecycleOwner::class.java)
    private val lifecycle = LifecycleRegistry(lifecycleOwner)
    private val homeViewModel = HomeViewModel(lifecycle, lifecycleOwner)

    //Mock Data
    private lateinit var case_1_mock_data: MutableList<Job>
    private lateinit var case_2_mock_data: MutableList<Job>
    private lateinit var case_3_mock_data: MutableList<Job>
    private lateinit var case_4_mock_data: MutableList<Job>

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init_home_view_model_data() {
        val jobTypes: MutableList<JobType> = mutableListOf(JobType("classic", "269.99"), JobType("standout", "322.99"), JobType("premium", "394.99"))
        val priceRules: MutableList<PriceRule> = mutableListOf(
                PriceRule("UNILEVER", OneDealForFree("classic", 2), mutableListOf()),
                PriceRule("APPLE", OneDealForFree("", 0), mutableListOf(Discount("standout", 1, "299.99"))),
                PriceRule("NIKE", OneDealForFree("", 0), mutableListOf(Discount("premium", 4, "379.99"))),
                PriceRule("FORD", OneDealForFree("classic", 2), mutableListOf(Discount("standout", 1, "309.99"), Discount("premium", 1, "389.99")))
        )
        homeViewModel.mockPriceRules(PriceRules(jobTypes, priceRules))

        case_1_mock_data = mutableListOf(
                Job(19234753, "test1", "test1", "", "classic"),
                Job(19234754, "test2", "test2", "", "standout"),
                Job(19234755, "test3", "test3", "", "premium"))

        case_2_mock_data = mutableListOf(
                Job(19234753, "test1", "test1", "", "classic"),
                Job(19234754, "test2", "test2", "", "classic"),
                Job(19234755, "test3", "test3", "", "classic"),
                Job(19234756, "test4", "test4", "", "premium"))

        case_3_mock_data = mutableListOf(
                Job(19234753, "test1", "test1", "", "standout"),
                Job(19234754, "test2", "test2", "", "standout"),
                Job(19234755, "test3", "test3", "", "standout"),
                Job(19234756, "test4", "test4", "", "premium"))

        case_4_mock_data = mutableListOf(
                Job(19234753, "test1", "test1", "", "premium"),
                Job(19234754, "test2", "test2", "", "premium"),
                Job(19234755, "test3", "test3", "", "premium"),
                Job(19234755, "test4", "test4", "", "premium"))
    }

    @Test
    fun test_home_view_model_customer() {
        val customer = Customer(Customer.Type.DEFAULT)
        homeViewModel.setCustomer(customer)

        assertEquals(customer.type.name, Customer.Type.DEFAULT.name)
        assertNotEquals(customer.type.id, Customer.Type.DEFAULT.name)
        assertEquals(homeViewModel.getCustomer(), customer)
    }

    // Check empty case
    @Test
    fun testEmptyCase() {
        assertEquals(homeViewModel.filterPriceByRequirement(mutableListOf()), 0F)
    }

    // Check customer with Default
    @Test
    fun testCase1WithDefault(){
        val customer = Customer(Customer.Type.DEFAULT)
        homeViewModel.setCustomer(customer)
        assertEquals(homeViewModel.filterPriceByRequirement(case_1_mock_data), 987.97F)
    }

    // Check customer with Unilever
    @Test
    fun testCase2WithUnilever(){
        val customer = Customer(Customer.Type.UNILEVER)
        homeViewModel.setCustomer(customer)
        assertEquals(homeViewModel.filterPriceByRequirement(case_2_mock_data), 934.97F)
    }

    // Check customer with Apple
    @Test
    fun testCase3WithUnilever(){
        val customer = Customer(Customer.Type.APPLE)
        homeViewModel.setCustomer(customer)
        assertEquals(homeViewModel.filterPriceByRequirement(case_3_mock_data), 1294.96F)
    }

    // Check customer with Nike
    @Test
    fun testCase4WithUnilever(){
        val customer = Customer(Customer.Type.NIKE)
        homeViewModel.setCustomer(customer)
        assertEquals(homeViewModel.filterPriceByRequirement(case_4_mock_data), 1519.96F)
    }

}
