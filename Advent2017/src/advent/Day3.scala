package advent

import scala.io.StdIn
import scala.collection.mutable.HashMap
import scala.util.control.Breaks._

object Day3 extends App {
  // 0-based
  val input = StdIn.readInt() - 1
  // (2n+1)^2 elements inside ring n
  val ring = Math.floor((Math.floor(Math.sqrt(input)) + 1) / 2)
  // Remove elements inside the ring to find position on the circumference
  val offset = input - Math.pow(2 * ring - 1, 2)
  // Double symmetry, both amongst the four edges and any side of an edge
  val sideOffset = Math.abs(offset % (ring * 2) - ring + 1)
  // Side offset plus the ring give the total distance to travel
  val p1 = ring + sideOffset

  val map = new HashMap[(Int, Int), Int]()
  def getSumNeighbors(x: Int, y: Int) =
    (map.get((x - 1, y))
      ++ map.get((x + 1, y))
      ++ map.get((x, y - 1))
      ++ map.get((x, y + 1))
      ++ map.get((x - 1, y - 1))
      ++ map.get((x - 1, y + 1))
      ++ map.get((x + 1, y - 1))
      ++ map.get((x + 1, y + 1))).foldLeft(0)(_ + _)
  // Seed the map
  map.put((0, 0), 1)

  val p2 = Stream.from(1).map(ring => {
    // The first position on a ring is one above the bottom right corner which has position (ring, -ring)
    var x = ring;
    var y = -ring

    for (direction <- Range(0, 4); step <- Range(0, 2 * ring)) yield {
      direction match {
        case 0 => y = y + 1
        case 1 => x = x - 1
        case 2 => y = y - 1
        case 3 => x = x + 1
      }
      val sumNeighbors = getSumNeighbors(x, y)
      map.put((x, y), sumNeighbors)
      sumNeighbors
    }
  }).flatten.find(_ > input + 1).get

  println(p1)
  println(p2)
}
