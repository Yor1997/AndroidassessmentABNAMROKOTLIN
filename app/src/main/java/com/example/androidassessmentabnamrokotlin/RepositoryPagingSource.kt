package com.example.androidassessmentabnamrokotlin

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.androidassessmentabnamrokotlin.network.RepositoryData
import com.example.androidassessmentabnamrokotlin.network.RetroService

class RepositoryPagingSource(val apiService: RetroService): PagingSource<Int, RepositoryData>() {
    override fun getRefreshKey(state: PagingState<Int, RepositoryData>): Int? {
        return  state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryData> {
        return try {
            val nextPage: Int = params.key?: FIRST_PAGE_INDEX
            val response = apiService.getDataFromAPI(nextPage, 10)
            var nextPageNumber: Int? = null

            val responseLinkHeaders = response.headers().get("Link")
            val hasNext = responseLinkHeaders?.contains("next")
            if(hasNext == true) {
                val linkEntries = responseLinkHeaders.split(",")
                println("ResponseLinkHeaders = $responseLinkHeaders")
                for (string in linkEntries) {
                    if (string.contains("next")) {
                        val nextLink = string.substringAfter("<").substringBefore('>')
                        val page = nextLink.substringAfter("page=").substringBefore("&")
                        val perPage = nextLink.substringAfter("per_page=")
                        println("nextLink = ${nextLink}, page = ${page}, perPage = ${perPage}")
                        nextPageNumber = page.toInt()
                    }
                }
            }

            LoadResult.Page(
                data = response.body() as List<RepositoryData>,
                prevKey = null,
                nextKey = nextPageNumber
            )

        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}