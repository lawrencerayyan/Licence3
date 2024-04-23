error id: file:///C:/L3-upec/Culture_des_langages/Scala/TP4/tp4.scala:[425..425) in Input.VirtualFile("file:///C:/L3-upec/Culture_des_langages/Scala/TP4/tp4.scala", "import scala.collection.immutable.HashMap

object Cache {
}

 class  Cache [T,U] private( f : T => U , map : HashMap[T,U]) {
        
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

class ")
file:///C:/L3-upec/Culture_des_langages/Scala/TP4/tp4.scala
file:///C:/L3-upec/Culture_des_langages/Scala/TP4/tp4.scala:20: error: expected identifier; obtained eof
class 
      ^
#### Short summary: 

expected identifier; obtained eof