package com.temporary.myapplication.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.temporary.myapplication.R
import com.temporary.myapplication.customClasses.PageIndicator
import com.temporary.myapplication.interfaces.CellClickListener
import com.temporary.myapplication.pojoModel.RocketListResponseModel
import kotlinx.android.synthetic.main.rocket_list_recyclerview_layout.view.*

class RocketListAdapter(
    private var mContext: Context,
    private val cellClickListener: CellClickListener
) :
    RecyclerView.Adapter<RocketListAdapter.ViewHolder>() {

    private var mArrayList: List<RocketListResponseModel.Main>? = null
    private var viewPager: ViewPager? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(mContext)
                .inflate(R.layout.rocket_list_recyclerview_layout, parent, false)
        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return mArrayList?.size ?: 0
    }

    fun addDataToList(mArrayList: List<RocketListResponseModel.Main>?) {
        this.mArrayList = mArrayList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val mIndicatorDots = holder.itemView.findViewById(R.id.page_indicator) as PageIndicator
        mIndicatorDots.setOrientation(0)
        mIndicatorDots.setDiff(3)
        mIndicatorDots.setColor(Color.WHITE, Color.GRAY)
        mIndicatorDots.setStrokeWidthWithoutFillColor(1 * mContext.resources.displayMetrics.density)
        mIndicatorDots.invalidateDots(0)
        mIndicatorDots.setRadius(3 * mContext.resources.displayMetrics.density)
        mIndicatorDots.setDotsCount(mArrayList!![position].flickrImages!!.size)
        mIndicatorDots.visibility = View.VISIBLE

        val mCustomerAdapter =
            CustomPagerAdapter(mContext, mArrayList!![position].flickrImages, cellClickListener)

        viewPager = holder.itemView.findViewById(R.id.viewPager)

        viewPager!!.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                mIndicatorDots.invalidateDots(position)
            }
        })

        viewPager!!.adapter = mCustomerAdapter

        holder.itemView.name.text = mArrayList?.get(position)?.name
        holder.itemView.country.text = mArrayList?.get(position)?.country
        holder.itemView.count.text = mArrayList?.get(position)?.stages.toString()

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(
                /*mArrayList!![position].id!!*/"", position
            )
        }
    }
}