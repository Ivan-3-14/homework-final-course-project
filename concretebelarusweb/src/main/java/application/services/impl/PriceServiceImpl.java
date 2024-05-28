package application.services.impl;

import application.DTO.autoDTO.AutoCapacityDTO;
import application.DTO.autoDTO.AutoPriceDTO;
import application.DTO.concreteDTO.ConcretePriceDTO;
import application.DTO.filtersDTO.PriceFilterToFront;
import application.converter.AutoCapacityMapper;
import application.converter.AutoPriceMapper;
import application.converter.ConcretePriceMapper;
import application.entity.autotransportation.AutoCapacity;
import application.entity.autotransportation.AutoPrice;
import application.entity.concreteentities.ConcretePrice;
import application.repository.AutoCapacityRepository;
import application.repository.AutoPriceRepository;
import application.repository.ConcretePriceRepository;
import application.services.interfaces.PriceService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static application.utils.Constant.MINUS_ONE;

@Transactional
@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final ConcretePriceMapper concretePriceMapper = Mappers.getMapper(ConcretePriceMapper.class);
    private final AutoPriceMapper autoPriceMapper = Mappers.getMapper(AutoPriceMapper.class);
    private final AutoCapacityMapper autoCapacityMapper = Mappers.getMapper(AutoCapacityMapper.class);

    private final ConcretePriceRepository concretePriceRepository;
    private final AutoPriceRepository autoPriceRepository;
    private final AutoCapacityRepository autoCapacityRepository;


    @Transactional(readOnly = true)
    public PriceFilterToFront getAllPrice() {

        List<ConcretePriceDTO> concretePriceDTOS = concretePriceRepository.findAll().stream()
                .map(concretePriceMapper::toDTO)
                .collect(Collectors.toList());

        List<AutoPriceDTO> autoPriceDTOS = autoPriceRepository.findAll().stream()
                .map(autoPriceMapper::toDTO)
                .collect(Collectors.toList());

        List<AutoCapacityDTO> autoCapacityDTOS = autoCapacityRepository.findAll().stream()
                .map(autoCapacityMapper::toDTO)
                .collect(Collectors.toList());

        return PriceFilterToFront.builder()
                .autoPriceDTOList(autoPriceDTOS)
                .concretePriceDTOList(concretePriceDTOS)
                .autoCapacityDTOList(autoCapacityDTOS)
                .build();
    }

    public ConcretePriceDTO updateConcretePrice(Long id, Double newPrice) {
        ConcretePrice concretePrice = concretePriceRepository.getById(id);
        concretePrice.setPrice(newPrice);
        concretePriceRepository.save(concretePrice);
        return concretePriceMapper.toDTO(concretePrice);
    }

    public AutoPriceDTO updateAutoPriceDTO(Long id, Double newPrice) {
        AutoPrice autoPrice = autoPriceRepository.getById(id);
        autoPrice.setPrice(newPrice);
        autoPriceRepository.save(autoPrice);
        return autoPriceMapper.toDTO(autoPrice);
    }

    public AutoCapacityDTO updateAutoCapacityDTO(Long idDelBefore50, Long idDelAfter50,  Integer delCoefficient) {
        AutoCapacity autoCapacity = null;
        if (!idDelBefore50.equals(MINUS_ONE)) {
            autoCapacity = autoCapacityRepository.getById(idDelBefore50);
            autoCapacity.getDeliveryCoefficient().setDeliveryCoefficientBfr50(delCoefficient);
            autoCapacity = autoCapacityRepository.save(autoCapacity);
        } else if (!idDelAfter50.equals(MINUS_ONE)){
            autoCapacity = autoCapacityRepository.getById(idDelAfter50);
            autoCapacity.getDeliveryCoefficient().setDeliveryCoefficientBfr50(delCoefficient);
            autoCapacity = autoCapacityRepository.save(autoCapacity);
        }

        return autoCapacityMapper.toDTO(autoCapacity);
    }
}


