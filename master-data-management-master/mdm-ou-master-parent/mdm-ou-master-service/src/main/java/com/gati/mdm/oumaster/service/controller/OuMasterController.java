package com.gati.mdm.oumaster.service.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gati.mdm.common.error.ErrorDetails;
import com.gati.mdm.common.exception.business.ResourceAlreadyFoundException;
import com.gati.mdm.common.exception.business.ResourceNotFoundException;
import com.gati.mdm.oumaster.common.data.entity.OuMaster;
import com.gati.mdm.oumaster.common.data.projection.OuMasterProjection;
import com.gati.mdm.oumaster.common.data.service.OuMasterService;
import com.gati.mdm.oumaster.common.dto.OuMasterListRequest;
import com.gati.mdm.oumaster.common.mapper.OuMasterDtoMapper;
import com.gati.mdm.oumaster.service.kafka.OuMasterSourceStreamProducer;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mdm/oumaster")
public class OuMasterController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OuMasterController.class);

    private final OuMasterService ouMasterService;

    private final OuMasterSourceStreamProducer ouMasterSourceStreamProducer;

    @Autowired
    public OuMasterController(OuMasterService ouMasterService, OuMasterDtoMapper ouMasterDtoMapper,
            OuMasterSourceStreamProducer ouMasterSourceStreamProducer) {
        this.ouMasterService = ouMasterService;
        this.ouMasterSourceStreamProducer = ouMasterSourceStreamProducer;
    }

    @GetMapping
    public ResponseEntity<List<OuMasterProjection>> getOuMasterByCriteria(OuMasterListRequest request) {
        LOGGER.debug("Request received : {}", request);
        return ResponseEntity.ok(ouMasterService.getOuMasterByCriteria(request));
    }

    @GetMapping("/details/{ouCode}/{companyId}")
    public ResponseEntity<OuMaster> getOuMasterByOuCodeAndCompanyId(@PathVariable(name = "ouCode") String ouCode,
            @PathVariable(name = "companyId") Integer companyId) throws ResourceNotFoundException {
        OuMaster ouMaster = ouMasterService.validateAndGetOuMasterByOuCodeAndCompanyId(ouCode, companyId);
        return ResponseEntity.ok(ouMaster);
    }

    @PostMapping
    public ResponseEntity createOuMaster(
            @Valid
            @RequestBody OuMaster oumaster, Errors errors)
            throws ResourceAlreadyFoundException {
        if (errors.hasErrors()) {
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), errors.getFieldError()
                                                                           .getDefaultMessage(),
                    errors.getFieldError()
                          .toString());
            LOGGER.info("Error occurred createOuMaster:{}", errorDetails);
            return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
        }

        // TODO: GUI should change YES to Y and NO to N
        OuMaster dbOuMaster = ouMasterService.validateOuMasterExistsByOuCodeAndCompanyId(oumaster.getOuCode(),
                oumaster.getCompanyId());
        LOGGER.debug("createOuMaster Fetched OuMaster from DB:{} sending message to Kafka ...", dbOuMaster);
        try {
            LOGGER.debug("Producer Object : {}", ouMasterSourceStreamProducer);
            ouMasterSourceStreamProducer.sendCreateOuMasterEvent(oumaster);
        } catch (Exception exception) {
            LOGGER.error("Exception while sending message to kafka: {}", exception);
        }
        return new ResponseEntity<>(dbOuMaster, HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<OuMaster> updateOuMaster(@RequestBody OuMaster ouMaster) throws ResourceNotFoundException {
        LOGGER.info("updateOuMaster CompanyId:{} OuMasterCode:{}", ouMaster.getCompanyId(), ouMaster.getOuCode());

        // TODO: GUI should change YES to Y and NO to N
        OuMaster dbOuMaster = ouMasterService.validateAndGetOuMasterByOuCodeAndCompanyId(ouMaster.getOuCode(),
                ouMaster.getCompanyId());
        ouMasterService.validateAndCheckIfOuMasterChanged(dbOuMaster, ouMaster);
        ouMasterSourceStreamProducer.sendUpdateOuMasterEvent(ouMaster);
        // TODO: Do other checks like diff between objects
        return new ResponseEntity<>(ouMaster, HttpStatus.ACCEPTED);
    }
}
