package dev.xascar.moviesapppro.usecases

import dev.xascar.moviesapppro.domain.MovieDomain
import dev.xascar.moviesapppro.repository.MoviesRepository
import dev.xascar.moviesapppro.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

/**
 *  Are part of the domain layer
 *  Receives data from repository, emits data to the viewModel
 *  In clean architecture the data layer is the one that handles the data from network or local database
 *  Decides the data that goes to the viewmodel
 *  Brings you separation of concerns -> decoupled code -> stability, testability, scalability
 *  follows SOLID principles ()
 *  Use cases -> Single responsibility
 *  Extra utility classes, interfaces, abstract classes -> Open close principle
 *
 *  Clean architecture -> Interface segregation
 *  Dependency Injection, Service Locator pattern -> Dependency inversion
 *
 */
//todo Create a generic use case (PopularMoviesUseCase extends from GenericUseCase)
//todo implement TypeAliases with use cases
class PopularMoviesUseCase(private val moviesRepository: MoviesRepository) {

//    operator fun invoke(page: Int): Flow<ResultState<List<MovieDomain>>> {
////        moviesRepository.getPopularMovies(page).collect{
////
////        }
//
//    }

}