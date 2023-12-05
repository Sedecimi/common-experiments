package concurrent

class Task<T>(
    val name: String,
    val operation: suspend () -> T
) {
    suspend operator fun invoke(): T = operation.invoke()
}
