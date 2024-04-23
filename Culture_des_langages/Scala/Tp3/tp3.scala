object Main {
    def main(args: Array[String]): Unit = {
        val formule = Et ( Non ( Variable ( " a " )) , Ou ( Faux , Variable ( " b " )))
        println(formule)
        val f2 = Et ( Non ( Non ( Variable ( " c " ))) , Ou ( Faux , Variable ( " b " )))
        println(f2)
        
        val formuleModifiee = formule.substituer(Map(Variable("c") -> Variable("a")))

        println(formuleModifiee)
    }
    //( non ( a )) et (( faux ) ou ( b ))
    
    //((non  a ) et (faux ou  b ))
}
sealed abstract class Formule {
    override def toString: String = this match {
        case Variable(nom) => nom
        case Non(form) => s"non($form)"
        case Et(formg, formd) => s"($formg et $formd)"
        case Ou(formg, formd) => s"($formg ou $formd)"
        case Vrai => "(vrai)"
        case Faux => "(faux)"
    }

     def substituer(sub: Map[Variable, Formule]): Formule = this match {
        case Variable(nom) => sub.get(Variable(nom)).getOrElse(this)
        case Non(form) => Non(form.substituer(sub))
        case Et(formg, formd) => Et(formg.substituer(sub), &formd.substituer(sub))
        case Ou(formg, formd) => Ou(formg.substituer(sub), formd.substituer(sub))
        case _ => this
    }
}

case class Variable(nom: String) extends Formule
case class Non(form: Formule) extends Formule
case class Et(formg: Formule, formd: Formule) extends Formule
case class Ou(formg: Formule, formd: Formule) extends Formule

case object Vrai extends Formule
case object Faux extends Formule

