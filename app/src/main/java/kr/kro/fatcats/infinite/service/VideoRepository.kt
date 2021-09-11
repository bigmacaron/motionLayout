package kr.kro.fatcats.infinite.service

class VideoRepository {

    private val client = VideoService.client

    suspend fun getVideoData() =
        client.listVideos()
}