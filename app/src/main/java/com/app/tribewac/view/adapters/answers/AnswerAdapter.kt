package com.app.tribewac.view.adapters.answers

import android.content.Context
import android.text.Html
import com.app.tribewac.R
import com.app.tribewac.base.BaseAdapter
import com.app.tribewac.data.models.answersmodel.AnswersModelResponseItem
import com.app.tribewac.databinding.ItemAnswersBinding
import com.app.tribewac.view.listeners.AdapterClickListener

class AnswerAdapter(
    val listener: AdapterClickListener<AnswersModelResponseItem>,
    context: Context
) :
    BaseAdapter<AnswersModelResponseItem>() {
    override fun compare(
        oldList: AnswersModelResponseItem,
        newList: AnswersModelResponseItem
    ): Boolean {
        return oldList.id == newList.id
    }

    override fun getAdapterListener(): AdapterClickListener<AnswersModelResponseItem> {
        return listener
    }

    override fun getLayoutIdForViewType(viewType: Int): Int {
        return R.layout.item_answers
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        val binding = holder.getBinding() as ItemAnswersBinding
        val item = currentList[position]

        binding.textQuestion.text = Html.fromHtml(Html.fromHtml(item.content).toString())
        binding.textPerson.text = item.user?.profile?.name ?: ""
    }
}