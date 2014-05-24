object scala {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(220); 
  
  def mapReduce(func: Int => Int, combiner: (Int, Int) => Int, unitValue: Int)(a: Int, b: Int): Int = {
  	if (a > b) unitValue
  	else combiner(func(a), mapReduce(func, combiner, unitValue)(a+1,b))
  };System.out.println("""mapReduce: (func: Int => Int, combiner: (Int, Int) => Int, unitValue: Int)(a: Int, b: Int)Int""");$skip(147); 
                                                  
  def sum(func: Int => Int) (a: Int, b: Int) = mapReduce(func, (x: Int, y: Int) => x+y, 0)(a,b);System.out.println("""sum: (func: Int => Int)(a: Int, b: Int)Int""");$skip(151); 
                                                  
  def product(func: Int => Int) (a: Int, b: Int) = mapReduce(func, (x: Int, y: Int) => x*y, 1)(a,b);System.out.println("""product: (func: Int => Int)(a: Int, b: Int)Int""");$skip(26); val res$0 = 
 
  
  sum(x => x) (1, 3);System.out.println("""res0: Int = """ + $show(res$0));$skip(29); val res$1 = 
  product(x => x * x) (3, 4);System.out.println("""res1: Int = """ + $show(res$1));$skip(54); 
  
  
  def factorial(x: Int) = product(x => x)(1, x);System.out.println("""factorial: (x: Int)Int""");$skip(18); val res$2 = 
  
  factorial(5)

  class Rational(x: Int, y: Int) {
  	var numer = x / g;
  	var denom = y / g;
 
  	def neg = new Rational(-numer, denom)
  	def sub(that: Rational) = add(that.neg)
  	def add(that: Rational) = new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  	def mul(that: Rational) = new Rational(numer * that.numer, denom * that.denom)
  	
  	private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
  	private def g = gcd(x, y)
  	
  	override def toString = numer + "/" + denom
  };System.out.println("""res2: Int = """ + $show(res$2));$skip(541); val res$3 = 
  
  new Rational(2,3).neg;System.out.println("""res3: scala.Rational = """ + $show(res$3));$skip(43); val res$4 = 
  new Rational(2,3).add(new Rational(1,3));System.out.println("""res4: scala.Rational = """ + $show(res$4));$skip(43); val res$5 = 
  new Rational(2,3).sub(new Rational(2,3));System.out.println("""res5: scala.Rational = """ + $show(res$5));$skip(31); 
  
  val x = new Rational(1,3);System.out.println("""x  : scala.Rational = """ + $show(x ));$skip(28); 
  val y = new Rational(5,7);System.out.println("""y  : scala.Rational = """ + $show(y ));$skip(28); 
  val z = new Rational(3,2);System.out.println("""z  : scala.Rational = """ + $show(z ));$skip(18); val res$6 = 
  x.add(y).mul(z);System.out.println("""res6: scala.Rational = """ + $show(res$6));$skip(18); val res$7 = 
  x.sub(y).sub(z);System.out.println("""res7: scala.Rational = """ + $show(res$7));$skip(11); val res$8 = 
  y.add(y);System.out.println("""res8: scala.Rational = """ + $show(res$8))}
}
