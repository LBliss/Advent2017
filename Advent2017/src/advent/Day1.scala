package advent

import scala.io.StdIn

object Day1 extends App {
  val input = StdIn.readLine()

  val sumP1 = (input + input.charAt(0)).zip("0" + input).filter(p => p._1 == p._2).foldLeft(0)(_ + _._1 - '0')
  val sumP2 = input.zip(input.drop(input.length / 2) + input.take(input.length / 2)).filter(p => p._1 == p._2).foldLeft(0)(_ + _._1 - '0')

  println(sumP1)
  println(sumP2)
}
