package com.temporary.myapplication.activity

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.temporary.myapplication.R
import com.temporary.myapplication.adapters.CustomPagerAdapter
import com.temporary.myapplication.customClasses.PageIndicator
import com.temporary.myapplication.roomdatabase.AppDataBase
import com.temporary.myapplication.roomdatabase.RocketListDataEntity
import kotlinx.android.synthetic.main.rocket_details_activity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RocketDetailsActivity : BaseActivity() {

    private var mPosition: Int = 0
    private var mViewPager: ViewPager? = null
    private val mContext: Context = this
    var list: ArrayList<RocketListDataEntity>? = null

    override fun getLayout(): Int {
        return R.layout.rocket_details_activity
    }

    @SuppressLint("SetTextI18n")
    override fun initialize() {

        mPosition = intent.getIntExtra("position", 0)

        var list: ArrayList<RocketListDataEntity>

        GlobalScope.launch(Dispatchers.IO) {

            val userDao = AppDataBase.getInstance(mContext).mRocketListDao()
            list = userDao.getAllRocketList() as ArrayList<RocketListDataEntity>

            withContext(Dispatchers.Main) {

                setToolBar(list[mPosition].name[mPosition].name, true)
                wiki_link.text = list[mPosition].wikiLink[mPosition].wikiLink
                status.text = list[mPosition].activeStatus[mPosition].activeStatus.toString()
                cost.text = list[mPosition].costPerLaunch[mPosition].costPerLaunch
                rate_percent.text = list[mPosition].successRate[mPosition].successRate
                description.text = list[mPosition].desc[mPosition].desc
                height.text =
                    "Height: " + list[mPosition].height[mPosition].height + " feet Diameter " + list[mPosition].diameter[mPosition].diameter + " feet"
                wiki_link.movementMethod = LinkMovementMethod.getInstance()


                val mIndicatorDots = findViewById<PageIndicator>(R.id.page_indicator)
                mIndicatorDots.setOrientation(0)
                mIndicatorDots.setDiff(3)
                mIndicatorDots.setColor(Color.WHITE, Color.GRAY)
                mIndicatorDots.setStrokeWidthWithoutFillColor(1 * resources.displayMetrics.density)
                mIndicatorDots.invalidateDots(0)
                mIndicatorDots.setRadius(3 * resources.displayMetrics.density)
                mIndicatorDots.setDotsCount(list[mPosition].images[mPosition].images!!.size)
                mIndicatorDots.visibility = View.VISIBLE

                val temp = ArrayList<String>()

                for (i in list[mPosition].images[mPosition].images!!.indices) {
                    temp.add(list[mPosition].images[mPosition].images!![i])
                }

                val mCustomerAdapter =
                    CustomPagerAdapter(mContext, temp, null)

                mViewPager = findViewById(R.id.viewPager)

                mViewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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

                viewPager.adapter = mCustomerAdapter
            }
        }
    }
}