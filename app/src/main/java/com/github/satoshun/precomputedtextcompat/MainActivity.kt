package com.github.satoshun.precomputedtextcompat

import android.os.Bundle
import android.text.SpannableString
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.text.util.LinkifyCompat
import androidx.core.widget.TextViewCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.BindAwareViewHolder
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.precomputedtextcompat.databinding.MainActBinding
import com.github.satoshun.precomputedtextcompat.databinding.MainItemBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Random

class MainActivity : AppCompatActivity() {
  private lateinit var binding: MainActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.main_act)
    binding.recycler.layoutManager = LinearLayoutManager(this)
    binding.recycler.adapter = Adapter()
  }
}

private class Adapter : RecyclerView.Adapter<ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
    ViewHolder(MainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

  override fun getItemCount(): Int = 1000

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val spannable = spannables[position]
    val important = predicates[position]
    holder.binding.title.textSize = if (important) 16f else 12f

    // new way future approach
    if (false) {
      (holder.binding.title as AppCompatTextView).setTextFuture(
        PrecomputedTextCompat.getTextFuture(
          spannable,
          TextViewCompat.getTextMetricsParams(holder.binding.title),
          null
        )
      )
    }

    // new way coroutine approach
    if (true) {
      val job = GlobalScope.launch(Dispatchers.Main) {
        val text = withContext(Dispatchers.IO) {
          val params = TextViewCompat.getTextMetricsParams(holder.binding.title)
          PrecomputedTextCompat.create(spannable, params)
        }
        TextViewCompat.setPrecomputedText(holder.binding.title, text)
      }
      holder.job = job
    }

    // normal way
    if (false) {
      holder.binding.title.text = spannable
    }
  }

  override fun getItemViewType(position: Int): Int {
    return position
  }
}

private class ViewHolder(
  val binding: MainItemBinding
) : BindAwareViewHolder(binding.root) {
  var job: Job? = null

  override fun onUnbind() {
    job?.cancel()
  }
}

private val random = Random()
private val alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray()

// simulate expensive spannable
private val spannables = (0..1000).map {
  SpannableString(generateText()).apply {
    LinkifyCompat.addLinks(this, Linkify.ALL)
  }
}
private val predicates = (0..1000).map { random.nextInt(5) % 5 == 0 }

private fun generateText(): String {
  return (0..700)
    .map { alphabet[random.nextInt(26)] }
    .joinToString("")
}
