package chapter5.exercises.ex2

import chapter3.List
import chapter3.Nil
import chapter5.Cons
import chapter5.Empty
import chapter5.Stream
import chapter5.toList
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

class Exercise2 : WordSpec({

    //tag::take[]
    fun <A> Stream<A>.take(n: Int): Stream<A> {
        fun go(s: Stream<A>, cnt: Int) : Stream<A> {
            return if(cnt == 0) {
                Empty
            } else {
                when(s) {
                    is Cons -> Cons(s.head) { go(s.tail.invoke(), cnt - 1)}
                    is Empty -> Empty
                }
            }
        }

        return go(this, n)
    }


    //tag::drop[]
    fun <A> Stream<A>.drop(n: Int): Stream<A> {
        tailrec fun go(s: Stream<A>, cnt: Int) : Stream<A> {
            return if(cnt == 0) {
                s
            } else {
                when(s) {
                    is Cons -> go(s.tail.invoke(), cnt - 1)
                    is Empty -> Empty
                }
            }
        }

        return go(this, n)
    }
    //end::drop[]

    "Stream.take(n)" should {
        "return the first n elements of a stream" {
            val s = Stream.of(1, 2, 3, 4, 5)
            s.take(3).toList() shouldBe List.of(1, 2, 3)
        }

        "return all the elements if the stream is exhausted" {
            val s = Stream.of(1, 2, 3)
            s.take(5).toList() shouldBe List.of(1, 2, 3)
        }

        "return an empty stream if the stream is empty" {
            val s = Stream.empty<Int>()
            s.take(3).toList() shouldBe Nil
        }
    }

    "Stream.drop(n)" should {
        "return the remaining elements of a stream" {
            val s = Stream.of(1, 2, 3, 4, 5)
            s.drop(3).toList() shouldBe List.of(4, 5)
        }

        "return empty if the stream is exhausted" {
            val s = Stream.of(1, 2, 3)
            s.drop(5).toList() shouldBe Nil
        }

        "return empty if the stream is empty" {
            val s = Stream.empty<Int>()
            s.drop(5).toList() shouldBe Nil
        }
    }
})
