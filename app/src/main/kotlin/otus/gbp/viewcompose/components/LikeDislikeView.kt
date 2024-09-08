package otus.gbp.viewcompose.components

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.view.Gravity.CENTER
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.withStyledAttributes
import kotlinx.parcelize.Parcelize
import otus.gbp.viewcompose.R
import otus.gbp.viewcompose.databinding.LikeDislikeBinding

class LikeDislikeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.like_dislike_panelStyle
) : LinearLayoutCompat(context, attrs, defStyleAttr) {
    private val binding: LikeDislikeBinding

    var likes = 0
        set(value) {
            field = value
            binding.numLikes.text = value.toString()
        }

    init {
        isSaveEnabled = true;
        binding = LikeDislikeBinding.inflate(LayoutInflater.from(context), this)
        initPanel(attrs, defStyleAttr)
        initLikes(attrs, defStyleAttr)
    }

    @Parcelize
    private data class LikeDislikeState(val superState: Parcelable?, val likes: Int): Parcelable

    override fun onSaveInstanceState(): Parcelable {
        return LikeDislikeState(super.onSaveInstanceState(), likes)
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is LikeDislikeState) {
            super.onRestoreInstanceState(state.superState)
            likes = state.likes
            binding.numLikes.text = likes.toString()
        }
    }

    private fun initPanel(attrs: AttributeSet?, defStyleAttr: Int) {
        // for android limitation, before finding attrs, you need to sort all attrs you want to find in ascending order, otherwise some attrs cannot be found
        // https://developer.android.com/reference/android/content/res/Resources.Theme.html#obtainStyledAttributes(android.util.AttributeSet, int[], int, int)
        val toRetrieve = intArrayOf(
            android.R.attr.layout_width,
            android.R.attr.layout_height,
            android.R.attr.orientation,
            android.R.attr.gravity,
            android.R.attr.paddingTop,
            android.R.attr.paddingBottom,
            android.R.attr.background
        ).apply { sort() }

        context.withStyledAttributes(attrs, toRetrieve, defStyleAttr, R.style.like_dislike_panel) {
            layoutParams = LayoutParams(
                getInt(toRetrieve.indexOf(android.R.attr.layout_width), LayoutParams.WRAP_CONTENT),
                getInt(toRetrieve.indexOf(android.R.attr.layout_height), LayoutParams.WRAP_CONTENT),
            )
            orientation = getInt(toRetrieve.indexOf(android.R.attr.orientation), HORIZONTAL)
            gravity = getInt(toRetrieve.indexOf(android.R.attr.gravity), CENTER)
            setPadding(
                0,
                getDimensionPixelSize(toRetrieve.indexOf(android.R.attr.paddingTop), 0),
                0,
                getDimensionPixelSize(toRetrieve.indexOf(android.R.attr.paddingBottom), 0),
            )
            background = getDrawable(toRetrieve.indexOf(android.R.attr.background))
        }
    }

    private fun initLikes(attrs: AttributeSet?, defStyleAttr: Int) = with(binding) {
        context.withStyledAttributes(attrs, R.styleable.LikeDislike, defStyleAttr, R.style.like_dislike_panel) {
            likes = getInt(R.styleable.LikeDislike_like_dislike_count, 0)
            numLikes.text = likes.toString()
            likeButton.setOnClickListener {
                numLikes.text = (++likes).toString()
            }
            dislikeButton.setOnClickListener {
                numLikes.text = (--likes).coerceAtLeast(0).toString()
            }
        }
    }
}