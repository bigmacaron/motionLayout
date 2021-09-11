package kr.kro.fatcats.infinite.service

object VideoService {
    const val BASE_DOMAIN : String = "http://run.mocky.io/"
    val client : VideoApi = BaseService.getClient(BASE_DOMAIN).create(VideoApi::class.java)

}