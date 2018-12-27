package be.mafken.gowalk.model

data class Walk (val id :Int = -1,
                 val userId : String = "",
                 var amountKm: Int = -1,
                 var time: Int = -1)