package com.example.quizzit.score

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzit.R
import com.example.quizzit.databinding.ListitemScoreBinding
import com.example.quizzit.domain.Score
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ITEM_VIEW_TYPE_ITEM = 1

class ScoreAdapter :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(ScoreDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun setList(list: List<Score>?) {
        adapterScope.launch {
            val items = list?.map {
                DataItem.ScoreItem(it)
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val scoreItem = getItem(position) as DataItem.ScoreItem
                holder.bind(scoreItem.score)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.ScoreItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class ViewHolder private constructor(private val binding: ListitemScoreBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Score) {
            binding.score = item
            val drawable = when(position) {
                0 -> R.drawable.plaats1
                1 -> R.drawable.plaats2
                2 -> R.drawable.plaats3
                3 -> R.drawable.plaats4
                4 -> R.drawable.plaats5
                5 -> R.drawable.plaats6
                6 -> R.drawable.plaats7
                7 -> R.drawable.plaats8
                8 -> R.drawable.plaats9
                9 -> R.drawable.plaats10
                else -> R.drawable.plaats1
            }
            binding.imgScoreplaats.setImageResource(drawable)
            binding.txtScoreInfo.text = String.format("naam: %s%n punten: %d%n tijd: %s%n", item.nicknaam, item.punten, item.tijd)
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListitemScoreBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class ScoreDiffCallback : DiffUtil.ItemCallback<DataItem>() {

    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.equals(newItem)
    }
}


sealed class DataItem {
    data class ScoreItem(val score: Score) : DataItem() {
        override val id = score.id
    }

    abstract val id: Int
}