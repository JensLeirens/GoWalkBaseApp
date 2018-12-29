package be.mafken.gowalk.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.mafken.gowalk.data.OnServiceDataCallback
import be.mafken.gowalk.data.firebase.FirebaseServiceProvider
import be.mafken.gowalk.data.service.UserService
import be.mafken.gowalk.data.service.WalkService
import be.mafken.gowalk.model.User
import be.mafken.gowalk.model.Walk
import com.google.firebase.auth.FirebaseAuth

class HomeViewModel : ViewModel() {

    private val walkService: WalkService = FirebaseServiceProvider.getWalkService()
    val walkList: MutableLiveData<List<Walk>> = MutableLiveData()
    var totalDistance = 0
    var totalHours = 0
    var totalMinutes = 0
    var totalSeconds = 0

    fun getWalkingsForUserFromDatabase(){
        walkService.loadWalksFromDatabase(object: OnServiceDataCallback<List<Walk>>{
            override fun onDataLoaded(data: List<Walk>) {
                walkList.value = data.filter {
                    it.userId == FirebaseAuth.getInstance().uid
                }
            }

            override fun onError(error: Throwable) {
            }
        })
    }

    fun saveUser(){
        val userService: UserService = FirebaseServiceProvider.getFirebaseUserService()

        userService.loadUserOnce(FirebaseAuth.getInstance().currentUser!!.uid, object: OnServiceDataCallback<User>{
            override fun onDataLoaded(data: User) {
                data.totalDistance = totalDistance
                data.totalTime = "${totalHours}h : ${totalMinutes}m : ${totalSeconds}s"
                userService.saveUserToDatabase(data)
            }

            override fun onError(error: Throwable) {
                //ToDo: implement

            }
        })
    }

    fun getSecondsFromWalk(){
        totalDistance = 0
        totalHours = 0
        totalMinutes = 0
        totalSeconds = 0

        walkList.value!!.forEach {
            totalDistance += it.amountKm

            val temp = it.displayTime.split(":")
            val hours = temp[0].replace("h ", "").toInt()
            val minutes = temp[1].replace("m ", "").replace(" ", "").toInt()
            val seconds = temp[2].replace("s", "").replace(" ", "").toInt()

            totalHours += hours
            totalMinutes += minutes
            totalSeconds += seconds

            if (totalSeconds > 59) {
                totalSeconds -= 60
                totalMinutes += 1
            }

            if (totalMinutes > 59) {
                totalMinutes -= 60
                totalHours += 1
            }
        }
    }
}
