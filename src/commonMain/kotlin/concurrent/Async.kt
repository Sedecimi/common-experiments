package concurrent

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine

fun <T> async(func: suspend () -> T): T {
    var ret: T? = null
    func.startCoroutine(object : Continuation<T> {
        override val context: CoroutineContext
            get() = EmptyCoroutineContext

        override fun resumeWith(result: Result<T>) {
            ret = result.getOrThrow()
        }
    })
    return ret?.let { it as T } ?: throw IllegalStateException()
}
