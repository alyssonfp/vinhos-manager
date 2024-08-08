package com.vinhosales.vinhos.repository;

import com.vinhosales.vinhos.model.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataMongoTest
class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @BeforeEach
    void setUp() {
        categoriaRepository.deleteAll().block();
    }

    @Test
    void testSaveAndFindById() {
        Categoria categoria = new Categoria("1", "Vinhos Tintos", "Vinhos encorpados e elegantes");

        Mono<Categoria> saveCategoria = categoriaRepository.save(categoria);

        StepVerifier.create(saveCategoria)
                .expectNextMatches(savedCategoria -> savedCategoria.getId().equals("1"))
                .verifyComplete();

        Mono<Categoria> foundCategoria = categoriaRepository.findById("1");

        StepVerifier.create(foundCategoria)
                .expectNextMatches(found -> found.getId().equals("1"))
                .verifyComplete();
    }

    @Test
    void testFindByNome() {
        Categoria categoria = new Categoria("2", "Vinhos Brancos", "Vinhos leves e refrescantes");

        categoriaRepository.save(categoria).block();

        Mono<Categoria> foundCategoria = categoriaRepository.findByNome("Vinhos Brancos");

        StepVerifier.create(foundCategoria)
                .expectNextMatches(found -> found.getNome().equals("Vinhos Brancos"))
                .verifyComplete();
    }
}

