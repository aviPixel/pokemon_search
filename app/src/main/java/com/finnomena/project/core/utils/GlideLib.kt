package com.finnomena.project.core.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

class GlideLib {

    companion object {

        private val options = RequestOptions()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.img_placeholder)
//                .error(R.drawable.img_error);

        private val optionsNoScale = RequestOptions()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)

        /**
         *  image drawable
         */
        fun setImage(context: Context, resId: Int, imageView: ImageView) {
            Glide.with(context)
                    .load(resId)
                    .transition(withCrossFade())
                    .apply(options)
                    .into(imageView)
        }

        /**
         *  image url
         */
        fun setImage(context: Context, url: String, imageView: ImageView) {
            Glide.with(context)
                    .load(url)
                    .transition(withCrossFade())
                    .apply(options)
                    .into(imageView)
        }

        /**
         *  bitmap
         */
        fun setImage(context: Context, bitmap: Bitmap, imageView: ImageView) {
            Glide.with(context)
                .load(bitmap)
                .transition(withCrossFade())
                .apply(options)
                .into(imageView)
        }

        /**
         *  image url not scale type
         */
        fun setImageNoScale(context: Context, url: String, imageView: ImageView) {
            Glide.with(context)
                    .load(url)
                    .transition(withCrossFade())
                    .apply(optionsNoScale)
                    .into(imageView)
        }

        fun setImageNoScale(context: Context, resId: Int, imageView: ImageView) {
            Glide.with(context)
                    .load(resId)
                    .transition(withCrossFade())
                    .apply(optionsNoScale)
                    .into(imageView)
        }

        /**
         *  set image scale type with place holder
         */
        fun setImageScale(context: Context, url: String, placeholder: Drawable?, imageView: ImageView) {
            Glide.with(context)
                    .load(url)
                    .transition(withCrossFade())
                    .apply(optionsNoScale.placeholder(placeholder))
                    .into(imageView)
        }

        /**
         *  set image scale type with request listener
         */
        fun setImageScale(context: Context, url: String, imageView: ImageView, requestListener: RequestListener<Drawable>) {
            Glide.with(context)
                    .load(url)
                    .listener(requestListener)
                    .transition(withCrossFade())
                    .apply(optionsNoScale)
                    .into(imageView)
        }

        /**
         * progressbar
         */
        fun setImage(context: Context, url: String, imageView: ImageView, progressBar: ProgressBar) {
            Glide.with(context)
                    .load(url)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                            progressBar.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                            progressBar.visibility = View.GONE
                            return false
                        }
                    })
                    .transition(withCrossFade())
                    .apply(options)
                    .into(imageView)
        }

    }

}

fun ImageView.setCircleImage(url: String) {
    Glide.with(context).load(url).apply(RequestOptions()
            .optionalCircleCrop()
            .format(DecodeFormat.PREFER_ARGB_8888))//.placeholder(R.drawable.ic_launcher_background)
            .transition(withCrossFade())
            .into(this)
}