package com.nbs.iais.ms.common.utils;

import com.nbs.iais.ms.common.dto.impl.*;
import com.nbs.iais.ms.common.dto.impl.mini.AgentMiniDTO;
import com.nbs.iais.ms.common.dto.impl.mini.BusinessFunctionMiniDTO;
import com.nbs.iais.ms.common.dto.impl.mini.ProcessDocumentationMiniDTO;
import com.nbs.iais.ms.common.dto.impl.mini.StatisticalProgramMiniDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DTOMocks {

    public static AccountDTO account() {
        AccountDTO accountDTO = new AccountDTO(1L);
        accountDTO.setName("Name Surname");
        accountDTO.setRole(AccountRole.USER);
        accountDTO.setUsername("username");
        accountDTO.setStatus(AccountStatus.ACTIVE);
        accountDTO.setEmail("name.surname@email.com");
        return accountDTO;
    }

    public static AgentMiniDTO agentMini() {
        final AgentMiniDTO agentMiniDTO = new AgentMiniDTO(1L);
        agentMiniDTO.setName("Organization");
        agentMiniDTO.setType(AgentType.ORGANIZATION);
        agentMiniDTO.setLink("/agents/1");

        return agentMiniDTO;
    }

    public static AgentMiniDTO maintainer() {
        final AgentMiniDTO agentMiniDTO = new AgentMiniDTO(1L);
        agentMiniDTO.setName("Division");
        agentMiniDTO.setType(AgentType.DIVISION);
        agentMiniDTO.setLink("/agents/2");
        return agentMiniDTO;
    }

    public static LegislativeReferenceDTO legislativeReference() {
        final LegislativeReferenceDTO legislativeReferenceDTO = new LegislativeReferenceDTO(1L);
        legislativeReferenceDTO.setName("Legislative Reference");
        legislativeReferenceDTO.setDescription("Description");
        legislativeReferenceDTO.setLocalId("2034/2");
        legislativeReferenceDTO.setApproval(LocalDateTime.of(2005, 10, 4, 10, 30));
        legislativeReferenceDTO.setLink("/legislative/references/1");
        legislativeReferenceDTO.setVersion("232");
        legislativeReferenceDTO.setVersionDate(LocalDateTime.of(2008, 12, 3, 10, 30));
        legislativeReferenceDTO.setType(LegislativeType.REGULATION);
        legislativeReferenceDTO.setExternalLink("https://country.gov/link/to/gov/law.gov");
        return legislativeReferenceDTO;
    }

    public static AgentMiniDTO contact() {
        final AgentMiniDTO agentMiniDTO = new AgentMiniDTO(1L);
        agentMiniDTO.setName("Individual");
        agentMiniDTO.setType(AgentType.INDIVIDUAL);
        agentMiniDTO.setLink("/agents/3");
        return agentMiniDTO;
    }

    public static AgentDTO agent() {
        final AgentDTO agentDTO = new AgentDTO(2L);
        agentDTO.setName("Division");
        agentDTO.setType(AgentType.DIVISION);
        agentDTO.setDescription("description");
        agentDTO.setLink("/agents/" + agentDTO.getId().toString());
        agentDTO.setParent(agentMini());
        agentDTO.setLocalId("DPAI");
        return agentDTO;
    }

    public static BusinessFunctionDTO businessFunction() {
        final BusinessFunctionDTO businessFunctionDTO = new BusinessFunctionDTO(1L);
        businessFunctionDTO.setName("Specify Needs");
        businessFunctionDTO.setDescription("Description of the sub-phase");
        businessFunctionDTO.setPhaseId(1);
        businessFunctionDTO.setPhase("Identify Needs");
        businessFunctionDTO.setLocalId("1.1");
        businessFunctionDTO.setVersion("5.1");
        businessFunctionDTO.setLink("/business/functions/1");
        return businessFunctionDTO;
    }

    public static BusinessServiceDTO businessService() {
        final BusinessServiceDTO businessServiceDTO = new BusinessServiceDTO(1L);
        businessServiceDTO.setName("Software");
        businessServiceDTO.setDescription("Description of software");
        businessServiceDTO.setLocalId("localId");
        businessServiceDTO.setVersion("1.0");
        businessServiceDTO.setVersionDate(LocalDateTime.now());
        businessServiceDTO.setVersionRationale("New version");
        final List<String> interfaces = new ArrayList<>();
        interfaces.add("Interface1");
        interfaces.add("Interface2");
        businessServiceDTO.setInterfaces(interfaces);
        businessServiceDTO.setLink("/business/service/1");
        return businessServiceDTO;
    }

    public static BusinessFunctionMiniDTO businessFunctionMini() {
        final BusinessFunctionMiniDTO businessFunctionMiniDTO = new BusinessFunctionMiniDTO(1L);
        businessFunctionMiniDTO.setName("Specify Needs");
        businessFunctionMiniDTO.setLocalId("1.1");
        businessFunctionMiniDTO.setLink("/business/functions/1");
        return businessFunctionMiniDTO;
    }

    public static StatisticalProgramDTO statisticalProgram() {
        final StatisticalProgramDTO statisticalProgramDTO = new StatisticalProgramDTO(1L);
        statisticalProgramDTO.setName("Labor Force Survey");
        statisticalProgramDTO.setDescription("Description");
        statisticalProgramDTO.setBudget(0.0);
        statisticalProgramDTO.setVersion("1.0");
        statisticalProgramDTO.setLink("/statistical/programs/1");
        statisticalProgramDTO.setLocalId("lfs001");
        statisticalProgramDTO.setDateInitiated(LocalDateTime.of(2000, 10, 20,0, 0));
        statisticalProgramDTO.setDateEnded(LocalDateTime.of(2020, 10, 20,0, 0));
        statisticalProgramDTO.setProgramStatus(ProgramStatus.CURRENT);
        statisticalProgramDTO.setSourceOfFunding("NBS");
        statisticalProgramDTO.setAcronym("LFS");
        statisticalProgramDTO.setContact(contact());
        statisticalProgramDTO.setMaintainer(maintainer());
        statisticalProgramDTO.setOwner(agentMini());

        statisticalProgramDTO.setStatisticalStandards(DTOList.create(statisticalStandard()));
        statisticalProgramDTO.setLegislativeReferences(DTOList.create(legislativeReference()));

        statisticalProgramDTO.setProcessDocumentations(DTOList.create(processDocumentationMini()));

        return statisticalProgramDTO;
    }

    public static ProcessDocumentationDTO processDocumentation() {
        final ProcessDocumentationDTO processDocumentationDTO = new ProcessDocumentationDTO(1L);
        processDocumentationDTO.setBusinessFunction(businessFunctionMini());
        processDocumentationDTO.setStatisticalProgram(statisticalProgramMini());
        processDocumentationDTO.setDescription("Description");
        processDocumentationDTO.setMaintainer(maintainer());
        processDocumentationDTO.setFrequency(Frequency.YEARLY);
        return processDocumentationDTO;
    }

    public static StatisticalProgramMiniDTO statisticalProgramMini() {
        final StatisticalProgramMiniDTO statisticalProgramMiniDTO = new StatisticalProgramMiniDTO(1L);
        statisticalProgramMiniDTO.setName("Labor Force Survey");
        statisticalProgramMiniDTO.setAcronym("LFS");
        statisticalProgramMiniDTO.setDescription("Description");
        statisticalProgramMiniDTO.setLink("/statistical/programs/1");
        return statisticalProgramMiniDTO;
    }

    public static ProcessDocumentationMiniDTO processDocumentationMini() {
        final ProcessDocumentationMiniDTO processDocumentationMiniDTO = new ProcessDocumentationMiniDTO(1L);
        processDocumentationMiniDTO.setBusinessFunction(businessFunctionMini());
        processDocumentationMiniDTO.setStatisticalProgram(statisticalProgramMini());
        processDocumentationMiniDTO.setDescription("Description");
        processDocumentationMiniDTO.setMaintainer(maintainer());
        processDocumentationMiniDTO.setFrequency(Frequency.YEARLY);
        processDocumentationMiniDTO.setLink("/process/documentations/1");

        return processDocumentationMiniDTO;
    }

    public static StatisticalStandardDTO statisticalStandard() {

        final StatisticalStandardDTO statisticalStandardDTO = new StatisticalStandardDTO(1L);
        statisticalStandardDTO.setName("Name");
        statisticalStandardDTO.setDescription("Description");
        statisticalStandardDTO.setLink("http://iais.nbs.com/statistical/standard/1");
        statisticalStandardDTO.setExternalLink("http://external/link/statistical/standard");
        statisticalStandardDTO.setType(StatisticalStandardType.CLASSIFICATIONS);
        statisticalStandardDTO.setVersion("1.0");
        statisticalStandardDTO.setVersionDate(LocalDateTime.now());
        statisticalStandardDTO.setVersionRationale("Version Rationale");
        statisticalStandardDTO.setLocalId("local_id");

        return statisticalStandardDTO;
    }

}
