package dev.xascar.moviesapppro.usecases

import dev.xascar.moviesapppro.data.model.DomainMovie
import dev.xascar.moviesapppro.database.LocalRepository
import dev.xascar.moviesapppro.repository.MoviesRepository
import dev.xascar.moviesapppro.utils.ResultState
import dev.xascar.network_sdk.AppUseCase
import dev.xascar.network_sdk.NetworkApi
import dev.xascar.network_sdk.utils.MovieCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

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
class PopularMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
private val localRepository: LocalRepository,
private val networkApi: NetworkApi
                           ): AppUseCase<ResultState<List<DomainMovie>>> {

    override operator fun invoke(arguments: Any?): Flow<ResultState<List<DomainMovie>>> = flow {
        if (networkApi.checkNetworkState()){
            try{
                arguments?.let {
                    moviesRepository.getPopularMovies(it as Int).collect{ state ->
                        if (state is ResultState.Success){
                            state.response.let { listMovieDomain ->
                                localRepository.insertMovies(listMovieDomain)
                            }
                            localRepository.getAllMoviesByType(MovieCategory.POPULAR).collect{ movieListDomain ->
                                emit(ResultState.Success(movieListDomain))
                            }

                        } else {
                            emit(state)
                        }

                    }
                }

            }catch (e: Exception){
                emit(ResultState.Error(e))
            }
        }else {
            localRepository.getAllMoviesByType(MovieCategory.POPULAR).collect{ movieListDomain ->
                if (movieListDomain.isNotEmpty()){
                    emit(ResultState.Success(movieListDomain))
                }
                else{
                    emit(ResultState.Error(Exception("No")))

                }
            }
        }

    }

}