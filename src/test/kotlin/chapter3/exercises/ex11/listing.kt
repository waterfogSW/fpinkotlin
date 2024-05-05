package chapter3.exercises.ex11

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import chapter3.foldLeft
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec


// Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil))))))
// Cons(5, Cons(4, Cons(3, Cons(2, Cons(1, Nil))))))

/**
 * cons(1, cons(2, nil))
 * cons(2, cons(1, nul))
 *
 * xs.head = 1
 * xs.tail = list(2)
 *
 * xs.head = 2
 * xs.tail = list(1)
 */

fun <A> reverse(xs: List<A>): List<A> =
    foldLeft(xs, Nil as List<A>) { b, a -> Cons(a, b) }

class Exercise11 : WordSpec({
    "list reverse" should {
        "reverse list elements" {
            reverse(List.of(1, 2, 3, 4, 5)) shouldBe
                    List.of(5, 4, 3, 2, 1)
        }
    }
})
