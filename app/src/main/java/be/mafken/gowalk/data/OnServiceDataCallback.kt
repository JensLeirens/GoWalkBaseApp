package be.mafken.gowalk.data

interface OnServiceDataCallback<T> {
    fun onDataLoaded(data: T)

    fun onError(error: Throwable)
}