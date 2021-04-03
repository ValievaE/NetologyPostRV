package ru.netology.netologypostrv

import androidx.annotation.Nullable

data class Post(
        val id: Long,
        val author: String,
        val content: String,
        val published: String,
        val liked: Boolean = false,
        val shared: Boolean,
        val likesCount: Int = 0,
        val sharesCount: Int = 0,
        @Nullable val video: String? = null
)

