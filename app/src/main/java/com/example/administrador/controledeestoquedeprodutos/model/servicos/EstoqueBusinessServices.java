package com.example.administrador.controledeestoquedeprodutos.model.servicos;

import com.example.administrador.controledeestoquedeprodutos.model.entidade.Estoque;
import com.example.administrador.controledeestoquedeprodutos.model.persistence.EstoqueRepository;

import java.util.List;

/**
 * Created by Administrador on 25/09/2015.
 */
public class EstoqueBusinessServices {

    public static List<Estoque> findAll(){
        return EstoqueRepository.getAll();
    }

    public static void save(Estoque estoque){
        Integer id = EstoqueRepository.getIdByWebId(estoque.getWeb_id());
        if(id > 0)
            estoque.set_id(id);
         EstoqueRepository.save(estoque);
    }

    public static void delete(Estoque selectEstoque) {
        EstoqueRepository.delete(selectEstoque.get_id());
    }

}
