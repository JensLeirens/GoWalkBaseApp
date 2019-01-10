package be.mafken.gowalk.model

data class User(
 val uid: String = "",
 var email: String = "",
 var name: String = "",
 var totalDistance: Int = -1,
 var totalTime: String = ""
)
