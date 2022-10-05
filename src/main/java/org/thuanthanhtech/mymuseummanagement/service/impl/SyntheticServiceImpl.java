package org.thuanthanhtech.mymuseummanagement.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.thuanthanhtech.mymuseummanagement.dto.SyntheticDTO;
import org.thuanthanhtech.mymuseummanagement.entity.Synthetic;
import org.thuanthanhtech.mymuseummanagement.repository.SyntheticRepository;
import org.thuanthanhtech.mymuseummanagement.service.SyntheticService;
import org.thuanthanhtech.mymuseummanagement.utils.Constants;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SyntheticServiceImpl implements SyntheticService {


    private final SyntheticRepository syntheticRepository;

    @SneakyThrows
    @Override
    @Transactional
    public Synthetic createSynthetic(Synthetic synthetic) {
        Synthetic synthetics = new Synthetic();

        synthetics.setType(synthetic.getType());
        synthetics.setName(synthetic.getName());
        synthetics.setTitle(synthetic.getTitle());
        synthetics.setContent(synthetic.getContent());
        synthetics.setUpdateBy(synthetic.getUpdateBy());
        synthetics.setStatus(Constants.STATUS_ACTIVE);
        syntheticRepository.save(synthetics);

        return synthetic;
    }

    @SneakyThrows
    @Override
    @Transactional
    public Synthetic updateSynthetic(Synthetic synthetic, Long id) {
        Optional<Synthetic> optionalSynthetic = syntheticRepository.findById(id);
        if (optionalSynthetic.isPresent()) {
            Synthetic sy = optionalSynthetic.get();

            sy.setType(synthetic.getType());
            sy.setName(synthetic.getName());
            sy.setTitle(synthetic.getTitle());
            sy.setContent(synthetic.getContent());
            sy.setUpdateBy(synthetic.getUpdateBy());
            sy.setStatus(Constants.STATUS_ACTIVE);
            syntheticRepository.save(sy);
        } else {
            throw new NoSuchElementException();
        }
        return synthetic;
    }

    @SneakyThrows
    @Override
    @Transactional
    public void deleteSynthetic(Long id) {
        List<Synthetic> syntheticList = syntheticRepository.findALlByIdAndStatus(id, Constants.STATUS_ACTIVE);
        if (CollectionUtils.isEmpty(syntheticList)) {
            throw new NoSuchElementException("Can not found!");
        }
        for (Synthetic synthetic : syntheticList) {
            synthetic.setCreatDate(LocalDate.now());
            synthetic.setStatus(Constants.STATUS_INACTIVE);
            syntheticRepository.save(synthetic);
        }
    }

    @Override
    public Page<Synthetic> getAllSynthetic(Pageable pageable, SyntheticDTO syntheticDTO) {
        String search;
        if (StringUtils.isEmpty(syntheticDTO.getSearch())) {
            search = "%%";
        } else {
            search ="%" + syntheticDTO.getSearch().toLowerCase() +"%";
        }
        return syntheticRepository.findAllBySearch(pageable, search);
    }
}
