package u06lab.code

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import u06lab.code.TicTacToe.{Board, Mark, O, X, placeAnyMark, printBoards}

class TicTacToeTest {

  @Test def testPlaceAnyMark0(){
   var seq: List[Board] = List()
   val board : Board = List();
   seq = board.appended(Mark(2, 2, X)):: seq
   seq = board.appended(Mark(2, 1, X)):: seq
   seq = board.appended(Mark(2, 0, X)):: seq

   seq = board.appended(Mark(1, 2, X)):: seq
   seq = board.appended(Mark(1, 1, X)):: seq
   seq = board.appended(Mark(1, 0, X)):: seq

   seq = board.appended(Mark(0, 2, X)) :: seq
   seq = board.appended(Mark(0, 1, X)) :: seq
   seq = board.appended(Mark(0, 0, X)) :: seq

   val result = placeAnyMark(List(),X)
   println("Actual "); printBoards(result); println("Expected "); printBoards(seq); println()
   assertEquals(seq, result)

    //... ... ..X ... ... .X. ... ... X..
    //... ..X ... ... .X. ... ... X.. ...
    //..X ... ... .X. ... ... X.. ... ...
  }

 @Test def testPlaceAnyMark1(){
  var seq: List[Board] = List()
  val board : Board = List(Mark(0, 0, O));
  seq = board.appended(Mark(2, 2, X)):: seq
  seq = board.appended(Mark(2, 1, X)):: seq
  seq = board.appended(Mark(2, 0, X)):: seq

  seq = board.appended(Mark(1, 2, X)):: seq
  seq = board.appended(Mark(1, 1, X)):: seq
  seq = board.appended(Mark(1, 0, X)):: seq

  seq = board.appended(Mark(0, 2, X)) :: seq
  seq = board.appended(Mark(0, 1, X)) :: seq

  val result = placeAnyMark(List(Mark(0,0,O)),X)
  println("Actual "); printBoards(result); println("Expected "); printBoards(seq); println()
  assertEquals(seq, result)

  //O.. O.. O.X O.. O.. OX. O.. O..
  //... ..X ... ... .X. ... ... X..
  //..X ... ... .X. ... ... X.. ...

 }

 @Test def testPlaceAnyMark2(){

  var seq: List[Board] = List()
  val board : Board = List(Mark(0, 0, X), Mark(0, 1, O));
  seq = board.appended(Mark(2, 2, X)):: seq
  seq = board.appended(Mark(2, 1, X)):: seq
  seq = board.appended(Mark(2, 0, X)):: seq

  seq = board.appended(Mark(1, 2, X)):: seq
  seq = board.appended(Mark(1, 1, X)):: seq
  seq = board.appended(Mark(1, 0, X)):: seq

  seq = board.appended(Mark(0, 2, X)) :: seq

  val result = placeAnyMark(List(Mark(0, 0, X), Mark(0, 1, O)), X)
  println("Actual "); printBoards(result); println("Expected "); printBoards(seq); println()
  assertEquals(seq, result)

//  X.. X.. X.X X.. X.. XX. X..
//  O.. O.X O.. O.. OX. O.. O..
//  ..X ... ... .X. ... ... X..
 }
}
