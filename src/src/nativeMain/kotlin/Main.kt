import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.time.measureTime

val size = 1_000_000
fun main() = runBlocking {
    val random = Random(size)
    val plainArray = IntArray(size){
        random.nextInt() % 100
    }.also {
        println("Plain array filled")
    }
    val asyncArray = IntArray(size){
        random.nextInt() % 100
    }.also {
        println("Async array filled")
    }
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