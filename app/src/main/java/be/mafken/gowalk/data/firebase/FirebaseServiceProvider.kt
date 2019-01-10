package be.mafken.gowalk.data.firebase

import be.appfoundry.appcademy.data.firebase.FirebaseAuthService
import be.mafken.gowalk.data.service.AuthService
import be.mafken.gowalk.data.service.TrackerService
import be.mafken.gowalk.data.service.UserService
import be.mafken.gowalk.data.service.WalkService


object FirebaseServiceProvider {

 fun getWalkService(): WalkService {
  return FirebaseWalkService()
 }

 fun getFirebaseUserService(): UserService {
  return FirebaseUserService()
 }

 fun getFirebaseAuthService(): AuthService {
  return FirebaseAuthService()
 }

 fun getFirebaseTrackerService(): TrackerService {
  return FirebaseTrackerService()
 }
}