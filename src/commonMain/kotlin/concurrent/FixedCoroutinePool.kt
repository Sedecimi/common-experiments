package concurrent

import kotlinx.coroutines.channels.*
import kotlinx.coroutines.*

class FixedCoroutinePool<T>(val workers: Int) {
    val channel = Channel<Task<T>>()
    val jobs = mutableListOf<Job>()

    init {
        start()
    }

    fun start() {
        repeat(workers) {
            jobs.add(GlobalScope.launch {
                for(task in channel) {
                    println("Starting worker-$it with task ${task.name}")
                    task()
                    println("Complete worker-$it")
                }
            })
        }
    }
    fun execute(block: Task<T>) {
        GlobalScope.launch {
            channel.send(block)
        }
    }

    suspend fun join() {
        for(job in jobs) job.join()
    }
    fun close() = channel.close()
}

