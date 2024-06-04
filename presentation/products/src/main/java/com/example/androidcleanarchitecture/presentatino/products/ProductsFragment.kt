package com.example.androidcleanarchitecture.presentatino.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.androidcleanarchitecture.core.common.ViewStatus
import com.example.androidcleanarchitecture.presentatino.products.adapter.ProductAdapter
import com.example.androidcleanarchitecture.presentation.products.databinding.FragmentProductsBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductsFragment : Fragment() {

    private val viewModel: ProductsViewModel by viewModel()


    private val productAdapter: ProductAdapter by lazy { ProductAdapter(onClickProduct) }
    private var onClickProduct: ((description: String) -> Unit)? = { description ->
        Toast.makeText(binding.root.context, description, Toast.LENGTH_SHORT).show()
    }


    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvProducts.adapter = productAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.productsStateFlow.collect { productsState ->
                        when (productsState.viewStatus) {
                            ViewStatus.INITIAL -> {
                                loadingVisibility(true)
                            }
                            ViewStatus.LOADING -> {
                                loadingVisibility(true)
                                showRetryMessage(productsState.message, false)
                            }
                            ViewStatus.SUCCESS -> {
                                productAdapter.submitList(productsState.products) {
                                    binding.rvProducts.scrollToPosition(0)
                                    loadingVisibility(false)
                                    showRetryMessage(productsState.message, false)
                                }
                            }

                            ViewStatus.FAILED -> {
                                loadingVisibility(false)
                                showRetryMessage(productsState.message, true)
                            }

                        }

                    }
                }
            }
        }

        binding.svProduct.getQueryTextChangeStateFlow()

        binding.layoutMessageRetry.retryButton.setOnClickListener {
            viewModel.getProducts()
        }

    }


    private fun SearchView.getQueryTextChangeStateFlow() {
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.queryStateFlow.value = newText
                return true
            }
        })
    }

    private fun loadingVisibility(isVisible: Boolean) {
        binding.pbProducts.isVisible = isVisible
    }

    private fun showRetryMessage(errorMessage: String, isVisible: Boolean) {
        when (isVisible) {
            true -> {
                binding.layoutMessageRetry.errorMessage.text = errorMessage
                binding.layoutMessageRetry.root.visibility = View.VISIBLE
            }

            false -> {
                binding.layoutMessageRetry.root.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.svProduct.setOnQueryTextListener(null)
        binding.rvProducts.adapter = null
        _binding = null
    }
}