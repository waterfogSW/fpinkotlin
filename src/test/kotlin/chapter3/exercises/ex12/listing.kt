package chapter3.exercises.ex12

import chapter3.List
import chapter3.foldLeft
import chapter3.foldRight
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun <A, B> foldLeftR(
    xs: List<A>,
    z: B,
    f: (B, A) -> B
): B = foldRight(xs, z) { a, b -> f(b, a) }

fun <A, B> foldRightL(
    xs: List<A>,
    z: B,
    f: (A, B) -> B
): B = foldLeft(xs, z) { a, b -> f(b, a) }

class Exercise12 : WordSpec({
    "list foldLeftR" should {
        "implement foldLeft functionality using foldRight" {
            foldLeftR(
                List.of(1, 2, 3, 4, 5),
                0,
                { x, y -> x + y }) shouldBe 15
        }
    }

    "list foldRightL" should {
        "implement foldRight functionality using foldLeft" {
            foldRightL(
                List.of(1, 2, 3, 4, 5),
                0,
                { x, y -> x + y }) shouldBe 15
        }
    }
})
