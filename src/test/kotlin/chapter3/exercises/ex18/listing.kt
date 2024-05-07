package chapter3.exercises.ex18

import chapter3.Cons
import chapter3.List
import chapter3.foldRight
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

fun <A> filter(
    xs: List<A>,
    f: (A) -> Boolean
): List<A> =
    foldRight(xs, List.empty()) { cur, acc ->
        if (f(cur)) Cons(cur, acc) else acc
    }

class Exercise18 : WordSpec({
    "list filter" should {
        "filter out elements not compliant to predicate" {
            val xs = List.of(1, 2, 3, 4, 5)
            filter(xs) { it % 2 == 0 } shouldBe List.of(2, 4)
        }
    }
})
