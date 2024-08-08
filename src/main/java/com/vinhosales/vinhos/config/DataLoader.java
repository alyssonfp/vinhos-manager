package com.vinhosales.vinhos.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhosales.vinhos.model.Categoria;
import com.vinhosales.vinhos.model.Produto;
import com.vinhosales.vinhos.repository.CategoriaRepository;
import com.vinhosales.vinhos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.List;

@Configuration
public class DataLoader {

    @Value("${vinhos.json.path:vinhos.json}")
    private String jsonFilePath;

    @Bean
    public CommandLineRunner loadData(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository) {
        return args -> {
            categoriaRepository.deleteAll().subscribe();
            produtoRepository.deleteAll().subscribe();

            Categoria categoriaVinhosBrancos = new Categoria("vinhosBrancos", "Vinhos Brancos", "Vinhos leves e refrescantes.");
            Categoria categoriaVinhosTintos = new Categoria("vinhosTintos", "Vinhos Tintos", "Vinhos encorpados e elegantes.");
            Categoria categoriaEspumantes = new Categoria("espumantes", "Espumantes", "Vinhos espumantes para celebrações.");

            categoriaRepository.saveAll(List.of(categoriaVinhosBrancos, categoriaVinhosTintos, categoriaEspumantes)).subscribe();

            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<Produto>> typeReference = new TypeReference<>() {};
            try {
                File jsonFile = new File(jsonFilePath);
                if (jsonFile.exists()) {
                    List<Produto> vinhos = objectMapper.readValue(jsonFile, typeReference);
                    vinhos.forEach(produto -> {
                        switch (produto.getCategoriaId()) {
                            case "vinhosBrancos":
                                produto.setCategoriaId(categoriaVinhosBrancos.getId());
                                break;
                            case "vinhosTintos":
                                produto.setCategoriaId(categoriaVinhosTintos.getId());
                                break;
                            case "espumantes":
                                produto.setCategoriaId(categoriaEspumantes.getId());
                                break;
                        }
                    });
                    produtoRepository.saveAll(vinhos).subscribe();
                } else {
                    System.err.println("Arquivo JSON não encontrado: " + jsonFilePath);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
