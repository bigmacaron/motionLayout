package kr.kro.fatcats.infinite.service

import kr.kro.fatcats.infinite.model.VideoDto
import retrofit2.Response
import retrofit2.http.GET

interface VideoApi {

    @GET("v3/cbb293eb-e8b2-4079-ba82-472d1c0419d1")
    suspend fun listVideos() : Response<VideoDto>
}