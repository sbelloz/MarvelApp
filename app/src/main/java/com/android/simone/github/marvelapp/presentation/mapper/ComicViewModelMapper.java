package com.android.simone.github.marvelapp.presentation.mapper;

import com.android.simone.github.marvelapp.domain.mapper.Mapper;
import com.android.simone.github.marvelapp.domain.model.Comic;
import com.android.simone.github.marvelapp.presentation.di.scope.ActivityScope;
import com.android.simone.github.marvelapp.presentation.viewmodel.ComicViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Simone Bellotti
 */
@ActivityScope
public class ComicViewModelMapper implements Mapper<Comic, ComicViewModel> {

    @Inject
    public ComicViewModelMapper() {
    }

    @Override
    public ComicViewModel transform(Comic comic) {
        ComicViewModel viewModel = new ComicViewModel();
        viewModel.setId(comic.getId());
        viewModel.setDescription(comic.getDescription());
        viewModel.setImages(comic.getImages());
        viewModel.setThumbnailUrl(comic.getThumbnail());
        viewModel.setTitle(comic.getTitle());
        viewModel.setYear(comic.getYear());
        viewModel.setPrice(comic.getPrice());
        return viewModel;
    }

    @Override
    public List<ComicViewModel> transformCollection(Collection<Comic> fromList) {
        List<ComicViewModel> comicList = new ArrayList<>();
        for (Comic comic : fromList) {
            comicList.add(transform(comic));
        }
        return comicList;
    }
}
