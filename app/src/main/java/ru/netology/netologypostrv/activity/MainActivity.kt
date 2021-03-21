package ru.netology.netologypostrv.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.card_post.*
import kotlinx.android.synthetic.main.card_post.view.*
import ru.netology.netologypostrv.Post
import ru.netology.netologypostrv.R
import ru.netology.netologypostrv.adapter.OnInteractionListener
import ru.netology.netologypostrv.adapter.PostAdapter
import ru.netology.netologypostrv.databinding.ActivityMainBinding
import ru.netology.netologypostrv.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    private val newPostRequestCode = 1
    private val editPostRequestCode = 2
    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PostAdapter(object : OnInteractionListener {
            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }

            override fun onLike(post: Post) {
                viewModel.like(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.remove(post.id)
            }
            override fun onClickPost (post: Post, position: Int){
                viewModel.onClickPost(post, position)

                val intent = Intent(this@MainActivity, EditPostActivity::class.java)
                intent.putExtra("postToEdit", post.content)
                startActivityForResult(intent, editPostRequestCode)

            }

            override fun onShare(post: Post) {
                viewModel.share(post.id)

                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }

                val shareIntent =
                    Intent.createChooser(intent, getString(R.string.chooser_share_post))
                startActivity(shareIntent)
            }
        })
        binding.list.adapter = adapter
        viewModel.data.observe(this, { posts ->
            adapter.submitList(posts)
        })

        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewPostActivity::class.java)
            startActivityForResult(intent, newPostRequestCode)
        }

//        viewModel.edited.observe(this, { post ->
//            if (post.id == 0L) {
//                return@observe
//            }
//            with(binding.content) {
//                requestFocus()
//                setText(post.content)
//            }
//        })
//
//        binding.save.setOnClickListener {
//            with(binding.content) {
//                if (text.isNullOrBlank()) {
//                    Toast.makeText(
//                            this@MainActivity,
//                            context.getString(R.string.error_empty_content),
//                            Toast.LENGTH_SHORT
//                    ).show()
//                    return@setOnClickListener
//                }
//
//                viewModel.changeContent(text.toString())
//                viewModel.save()
//
//                setText("")
//                clearFocus()
//                AndroidUtils.hideKeyboard(this)
//            }
//        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            newPostRequestCode, editPostRequestCode -> {
                if (resultCode != Activity.RESULT_OK) {
                    return
                }

                data?.getStringExtra(Intent.EXTRA_TEXT)?.let {
                    viewModel.changeContent(it)
                    viewModel.save()
                }
            }

        }
    }

}