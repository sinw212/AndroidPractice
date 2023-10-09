package com.example.searchimage.data.remote

import com.example.searchimage.data.model.ImageDocumentResponse
import com.example.searchimage.data.model.SearchResponse
import com.example.searchimage.data.model.VideoDocumentResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRemoteDatasource {
    @GET("/v2/search/image")
    suspend fun getSearchImage(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): SearchResponse<ImageDocumentResponse>

    @GET("/v2/search/vclip")
    suspend fun getSearchVideo(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): SearchResponse<VideoDocumentResponse>
}