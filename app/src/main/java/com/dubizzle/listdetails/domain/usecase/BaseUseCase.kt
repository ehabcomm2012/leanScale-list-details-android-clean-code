package com.dubizzle.listdetails.domain.usecase

import kotlinx.coroutines.flow.Flow

/**
 * The Base Use Case which used across the whole app to define a flow of a business or API integration from a user prespective
 */
abstract class BaseUseCase<in Params,out Type> {
    abstract  fun execute(params:Params?=null): Flow<Type>
}