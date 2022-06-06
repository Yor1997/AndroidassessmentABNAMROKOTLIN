package com.example.androidassessmentabnamrokotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.androidassessmentabnamrokotlin.network.RepositoryData
import com.example.androidassessmentabnamrokotlin.network.RetroInstance
import com.example.androidassessmentabnamrokotlin.network.RetroService
import kotlinx.coroutines.flow.Flow

class MainActivityViewModel: ViewModel() {

    lateinit var retroService: RetroService

    init {
        retroService = RetroInstance.getRetroInstance().create(RetroService::class.java)
    }

    fun getListData(): Flow<PagingData<RepositoryData>> {
        return Pager (config = PagingConfig(pageSize = 10, maxSize = 100),
        pagingSourceFactory = {RepositoryPagingSource(retroService)}).flow.cachedIn(viewModelScope)
    }
}