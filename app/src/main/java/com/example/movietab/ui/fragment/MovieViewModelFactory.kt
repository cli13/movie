package com.example.movietab.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movietab.data.MainRepository
import javax.inject.Inject

class MovieViewModelFactory constructor(private val mainRepository: MainRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(mainRepository) as T
    }
}