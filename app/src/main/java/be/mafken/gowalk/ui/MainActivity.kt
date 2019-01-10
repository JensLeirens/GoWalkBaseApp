package be.mafken.gowalk.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import be.mafken.gowalk.R
import be.mafken.gowalk.data.OnServiceDataCallback
import be.mafken.gowalk.data.firebase.FirebaseServiceProvider
import be.mafken.gowalk.data.service.UserService
import be.mafken.gowalk.extensions.goToFragmentWithoutBackstack
import be.mafken.gowalk.model.User
import be.mafken.gowalk.ui.home.HomeFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  setContentView(R.layout.activity_main)
  goToFragmentWithoutBackstack(HomeFragment.newInstance())

  FirebaseAuth.getInstance().addAuthStateListener {
   it.currentUser?.let { user ->
    createNewUser(user.email!!, user.uid)
   }
  }

 }

 private fun createNewUser(email: String, userId: String) {
  val userService: UserService =
   FirebaseServiceProvider.getFirebaseUserService()
  val users: MutableList<User> = mutableListOf()
  val currentUser = User(uid = userId, email = email)
  var userAlreadyExists = false

  userService.loadUsersFromDatabase(object :
   OnServiceDataCallback<List<User>> {
   override fun onDataLoaded(data: List<User>) {
    users.addAll(data)
   }

   override fun onError(error: Throwable) {
   }
  })

  users.forEach {
   if (it.uid == currentUser.uid)
    userAlreadyExists = true
  }

  if (!userAlreadyExists) {
   val nameList = currentUser.email
    .split("@")[0].split(".")
   currentUser.name = nameList
    .joinToString(separator = " ") { it.capitalize() }
   userService.saveUserToDatabase(currentUser)
  }


 }
}
