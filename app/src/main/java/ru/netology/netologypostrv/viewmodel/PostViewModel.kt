package ru.netology.netologypostrv.viewmodel

import ru.netology.netologypostrv.repository.PostRepositoryInMemory
import androidx.lifecycle.ViewModel
import ru.netology.netologypostrv.repository.PostRepository

class PostViewModel: ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemory()
    val data = repository.getAll()
    fun like(id: Long) = repository.like(id)
    fun share(id: Long) = repository.share(id)
}