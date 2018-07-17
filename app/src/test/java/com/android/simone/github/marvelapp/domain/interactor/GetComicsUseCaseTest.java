package com.android.simone.github.marvelapp.domain.interactor;

import com.android.simone.github.marvelapp.domain.executor.PostExecutionThread;
import com.android.simone.github.marvelapp.domain.executor.ThreadExecution;
import com.android.simone.github.marvelapp.domain.repository.ComicRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * @author Simone Bellotti
 */

public class GetComicsUseCaseTest {

    GetComicsUseCase getComicsUseCase;

    @Mock
    ThreadExecution mockThreadExecutor;
    @Mock
    PostExecutionThread mockPostExecutionThread;
    @Mock
    ComicRepository mockUserRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getComicsUseCase = new GetComicsUseCase(mockUserRepository, mockThreadExecutor, mockPostExecutionThread);
    }

    @Test
    public void testGetComicsUseCase_success() throws Exception {
        int page = 0;
        getComicsUseCase.buildObservable(GetComicsUseCase.Params.of(page, "1009220"));

        verify(mockUserRepository).getComics(page, "1009220");
        verifyNoMoreInteractions(mockUserRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }

}
