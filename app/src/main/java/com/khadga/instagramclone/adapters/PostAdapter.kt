package com.khadga.instagramclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.khadga.instagramclone.DataStore
import com.khadga.instagramclone.R
import com.khadga.instagramclone.models.Post
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class PostAdapter(val postList: List<Post>, val context: Context) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgCardProfile: CircleImageView
        var tvProfileName: TextView
        var ivPhoto: ImageView
        var tvProfileNameCaption: TextView
        var tvCaption: TextView

        init {
            imgCardProfile = view.findViewById(R.id.imgCardProfile)
            tvProfileName = view.findViewById(R.id.tvProfileName)
            ivPhoto = view.findViewById(R.id.ivPhoto)
            tvProfileNameCaption = view.findViewById(R.id.tvProfileNameCaption)
            tvCaption = view.findViewById(R.id.tvCaption)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.main_photo_card, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost = postList[position]
        for (user in DataStore.users) {
            if (user.coventryID == currentPost.studentID) {
                val name = "${user.fName} ${user.lName}"
                holder.tvProfileName.text = name
                holder.tvProfileNameCaption.text = name

                Glide.with(context).load(user.profilePicture).into(holder.imgCardProfile)
            }
        }
        holder.tvCaption.text = currentPost.caption
        Glide.with(context).load(currentPost.photoLink).into(holder.ivPhoto)



    }

    override fun getItemCount(): Int {
        return postList.size
    }
}