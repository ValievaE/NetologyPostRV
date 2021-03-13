package ru.netology.netologypostrv.viewmodel

import androidx.lifecycle.MutableLiveData
import ru.netology.netologypostrv.repository.PostRepositoryInMemory
import androidx.lifecycle.ViewModel
import ru.netology.netologypostrv.Post
import ru.netology.netologypostrv.repository.PostRepository

private val empty = Post(
    id = 0,
    content = "",
    author = "",
    liked = false,
    published = "",
    shared = false,
    sharesCount = 0,
    likesCount = 0
)

class PostViewModel: ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemory()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)
    fun like(id: Long) = repository.like(id)
    fun share(id: Long) = repository.share(id)
    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun changeContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }

    fun remove(id: Long) = repository.remove(id)
}