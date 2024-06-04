package com.example.androidcleanarchitecture.core.common.dispatcher

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module


val dispatcherModule = module {
    single(named(AppDispatchers.IO)) {
        Dispatchers.IO
    }
    single(named(AppDispatchers.Default)) {
        Dispatchers.Default
    }

}
