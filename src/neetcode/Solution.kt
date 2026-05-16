package neetcode
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlin.math.sqrt
object Solution {

    fun solution(ranks: IntArray, cars: Int): Long {
        var left = 1L
        var right = ranks.minOrNull()!!.toLong() * cars.toLong() * cars.toLong() // long
        var answer = right
        while (left <= right) {
            val mid = (left + right) / 2
            var totalCars = 0L
            for (r in ranks) {
                totalCars += sqrt(mid.toDouble() / r).toLong()
                if (totalCars >= cars)
                    break
            }
            if (totalCars >= cars) {
                answer = mid
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return answer
    }

    @JvmStatic
    fun main(args: Array<String>) {
        runBlocking {
            println("Main scope start")
            try {
                supervisorScope {
                    launch {
                        println("child 1 is starting")
                        delay(100)
                        throw IllegalStateException("child 1 : caught exception")
                    }
                    launch {
                        println("child 2 is starting")
                        delay(500)
                        println("child 2 completed successfully")
                    }
                }
            } catch (e: Exception) {
                println(e.message)
            }
            println("Main scope End")
        }
    }

}