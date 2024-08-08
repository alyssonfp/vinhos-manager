package com.vinhosales.vinhos.service;

import com.vinhosales.vinhos.model.Categoria;
import com.vinhosales.vinhos.repository.CategoriaRepository;
import com.vinhosales.vinhos.service.impl.CategoriaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoriaServiceImplTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaServiceImpl categoriaService;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        categoria = new Categoria("1", "Vinhos Tintos", "Vinhos encorpados e elegantes");
    }

    @Test
    void getAllCategorias_ShouldReturnAllCategorias() {
        when(categoriaRepository.findAll()).thenReturn(Flux.just(categoria));

        Flux<Categoria> result = categoriaService.getAllCategorias();

        StepVerifier.create(result)
                .expectNext(categoria)
                .verifyComplete();
    }

    @Test
    void getCategoriaById_ShouldReturnCategoria_WhenCategoriaExists() {
        when(categoriaRepository.findById(anyString())).thenReturn(Mono.just(categoria));

        Mono<Categoria> result = categoriaService.getCategoriaById("1");

        StepVerifier.create(result)
                .expectNext(categoria)
                .verifyComplete();
    }

    @Test
    void createCategoria_ShouldReturnCreatedCategoria() {
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(Mono.just(categoria));

        Mono<Categoria> result = categoriaService.createCategoria(categoria);

        StepVerifier.create(result)
                .expectNext(categoria)
                .verifyComplete();
    }

    @Test
    void deleteCategoria_ShouldComplete_WhenCategoriaDeleted() {
        when(categoriaRepository.deleteById(anyString())).thenReturn(Mono.empty());

        Mono<Void> result = categoriaService.deleteCategoria("1");

        StepVerifier.create(result)
                .verifyComplete();
    }
}
