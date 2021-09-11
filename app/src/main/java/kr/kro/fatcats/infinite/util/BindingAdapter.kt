package kr.kro.fatcats.infinite.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bindThumbnail")
fun bindViewThumbnail(view: ImageView, values: String?) {
    values?.let {
        Glide.with(view.context).load(it).into(view)
    }
}
