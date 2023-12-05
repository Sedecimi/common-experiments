fun mergeSortPlain(array: IntArray, startIndex: Int = 0, endIndex: Int = array.size-1): IntArray {
    if (array.size <= 1) {
        return array
    }

    val middleIndex = (startIndex + endIndex) / 2
    val leftArray = array.sliceArray(startIndex..middleIndex)
    val rightArray = array.sliceArray(middleIndex + 1..endIndex)

    return merge(mergeSortPlain(leftArray), mergeSortPlain(rightArray))
}

suspend fun mergeSortAsync(array: IntArray, startIndex: Int = 0, endIndex: Int = array.size-1): IntArray {
       if (array.size <= 1) {
           return array
       }

       val middleIndex = (startIndex + endIndex) / 2
       val leftArray = array.sliceArray(startIndex..middleIndex).let {
           async { mergeSortAsync(it) }
       }
       val rightArray = array.sliceArray(middleIndex + 1..endIndex).let {
           async { mergeSortAsync(it) }
       }

       return merge(
           leftArray,
           rightArray
       )
}