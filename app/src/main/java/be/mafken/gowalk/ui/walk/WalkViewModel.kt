package be.mafken.gowalk.ui.walk

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import be.mafken.gowalk.data.OnServiceDataCallback
import be.mafken.gowalk.data.firebase.FirebaseServiceProvider
import be.mafken.gowalk.data.service.TrackerService
import be.mafken.gowalk.data.service.WalkService
import be.mafken.gowalk.model.Tracker
import be.mafken.gowalk.model.Walk
import com.google.firebase.auth.FirebaseAuth

class WalkViewModel : ViewModel() {

 private val walkService: WalkService =
  FirebaseServiceProvider.getWalkService()
 val walk: MutableLiveData<Walk> = MutableLiveData()

 fun getWalkingsFromDatabase() {
  walk.value = Walk(userId = FirebaseAuth.getInstance().uid!!)
  walkService.loadWalksOnceFromDatabase(object :
   OnServiceDataCallback<List<Walk>> {
   override fun onDataLoaded(data: List<Walk>) {
    walk.value?.id = getNewIdForWalk(data)
   }

   override fun onError(error: Throwable) {
    //ToDo: implement

   }
  })
 }

 fun incrementWalkingsCreatedTracker() {
  val trackerService: TrackerService =
   FirebaseServiceProvider.getFirebaseTrackerService()
  trackerService.loadTrackerOnceFromDatabase(object :
   OnServiceDataCallback<Tracker> {
   override fun onDataLoaded(data: Tracker) {
    data.addWalkingScreenOpend += 1
    trackerService.saveTrackerToDatabase(data)

   }

   override fun onError(error: Throwable) {
   }
  })
 }

 fun saveWalkToDatabase() {
  walkService.saveWalkToDatabase(walk.value!!)
 }

 fun getNewIdForWalk(walkings: List<Walk>): Int {
  return if (walkings.isNotEmpty()) {
   walkings.sortedBy { it.id }.last().id + 1
  } else 0

 }
}
