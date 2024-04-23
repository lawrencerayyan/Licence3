// en java :
// class Main {

//   public static void main(String[] args) {
//     println("Hello world!");
//   }

// }

class Main {

  def f() : Unit =
    println("foo")

}

object Main { // object crée un singleton, appelé "objet compagnon" de la classe Main

  // main : Array[String] => Unit
  // def permet de définir une méthode
  def main(args : Array[String]) : Unit = {
    val a : Int = 42 // non-mutable
    var b : Int = 43 // mutable
    //a = 10
    b = 10
    println("Hello world!");

    def cat_methode(s1 : String, s2: String) : String = // méthode
      s1+s2

    val cat : (String,String) => String = { // fonction
      (s1,s2) => s1+s2 // pas besoin de return
    }

    println(cat_methode("ab","cd"))
    println(cat("ef","gh"))

    println(cat)
    //println(cat_methode)

    val p = new Point(1)

    println(p.x)

    val p2 = Point(2,3) // traduit en Point.apply(2,3), donc en new Point(x,y)

    println(p2)

    //val p3 = Point(-2,5)

    println(p.plusLoinQue(p2))

    val bidule = new Main()

    val cp = new PointCol(1,2,"Rouge")

    println(cp)
    cp.verdir()
    println(cp)

    val e1 = Noeud(Feuille(2),"+",Noeud(Feuille(3),"*",Feuille(5)))

    println(e1.eval())

    var t = ("foo",10) // tuple t non-mutable de type (String,Int)
    println(t._1) // première composante
    //t._1 = "bar" illégal
    t = ("bar",13)

    // List[T] : liste non-mutable d'objets ayant le même type T
    val l1 = List(1,2,3)
    val l2 = 4::5::6::Nil // Nil est la liste vide
                          // val l2 = Nil.::(6).::(5).::(4)
                          // def :: (t:T) : List[T]
    // :: est l'ajout en tête (Cons)

    println(l2)

    // o1 !! o2 -> o1.!!(o2) (opérateur binaire = méthode à un argument)
    // :: est une méthode de List[T]
    // "test" :: Nil -> Nil.::("test")
    // quand le nom d'une méthode se termine par :, c'est la méthode de l'objet de droite

    // l1::l2  -> l2.::(l1)
    // l2: List[Int]    l1: List[Int]
    println(l1::l2) // autorisé, cast l2 en List[Any]
                    // possible car List[Int] est un sous-type de List[Any] en scala (et pas en java, où les List sont mutables)

    println(l1:::l2) // appelle l2.:::(l1)

    def sommeEval[T](l:List[T], eval:T=>Int) : Int = { // sommeEval est paramétrée par le type T
      l match {
        case Nil => 0
        case t::q => eval(t) + sommeEval(q,eval)
      }
    }

    val l3 = List("foo","bar")
    println(sommeEval(l3, (s:String) => s.length() ))

    // méthodes de List[T]
    // length()
    // isEmpty()
    // contains(t:T)
    // exists(cond: T=>Boolean) // existe-t-il au moins un élément t to cond(t)

    // contains pourrait se définir
    // contains(t:T) = {
    //   exists((u:T) => t==u) // == appelle equals ; pour l'égalité de référence, t.eq(u)
    // }

    println(l2.exists((u:Int)=>u%2==0))

    // forall(cond: T=> Boolean  // tous les éléments t vérifient-ils cond(t)
    // map[U](f:T=>U) : List[U]

    println(l2.map((n:Int) => 2*n))

    // filter(cond: T=>Boolean) : List[T] // renvoie la liste de tous les éléments vérifiant cond

    // foldLeft[U](u:U)(combine : (U,T)=>U ) :U  // u est la valeur de départ de l'accumulateur

    def somme(l:List[Int]): Int = l.foldLeft(0)((acc:Int,t:Int) => acc+t)

    println(somme(l1))
  }

}

object Point {

  def apply(x:Double, y:Double) = new Point(x,y)

}

class Point(val x:Double, val y:Double) { // val devant les arguments du constructeur principal permet de déclarer des attributs publics
  // Point hérite de AnyRef, qui hérite de Any

  require(x>=0 && y>=0) // obligation

  val dist = Math.sqrt(x*x + y*y)

  def plusLoinQue(other:Point ): Boolean = this.dist >= other.dist

  override def toString():String = "("+x+","+y+")" // le mot-clef "override" est essentiel

  def this(x:Double) = { // constructeur secondaire dont la première ligne **doit** être un appel au constructeur principal
    this(x,x)
  }

  def this(p:Point) = {
    this(p.x,p.y)
  }

  override def equals(that : Any) : Boolean = { // on override le equals de Any (l'équivalent de Object en java)
    that match {  // remplace le instanceOf
      case that:Point => x==that.x && y==that.y
      case _ => false
    }
  }

  // dès qu'on override equals(), on doit override hashCode()
  // pour éviter de incohérence sur les Sets, HashMap, etc.
  // deux points égaux (pour equals) doivent avoir le même hashCode
  override def hashCode() :Int = {
    ((this.x.toInt + 31) * 31 + this.y.toInt) // doit dépendre des valeurs non-mutables de this, et être "pseudo-aléatoire"
  }

}

class PointCol(x:Double, y:Double, private var col:String) extends Point(x,y) {

  def verdir():Unit= col="Vert"

  override def toString() = super.toString()+col
}

// on veut définir des expressions arithmétiques avec des entiers, + et *


sealed abstract class Expr { // sealed signifie que tous les case class héritant de cette class sont définies dans ce fichier
  // permet à scalac de vérifier si le pattern matching est exhaustif

  def eval():Int = { //on aurait pu déclarer (sans l'implémenter) la méthode eval() dans Expr et étendre cette méthode dans chacune des case class
    this match { // pattern matching sur "this"
      case Feuille(n) => n
      case Noeud(fg,"+",fd) => fg.eval() + fd.eval()
      case Noeud(fg,"*",fd) => fg.eval() * fd.eval()
      case _ => throw new Exception("pas possible")
    }
  }

}

case class Feuille(n:Int) extends Expr // case class définit une factory par défaut et un toString décent

case class Noeud(fg:Expr, op:String, fd:Expr) extends Expr {
  require(op=="+" || op=="*") // == signifie .equals
}


