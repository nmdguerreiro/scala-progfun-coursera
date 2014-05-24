package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) {
      1
    }
    else {
      pascal(c-1, r-1) + pascal(c, r-1)
    }
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def balanceInner(charsRemaining: List[Char], imbalance: Int): Boolean = {
      if (charsRemaining.isEmpty) {
        imbalance == 0;
      }
      else {
	      if (charsRemaining.head == '(') {
	        balanceInner(charsRemaining.tail, imbalance + 1)
	      }
	      else if (charsRemaining.head == ')') {
	        imbalance > 0 && balanceInner(charsRemaining.tail, imbalance -1); 
	      }
	      else {
	        balanceInner(charsRemaining.tail, imbalance)
	      }
      }
    }
    
    balanceInner(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def countChangeInner(curCoins: Int, remainingMoney: Int, outstandingCoins: List[Int]): Int = {
         if (remainingMoney == 0) {
           1
         }
         else if (remainingMoney < 0 || money == 0 || outstandingCoins.isEmpty) {
	       0
	     }
	     else {
	       countChangeInner(curCoins + 1, remainingMoney - outstandingCoins.head, outstandingCoins) +
	    		  countChangeInner(0, remainingMoney, outstandingCoins.tail);
         }
    }
    
    countChangeInner(0, money, coins);
  }
}
