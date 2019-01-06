package com.gati.mdm.oumaster.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;
import org.springframework.stereotype.Service;

import com.gati.mdm.avro.model.ou.OuMasterEvent;
import com.gati.mdm.common.mapper.CharSequenceMapper;
import com.gati.mdm.common.mapper.LocalDateTimeMapper;
import com.gati.mdm.common.util.Constants;
import com.gati.mdm.oumaster.common.data.entity.OuMaster;

@Mapper(componentModel = "spring", uses = { LocalDateTimeMapper.class, CharSequenceMapper.class },
                  unmappedTargetPolicy = ReportingPolicy.IGNORE,
                  unmappedSourcePolicy = ReportingPolicy.IGNORE,
                  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Service
public interface OuMasterEventMapper {

    // Change the values received from FrontEnd YES -> Y and NO -> Y
    @Mapping(target = "startDatetime", source = "ouMaster.startDatetime", dateFormat = "dd-MM-yyyy HH:mm:ss")
    @ValueMappings({ @ValueMapping(source = Constants.YES, target = Constants.Y),
            @ValueMapping(source = Constants.NO, target = Constants.N) })
    OuMasterEvent mapToOuMasterEvent(OuMaster ouMaster);

    @Mapping(target = "startDatetime", source = "ouMasterEvent.startDatetime", dateFormat = "dd-MM-yyyy HH:mm:ss")
    OuMaster mapToOuMaster(OuMasterEvent ouMasterEvent);

    void updateOuMasterFromEvent(OuMasterEvent ouMasterEvent, @MappingTarget OuMaster ouMaster);
}
