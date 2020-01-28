# Functional Programming Principles in Scala

## Tools and Setup

Note: The following sections contain information about all the tools you will need to complete the
course. If you have problems following trough it or prefer watching videos, check the Tools Setup
videos in the Getting Started Section.

In order to work on the programming assignments, you need to have the following tools installed on
your machine:

Oracle JDK, the Java Development Kit, version 1.8 or higher. Check you have the right version by
typing in the console:


```bash
java -version
```
Scala Build Tool (sbt), a build tool for Scala, version 1.x, or newer.
IntelliJ IDEA, the Scala IDE for Eclipse, Scala Metals, or another IDE of your choice.  Please
follow the instructions on this page carefully.

### Installing the JDK

#### Linux

Ubuntu, Debian: To install the JDK using apt-get, execute the following
command in a terminal sudo apt-get install openjdk-11-jdk Fedora, Oracle, Red Had: To install the
JDK using yum, execute the following command in a terminal su -c "yum install java-11-openjdk-devel"
Manual Installation: To install the JDK manually on a Linux system, follow these steps:

1. Download the .tar.gz archive from the Oracle website

2. Unpack the downloaded archive to a directory of your choice

3. Add the bin/ directory of the extracted JDK to the PATH environment variable. Open the file
   ~/.bashrc in an editor (create it if it doesn't exist) and add the following line:


```bash
export PATH="/PATH/TO/YOUR/jdk11-VERSION/bin:$PATH"
```
If you are using another shell, add that line in the corresponding configuration file (e.g.
~/.zshrc for zsh).

Verify your setup: Open a new terminal (to apply the changed ~/.bashrc in case you did the manual
installation) and type java -version. If you have problems installing the JDK, ask for help on the
forums.

#### Mac OS X

Mac OS X either comes with a pre-installed JDK, or installs it automatically.

To verify your JDK installation, open the Terminal application in /Applications/Utilities/ and type
java -version. If the JDK is not yet installed, the system will ask you if you would like to
download and install it. Make sure you install Java 1.8, or higher.

#### Windows

Download the JDK installer for Windows from the Oracle website.  Run the installer.  Add the
bin directory of the installed JDK to the PATH environment variable, as described here.  To verify
the JDK installation, open the Command Prompt and type `java -version`. If you run into any problem,
go to the official Oracle documentation.

#### Installing sbt

Follow the instructions for your platform to get it running.

This course requires sbt version >1.x. If you have previously installed sbt 0.12.x, you need to
uninstall it and install a newer version. sbt 1.x can be used for projects and other courses
requiring sbt 0.12.x, but not the other way around. If in doubt, you can check your currently
installed sbt like this: in an arbitrary directory that is not a programming assignment or otherwise
an sbt project, run:

```bash
$ sbt about
```

You should see something like this:


```bash
This is sbt 1.2.8
```
If the sbt command is not found, you need to install sbt. In this case, go to the official
instructions for your platform.

## Cheat Sheet

This cheat sheet originated from the #progfun forum, credits to Laurent Poulain. We copied it and
changed or added a few things. There are certainly a lot of things that can be improved! If you
would like to contribute, you have two options:

Click the "Edit" button on this file on
GitHub:https://github.com/lrytz/progfun-wiki/blob/gh-pages/CheatSheet.md You can submit a pull
request directly from there without checking out the git repository to your local machine.  Fork the
repository https://github.com/lrytz/progfun-wiki and check it out locally. To preview your changes,
you need jekyll. Navigate to your checkout and invoke jekyll --auto --server, then open the page
http://localhost:4000/CheatSheet.html.

#### Evaluation Rules

Call by value: evaluates the function arguments before calling the function Call by
name: evaluates the function first, and then evaluates the arguments if need be

```bash
def example = 2      // evaluated when called
val example = 2      // evaluated immediately
lazy val example = 2 // evaluated once when needed

def square(x: Double)    // call by value
def square(x: => Double) // call by name
def myFct(bindings: Int*) = { ... } // bindings is a sequence of int, containing a varying # of arguments
```

#### Higher order

functions These are functions that take a function as a parameter or return functions.


```bash
// sum() returns a function that takes two integers and returns an integer
def sum(f: Int => Int): (Int, Int) => Int = {
  def sumf(a: Int, b: Int): Int = {...}
  sumf
}

// same as above. Its type is (Int => Int) => (Int, Int) => Int
def sum(f: Int => Int)(a: Int, b: Int): Int = { ... }

// Called like this
sum((x: Int) => x * x * x)          // Anonymous function, i.e. does not have a name
sum(x => x * x * x)                 // Same anonymous function with type inferred

def cube(x: Int) = x * x * x
sum(x => x * x * x)(1, 10) // sum of cubes from 1 to 10
sum(cube)(1, 10)           // same as above

```
#### Currying

Converting a function with multiple arguments into a function with a single argument
that returns another function.

```bash
def f(a: Int, b: Int): Int // uncurried version (type is (Int, Int) => Int)
def f(a: Int)(b: Int): Int // curried version (type is Int => Int => Int)
```
#### Classes

```bash
class MyClass(x: Int, y: Int) {           // Defines a new type MyClass with a constructor
  require(y > 0, "y must be positive")    // precondition, triggering an IllegalArgumentException if not met
  def this (x: Int) = { ... }             // auxiliary constructor
  def nb1 = x                             // public method computed every time it is called
  def nb2 = y
  private def test(a: Int): Int = { ... } // private method
  val nb3 = x + y                         // computed only once
  override def toString =                 // overridden method
      member1 + ", " + member2
  }

new MyClass(1, 2) // creates a new object of type
```
this references the current object, assert(<condition>) issues AssertionError if
condition is not met. See scala.Predef for require, assume and assert.

#### Operators

myObject myMethod 1 is the same as calling myObject.myMethod(1)

Operator (i.e. function) names can be alphanumeric, symbolic (e.g. x1, *, +?%&, vector_++,
counter_=)

The precedence of an operator is determined by its first character, with the following increasing
order of priority:

 The associativity of an operator is determined by its last character: Right-associative if
ending with :, Left-associative otherwise.

Note that assignment operators have lowest precedence. (Read Scala Language Specification 2.9
sections 6.12.3, 6.12.4 for more info)

#### Class hierarchies


```bash
abstract class TopLevel {     // abstract class
  def method1(x: Int): Int   // abstract method
  def method2(x: Int): Int = { ... }
}

class Level1 extends TopLevel {
  def method1(x: Int): Int = { ... }
  override def method2(x: Int): Int = { ...} // TopLevel's method2 needs to be explicitly overridden
}

object MyObject extends TopLevel { ... } // defines a singleton object. No other instance can be created
```
To create an runnable application in Scala:

```bash
object Hello {
  def main(args: Array[String]) = println("Hello world")
}
```
or

```bash
object Hello extends App {
  println("Hello World")
}
```
#### Class Organization

Classes and objects are organized in packages (package myPackage).  They can
be referenced through import statements (import myPackage.MyClass, import myPackage._, import
myPackage.{MyClass1, MyClass2}, import myPackage.{MyClass1 => A}) They can also be directly
referenced in the code with the fully qualified name (new myPackage.MyClass1) All members of
packages scala and java.lang as well as all members of the object scala.Predef are automatically
imported.  Traits are similar to Java interfaces, except they can have non-abstract members:trait
Planar { ... } class Square extends Shape with Planar General object hierarchy:
- scala.Any base type of all types. Has methods hashCode and toString that can be overridden

- scala.AnyVal base type of all primitive types. (scala.Double, scala.Float, etc.)

- scala.AnyRef base type of all reference types. (alias of java.lang.Object, supertype of
    java.lang.String, scala.List, any user-defined class)

- scala.Null is a subtype of any scala.AnyRef (null is the only instance of type Null), and
    scala.Nothing is a subtype of any other type without any instance.

#### Type Parameters

Similar to C++ templates or Java generics. These can apply to classes, traits or
functions.

```bash
class MyClass[T](arg1: T) { ... }
new MyClass[Int](1)
new MyClass(1)   // the type is being inferred, i.e. determined based on the value arguments
```
It is possible to restrict the type being used, e.g.

```bash
def myFct[T <: TopLevel](arg: T): T = { ... } // T must derive from TopLevel or be TopLevel
def myFct[T >: Level1](arg: T): T = { ... }   // T must be a supertype of Level1
def myFct[T >: Level1 <: Top Level](arg: T): T = { ... }
```
Variance Given A <: B

If C[A] <: C[B], C is covariant

If C[A] >: C[B], C is contravariant

Otherwise C is nonvariant

```bash
class C[+A] { ... } // C is covariant
class C[-A] { ... } // C is contravariant
class C[A]  { ... } // C is nonvariant
```
For a function, if A2 <: A1 and B1 <: B2, then A1 => B1 <: A2 => B2.

Functions must be contravariant in their argument types and covariant in their result types, e.g.

```bash
trait Function1[-T, +U] {
  def apply(x: T): U
} // Variance check is OK because T is contravariant and U is covariant

class Array[+T] {
  def update(x: T)
} // variance checks fails
```
Find out more about variance in lecture 4.4.

#### Pattern Matching

Pattern matching is used for decomposing data structures:

```bash
unknownObject match {
  case MyClass(n) => ...
  case MyClass2(a, b) => ...
}
```
Here are a few example patterns

```bash
(someList: List[T]) match {
  case Nil => ...          // empty list
  case x :: Nil => ...     // list with only one element
  case List(x) => ...      // same as above
  case x :: xs => ...      // a list with at least one element. x is bound to the head,
                           // xs to the tail. xs could be Nil or some other list.
  case 1 :: 2 :: cs => ... // lists that starts with 1 and then 2
  case (x, y) :: ps => ... // a list where the head element is a pair
  case _ => ...            // default case if none of the above matches
}
```
The last example shows that every pattern consists of sub-patterns: it only matches lists with
at least one element, where that element is a pair. x and y are again patterns that could match only
specific types.

#### Options

Pattern matching can also be used for Option values. Some functions (like Map.get) return a
value of type Option[T] which is either a value of type Some[T] or the value None:

```bash
val myMap = Map("a" -> 42, "b" -> 43)
def getMapValue(s: String): String = {
  myMap get s match {
    case Some(nb) => "Value found: " + nb
    case None => "No value found"
  }
}
getMapValue("a")  // "Value found: 42"
getMapValue("c")  // "No value found"
```
Most of the times when you write a pattern match on an option value, the same expression can be
written more concisely using combinator methods of the Option class. For example, the function
getMapValue can be written as follows:

```bash
def getMapValue(s: String): String =
  myMap.get(s).map("Value found: " + _).getOrElse("No value found")
```

#### Pattern Matching in Anonymous Functions

Pattern matches are also used quite often in anonymous
functions:

```bash
val pairs: List[(Char, Int)] = ('a', 2) :: ('b', 3) :: Nil
val chars: List[Char] = pairs.map(p => p match {
  case (ch, num) => ch
})
```
Instead of p => p match { case ... }, you can simply write {case ...}, so the above example
becomes more concise:

```bash
val chars: List[Char] = pairs map {
  case (ch, num) => ch
}
```

#### Collections

Scala defines several collection classes:

Base Classes

- Iterable (collections you can iterate on)
- Seq (ordered sequences)
- Set
- Map (lookup data structure)

#### Immutable Collections

- List (linked list, provides fast sequential access)
- Stream (same as List, except that the tail is evaluated only on demand)
- Vector (array-like type, implemented as tree of blocks, provides fast random access)
- Range (ordered sequence of integers with equal spacing)
- String (Java type, implicitly converted to a character sequence, so you can treat every string like a Seq[Char])
- Map (collection that maps keys to values)
- Set (collection without duplicate elements)

#### Mutable Collections

- Array (Scala arrays are native JVM arrays at runtime, therefore they are very performant)
- Scala also has mutable maps and sets; these should only be used if there are performance
issues with immutable types

Examples

```bash
val fruitList = List("apples", "oranges", "pears")
// Alternative syntax for lists
val fruit = "apples" :: ("oranges" :: ("pears" :: Nil)) // parens optional, :: is right-associative
fruit.head   // "apples"
fruit.tail   // List("oranges", "pears")
val empty = List()
val empty = Nil

val nums = Vector("louis", "frank", "hiromi")
nums(1)                     // element at index 1, returns "frank", complexity O(log(n))
nums.updated(2, "helena")   // new vector with a different string at index 2, complexity O(log(n))

val fruitSet = Set("apple", "banana", "pear", "banana")
fruitSet.size    // returns 3: there are no duplicates, only one banana

val r: Range = 1 until 5 // 1, 2, 3, 4
val s: Range = 1 to 5    // 1, 2, 3, 4, 5
1 to 10 by 3  // 1, 4, 7, 10
6 to 1 by -2  // 6, 4, 2

val s = (1 to 6).toSet
s map (_ + 2) // adds 2 to each element of the set

val s = "Hello World"
s filter (c => c.isUpper) // returns "HW"; strings can be treated as Seq[Char]

// Operations on sequences
val xs = List(...)
xs.length   // number of elements, complexity O(n)
xs.last     // last element (exception if xs is empty), complexity O(n)
xs.init     // all elements of xs but the last (exception if xs is empty), complexity O(n)
xs take n   // first n elements of xs
xs drop n   // the rest of the collection after taking n elements
xs(n)       // the nth element of xs, complexity O(n)
xs ++ ys    // concatenation, complexity O(n)
xs.reverse  // reverse the order, complexity O(n)
xs updated(n, x)  // same list than xs, except at index n where it contains x, complexity O(n)
xs indexOf x      // the index of the first element equal to x (-1 otherwise)
xs contains x     // same as xs indexOf x >= 0
xs filter p       // returns a list of the elements that satisfy the predicate p
xs filterNot p    // filter with negated p
xs partition p    // same as (xs filter p, xs filterNot p)
xs takeWhile p    // the longest prefix consisting of elements that satisfy p
xs dropWhile p    // the remainder of the list after any leading element satisfying p have been removed
xs span p         // same as (xs takeWhile p, xs dropWhile p)

List(x1, ..., xn) reduceLeft op    // (...(x1 op x2) op x3) op ...) op xn
List(x1, ..., xn).foldLeft(z)(op)  // (...( z op x1) op x2) op ...) op xn
List(x1, ..., xn) reduceRight op   // x1 op (... (x{n-1} op xn) ...)
List(x1, ..., xn).foldRight(z)(op) // x1 op (... (    xn op  z) ...)

xs exists p    // true if there is at least one element for which predicate p is true
xs forall p    // true if p(x) is true for all elements
xs zip ys      // returns a list of pairs which groups elements with same index together
xs unzip       // opposite of zip: returns a pair of two lists
xs.flatMap f   // applies the function to all elements and concatenates the result
xs.sum         // sum of elements of the numeric collection
xs.product     // product of elements of the numeric collection
xs.max         // maximum of collection
xs.min         // minimum of collection
xs.flatten     // flattens a collection of collection into a single-level collection
xs groupBy f   // returns a map which points to a list of elements
xs distinct    // sequence of distinct entries (removes duplicates)

x +: xs  // creates a new collection with leading element x
xs :+ x  // creates a new collection with trailing element x

// Operations on maps
val myMap = Map("I" -> 1, "V" -> 5, "X" -> 10)  // create a map
myMap("I")      // => 1
myMap("A")      // => java.util.NoSuchElementException
myMap get "A"   // => None
myMap get "I"   // => Some(1)
myMap.updated("V", 15)  // returns a new map where "V" maps to 15 (entry is updated)
                        // if the key ("V" here) does not exist, a new entry is added

// Operations on Streams
val xs = Stream(1, 2, 3)
val xs = Stream.cons(1, Stream.cons(2, Stream.cons(3, Stream.empty))) // same as above
(1 to 1000).toStream // => Stream(1, ?)
x #:: xs // Same as Stream.cons(x, xs)
         // In the Stream's cons operator, the second parameter (the tail)
         // is defined as a "call by name" parameter.
         // Note that x::xs always produces a List
```
#### Pairs (similar for larger Tuples)

```bash
val pair = ("answer", 42)   // type: (String, Int)
val (label, value) = pair   // label = "answer", value = 42
pair._1 // "answer"
pair._2 // 42
```

#### Ordering

There is already a class in the standard library that represents orderings: scala.math.Ordering[T] which
contains comparison functions such as lt() and gt() for standard types. Types with a single natural
ordering should inherit from the trait scala.math.Ordered[T].

```bash
import math.Ordering

def msort[T](xs: List[T])(implicit ord: Ordering) = { ...}
msort(fruits)(Ordering.String)
msort(fruits)   // the compiler figures out the right ordering
```
#### For-Comprehensions

A for-comprehension is syntactic sugar for map, flatMap and filter
operations on collections.

The general form is for (s) yield e

- s is a sequence of generators and filters
- p <- e is a generator
- if f is a filter
- If there are several generators (equivalent of a nested loop), the last generator varies faster than the first
- You can use { s } instead of ( s ) if you want to use multiple lines without requiring semicolons
- e is an element of the resulting collection

Example 1

```bash
// list all combinations of numbers x and y where x is drawn from
// 1 to M and y is drawn from 1 to N
for (x <- 1 to M; y <- 1 to N)
  yield (x,y)
```
is equivalent to

```bash
(1 to M) flatMap (x => (1 to N) map (y => (x, y)))
```
#### Translation Rules

A for-expression looks like a traditional for loop but works differently
internally

for (x <- e1) yield e2 is translated to e1.map(x => e2)

for (x <- e1 if f) yield e2 is translated to for (x <- e1.filter(x => f)) yield e2

for (x <- e1; y <- e2) yield e3 is translated to e1.flatMap(x => for (y <- e2) yield e3)

This means you can use a for-comprehension for your own type, as long as you define map, flatMap and
filter.

Example 2

```bash
for {
  i <- 1 until n
  j <- 1 until i
  if isPrime(i + j)
} yield (i, j)
```

is equivalent to
```bash
for (i <- 1 until n; j <- 1 until i if isPrime(i + j))
    yield (i, j)
```

is equivalent to
```bash
(1 until n).flatMap(i => (1 until i).filter(j => isPrime(i + j)).map(j => (i, j)))
```

## SBT

We use sbt for building, testing, running and submitting assignments. This tutorial explains all sbt
commands that you will use during our class. The Tools Setup page explains how to install sbt.

The following page introduces:

Basic sbt tasks useful for any Scala developer. For more details about SBT or their commands, we
highly recommend you to check the SBT Reference Manual or this SBT tutorial made by the Scala
Community.
A way to submit your assignments to our Coursera graders with SBT.

### The Basics

#### Base or project's root directory

In sbt’s terminology, the “base or project's root directory” is the directory containing the
project. So if you go into any SBT project, you'll see a `build.sbt` declared in the top-level
directory that is, the base directory.

#### Source code

Source code can be placed in the project’s base directory as with hello/hw.scala.
However, most people don’t do this for real projects; too much clutter.

SBT uses the same directory structure as Maven for source files by default (all paths are relative
to the base directory):

```bash
src/
  main/
    resources/
       <files to include in main jar here>
    scala/
       <main Scala sources>
    java/
       <main Java sources>
  test/
    resources
       <files to include in test jar here>
    scala/
       <test Scala sources>
    java/
       <test Java sources>
Other directories in src/ will be ignored. Additionally, all hidden
directories will be ignored.

```
Other directories in src/ will be ignored. Additionally, all hidden directories will be
ignored.

#### SBT build definition files

You’ve already seen build.sbt in the project’s base directory. Other sbt
files appear in a project subdirectory.

The project folder can contain .scala files, which are combined with .sbt files to form the complete
build definition. See organizing the build for more.

```bash
build.sbt
project/
  Build.scala
```
You may see .sbt files inside project/ but they are not equivalent to .sbt files in the
project’s base directory. Explaining this will come later, since you’ll need some background
information first.

### SBT tasks

Starting up sbt In order to start sbt, open a terminal ("Command Prompt" in Windows) and
navigate to the directory of the assignment you are working on (where the build.sbt file is). Typing
sbt will open the sbt command prompt.

```bash
# This is the shell of the operating system
$ cd /path/to/parprog-project-directory
$ sbt
# This is the sbt shell
>
```
SBT commands are executed inside the SBT shell. Don't try to execute them in the Scala REPL,
because they won't work.

#### Running the Scala

Interpreter inside SBT The Scala interpreter is different than the SBT command
line.

However, you can start the Scala interpreter inside sbt using the `console` task. The interpreter
(also called REPL, for "read-eval-print loop") is useful for trying out snippets of Scala code. When
the REPL is executed from SBT, all your code in the SBT project will also be loaded and you will be
able to access it from the interpreter. That's why the Scala REPL can only start up if there are no
compilation errors in your code.

In order to quit the interpreter and get back to sbt, type <Ctrl+D>.

```bash
> console
[info] Starting scala interpreter...
Welcome to Scala version 2.11.5 (Java HotSpot(TM) 64-Bit Server VM, Java 1.7.0_04-ea).
Type in expressions to have them evaluated.
Type :help for more information.

scala> println("Oh, hai!")                                          # This is the Scala REPL, type some Scala code
Oh, hai!

scala> val l = List(1, 2, 3)
l: List[Int] = List(1, 2, 3)

scala> val squares = l.map(x => x * x)
squares: List[Int] = List(1, 4, 9)

scala>                                                              # Type [ctrl-d] to exit the Scala REPL
[success] Total time: 20 s, completed Mar 21, 2013 11:02:31 AM
>                                                                   # We're back to the sbt shell
```
#### Compiling your Code

The compile task will compile the source code of the assignment which is
located in the directory src/main/scala.

```bash
> compile
[info] Compiling 4 Scala sources to /Users/aleksandar/example/target/scala-2.11/classes...
[success] Total time: 1 s, completed Mar 21, 2013 11:04:46 PM
>
```
If the source code contains errors, the error messages from the compiler will be displayed.

#### Testing your Code

The directory src/test/scala contains unit tests for the project. In order to run
these tests in sbt, you can use the test command.

```bash
> test
[info] ListsSuite:
[info] - one plus one is two
[info] - sum of a few numbers *** FAILED ***
[info]   3 did not equal 2 (ListsSuite.scala:23)
[info] - max of a few numbers
[error] Failed: : Total 3, Failed 1, Errors 0, Passed 2, Skipped 0
[error] Failed tests:
[error]   example.ListsSuite
[error] {file:/Users/luc/example/}assignment/test:test: Tests unsuccessful
[error] Total time: 5 s, completed Aug 10, 2012 10:19:53 PM
>
```
#### Running your Code

If your project has an object with a main method (or an object extending the
trait App), then you can run the code in sbt easily by typing run. In case sbt finds multiple main
methods, it will ask you which one you'd like to execute.

```bash
> run
Multiple main classes detected, select one to run:

 [1] example.Lists
 [2] example.M2

Enter number: 1

[info] Running example.Lists
main method!
[success] Total time: 33 s, completed Aug 10, 2012 10:25:06 PM
>
```
#### Submitting your Solution to Coursera

The sbt task submit allows you to submit your solution for
the assignment. It will pack your source code into a .jar file and upload it to the coursera
servers. Note that the code can only be submitted if there are no compilation errors.

Warnings: Before proceeding:

- Make sure that you run sbt in the root folder of the project (where the build.sbt file is).
- Make sure that the console line is `>` and not `scala>`. Otherwise, you're inside the Scala console and
not the sbt shell.

The submit tasks takes two arguments: your Coursera e-mail address and the
submission token.

NOTE: the submission token is not your login password. Instead, it's a special
password generated by coursera for every assignment. It is available on the Assignments page.

```bash
> submit e-mail@university.org suBmISsioNPasSwoRd
[info] Packaging /Users/luc/example/target/scala-2.11/parprog-example_2.11-1.0.0-sources.jar ...
[info] Done packaging.
[info] Compiling 1 Scala source to /Users/luc/example/target/scala-2.11/classes...
[info] Connecting to coursera. Obtaining challenge...
[info] Computing challenge response...
[info] Submitting solution...
[success] Your code was successfully submitted: Your submission has been accepted and will be graded shortly.
[success] Total time: 6 s, completed Aug 10, 2012 10:35:53 PM
>
```
You can also submit your work without starting the sbt interactive shell, by running the
following command:

```bash
$ sbt "submit e-mail@university.org suBmISsioNPasSwoRd"
```
Note the usage of double quotes.
