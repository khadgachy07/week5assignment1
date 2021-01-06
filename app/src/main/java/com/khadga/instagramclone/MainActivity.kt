package com.khadga.instagramclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khadga.instagramclone.adapters.PostAdapter
import com.khadga.instagramclone.adapters.StoryAdapter
import com.khadga.instagramclone.models.Post
import com.khadga.instagramclone.models.User
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var currentUserID: String
    private lateinit var storyAdapter : StoryAdapter
    private lateinit var postAdapter: PostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewStory: RecyclerView = findViewById(R.id.recyclerViewStory)
        val recyclerViewPost: RecyclerView = findViewById(R.id.recyclerViewPost)
        val fabAddPhoto : FloatingActionButton = findViewById(R.id.fabAddPhoto)
        loadDummyUser()
        loadDummyPosts()

        currentUserID = if (intent.hasExtra("id")) {
            intent.getStringExtra("id").toString()
        } else {
            ""
        }

        storyAdapter = StoryAdapter(DataStore.users, this)
        recyclerViewStory.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewStory.adapter = storyAdapter

        postAdapter = PostAdapter(DataStore.posts, this)
        recyclerViewPost.layoutManager = LinearLayoutManager(this)
        recyclerViewPost.adapter = postAdapter

        fabAddPhoto.setOnClickListener {
            showAlertDialog()
        }
    }

    private fun showAlertDialog() {
        val view = layoutInflater.inflate(R.layout.add_photo_dialog, null)
        val dialog = AlertDialog.Builder(this).setView(view)
                .setCancelable(true)
                .create()

        val etPhotoLink: EditText = view.findViewById(R.id.etPhotoLink)
        val etCaption: EditText = view.findViewById(R.id.etCaption)
        val btnAddPhoto: Button = view.findViewById(R.id.btnAddPhoto)

        btnAddPhoto.setOnClickListener {
            // validation
            if (TextUtils.isEmpty(etPhotoLink.text)) {
                etPhotoLink.error = "Please add a photo Link"
                etPhotoLink.requestFocus()
                return@setOnClickListener
            } else if (TextUtils.isEmpty(etCaption.text)) {
                etCaption.error = "Please add a photo Link"
                etCaption.requestFocus()
                return@setOnClickListener
            }

            // add post
            addPost(etPhotoLink.text.toString(), etCaption.text.toString())
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun addPost(link: String, caption: String) {
        DataStore.posts.add(0,
                Post(
                        link,
                        caption,
                        currentUserID
                )
        )
        postAdapter.notifyItemInserted(0)
        Toast.makeText(this, "Post Added", Toast.LENGTH_SHORT).show()
    }

    private fun loadDummyPosts() {
        DataStore.posts.add(
                Post(
                        "https://townsquare.media/site/366/files/2019/03/GettyImages-88574703.jpg",
                        "Kurt Cobain. Hail to the king.",
                        "000001"
                )
        )
        DataStore.posts.add(
                Post(
                        "https://www.grammy.com/sites/com/files/styles/twitter_image/public/muzooka/Nirvana/Nirvana_1_1_1603883064Muzooka.jpg?itok=sm_4NB9J",
                        "Nirvana",
                        "000002"
                )
        )
        DataStore.posts.add(
                Post(
                        "https://www.metalsucks.net/wp-content/uploads/2018/04/guns-n-roses-afd-band-photo-1000x500.jpg",
                        "Guns N' Roses",
                        "000003"
                )
        )
        DataStore.posts.add(
                Post(
                        "https://guitar.com/wp-content/uploads/2020/07/Slash-Hero-Credit-Robert-Knight-Archive-Redferns@2560x1707.jpg",
                        "Slash ",
                        "000004"
                )
        )
        DataStore.posts.add(
                Post(
                        "https://www.biography.com/.image/c_fill%2Ccs_srgb%2Cfl_progressive%2Ch_400%2Cq_auto:good%2Cw_620/MTY2MTcyNTE2MzQyMDQ4Mzk4/the-beatles-rehearse-for-that-nights-royal-variety-performance-at-the-prince-of-wales-theatre-4th-november-1963-the-queen-mother-will-attend-photo-by-central-press_hulton-archive_getty-images.jpg",
                        "The Beatles",
                        "000005"
                )
        )
        DataStore.posts.add(
            Post(
                "https://static.wikia.nocookie.net/marvel_dc/images/a/a5/Superman_Vol_5_1_Textless.jpg/revision/latest?cb=20180711061148",
                "Superman",
                "000001"
            )
        )
        DataStore.posts.add(
            Post(
                "https://static3.cbrimages.com/wordpress/wp-content/uploads/2021/01/Future-State-Next-Batman-feature-1.jpg",
                "Batman",
                "000002"
            )
        )
        DataStore.posts.add(
            Post(
                "https://static3.cbrimages.com/wordpress/wp-content/uploads/2020/10/nightwing-future-state-header.jpg",
                "Nightwing",
                "000003"
            )
        )
        DataStore.posts.add(
            Post(
                "https://www.denofgeek.com/wp-content/uploads/2019/07/thor-4-marvel-chris-hemsworth.jpg?resize=768%2C432",
                "Thor",
                "000004"
            )
        )
        DataStore.posts.add(
            Post(
                "https://gamingtrend.com/wp-content/uploads/2020/07/youtube-thumb.jpg",
                "Ironman",
                "000005"
            )
        )

    }

    private fun loadDummyUser() {
        DataStore.users.add(
                User(
                        "000001",
                        "Khadga",
                        "Chy",
                        "khadgachy07",
                        "chyshy12345",
                        "25C",
                        "https://scontent.fktm7-1.fna.fbcdn.net/v/t1.0-9/117584056_932000127277765_6911789296852874547_n.jpg?_nc_cat=104&ccb=2&_nc_sid=09cbfe&_nc_ohc=OdTrf4zp21oAX_XyxTW&_nc_ht=scontent.fktm7-1.fna&oh=78f2bb02e32b068842ff007fa63c3182&oe=601CA5F7"
                )
        )
        DataStore.users.add(
                User(
                        "000002",
                        "Aditya",
                        "Shah",
                        "Aditya",
                        "Aditya",
                        "25B",
                        "https://scontent.fktm7-1.fna.fbcdn.net/v/t1.0-9/72644580_3025764257649768_7669863287646846976_o.jpg?_nc_cat=100&ccb=2&_nc_sid=09cbfe&_nc_ohc=bbQaT5xT6xcAX9h-dQ4&_nc_ht=scontent.fktm7-1.fna&oh=7af69a0fe7d92290e1dfda8bbe69f95f&oe=60198734"
                )
        )
        DataStore.users.add(
                User(
                        "000003",
                        "Bibek",
                        "Pradhan",
                        "Bibek",
                        "Bibek",
                        "24A",
                        "https://scontent.fktm7-1.fna.fbcdn.net/v/t1.0-9/123136240_2880493618846922_7749377216651103011_o.jpg?_nc_cat=103&ccb=2&_nc_sid=09cbfe&_nc_ohc=Km_vfoTufqAAX8N7c_J&_nc_ht=scontent.fktm7-1.fna&oh=2b39d7c1231f83803cc84f92df44cdf7&oe=601BE24E"
                )
        )
        DataStore.users.add(
                User(
                        "000004",
                        "Amrit",
                        "Shahi",
                        "Amrit",
                        "Amrit",
                        "25A",
                        "https://scontent.fktm7-1.fna.fbcdn.net/v/t1.0-9/121310389_114663070411575_1673392049968155017_n.jpg?_nc_cat=110&ccb=2&_nc_sid=174925&_nc_ohc=ky6ZUG4VJeEAX9MecJF&_nc_ht=scontent.fktm7-1.fna&oh=b0a199d1b5af9e2908540151dc696df3&oe=601D0AA6"
                )
        )
        DataStore.users.add(
                User(
                        "000005",
                        "Avatar",
                        "Mahaju",
                        "Avatar",
                        "Avatar",
                        "24B",
                        "https://scontent.fktm7-1.fna.fbcdn.net/v/t1.0-9/131552473_2759445957659108_5017312205992893515_o.jpg?_nc_cat=109&ccb=2&_nc_sid=09cbfe&_nc_ohc=PrFxjBnhsb0AX_kqQWv&_nc_ht=scontent.fktm7-1.fna&oh=a18aae25669ced161f1dca20c4628fcd&oe=601C72C9"
                )
        )
    }
}