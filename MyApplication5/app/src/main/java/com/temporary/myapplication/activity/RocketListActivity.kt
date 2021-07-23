package com.temporary.myapplication.activity

import android.accounts.NetworkErrorException
import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.temporary.myapplication.R
import com.temporary.myapplication.adapters.RocketListAdapter
import com.temporary.myapplication.customClasses.Constant
import com.temporary.myapplication.di.component.DaggerRetrofitComponent
import com.temporary.myapplication.interfaces.CellClickListener
import com.temporary.myapplication.interfaces.RetryDialogClickHandler
import com.temporary.myapplication.pojoModel.RocketListResponseModel
import com.temporary.myapplication.viewModel.RocketListVMFactory
import com.temporary.myapplication.viewModel.RocketListViewModel
import kotlinx.android.synthetic.main.rocket_list_activity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RocketListActivity : BaseActivity(), CellClickListener, RetryDialogClickHandler {

    private lateinit var mRocketListViewModel: RocketListViewModel

    private lateinit var mRocketListAdapter: RocketListAdapter
    private val mContext: Context = this

    @Inject
    lateinit var mRocketListVMFactory: RocketListVMFactory

    private var mList = ArrayList<RocketListResponseModel.Main>()

    override fun getLayout(): Int {
        return R.layout.rocket_list_activity
    }

    override fun initialize() {

        setToolBar(getString(R.string.rocket_list), false)

        mRocketListVMFactory =
            DaggerRetrofitComponent.create().getRocketListVMFactory()

        mRocketListViewModel = ViewModelProviders.of(this, mRocketListVMFactory)
            .get(RocketListViewModel::class.java)

        mRocketListAdapter = RocketListAdapter(this, this)

        val linearLayoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = linearLayoutManager
        recycler_view.adapter = mRocketListAdapter


        mRocketListViewModel.mIsLoading.observe(this, Observer { isLoading ->
            if (isLoading) {
                showProgressDialog(this, getString(R.string.please_wait))
            } else {
                hideProgressDialog()
            }
        })

        mRocketListViewModel.mRocketList.observe(this, Observer {
            if (it.size < 0) {
                showToast("No data available")
            } else {
                mList = it as ArrayList<RocketListResponseModel.Main>
                mRocketListAdapter.addDataToList(it)
            }
        })

        mRocketListViewModel.throwableMutableLiveData.observe(this, Observer { throwable ->
            if (throwable is NullPointerException) {
                showToast(getString(R.string.server_error))
            } else {
                showRetryDialog(this, throwable, this)
            }
        })

        if (Constant.checkDataIsStored(this)) {
            mRocketListViewModel.getSavedRocketList(this)
        } else {
            callRocketListApi()
        }
    }

    private fun callRocketListApi() {
        if (checkInternetConnection()) {
            mRocketListViewModel.getRocketList(this)
        } else {
            showRetryDialog(this, NetworkErrorException(), this)
        }
    }

    override fun onCellClickListener(id: String, position: Int) {
        if (id == "") {
            val intent = Intent(this, RocketDetailsActivity::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        } else {
            GlobalScope.launch(Dispatchers.IO) {
                var temp = 0
                for (i in mList.indices) {
                    for (j in mList[i].flickrImages!!.indices) {
                        if (id == mList[i].flickrImages!![j]) {
                            temp = i
                            break
                        }
                    }
                }
                withContext(Dispatchers.Main) {
                    val intent = Intent(mContext, RocketDetailsActivity::class.java)
                    intent.putExtra("position", temp)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onRetryClick() {
        callRocketListApi()
    }

    override fun onDestroy() {
        hideProgressDialog()
        hideRetryDialog()
        super.onDestroy()
    }
}