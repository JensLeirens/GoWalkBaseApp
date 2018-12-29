package be.mafken.gowalk.data.service

import be.mafken.gowalk.data.OnServiceDataCallback
import be.mafken.gowalk.model.Tracker

interface TrackerService {
    
    fun loadTrackerOnceFromDatabase(callback: OnServiceDataCallback<Tracker>)
    
    fun saveTrackerToDatabase(tracker: Tracker)

}