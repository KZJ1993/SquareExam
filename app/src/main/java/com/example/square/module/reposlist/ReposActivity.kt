package com.example.square.module.reposlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import com.example.square.databinding.ActivityReposListBinding
import com.example.square.module.details.DetailsActivity
import com.example.square.utils.Constant
import com.example.square.utils.showAlertDialog
import com.example.square.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@AndroidEntryPoint
class ReposActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityReposListBinding::inflate)
    private val viewModel by viewModels<ReposViewModel>()

    @Inject
    lateinit var reposAdapter: ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        intiRecyclerView()

        lifecycleScope.launch {
            viewModel.errorFlow.collect {
                if (it.isNullOrEmpty().not()) {
                    showAlertDialog(it)
                }
            }
        }
    }

    private fun intiRecyclerView() {
        binding.apply {
            reposList.apply {
                setHasFixedSize(true)
                this.adapter = reposAdapter
            }
        }

        reposAdapter.setClickListener {
            val bundle = bundleOf(Constant.BUNDLE_REPO_ID to it.id)
            startActivity(Intent(this, DetailsActivity::class.java).apply {
                putExtras(bundle)
            })
        }

        lifecycleScope.launch {
            viewModel.allRepos.collectLatest { source ->
                binding.apply {
                    reposList.isVisible = true
                    progressBar.isVisible = false
                }
                reposAdapter.submitData(source)
            }
        }
    }
}