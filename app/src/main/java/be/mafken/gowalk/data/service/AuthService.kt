package be.mafken.gowalk.data.service

import be.mafken.gowalk.data.OnServiceDataCallback

interface AuthService {

 fun signInUserWithEmailAndPassword(
  email: String,
  pword: String,
  callback: OnServiceDataCallback<String>
 )

 fun createUserWithEmailAndPassword(email: String, pword: String)
}