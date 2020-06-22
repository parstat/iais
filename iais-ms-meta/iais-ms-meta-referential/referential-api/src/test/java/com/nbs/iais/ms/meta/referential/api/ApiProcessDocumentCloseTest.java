package com.nbs.iais.ms.meta.referential.api;

import com.nbs.iais.ms.common.api.messaging.gateway.IAISGateway;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.MediaType;
import com.nbs.iais.ms.meta.referential.api.controllers.ApiProcessDocumentClosed;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document.AddProcessDocumentationDocumentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document.RemoveProcessDocumentationDocumentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.document.UpdateProcessDocumentCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.nbs.iais.ms.common.utils.DTOMocks.processDocument;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ApiProcessDocumentClosed.class)
@AutoConfigureRestDocs
public class ApiProcessDocumentCloseTest {

    /*@Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAISGateway iaisGateway;

    @Test
    public void createProcessDocument() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final AddProcessDocumentationDocumentCommand command = AddProcessDocumentationDocumentCommand.create(jwt, 1L, "name", "description", "localId", "link", MediaType.APPLICATION_JSON, "version", LocalDateTime.now(), "New version", Language.ENG);
        command.getEvent().setData(processDocument());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(post("/api/v1/close/referential/process/documents/{processDocumentation}", 1L)
                .header("jwt-auth", jwt)
                .param("language", "en")
                .param("name", command.getName())
                .param("type", command.getType().toString())
                .param("description", command.getDescription())
                .param("local_id", command.getLocalId())
                .param("link", command.getLink())
                .param("version", command.getVersion())
                .param("versionDate", command.getVersionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("versionRationale", command.getVersionRationale()))
                .andDo(print()).andExpect(status().isOk());
        ArgumentCaptor<AddProcessDocumentationDocumentCommand> argumentCaptor = ArgumentCaptor.forClass(AddProcessDocumentationDocumentCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentCreateProcessDocument() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final AddProcessDocumentationDocumentCommand command = AddProcessDocumentationDocumentCommand.create(jwt, 1L, "name", "description", "localId", "link", MediaType.APPLICATION_JSON, "version", LocalDateTime.now(), "New version", Language.ENG);
        command.getEvent().setData(processDocument());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(RestDocumentationRequestBuilders.post("/api/v1/close/referential/process/documents/{processDocumentation}", 1L)
                .header("jwt-auth", jwt)
                .param("language", "en")
                .param("name", command.getName())
                .param("type", command.getType().toString())
                .param("description", command.getDescription())
                .param("local_id", command.getLocalId())
                .param("link", command.getLink())
                .param("version", command.getVersion())
                .param("versionDate", command.getVersionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("versionRationale", command.getVersionRationale()))
                .andDo(document("create-process-document",
                        requestParameters(
                                parameterWithName("language").description("language to get the result").optional(),
                                parameterWithName("name").description("Name of the requested process document"),
                                parameterWithName("type").description("Type of the requested process document"),
                                parameterWithName("description").description("Description of the requested process document").optional(),
                                parameterWithName("local_id").description("LocalId of the requested process document"),
                                parameterWithName("link").description("Link of the requested process document").optional(),
                                parameterWithName("version").description("Version of the requested process document").optional(),
                                parameterWithName("versionDate").description("Date version of the requested process document").optional(),
                                parameterWithName("versionRationale").description("Version rationale of the requested process document").optional()
                        ),
                        pathParameters(parameterWithName("processDocumentation").description("Process documentation of the requested process document")),
                        responseFields(
                                fieldWithPath("id").description("The id of requested process document").type(JsonFieldType.NUMBER),
                                fieldWithPath("link").description("Link of requested process document").type(JsonFieldType.STRING),
                                fieldWithPath("localId").description("LocalId of requested process document").type(JsonFieldType.STRING),
                                fieldWithPath("name").description("Name of requested process document").type(JsonFieldType.STRING),
                                fieldWithPath("version").description("Version of requested process document").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description of requested process document").type(JsonFieldType.STRING),
                                fieldWithPath("mediaType").description("MediaType of requested process document").type(JsonFieldType.STRING)
                        ))).andReturn();
    }

    @Test
    public void updateProcessDocument() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final UpdateProcessDocumentCommand command = UpdateProcessDocumentCommand.create(jwt, 1L, "name", "description", MediaType.APPLICATION_JSON, "localId", "likn", "version", LocalDateTime.now(), "New version", Language.ENG);
        command.getEvent().setData(processDocument());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(patch("/api/v1/close/referential/process/documents/{id}", 1L)
                .header("jwt-auth", jwt)
                .param("language", "en")
                .param("name", command.getName())
                .param("type", command.getType().toString())
                .param("description", command.getDescription())
                .param("local_id", command.getLocalId())
                .param("version", command.getVersion())
                .param("versionDate", command.getVersionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("versionRationale", command.getVersionRationale())
                .accept(org.springframework.http.MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
        ArgumentCaptor<UpdateProcessDocumentCommand> argumentCaptor = ArgumentCaptor.forClass(UpdateProcessDocumentCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentUpdateProcessDocument() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final UpdateProcessDocumentCommand command = UpdateProcessDocumentCommand.create(jwt, 1L, "name", "description", MediaType.APPLICATION_JSON, "localId", "likn", "version", LocalDateTime.now(), "New version", Language.ENG);
        command.getEvent().setData(processDocument());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(RestDocumentationRequestBuilders.patch("/api/v1/close/referential/process/documents/{id}",1L)
                .header("jwt-auth", jwt)
                .param("language", "en")
                .param("name", command.getName())
                .param("type", command.getType().toString())
                .param("description", command.getDescription())
                .param("local_id", command.getLocalId())
                .param("version", command.getVersion())
                .param("versionDate", command.getVersionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("versionRationale", command.getVersionRationale())
                .accept(org.springframework.http.MediaType.APPLICATION_JSON))
                .andDo(document("update-process-document",
                        requestParameters(
                                parameterWithName("language").description("language to get the result").optional(),
                                parameterWithName("name").description("Name of the requested process document").optional(),
                                parameterWithName("type").description("Type of the requested process document").optional(),
                                parameterWithName("description").description("Description of the requested process document").optional(),
                                parameterWithName("local_id").description("LocalId of the requested process document").optional(),
                                parameterWithName("link").description("Link of the requested process document").optional(),
                                parameterWithName("version").description("Version of the requested process document").optional(),
                                parameterWithName("versionDate").description("Date version of the requested process document").optional(),
                                parameterWithName("versionRationale").description("Version rationale of the requested process document").optional()
                        ),
                        pathParameters(parameterWithName("id").description("Id of the requested process document")),
                        responseFields(
                                fieldWithPath("id").description("The id of requested process document").type(JsonFieldType.NUMBER),
                                fieldWithPath("link").description("Link of requested process document").type(JsonFieldType.STRING),
                                fieldWithPath("localId").description("LocalId of requested process document").type(JsonFieldType.STRING),
                                fieldWithPath("name").description("Name of requested process document").type(JsonFieldType.STRING),
                                fieldWithPath("version").description("Version of requested process document").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description of requested process document").type(JsonFieldType.STRING),
                                fieldWithPath("mediaType").description("MediaType of requested process document").type(JsonFieldType.STRING)
                        ))).andReturn();
    }

    @Test
    public void deleteProcessDocument()throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final RemoveProcessDocumentationDocumentCommand command = RemoveProcessDocumentationDocumentCommand.create(jwt, 1L);
        command.getEvent().setData(DTOBoolean.TRUE);
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(delete("/api/v1/close/referential/process/documents/{id}", 1L)
                .header("jwt-auth", jwt))
                .andDo(print()).andExpect(status().isOk());
        ArgumentCaptor<RemoveProcessDocumentationDocumentCommand> argumentCaptor = ArgumentCaptor.forClass(RemoveProcessDocumentationDocumentCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentDeleteProcessDocument() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final RemoveProcessDocumentationDocumentCommand command = RemoveProcessDocumentationDocumentCommand.create(jwt, 1L);
        command.getEvent().setData(DTOBoolean.TRUE);
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/v1/close/referential/process/documents/{id}", 1L)
                .header("jwt-auth", jwt))
                .andDo(document("delete-process-document",
                        requestHeaders(headerWithName("jwt-auth").description("JWT authentication token")),
                        pathParameters(
                                parameterWithName("id").description("Id of the process document to delete").attributes()
                        ),

                        responseFields(
                                fieldWithPath("result").description("True if the process documentation has been deleted").type(JsonFieldType.BOOLEAN)
                        ))).andReturn();
    }*/
}
