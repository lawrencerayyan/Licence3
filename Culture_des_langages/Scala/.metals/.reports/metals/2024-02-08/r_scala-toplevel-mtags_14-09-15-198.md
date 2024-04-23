error id: file:///C:/L3-upec/Culture_des_langages/Scala/TP4/tp4.scala:[207..208) in Input.VirtualFile("file:///C:/L3-upec/Culture_des_langages/Scala/TP4/tp4.scala", "import scala.collection.immutable.HashMap

object Cache {
}

 class  Cache [T,U] private( f : T => U , map : HashMap[T,U]) {
        
    def this(f : T => U) = this(f, new HashMap[T,U]())

    def (x: T): (U, Cache[T, U]) = {
        if (map.contains(x)) {
            (map(x), this)
        } else {
            
        }
    }
}")
file:///C:/L3-upec/Culture_des_langages/Scala/TP4/tp4.scala
file:///C:/L3-upec/Culture_des_langages/Scala/TP4/tp4.scala:10: error: expected identifier; obtained lparen
    def (x: T): (U, Cache[T, U]) = {
        ^
#### Short summary: 

expected identifier; obtained lparen