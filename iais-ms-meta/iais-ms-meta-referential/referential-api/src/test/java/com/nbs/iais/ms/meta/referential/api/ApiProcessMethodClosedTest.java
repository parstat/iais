package com.nbs.iais.ms.meta.referential.api;

import com.nbs.iais.ms.common.api.messaging.gateway.IAISGateway;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.ProcessMethod;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.Frequency;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.api.controllers.ApiProcessDocumentationClosed;
import com.nbs.iais.ms.meta.referential.api.controllers.ApiProcessMethodClosed;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.*;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.method.CreateProcessMethodCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.method.DeleteProcessMethodCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.method.UpdateProcessMethodCommand;
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

import static com.nbs.iais.ms.common.utils.DTOMocks.processDocumentation;
import static com.nbs.iais.ms.common.utils.DTOMocks.processMethod;
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

@ExtendWith(SpringExtension.class)
@WebMvcTest(ApiProcessMethodClosed.class)
@AutoConfigureRestDocs
public class ApiProcessMethodClosedTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAISGateway iaisGateway;

    @Test
    public void createProcessMethod() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final CreateProcessMethodCommand command = CreateProcessMethodCommand.create(jwt, "localId", "name", "description", "1.0", "First version", LocalDateTime.of(2020,1,1, 0, 0), Language.ENG);
        command.getEvent().setData(processMethod());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);

        mockMvc.perform(post("/api/v1/close/referential/process/methods")
                .header("jwt-auth", jwt)
                .param("language", "en")
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("localId", command.getLocalId())
                .param("version", command.getVersion())
                .param("versionRationale", command.getVersionRationale())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
        ArgumentCaptor<CreateProcessMethodCommand> argumentCaptor = ArgumentCaptor.forClass(CreateProcessMethodCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentCreateProcessMethod() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final CreateProcessMethodCommand command = CreateProcessMethodCommand.create(jwt, "localId", "name", "description", "1.0", "First version", LocalDateTime.of(2020,1,1, 0, 0), Language.ENG);
        command.getEvent().setData(processMethod());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);

        mockMvc.perform(RestDocumentationRequestBuilders.post("/api/v1/close/referential/process/methods")
                .header("jwt-auth", jwt)
                .param("language", "en")
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("localId", command.getLocalId())
                .param("version", command.getVersion())
                .param("versionRationale", command.getVersionRationale())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(document("create-process-method",
                        requestHeaders(headerWithName("jwt-auth").description("JWT authentication token")),
                        requestParameters(
                                parameterWithName("language").description("language to get the result"),
                                parameterWithName("name").description("Name of the new process method"),
                                parameterWithName("description").description("Description of the new process method"),
                                parameterWithName("localId").description("LocalId of the new process method"),
                                parameterWithName("version").description("Version of the new process method"),
                                parameterWithName("versionRationale").description("rationale version of the new process method")
                        ),
                        responseFields(
                                fieldWithPath("id").description("The id of requested process method"),
                                fieldWithPath("localId").description("The localId of requested process method"),
                                fieldWithPath("name").description("The name of requested process method"),
                                fieldWithPath("version").description("The version of requested process method"),
                                fieldWithPath("description").description("The description of requested process method")
                        )
                        )).andReturn();
    }

    @Test
    public void updateProcessMethod() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final UpdateProcessMethodCommand command = UpdateProcessMethodCommand.create(jwt, 1L, "localId", "name", "description", Language.ENG);
        command.getEvent().setData(processMethod());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);

        mockMvc.perform(patch("/api/v1/close/referential/process/methods/{id}", 1L)
                .header("jwt-auth", jwt)
                .param("language", "en")
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("localId", command.getLocalId())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
        ArgumentCaptor<UpdateProcessMethodCommand> argumentCaptor = ArgumentCaptor.forClass(UpdateProcessMethodCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentUpdateProcessMethod() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final UpdateProcessMethodCommand command = UpdateProcessMethodCommand.create(jwt, 1L, "localId", "name", "description", Language.ENG);
        command.getEvent().setData(processMethod());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);

        mockMvc.perform(RestDocumentationRequestBuilders.patch("/api/v1/close/referential/process/methods/{id}", 1L)
                .header("jwt-auth", jwt)
                .param("language", "en")
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("localId", command.getLocalId())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(document("update-process-method",
                        requestHeaders(headerWithName("jwt-auth").description("JWT authentication token")),
                        requestParameters(
                                parameterWithName("language").description("language to get the result"),
                                parameterWithName("name").description("Name of the new process method"),
                                parameterWithName("description").description("Description of the new process method"),
                                parameterWithName("localId").description("LocalId of the new process method")
                        ),
                        pathParameters(
                                parameterWithName("id").description("Id of the new process method")
                        ),
                        responseFields(
                                fieldWithPath("id").description("The id of requested process method"),
                                fieldWithPath("localId").description("The localId of requested process method"),
                                fieldWithPath("name").description("The name of requested process method"),
                                fieldWithPath("version").description("The version of requested process method"),
                                fieldWithPath("description").description("The description of requested process method")
                        )
                )).andReturn();
    }

    @Test
    public void deleteProcessMethod() throws Exception  {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final DeleteProcessMethodCommand command = DeleteProcessMethodCommand.create(jwt, 1L);
        command.getEvent().setData(DTOBoolean.TRUE);
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(delete("/api/v1/close/referential/process/methods/{id}", 1L)
                .header("jwt-auth", jwt))
                .andDo(print()).andExpect(status().isOk());
        ArgumentCaptor<DeleteProcessMethodCommand> argumentCaptor = ArgumentCaptor.forClass(DeleteProcessMethodCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentDeleteProcessMethod() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final DeleteProcessMethodCommand command = DeleteProcessMethodCommand.create(jwt, 1L);
        command.getEvent().setData(DTOBoolean.TRUE);
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/v1/close/referential/process/methods/{id}", 1L)
                .header("jwt-auth", jwt))
                .andDo(document("delete-process-method",
                        requestHeaders(headerWithName("jwt-auth").description("JWT authentication token")),
                        pathParameters(
                                parameterWithName("id").description("Id of the process method to delete").attributes()
                        ),

                        responseFields(
                                fieldWithPath("result").description("True if the process method has been deleted").type(JsonFieldType.BOOLEAN)
                        ))).andReturn();
    }
}
