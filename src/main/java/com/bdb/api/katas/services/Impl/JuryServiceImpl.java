package com.bdb.api.katas.services.Impl;

import com.bdb.api.katas.dto.JurieDTO;
import com.bdb.api.katas.entity.mapper.JuryMapper;
import com.bdb.api.katas.repository.JuryRepository;
import com.bdb.api.katas.services.JuryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class JuryServiceImpl implements JuryService {

    private final JuryRepository jurieRepository;

    @Override
    public List<JurieDTO> getAllJuries() {
        var listJuries = jurieRepository.findAllJuries();
        return JuryMapper.toJuryListDTO(listJuries);
    }

    @Override
    public JurieDTO saveJury(JurieDTO jurieDTO) {
        return JuryMapper.toJuryDTO(jurieRepository.save(JuryMapper.toJuryEntity(jurieDTO)));
    }
}
