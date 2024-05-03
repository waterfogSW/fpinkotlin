package chapter2.exercises.ex2

import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import kotlinx.collections.immutable.persistentListOf

val <T> List<T>.tail: List<T>
    get() = drop(1)

val <T> List<T>.head: T
    get() = first()

fun <A> isSorted(
    aa: List<A>,
    order: (A, A) -> Boolean
): Boolean {
    tailrec fun go(
        head: A,
        tail: List<A>
    ): Boolean {
        if (tail.isEmpty()) return true
        if (order(head, tail.head).not()) return false
        return go(tail.head, tail.tail)
    }

    return aa.isEmpty() || go(aa.head, aa.tail)
}

class Exercise2 : WordSpec({

    "isSorted" should {
        """detect ordering of a list of correctly ordered Ints based
            on an ordering HOF""" {
            isSorted(
                persistentListOf(1, 2, 3)
            ) { a, b -> b > a } shouldBe true
        }
        """detect ordering of a list of incorrectly ordered Ints
            based on an ordering HOF""" {
            isSorted(
                persistentListOf(1, 3, 2)
            ) { a, b -> b > a } shouldBe false
        }
        """verify ordering of a list of correctly ordered Strings
            based on an ordering HOF""" {
            isSorted(
                persistentListOf("a", "b", "c")
            ) { a, b -> b > a } shouldBe true
        }
        """verify ordering of a list of incorrectly ordered Strings
            based on an ordering HOF""" {
            isSorted(
                persistentListOf("a", "z", "w")
            ) { a, b -> b > a } shouldBe false
        }
        "return true for an empty list" {
            isSorted(persistentListOf<Int>()) { a, b ->
                b > a
            } shouldBe true
        }
    }
})
