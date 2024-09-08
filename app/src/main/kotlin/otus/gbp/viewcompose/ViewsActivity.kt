package otus.gbp.viewcompose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import otus.gbp.viewcompose.components.LikeDislikeView

class ViewsActivity : AppCompatActivity(R.layout.activity_views) {
    companion object {
        fun buildIntent(context: Context) = Intent(context, ViewsActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val likeDislike: LikeDislikeView = findViewById(R.id.like_dislike)
        val dislike: Button = findViewById(R.id.dislike)
        val like: Button = findViewById(R.id.like)

        dislike.setOnClickListener {
            likeDislike.likes -= 1
        }
        like.setOnClickListener {
            likeDislike.likes += 1
        }
    }
}