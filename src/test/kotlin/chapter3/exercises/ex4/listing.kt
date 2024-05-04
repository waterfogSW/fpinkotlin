package chapter3.exercises.ex4

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

fun <A> dropWhile(
    l: List<A>,
    f: (A) -> Boolean
): List<A> {

    tailrec fun go(cur: List<A>): List<A> {
        return when (cur) {
            is Cons -> if (f(cur.head)) go(cur.tail) else cur
            is Nil -> cur
        }
    }

    return go(l)
}

class Exercise4 : WordSpec({

    "list dropWhile" should {
        "drop elements until predicate is no longer satisfied" {
            dropWhile(
                List.of(1, 2, 3, 4, 5)
            ) { it < 4 } shouldBe List.of(4, 5)
        }

        "drop no elements if predicate never satisfied" {
            dropWhile(
                List.of(1, 2, 3, 4, 5)
            ) { it == 100 } shouldBe List.of(1, 2, 3, 4, 5)
        }

        "drop all elements if predicate always satisfied" {
            dropWhile(
                List.of(1, 2, 3, 4, 5)
            ) { it < 100 } shouldBe List.of()
        }

        "return Nil if input is empty" {
            dropWhile(List.empty<Int>()) { it < 100 } shouldBe Nil
        }
    }
})
