package com.gati.mdm.oumaster.common.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Service;

import com.gati.mdm.oumaster.common.data.entity.OuMaster;
import com.gati.mdm.oumaster.common.dto.OuMasterDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
       unmappedSourcePolicy = ReportingPolicy.IGNORE,
       nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Service
public interface OuMasterDtoMapper {
    OuMasterDto mapToDto(OuMaster ouMaster);

    OuMaster mapToOuMaster(OuMasterDto ouMasterDto);

    List<OuMasterDto> mapToOuMasterDtoList(List<OuMaster> ouMasters);
}
