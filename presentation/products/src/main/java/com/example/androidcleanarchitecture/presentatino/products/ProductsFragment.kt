package com.example.androidcleanarchitecture.presentatino.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
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


    private val productAdapter: ProductAdapter by lazy { ProductAdapter() }

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
                            }

                            ViewStatus.FAILED -> {
                                loadingVisibility(false)
                            }

                            ViewStatus.SUCCESS -> {
                                productAdapter.submitList(productsState.products) {
                                    binding.rvProducts.scrollToPosition(0)
                                    loadingVisibility(false)

                                }

                            }
                        }

                    }
                }
            }
        }

        binding.svProduct.getQueryTextChangeStateFlow()

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

    override fun onDestroyView() {
        super.onDestroyView()
        binding.svProduct.setOnQueryTextListener(null)
        _binding = null
    }
}