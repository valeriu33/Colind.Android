package com.valeriu.colindandroid.colindDetail

import android.widget.Toast
import androidx.lifecycle.*
import com.valeriu.colindandroid.ColindApplication
import com.valeriu.colindandroid.Event
import com.valeriu.colindandroid.data.models.ColindEntity
import com.valeriu.colindandroid.data.source.ColindRepository
import com.valeriu.colindandroid.data.Result.Success
import com.valeriu.colindandroid.data.Result.Error
import com.valeriu.colindandroid.data.Result
import kotlinx.coroutines.launch

class ColindDetailViewModel(
    private val colindRepository: ColindRepository
): ViewModel() {

    private val _colindId = MutableLiveData<Int>()

    private val _colind = _colindId.switchMap { colindId ->
        colindRepository.observeColind(colindId).map { computeResult(it) }
    }

    val colind: LiveData<ColindEntity> = _colind

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _openColindEvent = MutableLiveData<Event<String>>()
    val openColindEvent: LiveData<Event<String>> = _openColindEvent

//    fun openColind(colindName: String) {
//        _openColindEvent.value = Event(colindName)
//    }

    fun start(colindId: Int?) {
        // If we're already loading or already loaded, return (might be a config change)
        if (_dataLoading.value == true || colindId == _colindId.value) {
            return
        }

        _colindId.value = colindId ?: 0
    }

    private fun computeResult(taskResult: Result<ColindEntity>): ColindEntity {
        return if (taskResult is Success) {
            _openColindEvent.value = Event(taskResult.data.title)
            taskResult.data
        } else {
//            showSnackbarMessage(R.string.loading_tasks_error) TODO implement snackbar
            ColindEntity(1,"title","text","")
        }
    }
}