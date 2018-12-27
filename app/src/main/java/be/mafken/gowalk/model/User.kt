package be.mafken.gowalk.model

data class User(val uid: String = "",
                var username: String = "",
                var email: String = "",
                var name: String = "",
                var walkings : MutableList<Walk> = mutableListOf())
