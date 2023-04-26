package dev.xascar.network_sdk

import kotlinx.coroutines.flow.Flow


interface AppUseCase<T> {
    operator fun invoke(arguments: Any?): Flow<T>
}