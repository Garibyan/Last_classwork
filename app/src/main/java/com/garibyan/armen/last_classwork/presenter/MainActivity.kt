package com.garibyan.armen.last_classwork.presenter

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.garibyan.armen.last_classwork.common.Resource
import com.garibyan.armen.last_classwork.databinding.ActivityMainBinding
import com.garibyan.armen.last_classwork.domain.model.Chat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val chatAdapter by lazy { ChatAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observer()

    }

    private fun observer() {
        lifecycleScope.launch {
            viewModel.viewState.collect { viewState ->
                when (viewState) {
                    is Resource.Loader -> loadingState()
                    is Resource.Error -> errorState(viewState.error)
                    is Resource.Success -> successfulState(viewState.result)
                }
            }
        }
    }

    private fun successfulState(result: List<Chat>?) = with(binding) {
        initRecyclerView()
        chatAdapter.submitList(result)
        rvChats.visibility = View.VISIBLE
        progressbar.visibility = View.GONE
        tvErrorMessage.visibility = View.GONE
    }

    private fun errorState(error: String?) = with(binding) {
        rvChats.visibility = View.GONE
        progressbar.visibility = View.GONE
        tvErrorMessage.visibility = View.VISIBLE
        tvErrorMessage.text = error
    }

    private fun loadingState() = with(binding) {
        rvChats.visibility = View.GONE
        progressbar.visibility = View.VISIBLE
        tvErrorMessage.visibility = View.GONE
    }

    private fun initRecyclerView() = with(binding.rvChats) {
        layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = chatAdapter

    }
}