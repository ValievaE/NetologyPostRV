package ru.netology.netologypostrv.repository

import androidx.lifecycle.LiveData
import ru.netology.netologypostrv.Post

    interface PostRepository {
        fun getAll(): LiveData<List<Post>>
        fun like(id: Long)
        fun share(id: Long)
        fun save(post: Post)
        fun remove(id: Long)
    }
