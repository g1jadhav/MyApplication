package com.temporary.myapplication.adapters

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.temporary.myapplication.R
import com.temporary.myapplication.activity.RocketListActivity
import com.temporary.myapplication.interfaces.CellClickListener

class CustomPagerAdapter(
    private val mContext: Context,
    private val list: List<String>?,
    private val cellClickListener: CellClickListener?
) :
    PagerAdapter() {

    override fun instantiateItem(collection: ViewGroup, position: Int): ViewGroup {

        val inflater = LayoutInflater.from(mContext)

        val layout = inflater.inflate(
            R.layout.contents_for_viewpager,
            collection,
            false
        ) as ViewGroup

        val mImageView =
            layout.findViewById<View>(R.id.imageview) as ImageView

        if (cellClickListener != null)
            mImageView.setOnClickListener {
                cellClickListener.onCellClickListener(mImageView.tag.toString(), position)
            }

        mImageView.setImageDrawable(null)
        mImageView.tag = list!![position]
        Picasso.with(mContext)
            .load(list[position])
            .resize(
                mContext.resources.displayMetrics.widthPixels,
                (mContext.resources.displayMetrics.widthPixels / 1.77f).toInt()
            )
            .config(Bitmap.Config.RGB_565)
            .tag(RocketListActivity::class.java)
            .into(mImageView, object : Callback {
                override fun onSuccess() {

                }

                override fun onError() {

                }
            })

        collection.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as FrameLayout?)
    }

    override fun getCount(): Int {
        return list!!.size
    }

    override fun isViewFromObject(view: View, any: Any): Boolean {
        return view === any
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return ""
    }
}