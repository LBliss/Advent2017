package advent

import scala.io.StdIn

object Day2 extends App {
  val lines = Iterator.continually(StdIn.readLine()).takeWhile(_ != "").map(_.split('\t').map(_.toInt)).toList

  val p1 = lines.map(_.foldLeft((Integer.MIN_VALUE, Integer.MAX_VALUE))((p, v) => (if (p._1 < v) v else p._1, if (p._2 > v) v else p._2))).map(p => p._1 - p._2).reduce(_ + _)
  val p2 = lines.map(l => l.map(x => l.filter(_ != x).find(_ % x == 0).map(_ / x))).flatten.flatten.reduce(_ + _)
  //val p2 = lines.map(nums => for (x <- nums; y <- nums if (x != y)) yield if (x % y == 0) x / y else if (y % x == 0) y / x else 0).flatten.reduce(_ + _) / 2

  println(p1)
  println(p2)
}
