package com.example.quizzit.screens.quiz.selecteren

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzit.R
import com.example.quizzit.databinding.ListitemQuizBinding
import com.example.quizzit.domain.Quiz
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ITEM_VIEW_TYPE_ITEM = 1

class QuizAdapter(val clickListener: QuizListener) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(QuizDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun setList(list: List<Quiz>?) {
        adapterScope.launch {
            val items = list?.map {
                DataItem.QuizItem(it)
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val quizItem = getItem(position) as DataItem.QuizItem
                holder.bind(clickListener, quizItem.quiz)
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
            is DataItem.QuizItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class ViewHolder private constructor(private val binding: ListitemQuizBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: QuizListener, item: Quiz) {
            binding.quiz = item
            binding.clickListener = clickListener
            binding.txtQuizNaam.text = item.naam
            binding.txtQuizCategorie.text = item.categorie
            val drawable = when (item.categorie) {
                "Geografie" -> R.drawable.geografie
                "Wetenschap en natuur" -> R.drawable.natuurwetenschap
                else -> R.drawable.algemeen
            }
            binding.quizImg.setImageResource(drawable)
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListitemQuizBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class QuizDiffCallback : DiffUtil.ItemCallback<DataItem>() {

    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.equals(newItem)
    }
}

class QuizListener(val clickListener: (quizId: Int) -> Unit) {
    fun onClick(quiz: Quiz) = clickListener(quiz.id)
}

sealed class DataItem {
    data class QuizItem(val quiz: Quiz) : DataItem() {
        override val id = quiz.id
    }

    abstract val id: Int
}
