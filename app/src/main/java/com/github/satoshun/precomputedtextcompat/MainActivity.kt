package com.github.satoshun.precomputedtextcompat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.precomputedtextcompat.databinding.MainActBinding
import com.github.satoshun.precomputedtextcompat.databinding.MainItemBinding
import java.util.*

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
          MainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
          generateText()
      )

  override fun getItemCount(): Int = 1000

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.binding.text.text = holder.text
  }
}

private class ViewHolder(
    val binding: MainItemBinding,
    val text: String
) : RecyclerView.ViewHolder(binding.root)

val random = Random()
val alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray()

private fun generateText(): String {
  return (0..500)
      .map { alphabet[random.nextInt(26)] }
      .joinToString("")
}
