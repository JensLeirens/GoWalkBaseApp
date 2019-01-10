package be.mafken.gowalk.data.service

import be.mafken.gowalk.data.OnServiceDataCallback
import be.mafken.gowalk.model.Walk

interface WalkService {

 fun loadWalksFromDatabase(callback: OnServiceDataCallback<List<Walk>>)

 fun loadWalksOnceFromDatabase(callback: OnServiceDataCallback<List<Walk>>)

 fun loadWalk(walkId: Int, callback: OnServiceDataCallback<Walk>)

 fun saveWalkToDatabase(walk: Walk)

 fun deleteWalk(walkId: Int)

}