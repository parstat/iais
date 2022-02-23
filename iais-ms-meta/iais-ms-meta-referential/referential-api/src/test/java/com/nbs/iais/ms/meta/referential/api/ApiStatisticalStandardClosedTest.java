package com.nbs.iais.ms.meta.referential.api;

import com.nbs.iais.ms.common.api.messaging.gateway.IAISGateway;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.StatisticalStandardType;
import com.nbs.iais.ms.meta.referential.api.controllers.ApiStatisticalStandardClosed;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.CreateLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.CreateStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.DeleteStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.UpdateStatisticalStandardCommand;
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
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.nbs.iais.ms.common.utils.DTOMocks.statisticalStandard;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ApiStatisticalStandardClosed.class)
@AutoConfigureRestDocs
public class ApiStatisticalStandardClosedTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAISGateway iaisGateway;

    @Test
    public void createStatisticalStandardTest() throws Exception {

        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final CreateStatisticalStandardCommand command = CreateStatisticalStandardCommand.create(jwt, "Name", "description", "834/2", StatisticalStandardType.CLASSIFICATIONS, "version", LocalDateTime.now(),"rationale", "https://external/link/standard.com", Language.ENG);
        command.getEvent().setData(statisticalStandard());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(post("/api/v1/close/referential/statistical/standards")
                .header("jwt-auth", jwt)
                .param("type", command.getType().toString())
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("localId", command.getLocalId())
                .param("link", command.getLink())
                .param("version", command.getVersion())
                .param("versionDate", command.getVersionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("versionRationale", command.getVersionRationale())
                .param("language", "en")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<CreateLegislativeReferenceCommand> argumentCaptor = ArgumentCaptor.forClass(CreateLegislativeReferenceCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentCreateStatisticalStandard() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final CreateStatisticalStandardCommand command = CreateStatisticalStandardCommand.create(jwt, "Name", "description", "834/2", StatisticalStandardType.CLASSIFICATIONS, "version", LocalDateTime.now(),"rationale", "https://external/link/standard.com", Language.ENG);
        command.getEvent().setData(statisticalStandard());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(RestDocumentationRequestBuilders.post("/api/v1/close/referential/statistical/standards")
                .header("jwt-auth", jwt)
                .param("type", command.getType().toString())
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("localId", command.getLocalId())
                .param("link", command.getLink())
                .param("version", command.getVersion())
                .param("versionDate", command.getVersionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("versionRationale", command.getVersionRationale())
                .param("language", "en")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(document("create-statistical-standard",
                        requestHeaders(
                                headerWithName("jwt-auth").description("authentication token")
                        ),
                        requestParameters(
                                parameterWithName("language").description("language to get the result"),
                                parameterWithName("type").description("type of the statistical standard").optional(),
                                parameterWithName("name").description("Name of the statistical standard").optional(),
                                parameterWithName("description").description("Description of statistical standard").optional(),
                                parameterWithName("localId").description("Local id of the statistical standard").optional(),
                                parameterWithName("link").description("The external website link of the statistical standard").optional(),
                                parameterWithName("version").description("The version of the statistical standard, if any").optional(),
                                parameterWithName("versionDate").description("The date of the version of the statistical standard").optional(),
                                parameterWithName("versionRationale").description("Rationale for version of the statistical standard").optional(),
                                parameterWithName("language").description("Language to use. Short country name(en for English, ro for Romanian... default: en").optional()
                        ),
                        responseFields(
                                fieldWithPath("id").description("The id of requested statistical standard").type(JsonFieldType.NUMBER),
                                fieldWithPath("name").description("Name of the requested statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description or the requested statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the requested statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("externalLink").description("The official link of the statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("type").description("Type of statistical standard: CLASSIFICATIONS,\n" +
                                        "    CONCEPTS,\n" +
                                        "    DEFINITIONS,\n" +
                                        "    METHODOLOGIES,\n" +
                                        "    PROCEDURES,\n" +
                                        "    RECOMMENDATIONS,\n" +
                                        "    FRAMEWORK").type(JsonFieldType.STRING),
                                fieldWithPath("localId").description("The local id of the standard").type(JsonFieldType.STRING),
                                fieldWithPath("version").description("Version of the statistical standard").type(JsonFieldType.STRING)

                        ))).andReturn();

        ArgumentCaptor<UpdateStatisticalStandardCommand> argumentCaptor = ArgumentCaptor.forClass(UpdateStatisticalStandardCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void updateStatisticalStandard() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final UpdateStatisticalStandardCommand command = UpdateStatisticalStandardCommand.create(jwt, 1L, "law", "description", StatisticalStandardType.CLASSIFICATIONS, "localId", "version", LocalDateTime.now(), "rationale", "https://statistical/standard.com", Language.ENG);
        command.getEvent().setData(statisticalStandard());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(patch("/api/v1/close/referential/statistical/standards/{id}", command.getId())
                .header("jwt-auth", jwt)
                .param("type", command.getType().toString())
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("localId", command.getLocalId())
                .param("link", command.getLink())
                .param("version", command.getVersion())
                .param("versionDate", command.getVersionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("versionRationale", command.getVersionRationale())
                .param("language", "en")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<UpdateStatisticalStandardCommand> argumentCaptor = ArgumentCaptor.forClass(UpdateStatisticalStandardCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentUpdateStatisticalStandard() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final UpdateStatisticalStandardCommand command = UpdateStatisticalStandardCommand.create(jwt, 1L, "law", "description", StatisticalStandardType.CLASSIFICATIONS, "localId", "version", LocalDateTime.now(), "rationale", "https://statistical/standard.com", Language.ENG);
        command.getEvent().setData(statisticalStandard());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(RestDocumentationRequestBuilders.patch("/api/v1/close/referential/statistical/standards/{id}", command.getId())
                .header("jwt-auth", jwt)
                .param("type", command.getType().toString())
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("localId", command.getLocalId())
                .param("link", command.getLink())
                .param("version", command.getVersion())
                .param("versionDate", command.getVersionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("versionRationale", command.getVersionRationale())
                .param("language", "en")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(document("update-statistical-standard",
                        requestHeaders(
                                headerWithName("jwt-auth").description("authentication token")
                        ),
                        requestParameters(
                                parameterWithName("language").description("language to get the result"),
                                parameterWithName("type").description("type of the legislative reference").optional(),
                                parameterWithName("name").description("Name of the legislative reference").optional(),
                                parameterWithName("description").description("Description of legislative reference").optional(),
                                parameterWithName("localId").description("The legislative reference number").optional(),
                                parameterWithName("link").description("The governmental website link of the legislative reference").optional(),
                                parameterWithName("version").description("The version of the legislative reference, if any").optional(),
                                parameterWithName("versionDate").description("The date of the version of legislative reference").optional(),
                                parameterWithName("versionRationale").description("Rationale for version of legislative reference").optional(),
                                parameterWithName("language").description("Language to use. Short country name(en for English, ro for Romanian... default: en").optional()
                        ),
                        pathParameters(
                                parameterWithName("id").description("The id of the legislative reference")
                        ),
                        responseFields(
                                fieldWithPath("id").description("The id of requested statistical standard").type(JsonFieldType.NUMBER),
                                fieldWithPath("name").description("Name of the requested statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description or the requested statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the requested statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("externalLink").description("The official link of the statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("type").description("Type of statistical standard: CLASSIFICATIONS,\n" +
                                        "    CONCEPTS,\n" +
                                        "    DEFINITIONS,\n" +
                                        "    METHODOLOGIES,\n" +
                                        "    PROCEDURES,\n" +
                                        "    RECOMMENDATIONS,\n" +
                                        "    FRAMEWORK").type(JsonFieldType.STRING),
                                fieldWithPath("localId").description("The local id of the standard").type(JsonFieldType.STRING),
                                fieldWithPath("version").description("Version of the statistical standard").type(JsonFieldType.STRING)

                        ))).andReturn();

        ArgumentCaptor<UpdateStatisticalStandardCommand> argumentCaptor = ArgumentCaptor.forClass(UpdateStatisticalStandardCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void deleteStatisticalStandard() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final DeleteStatisticalStandardCommand command = DeleteStatisticalStandardCommand.create(jwt, 1L);
        command.getEvent().setData(DTOBoolean.TRUE);
        when(iaisGateway.sendCommand(any(DeleteStatisticalStandardCommand.class), anyString())).thenReturn(command);
        mockMvc.perform(delete("/api/v1/close/referential/statistical/standards/{id}", command.getId().toString())
                .header("jwt-auth", jwt))
                .andDo(print()).andExpect(status().isOk());
        ArgumentCaptor<DeleteStatisticalStandardCommand> argumentCaptor = ArgumentCaptor.forClass(DeleteStatisticalStandardCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentDeleteStatisticalStandard() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final DeleteStatisticalStandardCommand command = DeleteStatisticalStandardCommand.create(jwt, 1L);
        command.getEvent().setData(DTOBoolean.TRUE);
        when(iaisGateway.sendCommand(any(DeleteStatisticalStandardCommand.class), anyString())).thenReturn(command);
        mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/v1/close/referential/statistical/standards/{id}", command.getId())
                .header("jwt-auth", jwt))
                .andDo(document("delete-statistical-standard",
                        requestHeaders(headerWithName("jwt-auth").description("JWT authentication token")),
                        pathParameters(
                                parameterWithName("id").description("Id of the statistical standard to delete").attributes()
                        ),

                        responseFields(
                                fieldWithPath("result").description("True if the legislative reference has been deleted").type(JsonFieldType.BOOLEAN)
                        ))).andReturn();
    }


}
