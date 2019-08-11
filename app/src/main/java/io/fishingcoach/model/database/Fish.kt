package io.fishingcoach.model.database

//L’intérêt d’utiliser une classe de donnée est la génération automatique de fonction d’affichage tel que
//toString(), de copie d’objet, copy(), d’égalité, equals(), etc
data class Fish (val Name : String, val LivingPlace : String){
}