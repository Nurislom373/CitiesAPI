package org.khasanof.citiesapi.controller;

import lombok.RequiredArgsConstructor;
import org.khasanof.citiesapi.service.BaseService;

@RequiredArgsConstructor
public abstract class AbstractController<S extends BaseService> {
    protected final S service;
}
