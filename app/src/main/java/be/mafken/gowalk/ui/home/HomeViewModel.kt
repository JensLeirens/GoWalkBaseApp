package be.mafken.gowalk.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.mafken.gowalk.data.OnServiceDataCallback
import be.mafken.gowalk.data.firebase.FirebaseServiceProvider
import be.mafken.gowalk.data.service.WalkService
import be.mafken.gowalk.model.Walk
import com.google.firebase.auth.FirebaseAuth

class HomeViewModel : ViewModel() {

    private val walkService: WalkService = FirebaseServiceProvider.getWalkService()
    val walkList: MutableLiveData<List<Walk>> = MutableLiveData()

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
}
