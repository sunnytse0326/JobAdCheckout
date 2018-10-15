package jobsad.checkout.viewmodel

import android.arch.lifecycle.*
import android.view.View
import jobsad.checkout.model.Job
import java.util.*

class EditJobInfoViewModel(val lifecycle: Lifecycle, private val lifecycleOwner: LifecycleOwner) : ViewModel() {
    val jobInfo: MutableLiveData<Job> = MutableLiveData()
    private var imageURL:String = ""

    fun updateJob(id: Int, title: String, description: String, type: String){
        jobInfo.value = Job(id, title, description, imageURL, type)
    }

    fun setImageURL(mURL:String){
        imageURL = mURL
    }

    class VMFactory(private val lifecycle: Lifecycle, private val lifecycleOwner: LifecycleOwner) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = EditJobInfoViewModel(lifecycle, lifecycleOwner) as T
    }
}