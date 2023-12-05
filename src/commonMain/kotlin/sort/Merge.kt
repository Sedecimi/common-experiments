package sort

fun merge(leftArray: IntArray, rightArray: IntArray): IntArray {
    val mergedArray = IntArray(leftArray.size + rightArray.size)
    var leftIndex = 0
    var rightIndex = 0
    var mergedIndex = 0

    while (leftIndex < leftArray.size && rightIndex < rightArray.size) {
        if (leftArray[leftIndex] <= rightArray[rightIndex]) {
            mergedArray[mergedIndex] = leftArray[leftIndex]
            leftIndex++
        } else {
            mergedArray[mergedIndex] = rightArray[rightIndex]
            rightIndex++
        }
        mergedIndex++
    }

    while (leftIndex < leftArray.size) {
        mergedArray[mergedIndex] = leftArray[leftIndex]
        leftIndex++
        mergedIndex++
    }

    while (rightIndex < rightArray.size) {
        mergedArray[mergedIndex] = rightArray[rightIndex]
        rightIndex++
        mergedIndex++
    }

    return mergedArray
}
