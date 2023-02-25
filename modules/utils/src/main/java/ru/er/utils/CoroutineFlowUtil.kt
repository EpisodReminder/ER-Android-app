package ru.er.utils

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.cancellation.CancellationException

inline fun <T> Flow<T>.collectWhenStarted(
    scope: LifecycleCoroutineScope,
    crossinline action: suspend (value: T) -> Unit,
) {
    scope.launchWhenStarted {
        collect { action(it) }
    }
}

inline fun <T> Flow<Consumable<T>?>.collectConsumableWhenStarted(
    scope: LifecycleCoroutineScope,
    crossinline action: suspend (value: T) -> Unit,
) {
    scope.launchWhenStarted {
        collect { state ->
            state?.consume {
                action(it)
            }
        }
    }
}

inline fun <T> Consumable<T>.consume(action: (T) -> Unit) {
    data?.let {
        if (consume()) {
            action(it)
        }
    }
}

fun <T> MutableStateFlow<Consumable<T>?>.post(updValue: T) {
    value = Consumable(updValue)
}

inline fun <T> CoroutineScope.launchTo(
    target: MutableStateFlow<in ActionState<T>>,
    default: T? = null,
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    crossinline block: suspend CoroutineScope.() -> T
): Job = launch(context, start) {
    target.value = ActionState.Loading(default)
    try {
        val result = block()
        target.value = ActionState.Success(result)
    } catch (expected: CancellationException) {
        target.value = ActionState.Idle(default)
    } catch (throwable: Throwable) {
        Log.e("episode-reminder:launchTo:error", throwable.stackTraceToString())
        target.value = ActionState.Error(default, throwable)
    }
}


val Fragment.viewScope
    get() = viewLifecycleOwner.lifecycle.coroutineScope
