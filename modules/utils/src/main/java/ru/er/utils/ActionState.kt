package ru.er.utils

sealed class ActionState<out T>(
    open val data: T? = null,
    open val exception: Throwable? = null
)  {

    class Idle<out T>(data: T? = null) : ActionState<T>(data)

    class Loading<out T>(data: T? = null) : ActionState<T>(data)

    class Success<out T>(override val data: T) : ActionState<T>(data)

    class Error<out T>(
        data: T? = null,
        override val exception: Throwable
    ) : ActionState<T>(data, exception)

    inline fun copy(block: (T) -> @UnsafeVariance T): ActionState<T> = when (this) {
        is Error -> data?.let { Error(block(it), exception) } ?: this
        is Idle -> data?.let { Idle(block(it)) } ?: this
        is Loading -> data?.let { Loading(block(it)) } ?: this
        is Success -> Success(block(data))
    }
}