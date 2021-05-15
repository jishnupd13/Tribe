package com.app.tribewac.view.adapters.questions

import android.content.Context
import com.app.tribewac.R
import com.app.tribewac.base.BaseAdapter
import com.app.tribewac.data.models.questionlists.QuestionListModelResponseItem
import com.app.tribewac.databinding.ItemQuestionsBinding
import com.app.tribewac.utils.hide
import com.app.tribewac.utils.show
import com.app.tribewac.view.listeners.AdapterClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class QuestionAdapter(
    val listener: AdapterClickListener<QuestionListModelResponseItem>,
    context: Context
) :
    BaseAdapter<QuestionListModelResponseItem>() {

    val context = context

    override fun compare(
        oldList: QuestionListModelResponseItem,
        newList: QuestionListModelResponseItem
    ): Boolean {
        return oldList.id == newList.id
    }

    override fun getAdapterListener(): AdapterClickListener<QuestionListModelResponseItem> {
        return listener
    }

    override fun getLayoutIdForViewType(viewType: Int): Int {
        return R.layout.item_questions
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        val binding = holder.getBinding() as ItemQuestionsBinding
        val item = currentList[position]


        if (item.images?.size ?: 0 > 0) {
            binding.imageQuestion.show()

            Glide.with(context)
                .load(item.images?.get(0)?.src ?: "")
                .placeholder(R.drawable.placeholder)
                .timeout(60000)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .error(R.drawable.placeholder)
                .into(binding.imageQuestion)

        } else
            binding.imageQuestion.hide()

        binding.title.text = item.title
        binding.description.text = item.description

        binding.textCounts.text = "${item.counts?.followers} Likes ${item.counts?.answers} Answers"


    }
}