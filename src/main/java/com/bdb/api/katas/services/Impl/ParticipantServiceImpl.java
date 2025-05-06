package com.bdb.api.katas.services.Impl;

import com.bdb.api.katas.dto.ParticipantDTO;
import com.bdb.api.katas.entity.mapper.ParticipantMapper;
import com.bdb.api.katas.repository.ParticipantRepository;
import com.bdb.api.katas.services.ParticipantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;

    @Override
    public List<ParticipantDTO> getAllParticipants() {
        var listParticipants = participantRepository.findAllParticipant();
        return ParticipantMapper.toParticipantListDTO(listParticipants);
    }

    @Override
    public ParticipantDTO saveParticipant(ParticipantDTO participantDTO) {
        return ParticipantMapper.toParticipantDTO(participantRepository.save(ParticipantMapper.toParticipantEntity(participantDTO)));
    }
}
