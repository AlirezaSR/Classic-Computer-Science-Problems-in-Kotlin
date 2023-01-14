package chapter02.search

class BinarySearch {
    companion object {
        @JvmStatic
        fun <T : Comparable<T>> search(list: List<T>, key: T): Int {
            var low = 0
            var high = list.size - 1
            while (low <= high) { // while there is still a Search space
                val middle = (low + high) / 2
                val comparison = list[middle].compareTo(key)
                if (comparison < 0) { // middle codon is less than key
                    low = middle + 1
                } else if (comparison > 0) { // middle codon is greater than key
                    high = middle - 1
                } else { // middle codon is equal to key
                    return middle
                }
            }
            return -1
        }
    }

}