package com.nbs.iais.ms.meta.referential.api;

import com.nbs.iais.ms.common.api.messaging.gateway.IAISGateway;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProgramStatus;
import com.nbs.iais.ms.meta.referential.api.controllers.ApiStatisticalProgramClosed;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.AddStatisticalProgramVersionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.CreateStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.DeleteStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.UpdateStatisticalProgramCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.nbs.iais.ms.common.utils.DTOMocks.statisticalProgram;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ApiStatisticalProgramClosed.class)
@AutoConfigureRestDocs
public class ApiStatisticalProgramClosedTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAISGateway iaisGateway;

    @Test
    public void createStatisticalProgram() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final LocalDateTime dateInitiated = LocalDateTime.of(2005,5,5, 10, 30);
        final LocalDateTime dateEnded = LocalDateTime.of(2020, 10, 5, 10, 30);
        final CreateStatisticalProgramCommand command = CreateStatisticalProgramCommand.create(jwt, "Labor Force Survey", "Description", "LFS", "lfs", dateInitiated, dateEnded, 1000.0, "NBS", ProgramStatus.CURRENT, 1L, 2L, 3L, Language.ENG);
        command.getEvent().setData(statisticalProgram());

        when(iaisGateway.sendCommand(any(CreateStatisticalProgramCommand.class), anyString())).thenReturn(command);

        mockMvc.perform(put("/api/v1/close/referential/statistical/programs/{local_id}", command.getLocalId())
                .header("jwt-auth", jwt)
                .param("name", command.getName())
                .param("acronym", command.getAcronym())
                .param("language", command.getLanguage().getShortName())
                .param("description", command.getDescription())
                .param("status", command.getStatus().toString())
                .param("budget", "2000")
                .param("funding", command.getSourceOfFunding())
                .param("dateInitiated", command.getDateInitiated().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("dateEnded", command.getDateEnded().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("owner", command.getOwner().toString())
                .param("maintainer", command.getMaintainer().toString())
                .param("contact", command.getContact().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<CreateStatisticalProgramCommand> argumentCaptor = ArgumentCaptor.forClass(CreateStatisticalProgramCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());

    }

    @Test
    public void documentCreateStatisticalProgram() throws Exception{
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final LocalDateTime dateInitiated = LocalDateTime.of(2005,5,5, 10, 30);
        final LocalDateTime dateEnded = LocalDateTime.of(2020, 10, 5, 10, 30);
        final CreateStatisticalProgramCommand command = CreateStatisticalProgramCommand.create(jwt, "Labor Force Survey", "Description", "LFS", "lfs", dateInitiated, dateEnded, 1000.0, "NBS", ProgramStatus.CURRENT, 1L, 2L, 3L, Language.ENG);
        command.getEvent().setData(statisticalProgram());

        when(iaisGateway.sendCommand(any(CreateStatisticalProgramCommand.class), anyString())).thenReturn(command);

        mockMvc.perform(RestDocumentationRequestBuilders.put("/api/v1/close/referential/statistical/programs/{local_id}", command.getLocalId())
                .header("jwt-auth", jwt)
                .param("name", command.getName())
                .param("acronym", command.getAcronym())
                .param("language", command.getLanguage().getShortName())
                .param("description", command.getDescription())
                .param("status", command.getStatus().toString())
                .param("budget", "2000")
                .param("funding", command.getSourceOfFunding())
                .param("dateInitiated", command.getDateInitiated().toString())
                .param("dateEnded", command.getDateEnded().toString())
                .param("owner", command.getOwner().toString())
                .param("maintainer", command.getMaintainer().toString())
                .param("contact", command.getContact().toString()))
                .andDo(document("create-statistical-program",
                        requestHeaders(headerWithName("jwt-auth").description("JWT authentication token")),
                        requestParameters(parameterWithName("language").description("language to get the result"),
                                parameterWithName("name").description("Name of the new statistical program").optional(),
                                parameterWithName("acronym").description("Acronym of the new statistical program").optional(),
                                parameterWithName("description").description("Description of new statistical program").optional(),
                                parameterWithName("version").description("Version of the new statistical program").optional(),
                                parameterWithName("status").description("Status of the new statistical program").optional(),
                                parameterWithName("budget").description("Budget of the new statistical program").optional(),
                                parameterWithName("funding").description("Source of funding for the new statistical program").optional(),
                                parameterWithName("dateInitiated").description("Date Initiated of the new statistical program").optional(),
                                parameterWithName("dateEnded").description("Date ended of the new statistical program").optional(),
                                parameterWithName("versionDate").description("Version date of statistical program").optional(),
                                parameterWithName("versionRationale").description("Reason of the first version of statistial program").optional(),
                                parameterWithName("owner").description("Id of Owner of the statistical program, usually an ORGANIZATION agent").optional(),
                                parameterWithName("maintainer").description("Id of the maintainer of the statistical program, usually a DIVISION agent ").optional(),
                                parameterWithName("contact").description("Id of the contact agent of the statistical program, usually an INDIVIDUAL agent").optional()),
                        pathParameters(
                                parameterWithName("local_id").description("Local id of the new created statistical program").attributes()
                        ),

                        responseFields(
                                fieldWithPath("id").description("The id of requested statistical program").type(JsonFieldType.NUMBER),
                                fieldWithPath("name").description("Name of the requested statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description or the requested statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("acronym").description("Acronym of statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the requested statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("version").description("Version of statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("localId").description("The local id of the statistical program"),
                                fieldWithPath("budget").description("Budget of statistical program").type(JsonFieldType.NUMBER),
                                fieldWithPath("dateInitiated").description("The initiated date of the statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("dateEnded").description("The ended date of the statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("sourceOfFunding").description("Source of Funding of the statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the entity").type(JsonFieldType.STRING),
                                fieldWithPath("programStatus").description("Status of the statistical program: CURRENT, COMPLETED, ").type(JsonFieldType.STRING),
                                fieldWithPath("contact").description("Contact agent of the statistical program").type(JsonFieldType.OBJECT),
                                fieldWithPath("contact.id").description("id of the contact agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("contact.name").description("Name of the contact agent").type(JsonFieldType.STRING),
                                fieldWithPath("contact.type").description("Type of contact agent, normally INDIVIDUAL").type(JsonFieldType.STRING),
                                fieldWithPath("contact.link").description("Client link of contact agent").type(JsonFieldType.STRING),
                                fieldWithPath("owner").description("Owner agent of the statistical program").type(JsonFieldType.OBJECT),
                                fieldWithPath("owner.id").description("Id of the owner agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("owner.name").description("Name of the owner agent").type(JsonFieldType.STRING),
                                fieldWithPath("owner.type").description("Type of owner agent, normally ORGANIZATION").type(JsonFieldType.STRING),
                                fieldWithPath("owner.link").description("Client link of owner agent").type(JsonFieldType.STRING),
                                fieldWithPath("maintainer").description("Maintainer agent of the statistical program").type(JsonFieldType.OBJECT),
                                fieldWithPath("maintainer.id").description("Id of the maintainer agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("maintainer.name").description("Name of the maintainer agent").type(JsonFieldType.STRING),
                                fieldWithPath("maintainer.type").description("Type of maintainer agent, normally DIVISION").type(JsonFieldType.STRING),
                                fieldWithPath("maintainer.link").description("Client link of contact agent").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[]").description("List of statistical standards of the statistical program").type(JsonFieldType.ARRAY),
                                fieldWithPath("statisticalStandards[].id").description("Id of the statistical standard").type(JsonFieldType.NUMBER),
                                fieldWithPath("statisticalStandards[].name").description("Name of the statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].description").description("Description of the statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].type").description("Type of statistical standard:  CLASSIFICATIONS, CONCEPTS, DEFINITIONS, METHODOLOGIES, PROCEDURES, RECOMMENDATIONS, FRAMEWORK").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].version").description("Version of statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].link").description("Client link of statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].externalLink").description("External link of statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].localId").description("Local id of statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[]").description("List of statistical standards of the legislative reference").type(JsonFieldType.ARRAY),
                                fieldWithPath("legislativeReferences[].id").description("Id of the legislative reference").type(JsonFieldType.NUMBER),
                                fieldWithPath("legislativeReferences[].name").description("Name of the legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].description").description("Description of the legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].type").description("Type of legislative reference:   REGULATION, LAW, CODE, GOVERNMENTAL_DECISION, AMENDMENT,").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].version").description("Version of legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].link").description("Client link of legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].externalLink").description("The governmental website link of the law").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].approval").description("First approval date of the legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].localId").description("Number of the legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[]").description("List of process (GSBPM sub-phases) documentations").type(JsonFieldType.ARRAY),
                                fieldWithPath("processDocumentations[].id").description("Id of process documentation").type(JsonFieldType.NUMBER),
                                fieldWithPath("processDocumentations[].description").description("Description of process for statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].frequency").description("Frequency of process for statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].link").description("Client link of process for statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].businessFunction").description("Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.OBJECT),
                                fieldWithPath("processDocumentations[].businessFunction.id").description("Id of Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.NUMBER),
                                fieldWithPath("processDocumentations[].businessFunction.name").description("Name of Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].businessFunction.link").description("Link of Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].businessFunction.localId").description("Local id of Business Function (GSBPM sub-phase number) being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].businessFunction.name").description("Name of Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].statisticalProgram").description("Statistical Program being documented").type(JsonFieldType.OBJECT),
                                fieldWithPath("processDocumentations[].statisticalProgram.id").description("Id of Statistical Program being documented").type(JsonFieldType.NUMBER),
                                fieldWithPath("processDocumentations[].statisticalProgram.name").description("Name of Statistical Program being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].statisticalProgram.acronym").description("Acronym of Statistical Program being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].statisticalProgram.description").description("Description of Statistical Program being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].statisticalProgram.link").description("Link of Statistical Program being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].maintainers[]").description("Maintainer of Process for statistical program, usually the responsible DIVISION agent").type(JsonFieldType.ARRAY),
                                fieldWithPath("processDocumentations[].maintainers[].id").description("Id of the maintainer agent of the process for statistical program").type(JsonFieldType.NUMBER),
                                fieldWithPath("processDocumentations[].maintainers[].name").description("Name of the maintainer agent of the process for statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].maintainers[].type").description("Type of maintainer agent of the process for statistical program, usually DIVISION").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].maintainers[].link").description("Client link of contact agent of the process for statistical program").type(JsonFieldType.STRING)


                        ))).andReturn();
    }

    @Test
    public void createVersionStatisticalProgram() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final LocalDateTime dateInitiated = LocalDateTime.of(2005,5,5, 10, 30);
        final LocalDateTime dateEnded = LocalDateTime.of(2020, 10, 5, 10, 30);
        final AddStatisticalProgramVersionCommand command = AddStatisticalProgramVersionCommand.create(jwt, "Labor Force Survey", "Description", "LFS", "lfs", "1.0", "1.1", LocalDateTime.now(), "New Version Added", dateInitiated, dateEnded, 1000.0, "NBS", ProgramStatus.CURRENT, 1L, 2L, 3L, Language.ENG);
        command.getEvent().setData(statisticalProgram());

        when(iaisGateway.sendCommand(any(AddStatisticalProgramVersionCommand.class), anyString())).thenReturn(command);

        mockMvc.perform(put("/api/v1/close/referential/statistical/programs/{local_id}/versions/{version}", command.getLocalId(), command.getVersion())
                .header("jwt-auth", jwt)
                .param("previousVersion", command.getPreviousVersion())
                .param("name", command.getName())
                .param("acronym", command.getAcronym())
                .param("language", command.getLanguage().getShortName())
                .param("description", command.getDescription())
                .param("status", command.getStatus().toString())
                .param("budget", "2000")
                .param("funding", command.getSourceOfFunding())
                .param("dateInitiated", command.getDateInitiated().toString())
                .param("dateEnded", command.getDateEnded().toString())
                .param("owner", command.getOwner().toString())
                .param("maintainer", command.getMaintainer().toString())
                .param("contact", command.getContact().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<AddStatisticalProgramVersionCommand> argumentCaptor = ArgumentCaptor.forClass(AddStatisticalProgramVersionCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());

    }

    @Test
    public void documentCreateVersionStatisticalProgram() throws Exception{
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final LocalDateTime dateInitiated = LocalDateTime.of(2005,5,5, 10, 30);
        final LocalDateTime dateEnded = LocalDateTime.of(2020, 10, 5, 10, 30);
        final AddStatisticalProgramVersionCommand command = AddStatisticalProgramVersionCommand.create(jwt, "Labor Force Survey", "Description", "LFS", "lfs", "1.0", "1.1", LocalDateTime.now(), "New Version Added", dateInitiated, dateEnded, 1000.0, "NBS", ProgramStatus.CURRENT, 1L, 2L, 3L, Language.ENG);
        command.getEvent().setData(statisticalProgram());

        when(iaisGateway.sendCommand(any(AddStatisticalProgramVersionCommand.class), anyString())).thenReturn(command);

        mockMvc.perform(RestDocumentationRequestBuilders.put("/api/v1/close/referential/statistical/programs/{local_id}/versions/{version}", command.getLocalId(), command.getVersion())
                .header("jwt-auth", jwt)
                .param("previousVersion", command.getPreviousVersion())
                .param("name", command.getName())
                .param("acronym", command.getAcronym())
                .param("language", command.getLanguage().getShortName())
                .param("description", command.getDescription())
                .param("status", command.getStatus().toString())
                .param("budget", "2000")
                .param("funding", command.getSourceOfFunding())
                .param("dateInitiated", command.getDateInitiated().toString())
                .param("dateEnded", command.getDateEnded().toString())
                .param("owner", command.getOwner().toString())
                .param("maintainer", command.getMaintainer().toString())
                .param("contact", command.getContact().toString()))
                .andDo(document("create-version-statistical-program",
                        requestHeaders(headerWithName("jwt-auth").description("JWT authentication token")),
                        requestParameters(parameterWithName("language").description("language to get the result"),
                                parameterWithName("previousVersion").description("The previous version, usually current version before adding the new one"),
                                parameterWithName("name").description("Name of the new statistical program").optional(),
                                parameterWithName("acronym").description("Acronym of the new statistical program").optional(),
                                parameterWithName("description").description("Description of new statistical program").optional(),
                                parameterWithName("status").description("Status of the new statistical program").optional(),
                                parameterWithName("budget").description("Budget of the new statistical program").optional(),
                                parameterWithName("funding").description("Source of funding for the new statistical program").optional(),
                                parameterWithName("dateInitiated").description("Date Initiated of the new statistical program").optional(),
                                parameterWithName("dateEnded").description("Date ended of the new statistical program").optional(),
                                parameterWithName("versionDate").description("Version date of statistical program").optional(),
                                parameterWithName("versionRationale").description("Reason of the first version of statistial program").optional(),
                                parameterWithName("owner").description("Id of Owner of the statistical program, usually an ORGANIZATION agent").optional(),
                                parameterWithName("maintainer").description("Id of the maintainer of the statistical program, usually a DIVISION agent ").optional(),
                                parameterWithName("contact").description("Id of the contact agent of the statistical program, usually an INDIVIDUAL agent").optional()),
                        pathParameters(
                                parameterWithName("local_id").description("Local id of the new created statistical program"),
                                parameterWithName("version").description("New version being added")
                        ),

                        responseFields(
                                fieldWithPath("id").description("The id of requested statistical program").type(JsonFieldType.NUMBER),
                                fieldWithPath("name").description("Name of the requested statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description or the requested statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("acronym").description("Acronym of statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the requested statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("version").description("Version of statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("localId").description("The local id of the statistical program"),
                                fieldWithPath("budget").description("Budget of statistical program").type(JsonFieldType.NUMBER),
                                fieldWithPath("dateInitiated").description("The initiated date of the statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("dateEnded").description("The ended date of the statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("sourceOfFunding").description("Source of Funding of the statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the entity").type(JsonFieldType.STRING),
                                fieldWithPath("programStatus").description("Status of the statistical program: CURRENT, COMPLETED, ").type(JsonFieldType.STRING),
                                fieldWithPath("contact").description("Contact agent of the statistical program").type(JsonFieldType.OBJECT),
                                fieldWithPath("contact.id").description("id of the contact agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("contact.name").description("Name of the contact agent").type(JsonFieldType.STRING),
                                fieldWithPath("contact.type").description("Type of contact agent, normally INDIVIDUAL").type(JsonFieldType.STRING),
                                fieldWithPath("contact.link").description("Client link of contact agent").type(JsonFieldType.STRING),
                                fieldWithPath("owner").description("Owner agent of the statistical program").type(JsonFieldType.OBJECT),
                                fieldWithPath("owner.id").description("Id of the owner agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("owner.name").description("Name of the owner agent").type(JsonFieldType.STRING),
                                fieldWithPath("owner.type").description("Type of owner agent, normally ORGANIZATION").type(JsonFieldType.STRING),
                                fieldWithPath("owner.link").description("Client link of owner agent").type(JsonFieldType.STRING),
                                fieldWithPath("maintainer").description("Maintainer agent of the statistical program").type(JsonFieldType.OBJECT),
                                fieldWithPath("maintainer.id").description("Id of the maintainer agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("maintainer.name").description("Name of the maintainer agent").type(JsonFieldType.STRING),
                                fieldWithPath("maintainer.type").description("Type of maintainer agent, normally DIVISION").type(JsonFieldType.STRING),
                                fieldWithPath("maintainer.link").description("Client link of contact agent").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[]").description("List of statistical standards of the statistical program").type(JsonFieldType.ARRAY),
                                fieldWithPath("statisticalStandards[].id").description("Id of the statistical standard").type(JsonFieldType.NUMBER),
                                fieldWithPath("statisticalStandards[].name").description("Name of the statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].description").description("Description of the statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].type").description("Type of statistical standard:  CLASSIFICATIONS, CONCEPTS, DEFINITIONS, METHODOLOGIES, PROCEDURES, RECOMMENDATIONS, FRAMEWORK").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].version").description("Version of statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].link").description("Client link of statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].externalLink").description("External link of statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].localId").description("Local id of statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].externalLink").description("External link of statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].localId").description("Local id of statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[]").description("List of statistical standards of the legislative reference").type(JsonFieldType.ARRAY),
                                fieldWithPath("legislativeReferences[].id").description("Id of the legislative reference").type(JsonFieldType.NUMBER),
                                fieldWithPath("legislativeReferences[].name").description("Name of the legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].description").description("Description of the legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].type").description("Type of legislative reference:   REGULATION, LAW, CODE, GOVERNMENTAL_DECISION, AMENDMENT,").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].version").description("Version of legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].link").description("Client link of legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].externalLink").description("The governmental website link of the law").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].approval").description("First approval date of the legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].localId").description("Number of the legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[]").description("List of process (GSBPM sub-phases) documentations").type(JsonFieldType.ARRAY),
                                fieldWithPath("processDocumentations[].id").description("Id of process documentation").type(JsonFieldType.NUMBER),
                                fieldWithPath("processDocumentations[].description").description("Description of process for statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].frequency").description("Frequency of process for statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].link").description("Client link of process for statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].businessFunction").description("Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.OBJECT),
                                fieldWithPath("processDocumentations[].businessFunction.id").description("Id of Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.NUMBER),
                                fieldWithPath("processDocumentations[].businessFunction.name").description("Name of Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].businessFunction.link").description("Link of Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].businessFunction.localId").description("Local id of Business Function (GSBPM sub-phase number) being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].businessFunction.name").description("Name of Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].statisticalProgram").description("Statistical Program being documented").type(JsonFieldType.OBJECT),
                                fieldWithPath("processDocumentations[].statisticalProgram.id").description("Id of Statistical Program being documented").type(JsonFieldType.NUMBER),
                                fieldWithPath("processDocumentations[].statisticalProgram.name").description("Name of Statistical Program being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].statisticalProgram.acronym").description("Acronym of Statistical Program being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].statisticalProgram.description").description("Description of Statistical Program being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].statisticalProgram.link").description("Link of Statistical Program being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].maintainers[]").description("Maintainer of Process for statistical program, usually the responsible DIVISION agent").type(JsonFieldType.ARRAY),
                                fieldWithPath("processDocumentations[].maintainers[].id").description("Id of the maintainer agent of the process for statistical program").type(JsonFieldType.NUMBER),
                                fieldWithPath("processDocumentations[].maintainers[].name").description("Name of the maintainer agent of the process for statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].maintainers[].type").description("Type of maintainer agent of the process for statistical program, usually DIVISION").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].maintainers[].link").description("Client link of contact agent of the process for statistical program").type(JsonFieldType.STRING)



                        ))).andReturn();
    }

    @Test
    public void updateStatisticalProgram() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final LocalDateTime dateInitiated = LocalDateTime.of(2005,5,5, 10, 30);
        final LocalDateTime dateEnded = LocalDateTime.of(2020, 10, 5, 10, 30);
        final UpdateStatisticalProgramCommand command = UpdateStatisticalProgramCommand.create(jwt, 1L, "Labor Force Survey", "Description", "LFS", "lfs", LocalDateTime.now(), dateInitiated, dateEnded, 1000.0, "NBS", ProgramStatus.CURRENT, Language.ENG);
        command.getEvent().setData(statisticalProgram());

        when(iaisGateway.sendCommand(any(UpdateStatisticalProgramCommand.class), anyString())).thenReturn(command);

        mockMvc.perform(patch("/api/v1/close/referential/statistical/programs/{id}", command.getId().toString())
                .header("jwt-auth", jwt)
                .param("name", command.getName())
                .param("acronym", command.getAcronym())
                .param("language", command.getLanguage().getShortName())
                .param("description", command.getDescription())
                .param("status", command.getStatus().toString())
                .param("budget", "2000")
                .param("funding", command.getSourceOfFunding())
                .param("dateInitiated", command.getDateInitiated().toString())
                .param("dateEnded", command.getDateEnded().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<UpdateStatisticalProgramCommand> argumentCaptor = ArgumentCaptor.forClass(UpdateStatisticalProgramCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());

    }

    @Test
    public void documentUpdateStatisticalProgram() throws Exception{
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final LocalDateTime dateInitiated = LocalDateTime.of(2005,5,5, 10, 30);
        final LocalDateTime dateEnded = LocalDateTime.of(2020, 10, 5, 10, 30);
        final UpdateStatisticalProgramCommand command = UpdateStatisticalProgramCommand.create(jwt, 1L, "Labor Force Survey", "Description", "LFS", "lfs", LocalDateTime.now(), dateInitiated, dateEnded, 1000.0, "NBS", ProgramStatus.CURRENT, Language.ENG);
        command.getEvent().setData(statisticalProgram());

        when(iaisGateway.sendCommand(any(UpdateStatisticalProgramCommand.class), anyString())).thenReturn(command);


        mockMvc.perform(RestDocumentationRequestBuilders.patch("/api/v1/close/referential/statistical/programs/{id}", command.getId())
                .header("jwt-auth", jwt)
                .param("name", command.getName())
                .param("acronym", command.getAcronym())
                .param("language", command.getLanguage().getShortName())
                .param("description", command.getDescription())
                .param("status", command.getStatus().toString())
                .param("budget", "2000")
                .param("funding", command.getSourceOfFunding())
                .param("dateInitiated", command.getDateInitiated().toString())
                .param("dateEnded", command.getDateEnded().toString()))
                .andDo(document("update-statistical-program",
                        requestHeaders(headerWithName("jwt-auth").description("JWT authentication token")),
                        pathParameters(
                                parameterWithName("id").description("Id of the updated statistical program").attributes()
                        ),
                        requestParameters(parameterWithName("language").description("language to get the result"),
                                parameterWithName("name").description("Name of the new statistical program").optional(),
                                parameterWithName("acronym").description("Acronym of the new statistical program").optional(),
                                parameterWithName("description").description("Description of new statistical program").optional(),
                                parameterWithName("version").description("Version of the new statistical program").optional(),
                                parameterWithName("status").description("Status of the new statistical program").optional(),
                                parameterWithName("budget").description("Budget of the new statistical program").optional(),
                                parameterWithName("funding").description("Source of funding for the new statistical program").optional(),
                                parameterWithName("dateInitiated").description("Date Initiated of the new statistical program").optional(),
                                parameterWithName("dateEnded").description("Date ended of the new statistical program").optional(),
                                parameterWithName("versionDate").description("Version date of statistical program").optional(),
                                parameterWithName("versionRationale").description("Reason of the first version of statistical program").optional()),
                        responseFields(
                                fieldWithPath("id").description("The id of requested statistical program").type(JsonFieldType.NUMBER),
                                fieldWithPath("name").description("Name of the requested statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description or the requested statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("acronym").description("Acronym of statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the requested statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("version").description("Version of statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("localId").description("The local id of the statistical program"),
                                fieldWithPath("budget").description("Budget of statistical program").type(JsonFieldType.NUMBER),
                                fieldWithPath("dateInitiated").description("The initiated date of the statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("dateEnded").description("The ended date of the statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("sourceOfFunding").description("Source of Funding of the statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the entity").type(JsonFieldType.STRING),
                                fieldWithPath("programStatus").description("Status of the statistical program: CURRENT, COMPLETED, ").type(JsonFieldType.STRING),
                                fieldWithPath("contact").description("Contact agent of the statistical program").type(JsonFieldType.OBJECT),
                                fieldWithPath("contact.id").description("id of the contact agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("contact.name").description("Name of the contact agent").type(JsonFieldType.STRING),
                                fieldWithPath("contact.type").description("Type of contact agent, normally INDIVIDUAL").type(JsonFieldType.STRING),
                                fieldWithPath("contact.link").description("Client link of contact agent").type(JsonFieldType.STRING),
                                fieldWithPath("owner").description("Owner agent of the statistical program").type(JsonFieldType.OBJECT),
                                fieldWithPath("owner.id").description("Id of the owner agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("owner.name").description("Name of the owner agent").type(JsonFieldType.STRING),
                                fieldWithPath("owner.type").description("Type of owner agent, normally ORGANIZATION").type(JsonFieldType.STRING),
                                fieldWithPath("owner.link").description("Client link of owner agent").type(JsonFieldType.STRING),
                                fieldWithPath("maintainer").description("Maintainer agent of the statistical program").type(JsonFieldType.OBJECT),
                                fieldWithPath("maintainer.id").description("Id of the maintainer agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("maintainer.name").description("Name of the maintainer agent").type(JsonFieldType.STRING),
                                fieldWithPath("maintainer.type").description("Type of maintainer agent, normally DIVISION").type(JsonFieldType.STRING),
                                fieldWithPath("maintainer.link").description("Client link of contact agent").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[]").description("List of statistical standards of the statistical program").type(JsonFieldType.ARRAY),
                                fieldWithPath("statisticalStandards[].id").description("Id of the statistical standard").type(JsonFieldType.NUMBER),
                                fieldWithPath("statisticalStandards[].name").description("Name of the statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].description").description("Description of the statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].type").description("Type of statistical standard:  CLASSIFICATIONS, CONCEPTS, DEFINITIONS, METHODOLOGIES, PROCEDURES, RECOMMENDATIONS, FRAMEWORK").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].version").description("Version of statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].link").description("Client link of statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].externalLink").description("External link of statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].localId").description("Local id of statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[]").description("List of statistical standards of the legislative reference").type(JsonFieldType.ARRAY),
                                fieldWithPath("legislativeReferences[].id").description("Id of the legislative reference").type(JsonFieldType.NUMBER),
                                fieldWithPath("legislativeReferences[].name").description("Name of the legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].description").description("Description of the legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].type").description("Type of legislative reference:   REGULATION, LAW, CODE, GOVERNMENTAL_DECISION, AMENDMENT,").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].version").description("Version of legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].link").description("Client link of legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].externalLink").description("The governmental website link of the law").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].approval").description("First approval date of the legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].localId").description("Number of the legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[]").description("List of process (GSBPM sub-phases) documentations").type(JsonFieldType.ARRAY),
                                fieldWithPath("processDocumentations[].id").description("Id of process documentation").type(JsonFieldType.NUMBER),
                                fieldWithPath("processDocumentations[].description").description("Description of process for statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].frequency").description("Frequency of process for statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].link").description("Client link of process for statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].businessFunction").description("Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.OBJECT),
                                fieldWithPath("processDocumentations[].businessFunction.id").description("Id of Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.NUMBER),
                                fieldWithPath("processDocumentations[].businessFunction.name").description("Name of Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].businessFunction.link").description("Link of Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].businessFunction.localId").description("Local id of Business Function (GSBPM sub-phase number) being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].businessFunction.name").description("Name of Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].statisticalProgram").description("Statistical Program being documented").type(JsonFieldType.OBJECT),
                                fieldWithPath("processDocumentations[].statisticalProgram.id").description("Id of Statistical Program being documented").type(JsonFieldType.NUMBER),
                                fieldWithPath("processDocumentations[].statisticalProgram.name").description("Name of Statistical Program being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].statisticalProgram.acronym").description("Acronym of Statistical Program being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].statisticalProgram.description").description("Description of Statistical Program being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].statisticalProgram.link").description("Link of Statistical Program being documented").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].maintainers[]").description("Maintainer of Process for statistical program, usually the responsible DIVISION agent").type(JsonFieldType.ARRAY),
                                fieldWithPath("processDocumentations[].maintainers[].id").description("Id of the maintainer agent of the process for statistical program").type(JsonFieldType.NUMBER),
                                fieldWithPath("processDocumentations[].maintainers[].name").description("Name of the maintainer agent of the process for statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].maintainers[].type").description("Type of maintainer agent of the process for statistical program, usually DIVISION").type(JsonFieldType.STRING),
                                fieldWithPath("processDocumentations[].maintainers[].link").description("Client link of contact agent of the process for statistical program").type(JsonFieldType.STRING)



                        ))).andReturn();
    }

    @Test
    public void deleteStatisticalProgram() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final DeleteStatisticalProgramCommand command = DeleteStatisticalProgramCommand.create(jwt, 1L);
        command.getEvent().setData(DTOBoolean.TRUE);

        when(iaisGateway.sendCommand(any(DeleteStatisticalProgramCommand.class), anyString())).thenReturn(command);

        mockMvc.perform(delete("/api/v1/close/referential/statistical/programs/{id}", command.getId().toString())
                .header("jwt-auth", jwt))
                .andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<DeleteStatisticalProgramCommand> argumentCaptor = ArgumentCaptor.forClass(DeleteStatisticalProgramCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());

    }

    @Test
    public void documentDeleteStatisticalProgram() throws Exception{
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final DeleteStatisticalProgramCommand command = DeleteStatisticalProgramCommand.create(jwt, 1L);
        command.getEvent().setData(DTOBoolean.TRUE);

        when(iaisGateway.sendCommand(any(DeleteStatisticalProgramCommand.class), anyString())).thenReturn(command);


        mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/v1/close/referential/statistical/programs/{id}", command.getId())
                .header("jwt-auth", jwt))
                .andDo(document("delete-statistical-program",
                        requestHeaders(headerWithName("jwt-auth").description("JWT authentication token")),
                        pathParameters(
                                parameterWithName("id").description("Id of the new created statistical program").attributes()
                        ),

                        responseFields(
                                fieldWithPath("result").description("True if statistical program has been deleted").type(JsonFieldType.BOOLEAN)
                        ))).andReturn();
    }

}
