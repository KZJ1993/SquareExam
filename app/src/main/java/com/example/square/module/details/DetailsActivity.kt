package com.example.square.module.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import com.example.square.R
import com.example.square.databinding.ActivityDetailsBinding
import com.example.square.utils.Constant
import com.example.square.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalPagingApi
@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityDetailsBinding::inflate)
    private val viewModel by viewModels<DetailsViewModel>()

    private var repoId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        intent.extras?.let {
            if (it.containsKey(Constant.BUNDLE_REPO_ID)) {
                repoId = it.getInt(Constant.BUNDLE_REPO_ID)
            }
        }

        if (repoId == 0) finish() else setData()

        lifecycleScope.launch {
            viewModel.errorFlow.collect {
                if (it.isNullOrEmpty().not()) {

                }
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setData() {
        binding.vm = viewModel

        lifecycleScope.launch {
            viewModel.getRepoItem(repoId).collect {
                binding.data = it
                binding.btnBookmark.text =
                    if (it.bookmark != null) getText(R.string.remove_from_bookmark)
                    else getText(R.string.add_to_bookmark)
                binding.imgBookmark.visibility = if (it.bookmark != null) View.VISIBLE
                else View.GONE
            }
        }
    }
}