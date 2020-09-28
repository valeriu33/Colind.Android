package com.valeriu.colindandroid.colindsList

import androidx.lifecycle.*
import com.valeriu.colindandroid.Event
import com.valeriu.colindandroid.data.source.ColindRepository
import com.valeriu.colindandroid.data.Result
import com.valeriu.colindandroid.data.Result.Success
import com.valeriu.colindandroid.data.models.ColindEntity
import kotlinx.coroutines.launch

class ColindsListViewModel (
    private val colindRepository: ColindRepository
): ViewModel() {

    private val _forceUpdate = MutableLiveData<Boolean>(false)

    private val _colindList: LiveData<List<ColindEntity>> = _forceUpdate.switchMap { forceUpdate ->
        if (forceUpdate){
            _dataLoading.value = true
            viewModelScope.launch {
                colindRepository.refreshColinds()
                _dataLoading.value = false
            }
        }
        colindRepository.observeColinds().distinctUntilChanged().switchMap { filterColinds(it) }
    }
    val colindList: LiveData<List<ColindEntity>> = _colindList;

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _openColindEvent = MutableLiveData<Event<Int>>()
    val openColindEvent: LiveData<Event<Int>> = _openColindEvent

    init {
        loadColinds()
    }

    fun openColind(colindId: Int) {
        _openColindEvent.value = Event(colindId)
    }

    private fun loadColinds() {
        _forceUpdate.value = true
    }

    private fun filterColinds(colinds: Result<List<ColindEntity>>): LiveData<List<ColindEntity>> {
        val result = MutableLiveData<List<ColindEntity>>()

        if (colinds is Success) {
            viewModelScope.launch {
                result.value = colinds.data
            }
        } else {
            result.value = emptyList()
        }

        return result
    }
}

