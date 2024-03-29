package app.ext

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.extensions.Duration
import app.extensions.showSnackBar

internal abstract class BaseFragment<DB : ViewDataBinding> : Fragment(), LifecycleOwner {
    lateinit var dataBinding: DB

    @get:LayoutRes
    abstract val layoutRes: Int

    abstract fun bindVariables(dataBinding: DB)
    abstract fun addLifecycleObservers(lifecycle: Lifecycle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addLifecycleObservers(lifecycle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        bindVariables(dataBinding)
        return dataBinding.root
    }

    fun showMessage(@StringRes messageId: Int? = null,
                    message: String = "") {
        dataBinding.root.showSnackBar(messageId = messageId, message = message, duration = Duration.LONG)
    }
}