package be.mafken.gowalk.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import be.mafken.gowalk.data.NonNullMediatorLiveData

fun <T> LiveData<T>.nonNull():
 NonNullMediatorLiveData<T> {
 val mediator: NonNullMediatorLiveData<T> =
  NonNullMediatorLiveData()
 mediator.addSource(this) {
  it?.let { mediator.value = it }
 }
 return mediator
}

fun <T> NonNullMediatorLiveData<T>
 .observe(owner: LifecycleOwner, observer: (t: T) -> Unit) {
 this.observe(owner, androidx.lifecycle.Observer {
  it?.let(observer)
 })
}