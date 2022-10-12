package com.garibyan.armen.last_classwork.common

sealed class Resource<out T>(
    val result: T? = null,
    val error: String? = null,
    val isLoading: Boolean? = null
) {
    class Success<out T>(result: T) : Resource<T>(result = result)
    class Error(error: String) : Resource<Nothing>(error = error)
    object Loader : Resource<Nothing>()
}
fun <T, R> Resource<List<T>>.mapSuccess(transform: (T) -> R): Resource<List<R>> {
    return when (this) {
        is Resource.Success -> Resource.Success(result = this.result?.map { model -> transform(model) } ?: emptyList())
        is Resource.Error -> Resource.Error(error = this.error ?: "")
        is Resource.Loader -> Resource.Loader
    }
}
