package chapter3.exercises.ex14

import chapter3.Cons
import chapter3.List
import chapter3.append
import chapter3.foldRight
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

// tag::init[]
fun <A> concat(lla: List<List<A>>): List<A> =
    foldRight(lla, List.empty()) { cur, acc ->
        foldRight(cur, acc) { icurr, iacc -> Cons(icurr, iacc) }
    }

fun <A> concat2(lla: List<List<A>>): List<A> =
    foldRight(lla, List.empty()) { cur, acc -> append(cur, acc) }

class Exercise14 : WordSpec({
    "list concat" should {
        "concatenate a list of lists into a single list" {
            concat(
                List.of(
                    List.of(1, 2, 3),
                    List.of(4, 5, 6)
                )
            ) shouldBe List.of(1, 2, 3, 4, 5, 6)

            concat2(
                List.of(
                    List.of(1, 2, 3),
                    List.of(4, 5, 6)
                )
            ) shouldBe List.of(1, 2, 3, 4, 5, 6)
        }
    }
})
