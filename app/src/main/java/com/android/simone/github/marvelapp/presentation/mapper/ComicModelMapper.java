package com.android.simone.github.marvelapp.presentation.mapper;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;

import com.android.simone.github.marvelapp.domain.mapper.ModelMapper;
import com.android.simone.github.marvelapp.domain.model.Comic;
import com.android.simone.github.marvelapp.presentation.di.scope.ActivityScope;
import com.android.simone.github.marvelapp.presentation.viewmodel.ComicModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Simone Bellotti
 */
@ActivityScope
public class ComicModelMapper implements ModelMapper<Comic, ComicModel> {

    @Inject
    ComicModelMapper() {

    }

    @Override
    public ComicModel transform(Comic comic) {
        ComicModel viewModel = new ComicModel();
        viewModel.setId(comic.getId());
        if (!TextUtils.isEmpty(comic.getDescription())) {
            viewModel.setDescription(fromHtml(comic.getDescription()).toString());
        }
        viewModel.setImages(comic.getImages());
        viewModel.setThumbnailUrl(comic.getThumbnail());
        viewModel.setTitle(comic.getTitle());
        viewModel.setYear(comic.getYear());
        viewModel.setPrice(comic.getPrice());
        return viewModel;
    }

    @Override
    public List<ComicModel> transformCollection(Collection<Comic> fromList) {
        List<ComicModel> comicList = new ArrayList<>();
        for (Comic comic : fromList) {
            comicList.add(transform(comic));
        }
        return comicList;
    }

    @SuppressWarnings("deprecation")
    private Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }
}
