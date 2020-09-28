package com.vasili.moviestestapp

import com.vasili.moviestestapp.common.Repository
import com.vasili.moviestestapp.common.SearchRequest
import com.vasili.moviestestapp.common.SearchResult
import com.vasili.moviestestapp.main.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var coroutineTestRule = CoroutinesTestRule()

    @MockK
    lateinit var repository: Repository

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(repository)
    }

    @Test
    fun `search click should return success`() =
        coroutineTestRule.testDispatcher.runBlockingTest {
            val searchRequest = SearchRequest("1", "", "", "")
            val searchResult = SearchResult(listOf())
            `when`(repository.getVideos(searchRequest)).thenReturn(searchResult)

            mainViewModel.onSearchClick(searchRequest)
            assert(mainViewModel.results.value == searchResult.search)
        }
}