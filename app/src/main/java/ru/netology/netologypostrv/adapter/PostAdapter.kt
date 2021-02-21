package ru.netology.netologypostrv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.netologypostrv.Converter
import ru.netology.netologypostrv.Post
import ru.netology.netologypostrv.R
import ru.netology.netologypostrv.databinding.CardPostBinding


class PostAdapter(
    val onShareListener: (Post) -> Unit,
    val onLikeListener: (Post) -> Unit
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener, onShareListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostViewHolder(
    private val binding: CardPostBinding,
    val onLikeListener: (Post) -> Unit,
    val onShareListener: (Post) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            textViewTitle.text = post.author
            textViewDate.text = post.published
            text.text = post.content
            textViewLikes.text = Converter.convert(post.likesCount)
            textViewShare.text = Converter.convert(post.sharesCount)
            likes.setImageResource(
                if (post.liked) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
            )

            likes.setOnClickListener {
                onLikeListener(post)
            }
            ivShare.setOnClickListener {
                onShareListener(post)
            }
        }
    }
}


class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}