package chapter3.exercises.ex15

import chapter3.Cons
import chapter3.List
import chapter3.foldRight
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

fun increment(xs: List<Int>): List<Int> =
    foldRight(xs, List.empty()) { cur, acc -> Cons(cur + 1, acc) }

//TODO: Enable tests by removing `!` prefix
class Exercise15 : WordSpec({
    "list increment" should {
        "add 1 to every element" {
            increment(List.of(1, 2, 3, 4, 5)) shouldBe
                List.of(2, 3, 4, 5, 6)
        }
    }
})
