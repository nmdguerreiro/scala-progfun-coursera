package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }	
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }
  
  test("create code tree") {
    val chars = string2Chars("aaaaaaaabbbcdefgh");
    val codeTree = createCodeTree(chars)
    assert(codeTree === 
      Fork(
          Leaf('a',8),
          Fork(
              Fork(
                  Fork(
                      Leaf('h',1),
                      Leaf('g',1),
                      List('h', 'g'), 
                      2),
                  Fork(
                      Leaf('f',1),
                      Leaf('e',1),
                      List('f', 'e'),
                      2),
                  List('h', 'g', 'f', 'e'),
                  4),
              Fork(
                  Fork(
                      Leaf('d',1),
                      Leaf('c',1),
                      List('d', 'c'),
                      2),
                  Leaf('b',3),
                  List('d', 'c', 'b'),
                  5),
              List('h', 'g', 'f', 'e', 'd', 'c', 'b'),
              9),
           List('a', 'h', 'g', 'f', 'e', 'd', 'c', 'b'),
           17)
      )
  }

  test("decode secret") {
    new TestTrees {
      assert(decodedSecret === "huffmanestcool".toList)
    }
  }
  
  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }
  
  test("decode and quick encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
    }
  }
}
