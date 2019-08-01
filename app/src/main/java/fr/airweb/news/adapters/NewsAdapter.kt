package fr.airweb.news.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.airweb.news.R
import fr.airweb.news.config.NO_NEWS_HOLDER_TYPE_CODE
import fr.airweb.news.config.POSTS_HOLDER_TYPE_CODE
import fr.airweb.news.data.models.Post

class NewsAdapter(val posts: ArrayList<Post>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int = if (posts.size == 0) NO_NEWS_HOLDER_TYPE_CODE else POSTS_HOLDER_TYPE_CODE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        lateinit var holder: RecyclerView.ViewHolder
        when(viewType) {
            POSTS_HOLDER_TYPE_CODE -> holder = PostsHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.post_layout,
                    parent,
                    false
                )
            )
            NO_NEWS_HOLDER_TYPE_CODE -> holder = NetworkErrorHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.no_news_layout,
                    parent,
                    false
                )
            )
        }
        return holder
    }

    override fun getItemCount(): Int = if (posts.size == 0) 1 else posts.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == POSTS_HOLDER_TYPE_CODE) {
            val post: Post = posts[position]

            holder as PostsHolder

            Glide.with(holder.itemView.context)
                .load(post.picture)
                .into(holder.pictureIv)

            holder.titleTv.text = post.title
            holder.typeTv.text = post.type
            holder.contentTv.text = post.content
            holder.dateTv.text = post.date
        }
    }

    class PostsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleTv = itemView.findViewById<TextView>(R.id.newsPostTitleTv)
        val typeTv = itemView.findViewById<TextView>(R.id.newsPostTypeTv)
        val pictureIv = itemView.findViewById<ImageView>(R.id.newsPostPictureIv)
        val contentTv = itemView.findViewById<TextView>(R.id.newsPostContentTv)
        val dateTv = itemView.findViewById<TextView>(R.id.newsPostDateTv)
    }

    class NetworkErrorHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}