package com.temporary.myapplication.activity

import android.accounts.NetworkErrorException
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.temporary.myapplication.R
import com.temporary.myapplication.interfaces.RetryDialogClickHandler
import kotlinx.android.synthetic.main.tool_bar_layout.*
import java.net.SocketTimeoutException

abstract class BaseActivity : AppCompatActivity() {
    protected abstract fun getLayout(): Int
    protected abstract fun initialize()

    private var mProgressDialog: ProgressDialog? = null
    private var mDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayout())
        initialize()
    }

    protected fun setToolBar(title: String, showBackButton: Boolean) {
        toolbar.title = title
        setSupportActionBar(toolbar)
        if (showBackButton) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar.setNavigationOnClickListener {
                finish()
            }
        }
    }

    fun showToast(desc: String) {
        val toast = Toast.makeText(this, desc, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    protected fun showProgressDialog(mContext: Context, title: String) {
        mProgressDialog = null
        mProgressDialog = ProgressDialog(mContext)
        mProgressDialog?.setCancelable(false)
        mProgressDialog?.setMessage("Please wait...")
        mProgressDialog?.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        mProgressDialog?.show()
    }

    protected fun hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog?.dismiss()
        }
    }

    protected fun showRetryDialog(
        context: Context,
        throwable: Throwable,
        retryDialogClickHandler: RetryDialogClickHandler
    ) {

        mDialog = null
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setCancelable(false)

        when (throwable) {
            is SocketTimeoutException -> {
                alertDialog.setMessage(getString(R.string.err_request_timeout))
                alertDialog.setTitle("Request Timeout!!!")
            }
            is NetworkErrorException -> {
                alertDialog.setMessage(getString(R.string.err_internet))
                alertDialog.setTitle("Internet")
            }
            else -> {
                alertDialog.setMessage(getString(R.string.err_common))
                alertDialog.setTitle("Try Again")
            }
        }

        alertDialog.setNegativeButton("retry") { dialog, _ ->
            dialog.dismiss()
            retryDialogClickHandler.onRetryClick()
        }

        mDialog = alertDialog.create()
        mDialog!!.show()
    }

    protected open fun hideRetryDialog() {
        if (mDialog != null && mDialog!!.isShowing) {
            mDialog!!.dismiss()
        }
    }

    protected fun checkInternetConnection(): Boolean {
        var result = false
        try {
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cm?.run {
                    cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                        result = when {
                            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                            else -> false
                        }
                    }
                }
            } else {
                @Suppress("DEPRECATION")
                cm?.run {
                    val activeInfo = cm.activeNetworkInfo
                    if (activeInfo != null && activeInfo.isConnected) {
                        return activeInfo.type == ConnectivityManager.TYPE_WIFI || activeInfo.type == ConnectivityManager.TYPE_MOBILE
                    }
                }
            }
        } catch (e: Exception) {
            e.stackTrace
        }
        return result
    }
}