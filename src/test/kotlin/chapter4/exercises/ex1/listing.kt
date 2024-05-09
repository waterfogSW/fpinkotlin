package chapter4.exercises.ex1

import chapter4.None
import chapter4.Option
import chapter4.Some
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

//tag::init[]
fun <A, B> Option<A>.map(f: (A) -> B): Option<B> =
    when(this) {
        is None -> None
        is Some -> Some(f(this.get))
    }

fun <A, B> Option<A>.flatMap(f: (A) -> Option<B>): Option<B> =
    when(this) {
        is None -> None
        is Some -> f(this.get)
    }

fun <A> Option<A>.getOrElse(default: () -> A): A =
    when(this) {
        is None -> default.invoke()
        is Some -> this.get
    }

fun <A> Option<A>.orElse(ob: () -> Option<A>): Option<A> =
    when(this) {
        is None -> ob.invoke()
        is Some -> Some(this.get)
    }

fun <A> Option<A>.filter(f: (A) -> Boolean): Option<A> =
    when(this) {
        is None -> None
        is Some -> if(f(this.get)) this else None
    }

fun <A, B> Option<A>.flatMap_2(
    f: (A) -> Option<B>
): Option<B> =
    when(this) {
        is None -> None
        is Some -> f(this.get)
    }

fun <A> Option<A>.orElse_2(
    ob: () -> Option<A>
): Option<A> =
    when(this) {
        is None -> ob.invoke()
        is Some -> this
    }

fun <A> Option<A>.filter_2(
    f: (A) -> Boolean
): Option<A> =
    when(this) {
        is None -> None
        is Some -> if(f(this.get)) Some(this.get) else None
    }

class Exercise1 : WordSpec({

    val none = Option.empty<Int>()

    val some = Some(10)

    "option map" should {
        "transform an option of some value" {
            some.map { it * 2 } shouldBe Some(20)
        }
        "pass over an option of none" {
            none.map { it * 10 } shouldBe None
        }
    }

    "option flatMap" should {
        """apply a function yielding an option to an
            option of some value""" {
            some.flatMap { a ->
                Some(a.toString())
            } shouldBe Some("10")

            some.flatMap_2 { a ->
                Some(a.toString())
            } shouldBe Some("10")
        }
        "pass over an option of none" {
            none.flatMap { a ->
                Some(a.toString())
            } shouldBe None

            none.flatMap_2 { a ->
                Some(a.toString())
            } shouldBe None
        }
    }

    "option getOrElse" should {
        "extract the value of some option" {
            some.getOrElse { 0 } shouldBe 10
        }
        "return a default value if the option is none" {
            none.getOrElse { 10 } shouldBe 10
        }
    }

    "option orElse" should {
        "return the option if the option is some" {
            some.orElse { Some(20) } shouldBe some
            some.orElse_2 { Some(20) } shouldBe some
        }
        "return a default option if the option is none" {
            none.orElse { Some(20) } shouldBe Some(20)
            none.orElse_2 { Some(20) } shouldBe Some(20)
        }
    }

    "option filter" should {
        "return some option if the predicate is met" {
            some.filter { it > 0 } shouldBe some
            some.filter_2 { it > 0 } shouldBe some
        }
        "return a none option if the predicate is not met" {
            some.filter { it < 0 } shouldBe None
            some.filter_2 { it < 0 } shouldBe None
        }
    }
})
