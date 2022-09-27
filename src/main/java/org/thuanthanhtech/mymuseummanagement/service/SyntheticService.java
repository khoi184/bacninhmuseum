package org.thuanthanhtech.mymuseummanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thuanthanhtech.mymuseummanagement.dto.SyntheticDTO;
import org.thuanthanhtech.mymuseummanagement.entity.Synthetic;

import java.util.List;

@Service
public interface SyntheticService {

    @Transactional
    Synthetic createSynthetic(Synthetic synthetic);

    @Transactional
    Synthetic updateSynthetic(Synthetic synthetic, Long id);

    @Transactional
    void deleteSynthetic(Long id);

    Page<Synthetic> getAllSynthetic(Pageable pageable, SyntheticDTO syntheticDTO);
}
