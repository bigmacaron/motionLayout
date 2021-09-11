package kr.kro.fatcats.infinite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.*
import kr.kro.fatcats.infinite.adapter.VideoAdapter
import kr.kro.fatcats.infinite.databinding.ActivityMainBinding
import kr.kro.fatcats.infinite.service.VideoRepository
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() , CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val videoRepository: VideoRepository? = VideoRepository()

    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter : VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//            .apply {
//
//        }
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(binding.fragmentContainer.id,PlayerFragment()).commit()
        setAdapter()
        launch {
            getVideoList()
        }
    }

    private fun setAdapter() {
        binding.mainRecyclerView.apply {
            mAdapter = VideoAdapter()
            layoutManager = LinearLayoutManager(binding.root.context)
            adapter = mAdapter
        }
    }


    private suspend fun getVideoList() =  withContext(Dispatchers.IO) {
        videoRepository?.getVideoData()?.let { response ->
            Log.d("response", "response=> $response")
            if (response.isSuccessful) {
                Log.d("MainActivity" , "response success")
                Log.d("MainActivity" , "${response.body()}")
                withContext(Dispatchers.Main){
                    response.body()?.apply {
                        mAdapter.submitList(this.videos)
                    }
                }
            }else{
                Log.e("MainActivity" , "response fail")
                Log.d("MainActivity" , "${response.code()}")
                //예외처리
            }
        }
    }
}
