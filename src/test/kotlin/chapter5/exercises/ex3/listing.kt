package chapter5.exercises.ex3

import chapter3.List
import chapter5.Cons
import chapter5.Empty
import chapter5.Stream
import chapter5.toList
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

//TODO: Enable tests by removing `!` prefix
class Exercise3 : WordSpec({

    //tag::init[]
    fun <A> Stream<A>.takeWhile(p: (A) -> Boolean): Stream<A> {
        tailrec fun go(s: Stream<A>): Stream<A> =
            when(s) {
                is Cons -> if(p(s.head.invoke())) {
                    Cons(s.head) { go(s.tail.invoke()) }
                } else {
                    Empty
                }
                is Empty -> Empty
            }

        return go(this)
    }
    //end::init[]

    "Stream.takeWhile" should {
        "return elements while the predicate evaluates true" {
            val s = Stream.of(1, 2, 3, 4, 5)
            s.takeWhile { it < 4 }.toList() shouldBe
                List.of(1, 2, 3)
        }
        "stop returning once predicate evaluates false" {
            val s = Stream.of(1, 2, 3, 4, 5, 4, 3, 2, 1)
            s.takeWhile { it < 4 }.toList() shouldBe
                List.of(1, 2, 3)
        }
        "return all elements if predicate always evaluates true" {
            val s = Stream.of(1, 2, 3, 4, 5)
            s.takeWhile { true }.toList() shouldBe
                List.of(1, 2, 3, 4, 5)
        }
        "return empty if predicate always evaluates false" {
            val s = Stream.of(1, 2, 3, 4, 5)
            s.takeWhile { false }.toList() shouldBe
                List.empty()
        }
    }
})
