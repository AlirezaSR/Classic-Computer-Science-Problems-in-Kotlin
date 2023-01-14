package chapter02.search

class LinearSearch {

    companion object {
        @JvmStatic
        fun <T : Comparable<T>> search(list: List<T>, key: T): Int {
            for (i in list.indices) if (list[i].compareTo(key) == 0) return i // found a match
            return -1 // not found
        }
    }

}