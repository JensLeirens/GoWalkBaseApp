package be.mafken.gowalk.data.service

import be.mafken.gowalk.data.OnServiceDataCallback
import be.mafken.gowalk.model.User

interface UserService {

 fun loadUsersFromDatabase(callback: OnServiceDataCallback<List<User>>)

 fun loadUsersOnceFromDatabase(callback: OnServiceDataCallback<List<User>>)

 fun loadUser(userId: String, callback: OnServiceDataCallback<User>)

 fun loadUserOnce(userId: String, callback: OnServiceDataCallback<User>)

 fun saveUserToDatabase(user: User)

}