package com.vb.cats.favouriteCats.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.vb.cats.R
import com.vb.cats.favouriteCats.presentation.FavouriteCatsViewModel
import com.vb.cats.cats.widget.CatAdapter
import com.vb.cats.databinding.FavouriteCatsFragmentBinding
import com.vb.cats.saveImageToStorage
import com.vb.cats.shouldRequestExternalStoragePermissions
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteCatsFragment : Fragment() {
    private val binding by lazy { FavouriteCatsFragmentBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<FavouriteCatsViewModel>()
    private val adapter = CatAdapter()
    val permissionLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            var isPermissionsGranted = true
            it.forEach { (_, result) ->
                if (!result) {
                    isPermissionsGranted = false
                }
            }
            if (isPermissionsGranted) {
                Snackbar.make(binding.root, R.string.great_you_can_save_your_image, Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                Snackbar.make(binding.root, R.string.permission_declined, Snackbar.LENGTH_SHORT)
                    .show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favouriteCatsRecycler.adapter = adapter
        binding.favouriteCatsRecycler.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        adapter.onSaveToGalleryClickListener = { cat ->
            val permissions = shouldRequestExternalStoragePermissions(requireContext())
            if (permissions.first) {
                permissionLauncher.launch(permissions.second)
            } else {
                saveImageToStorage(requireContext(), cat.url) {
                    if (it) {
                        Snackbar.make(binding.root, R.string.cat_was_saved, Snackbar.LENGTH_SHORT)
                            .show()
                    } else {
                        Snackbar.make(binding.root, R.string.default_error_message, Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
        viewModel.getFavouriteCats()
            .observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
    }
}
