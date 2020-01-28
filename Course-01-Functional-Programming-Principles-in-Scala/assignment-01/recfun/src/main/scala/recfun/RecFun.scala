package recfun

object RecFun extends RecFunInterface {

  def main(args: Array[String]): Unit = {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(s"${pascal(col, row)} ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int =
    if (c == 0 || c == r)
      1
    else
      pascal(c - 1, r - 1) + pascal(c, r - 1)

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    if (chars.isEmpty)
      true
    else if (chars.head == '(')
      check_inner(chars.tail, 1)
    else if (chars.head == ')')
      false
    else balance(chars.tail)
  }

  def check_inner(chars: List[Char], counter: Int): Boolean = {
    if (chars.isEmpty && counter != 0)
      false
    else if (chars.head ==')' && counter < 1)
      false
    else if (chars.isEmpty && counter == 0)
      true
    else if (!chars.contains('(') || !chars.contains(')') && counter == 0)
      true
    else if (chars.head == ')')
      check_inner(chars.tail, counter - 1)
    else if (chars.head == '(')
      check_inner(chars.tail, counter + 1)
    else check_inner(chars.tail, counter)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int =
    if (money < 0 || coins.isEmpty)
      0
    else if (money == 0)
      1
    else countChange(money - coins.head, coins) + countChange(money, coins.tail)
}
