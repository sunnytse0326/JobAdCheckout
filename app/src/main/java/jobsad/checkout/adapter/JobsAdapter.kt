package jobsad.checkout.adapter

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import jobsad.checkout.model.Job
import jobsad.checkout.uicomponent.EmptyViewHolder
import jobsad.checkout.uicomponent.EmptyViewUI
import jobsad.checkout.uicomponent.JobRecyclerViewHolder
import jobsad.checkout.uicomponent.JobRecyclerViewUI
import kotlinx.android.synthetic.main.abc_dialog_title_material.view.*
import org.jetbrains.anko.AnkoContext



class JobsAdapter constructor(mContext: Context, mListener: OnClickListener, mJobs: MutableList<Job>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var headerView: View? = null
    private var footerView: View? = null
    var listener: OnClickListener = mListener
    val context = mContext
    val jobs: MutableList<Job> = mJobs

    private enum class Type {
        Header, Content, Footer
    }

    fun setHeaderView(view: View) {
        headerView = view;
    }

    fun addJob(mPost: Job){
        jobs.add(0, mPost)
    }

    fun updateJob(mPost: Job){
        jobs.forEachIndexed { index, job ->
            if(job.id == mPost.id){
                jobs[index] = mPost
            }
        }
    }

    fun deleteJob(id: Int){
        jobs.removeAll { it.id == id }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0 && headerView != null) {
            Type.Header.ordinal
        } else if (position == itemCount - 1 && footerView != null) {
            Type.Footer.ordinal
        } else {
            Type.Content.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == Type.Header.ordinal) {
            val ui = EmptyViewUI()
            EmptyViewHolder(ui, ui.createView(AnkoContext.create(parent.context, parent)), headerView)
        } else if (viewType == Type.Footer.ordinal) {
            val ui = EmptyViewUI()
            EmptyViewHolder(ui, ui.createView(AnkoContext.create(parent.context, parent)), footerView)
        } else {
            val ui = JobRecyclerViewUI()
            JobRecyclerViewHolder(ui, ui.createView(AnkoContext.create(parent.context, parent)))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is JobRecyclerViewHolder) {
            holder.adType.text = "Type: ${jobs[position].type}"
            holder.title.text = jobs[position].title
            holder.description.text = jobs[position].description
            if(jobs[position].imageURL.isNotEmpty()){
                holder.imageView.setImageBitmap(MediaStore.Images.Media.getBitmap(context.contentResolver, Uri.parse(jobs[position].imageURL)))
            }
            holder.cardView.setOnClickListener { listener?.onBackgroundClicked(position) }

        }
    }

    override fun getItemCount(): Int {
        return jobs.size + (if(headerView != null) 1 else 0) + (if(footerView != null) 1 else 0)
    }

    interface OnClickListener {
        fun onBackgroundClicked(position: Int)
    }

}