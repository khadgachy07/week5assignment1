package com.khadga.instagramclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.khadga.instagramclone.R
import com.khadga.instagramclone.models.User
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class StoryAdapter(val userList : List<User>, val context : Context): RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    inner class StoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgStory : CircleImageView
        var tvStoryName : TextView

        init {
            imgStory =  view.findViewById(R.id.imgStory)
            tvStoryName = view.findViewById(R.id.tvStoryName)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.story, parent, false)
        return StoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.tvStoryName.text = "${currentUser.fName} ${currentUser.lName}"

        Glide.with(context).load(currentUser.profilePicture).into(holder.imgStory)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}