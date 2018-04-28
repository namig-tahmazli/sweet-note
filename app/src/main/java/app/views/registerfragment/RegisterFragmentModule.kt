package app.views.registerfragment

import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val registerFragmentModule = applicationContext {
    viewModel { RegisterFragmentViewModel(get(), get(), get()) }
    bean { params -> RegisterFragmentPresenter(params[RegisterFragment.FRAGMENT]) }
}