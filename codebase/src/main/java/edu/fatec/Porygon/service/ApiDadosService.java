package edu.fatec.Porygon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.fatec.Porygon.model.*;
import edu.fatec.Porygon.repository.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApiDadosService {

    @Autowired
    private ApiDadosRepository apiDadosRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private SinonimoRepository sinonimoRepository;

    public List<ApiDados> listarApiDados() {
        return apiDadosRepository.findAll();
    }

    public List<ApiDados> buscarApiDadosPorTags(List<Integer> tagIds) {
        List<Tag> selectedTags = tagRepository.findAllById(tagIds);

        List<Sinonimo> sinonimos = sinonimoRepository.findByTagIn(selectedTags);
        List<String> sinonimoNames = sinonimos.stream()
                .map(Sinonimo::getNome)
                .collect(Collectors.toList());

        List<Tag> synonymTags = tagRepository.findByNomeInIgnoreCase(sinonimoNames);

        Set<Tag> totalTags = new HashSet<>();
        totalTags.addAll(selectedTags);
        totalTags.addAll(synonymTags);

        List<Integer> totalTagIds = totalTags.stream()
                .map(Tag::getId)
                .collect(Collectors.toList());

        return apiDadosRepository.findDistinctByTags_IdIn(totalTagIds);
    }
}