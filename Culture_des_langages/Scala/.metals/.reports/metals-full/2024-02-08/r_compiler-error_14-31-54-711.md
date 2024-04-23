file:///C:/L3-upec/Culture_des_langages/Scala/TP4/tp4.scala
### dotty.tools.dotc.core.TypeError$$anon$1: bad parameter reference T at typer
the parameter is type T in class Cache but the prefix <noprefix>
does not define any corresponding arguments.
idx = 0, args = ,
constraint =  uninstantiated variables:
 constrained types:
 bounds:
 ordering:
 co-deps:
 contra-deps:


occurred in the presentation compiler.

action parameters:
offset: 372
uri: file:///C:/L3-upec/Culture_des_langages/Scala/TP4/tp4.scala
text:
```scala
import scala.collection.immutable.HashMap

object Cache {
}

 class  Cache [T,U] private( f : T => U , map : HashMap[T,U]) {
        
    def this(f : T => U) = this(f, new HashMap[T,U]())

    def apply(x: T): (U, Cache[T, U]) = {
        if (map.contains(x)) {
            (map(x), this)
        } else {
            val y = f(x)
            (y, new Cache(@@f, map + (x -> y)))
        }
    }
}
```



#### Error stacktrace:

```

```
#### Short summary: 

dotty.tools.dotc.core.TypeError$$anon$1: bad parameter reference T at typer
the parameter is type T in class Cache but the prefix <noprefix>
does not define any corresponding arguments.
idx = 0, args = ,
constraint =  uninstantiated variables:
 constrained types:
 bounds:
 ordering:
 co-deps:
 contra-deps:
