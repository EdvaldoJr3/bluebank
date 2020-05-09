package br.com.avaliacao.bluebank.service;

import br.com.avaliacao.bluebank.model.Agencia;
import br.com.avaliacao.bluebank.repository.AgenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("agenciaService")
public class AgenciaServiceImpl implements AgenciaService{

    @Autowired
    private AgenciaRepository agenciaRepository;

    @Override
    public List<Agencia> findAll(){return agenciaRepository.findAll();}
}
