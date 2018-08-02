package com.github.satoshun.precomputedtextcompat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.precomputedtextcompat.databinding.MainActBinding
import com.github.satoshun.precomputedtextcompat.databinding.MainItemBinding
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
      ViewHolder(
          MainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      )

  override fun getItemCount(): Int = 1000

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val text = texts[position]
    val important = predicates[position]

    // new way
    holder.binding.title.textSize = if (important) 16f else 12f
    (holder.binding.title as AppCompatTextView).setTextFuture(
        PrecomputedTextCompat.getTextFuture(
            text,
            TextViewCompat.getTextMetricsParams(holder.binding.title),
            null
        )
    )

    // normal way
//    holder.binding.title.text = text
  }

  override fun getItemViewType(position: Int): Int {
    return position
  }
}

private class ViewHolder(
    val binding: MainItemBinding
) : RecyclerView.ViewHolder(binding.root)

val random = Random()
val alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray()
val texts = (0..1000).map { generateText() }
val predicates = (0..1000).map { random.nextInt(5) % 5 == 0 }

private fun generateText(): String {
  return (0..700)
      .map { alphabet[random.nextInt(26)] }
      .joinToString("")
}
