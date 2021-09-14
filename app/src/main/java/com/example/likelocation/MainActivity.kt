package com.example.likelocation

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isGone


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val post = Post(
            "14 августа 2021",
            "Nikolay Petrenko",
            "First post in our network!",
            15, 10, 0
        )

        val date: TextView = findViewById(R.id.date)
        date.text = post.data
        val name: TextView = findViewById(R.id.name)
        name.text = post.name
        val text: TextView = findViewById(R.id.textPost)
        text.text = post.text
        val itemLike: TextView = findViewById(R.id.itemLike)
        itemLike.text = post.itemLike.toString()
        val itemCom: TextView = findViewById(R.id.itemComment)
        itemCom.text = post.itemComm.toString()
        val itemShare: TextView = findViewById(R.id.itemShare)
        itemShare.text = post.itemShare.toString()


        zeroItem(itemLike, post.itemLike)
        zeroItem(itemCom, post.itemComm)
        zeroItem(itemShare, post.itemShare)

        var result: Pair<Int, Boolean>
        val btnLike: ImageButton = findViewById(R.id.buttonLike)
        btnLike.setOnClickListener {
            result = clickBtn(
                post.activeLike, btnLike, post.itemLike, itemLike,
                R.drawable.ic_baseline_favoritered_24, R.drawable.ic_baseline_favorite_24
            )
            post.itemLike = result.first
            post.activeLike = result.second
        }
        val btnCom: ImageButton = findViewById(R.id.buttonComment)
        btnCom.setOnClickListener {
            result = clickBtn(
                post.activeComm, btnCom, post.itemComm, itemCom,
                R.drawable.ic_baseline_commentred_24, R.drawable.ic_baseline_comment_24)
            post.itemComm = result.first
            post.activeComm = result.second
        }
        val btnShare: ImageButton = findViewById(R.id.buttonShare)
        btnShare.setOnClickListener {
            result = clickBtn(
                post.activeShare, btnShare, post.itemShare, itemShare,
                R.drawable.ic_baseline_sharered_24r, R.drawable.ic_baseline_share_24)
            post.itemShare = result.first
            post.activeShare = result.second
        }

        val postSecond = Post(
            "12 сентября 2021",
            "Andrey Ivanov",
            "Second post in our network!",
            30, 45, 10,
            address = "г.Москва, ул. Охотный ряд 2",
            gps = Pair(55.75695, 37.61540))

        val dateNew: TextView = findViewById(R.id.dateNew)
        dateNew.text = postSecond.data
        val nameNew: TextView = findViewById(R.id.nameNew)
        nameNew.text = postSecond.name
        val textNew: TextView = findViewById(R.id.textPostNew)
        textNew.text = postSecond.text
        val itemLikeNew: TextView = findViewById(R.id.itemLikeNew)
        itemLikeNew.text = postSecond.itemLike.toString()
        val itemComNew: TextView = findViewById(R.id.itemCommentNew)
        itemComNew.text = postSecond.itemComm.toString()
        val itemShareNew: TextView = findViewById(R.id.itemShareNew)
        itemShareNew.text = postSecond.itemShare.toString()
        val address: TextView = findViewById(R.id.address)
        address.text = postSecond.address
        val gps: TextView = findViewById(R.id.gps)
        gps.text = postSecond.gps.toString()

        val intent = Intent().apply {
            this.action = Intent.ACTION_VIEW
            data = Uri.parse("geo:${postSecond.gps.first},${postSecond.gps.second}")
        }
        gps.setOnClickListener {
            startActivity(intent)
        }
    }

    private fun zeroItem(textView: TextView, count: Int) {
        textView.isGone = count == 0
    }

    private fun clickBtn(
        active: Boolean, btn: ImageButton, item: Int, text: TextView,
        imageRed: Int, imageGray: Int
    ): Pair<Int, Boolean> {
        val itemChange: Int
        var activeLike = active
        if (activeLike) {
            btn.setImageResource(imageGray)
            itemChange = item - 1
            text.text = (itemChange).toString()
            text.setTextColor(Color.GRAY)
            activeLike = false
        } else {
            btn.setImageResource(imageRed)
            itemChange = item + 1
            text.text = (itemChange).toString()
            text.setTextColor(Color.RED)
            activeLike = true
        }
        zeroItem(text, itemChange)
        return Pair(itemChange, activeLike)
    }
}