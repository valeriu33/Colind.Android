package com.valeriu.colindandroid

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.valeriu.colindandroid.colindDetail.ColindDetailViewModel
import com.valeriu.colindandroid.colindsList.ColindsListViewModel
import com.valeriu.colindandroid.data.source.ColindRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val tasksRepository: ColindRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(ColindsListViewModel::class.java) ->
                ColindsListViewModel(tasksRepository)
            isAssignableFrom(ColindDetailViewModel::class.java) ->
                ColindDetailViewModel(tasksRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}
