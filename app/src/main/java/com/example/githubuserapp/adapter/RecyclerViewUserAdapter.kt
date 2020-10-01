package com.example.githubuserapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuserapp.R
import com.example.githubuserapp.models.User
import de.hdodenhof.circleimageview.CircleImageView

class RecyclerViewUserAdapter(
    private val context: Context,
    private val listItems: ArrayList<User>
) : RecyclerView.Adapter<RecyclerViewUserAdapter.ViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (
            username,
            name,
            avatar,
            company,
            location,
            repository,
            followers,
            following) = listItems[position]

        holder.username.text = username
        holder.company.text = company
        holder.followers.text = followers
        Glide.with(context)
            .load(avatar)
            .apply(RequestOptions())
            .into(holder.avatar)

        val anim = AnimationUtils.loadAnimation(context, R.anim.recyclerview_anim)
        holder.itemView.animation = anim

        holder.itemView.setOnClickListener {
            val user =
                User(username, name, avatar, company, location, repository, followers, following)
            onItemClickCallback?.onItemClicked(user)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username: TextView = itemView.findViewById(R.id.tv_username)
        val company: TextView = itemView.findViewById(R.id.tv_company)
        val followers: TextView = itemView.findViewById(R.id.tv_followers)
        val avatar: CircleImageView = itemView.findViewById(R.id.img_avatar)
    }
}