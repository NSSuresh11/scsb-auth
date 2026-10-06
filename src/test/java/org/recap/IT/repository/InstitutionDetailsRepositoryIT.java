package org.recap.IT.repository;

import org.junit.jupiter.api.Test;
import org.recap.IT.BaseTestCase;
import org.recap.model.jpa.InstitutionEntity;
import org.recap.repository.jpa.InstitutionDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * Created by pvsubrah on 6/22/16.
 */
public class InstitutionDetailsRepositoryIT extends BaseTestCase {

    @Autowired
    InstitutionDetailsRepository institutionDetailsRepository;

    @Test
    public void saveAndFind() throws Exception {
        try {
            InstitutionEntity institutionEntity = getInstitutionEntity();
            InstitutionEntity savedInstitutionEntity = institutionDetailsRepository.save(institutionEntity);
            assertNotNull(savedInstitutionEntity);
            assertNotNull(savedInstitutionEntity.getId());
            assertEquals("OXF", savedInstitutionEntity.getInstitutionCode());
            assertEquals("Oxford", savedInstitutionEntity.getInstitutionName());

            InstitutionEntity byInstitutionCode = institutionDetailsRepository.findByInstitutionCode("OXF");
            assertNotNull(byInstitutionCode);
            assertEquals("OXF", byInstitutionCode.getInstitutionCode());
            assertEquals("Oxford", byInstitutionCode.getInstitutionName());

            InstitutionEntity byInstitutionName = institutionDetailsRepository.findByInstitutionName("Oxford");
            assertNotNull(byInstitutionName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private InstitutionEntity getInstitutionEntity() {
        InstitutionEntity institutionEntity = new InstitutionEntity();
        institutionEntity.setInstitutionCode("OXF");
        institutionEntity.setInstitutionName("Oxford");
        return institutionEntity;
    }

}