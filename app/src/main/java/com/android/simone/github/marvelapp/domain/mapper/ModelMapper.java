package com.android.simone.github.marvelapp.domain.mapper;

import java.util.Collection;

/**
 * @author Simone Bellotti
 */

public interface ModelMapper<From, To> {

    To transform(From from);

    Collection<To> transformCollection(Collection<From> fromList);
}
