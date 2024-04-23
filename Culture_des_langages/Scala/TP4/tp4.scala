import scala.collection.immutable.HashMap

object Cache {
}

 class  Cache [T,U] private( f : T => U , map:HashMap[T,U]) {
        
    def this(f : T => U) = this(f, new HashMap[T,U]())

    def apply(x: T): (U, Cache[T, U]) = {
        if (map.contains(x)) {
            (map(x), this)
        } else {
            val y = f(x)
            (y, new Cache(f, map.updated(x, y)))
        }
    }
}

class Etu (val numetu : Int) {
   

    
  override def equals(that : Any) : Boolean = {
    that match { 
      case that:Etu => numetu == that.numetu
      case _ => false
    }
  }
  override def hashCode() :Int = {
    ((this.numetu + 31) * 31 + this.numetu)
    }

    
}
object Main{

    def note ( e : Etu ) = {
    Thread.sleep (2000)
    e.numetu % 21
    }

    def main(args: Array[String]): Unit = {
        val e = new Etu(12345)
        val cache = new Cache((e:Etu) => note(e))   
        val test = cache(e)  
        println(test._1) 
        println(test._2(e)) 
        println(test._1) 
        println(test._2(new Etu(12345))) 
    }
}