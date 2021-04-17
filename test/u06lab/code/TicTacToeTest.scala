package u06lab.code

import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test
import u06lab.code.TicTacToe._

class TicTacToeTest {

  @Test def testPlaceAnyMark0() {
    var seq: List[Board] = List()
    val board: Board = List();
    seq = board.appended(Mark(2, 2, X)) :: seq
    seq = board.appended(Mark(2, 1, X)) :: seq
    seq = board.appended(Mark(2, 0, X)) :: seq

    seq = board.appended(Mark(1, 2, X)) :: seq
    seq = board.appended(Mark(1, 1, X)) :: seq
    seq = board.appended(Mark(1, 0, X)) :: seq

    seq = board.appended(Mark(0, 2, X)) :: seq
    seq = board.appended(Mark(0, 1, X)) :: seq
    seq = board.appended(Mark(0, 0, X)) :: seq

    val result = placeAnyMark(List(), X)
    println("Actual ");
    printBoards(result);
    println("Expected ");
    printBoards(seq);
    println()
    assertEquals(seq, result)

    //... ... ..X ... ... .X. ... ... X..
    //... ..X ... ... .X. ... ... X.. ...
    //..X ... ... .X. ... ... X.. ... ...
  }

  @Test def testPlaceAnyMark1() {
    var seq: List[Board] = List()
    val board: Board = List(Mark(0, 0, O));
    seq = board.appended(Mark(2, 2, X)) :: seq
    seq = board.appended(Mark(2, 1, X)) :: seq
    seq = board.appended(Mark(2, 0, X)) :: seq

    seq = board.appended(Mark(1, 2, X)) :: seq
    seq = board.appended(Mark(1, 1, X)) :: seq
    seq = board.appended(Mark(1, 0, X)) :: seq

    seq = board.appended(Mark(0, 2, X)) :: seq
    seq = board.appended(Mark(0, 1, X)) :: seq

    val result = placeAnyMark(List(Mark(0, 0, O)), X)
    println("Actual ");
    printBoards(result);
    println("Expected ");
    printBoards(seq);
    println()
    assertEquals(seq, result)

    //O.. O.. O.X O.. O.. OX. O.. O..
    //... ..X ... ... .X. ... ... X..
    //..X ... ... .X. ... ... X.. ...

  }

  @Test def testPlaceAnyMark2() {

    var seq: List[Board] = List()
    val board: Board = List(Mark(0, 0, X), Mark(0, 1, O));
    seq = board.appended(Mark(2, 2, X)) :: seq
    seq = board.appended(Mark(2, 1, X)) :: seq
    seq = board.appended(Mark(2, 0, X)) :: seq

    seq = board.appended(Mark(1, 2, X)) :: seq
    seq = board.appended(Mark(1, 1, X)) :: seq
    seq = board.appended(Mark(1, 0, X)) :: seq

    seq = board.appended(Mark(0, 2, X)) :: seq

    val result = placeAnyMark(List(Mark(0, 0, X), Mark(0, 1, O)), X)
    println("Actual ");
    printBoards(result);
    println("Expected ");
    printBoards(seq);
    println()
    assertEquals(seq, result)

    //  X.. X.. X.X X.. X.. XX. X..
    //  O.. O.X O.. O.. OX. O.. O..
    //  ..X ... ... .X. ... ... X..
  }

  @Test def testPlaceAnyMark4() {

    var seq: List[Board] = List()
    val board: Board = List(Mark(0, 0, X), Mark(0, 1, O), Mark(1, 2, X), Mark(2, 1, O));
    seq = board.appended(Mark(2, 2, X)) :: seq
    seq = board.appended(Mark(2, 0, X)) :: seq

    seq = board.appended(Mark(1, 1, X)) :: seq
    seq = board.appended(Mark(1, 0, X)) :: seq

    seq = board.appended(Mark(0, 2, X)) :: seq

    val result = placeAnyMark(List(Mark(0, 0, X), Mark(0, 1, O), Mark(1, 2, X), Mark(2, 1, O)), X)
    println("Actual ");
    printBoards(result);
    println("Expected ");
    printBoards(seq);
    println()
    assertEquals(seq, result)

//      X.. X.X X.. XX. X..
//      O.O O.O OXO O.O O.O
//      .XX .X. .X. .X. XX.
  }

  @Test def testComputeAnyGame(): Unit = {
    val player: Player = O

    assertEquals(1, computeAnyGame(player, 0).size)
    assertEquals(9, computeAnyGame(player, 1).size)
    assertEquals(72, computeAnyGame(player, 2).size)
    assertEquals(504, computeAnyGame(player, 3).size)
    assertEquals(3024, computeAnyGame(player, 4).size)
    assertEquals(15120, computeAnyGame(player, 5).size)
    assertEquals(60480, computeAnyGame(player, 6).size)
    assertEquals(181440, computeAnyGame(player, 7).size)
    assertEquals(362880, computeAnyGame(player, 8).size)
    assertEquals(362880, computeAnyGame(player, 9).size)
    assertThrows(classOf[IllegalArgumentException], () => computeAnyGame(player, 12).size)
  }

  @Test def testComputeAnyGameMove4(): Unit = {

    val player: Player = O
    val other: Player = player.other

    val games = computeAnyGame(player, 4)

    assertEquals(3024, games.size)

    val expected1: Game = List(
      List(Mark(0,0, other), Mark(0,1, player), Mark(0, 2, other), Mark(1, 0, player)),
      List(Mark(0,0, other), Mark(0,1, player), Mark(0, 2, other)),
      List(Mark(0,0, other), Mark(0,1, player)),
      List(Mark(0,0, other)),
      List(),
    )
    //... X.. X.. X.. XO.
    //... ... O.. O.. O..
    //... ... ... X.. X..
    assertTrue(games.contains(expected1))

    val expected2 = List(
      List(Mark(1,2, other), Mark(0,1, player), Mark(0, 0, other), Mark(2, 0, player)),
      List(Mark(1,2, other), Mark(0,1, player), Mark(0, 0, other)),
      List(Mark(1,2, other), Mark(0,1, player)),
      List(Mark(1,2, other)),
      List(),
    )
    //... ... .O. XO. XOO
    //... ... ... ... ...
    //... .X. .X. .X. .X.
    assertTrue(games.contains(expected2))
  }

}
