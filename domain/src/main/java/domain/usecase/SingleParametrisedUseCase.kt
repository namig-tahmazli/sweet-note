package domain.usecase

import io.reactivex.Single

abstract class SingleParametrisedUseCase<in P, R> : BaseUseCase() {
    abstract fun build(param: P): Single<R>
    private fun make(param: P, wrap: Boolean): Single<R> {
        val build = build(param)
        val wrapped = if (wrap) wrap(build) else build
        return wrapDebug(wrapped)
    }

    private fun wrap(single: Single<R>): Single<R> {
        return single.compose(schedulerTransformer.applySingleTransformer())
    }

    private fun wrapDebug(single: Single<R>): Single<R> {
        return if (debugTransformer.isPresent) {
            single.compose(debugTransformer.get().singleDebugTransformer())
        } else {
            single
        }
    }

    fun get(param: P): Single<R> = make(param, true)
    fun chain(param: P): Single<R> = make(param, false)
}