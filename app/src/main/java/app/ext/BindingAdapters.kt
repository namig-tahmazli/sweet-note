package app.ext

import android.databinding.BindingAdapter
import android.support.annotation.MenuRes
import android.support.design.widget.TextInputLayout
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.View
import app.customview.PinEditText

@BindingAdapter(value = ["android:visibility"])
fun setVisibility(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter(value = ["app:pin_length"])
internal fun setPinLength(pinEditText: PinEditText, length: Int) {
    pinEditText.defaultPinValueLength = length
}

@BindingAdapter(value = ["app:error_text"])
internal fun setError(textInputLayout: TextInputLayout, message: String?) {
    textInputLayout.error = message
}

@BindingAdapter(value = ["app:refreshing"])
internal fun setRefreshing(swipeRefreshLayout: SwipeRefreshLayout, isRefreshing: Boolean) {
    swipeRefreshLayout.isRefreshing = isRefreshing
}

@BindingAdapter(value = ["app:query"])
internal fun setQuery(searchView: SearchView, query: String?) {
    searchView.setQuery(query, false)
}

@BindingAdapter(value = ["app:menu"])
internal fun setMenu(toolbar: Toolbar, @MenuRes res: Int) {
    toolbar.inflateMenu(res)
}