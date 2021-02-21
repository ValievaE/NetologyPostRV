package ru.netology.netologypostrv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.netologypostrv.adapter.PostAdapter
import ru.netology.netologypostrv.databinding.ActivityMainBinding
import ru.netology.netologypostrv.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(
            onLikeListener = {
                viewModel.like(it.id)
            },
            onShareListener = {
                viewModel.share(it.id)
            }
        )

        binding.list.adapter = adapter

        viewModel.data.observe(this, { posts ->
            adapter.submitList(posts)
        })
    }
}