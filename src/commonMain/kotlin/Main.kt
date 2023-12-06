import concurrent.FixedCoroutinePool
import concurrent.Task
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import sort.mergeSortAsync
import sort.mergeSortPlain
import kotlin.random.Random
import kotlin.time.measureTime

val size = 1_000_000
fun main() = runBlocking {
    val random = Random(size)
    val plainArray = IntArray(size) {
        random.nextInt() % 100
    }.also {
        println("Plain array filled")
    }
    val asyncArray = IntArray(size) {
        random.nextInt() % 100
    }.also {
        println("Async array filled")
    }
    fixedPoolLike()
    measureTime {
        mergeSortPlain(plainArray)
    }.let {
        println("Plain sort took $it")
    }

    measureTime {
        mergeSortAsync(asyncArray)
    }.let {
        println("Async sort took $it")
    }
}

fun fixedPoolLike() {
    val pool = FixedCoroutinePool<Long>(4)
    pool.execute(
        Task("Task") {
            delay(1000L)
            0L
        },
    )

    for (i in 1..16) {
        pool.execute(
            Task("Task-$i") {
                delay(1000L)
                0L
            },
        )
    }
}
