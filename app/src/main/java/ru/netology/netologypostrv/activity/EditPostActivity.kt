package ru.netology.netologypostrv.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.netologypostrv.R
import ru.netology.netologypostrv.databinding.ActivityEditPostBinding
import ru.netology.netologypostrv.databinding.ActivityNewPostBinding

class EditPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEditPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        val editTextPost = bundle?.getString("postToEdit")
        binding.editTextpost.setText(editTextPost.toString())

        binding.editTextpost.requestFocus()
        binding.btnOk.setOnClickListener {
            val intent = Intent()
            if (binding.editTextpost.text.isNullOrBlank()) {
                setResult(Activity.RESULT_CANCELED, intent)
            } else {
                val content = binding.editTextpost.text.toString()
                intent.putExtra(Intent.EXTRA_TEXT, content)
                setResult(Activity.RESULT_OK, intent)

            }
            finish()
        }
    }
}