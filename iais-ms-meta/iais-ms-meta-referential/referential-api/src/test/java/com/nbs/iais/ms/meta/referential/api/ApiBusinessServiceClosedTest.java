package com.nbs.iais.ms.meta.referential.api;

import com.nbs.iais.ms.common.api.messaging.gateway.IAISGateway;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.api.controllers.ApiBusinessServiceClosed;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.nbs.iais.ms.common.utils.DTOMocks.businessService;
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
@WebMvcTest(ApiBusinessServiceClosed.class)
@AutoConfigureRestDocs
public class ApiBusinessServiceClosedTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAISGateway iaisGateway;

    @Test
    public void createBusinessService() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final CreateBusinessServiceCommand command = CreateBusinessServiceCommand.create(jwt, "name", "description", "localId", "1.0", "New version", LocalDateTime.now(), Language.ENG);
        command.getEvent().setData(businessService());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(put("/api/v1/close/referential/business/services/{local_id}", "localId")
                .header("jwt-auth", jwt)
                .param("language","en")
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("version", command.getVersion())
                .param("versionDate", command.getVersionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("versionRationale", command.getVersionRationale()))
                .andDo(print()).andExpect(status().isOk());
        ArgumentCaptor<CreateBusinessServiceCommand> argumentCaptor = ArgumentCaptor.forClass(CreateBusinessServiceCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentCreateBusinessService() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final CreateBusinessServiceCommand command = CreateBusinessServiceCommand.create(jwt, "name", "description", "localId", "1.0", "New version", LocalDateTime.now(), Language.ENG);
        command.getEvent().setData(businessService());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(RestDocumentationRequestBuilders.put("/api/v1/close/referential/business/services/{local_id}", "localId")
                .header("jwt-auth", jwt)
                .param("language","en")
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("version", command.getVersion())
                .param("versionDate", command.getVersionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("versionRationale", command.getVersionRationale()))
                .andDo(document("create-business-service",
                        requestParameters(
                                parameterWithName("language").description("language to get the result"),
                                parameterWithName("name").description("Name of the requested business service"),
                                parameterWithName("description").description("Description of the requested business service"),
                                parameterWithName("version").description("Version of the requested business service"),
                                parameterWithName("versionDate").description("Date version of the requested business service"),
                                parameterWithName("versionRationale").description("VersionRationale of the requested business service")
                        ),
                        pathParameters(
                                parameterWithName("local_id").description("localId of the requested business service")
                        ),
                        responseFields(
                                fieldWithPath("id").description("The id of requested business service").type(JsonFieldType.NUMBER),
                                fieldWithPath("name").description("Name of the requested business service").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description or the requested business service").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the requested business service").type(JsonFieldType.STRING),
                                fieldWithPath("localId").description("LocalId of the requested business service").type(JsonFieldType.STRING),
                                fieldWithPath("version").description("Version of the requested business service").type(JsonFieldType.STRING),
                                fieldWithPath("interfaces").description("Interfaces of the requested business service").type(JsonFieldType.ARRAY)
                        ))).andReturn();
    }

    @Test
    public void updateBusinessService() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final UpdateBusinessServiceCommand command = UpdateBusinessServiceCommand.create(jwt, 1L,"Software", "Description of software", "1.0", "New version", LocalDateTime.now(), Language.ENG);
        command.getEvent().setData(businessService());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(patch("/api/v1/close/referential/business/services/{id}", 1L)
                .header("jwt-auth", jwt)
                .param("language", "en")
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("version", command.getVersion())
                .param("versionDate", command.getVersionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("versionRationale", command.getVersionRationale()))
                .andDo(print()).andExpect(status().isOk());
        ArgumentCaptor<UpdateBusinessServiceCommand> argumentCaptor = ArgumentCaptor.forClass(UpdateBusinessServiceCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentUpdateBusinessService() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final UpdateBusinessServiceCommand command = UpdateBusinessServiceCommand.create(jwt, 1L,"name", "description", "1.0", "New version", LocalDateTime.now(), Language.ENG);
        command.getEvent().setData(businessService());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(RestDocumentationRequestBuilders.patch("/api/v1/close/referential/business/services/{id}", 1L)
                .header("jwt-auth", jwt)
                .param("language", "en")
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("version", command.getVersion())
                .param("versionDate", command.getVersionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("versionRationale", command.getVersionRationale()))
                .andDo(document("update-business-service",
                        requestParameters(
                                parameterWithName("language").description("language to get the result"),
                                parameterWithName("name").description("Name of the requested business service"),
                                parameterWithName("description").description("Description of the requested business service"),
                                parameterWithName("version").description("Version of the requested business service"),
                                parameterWithName("versionDate").description("Date version of the requested business service"),
                                parameterWithName("versionRationale").description("VersionRationale of the requested business service")
                        ),
                        pathParameters(
                                parameterWithName("id").description("Id of the requested business service")
                        ),
                        responseFields(
                                fieldWithPath("id").description("The id of requested business service").type(JsonFieldType.NUMBER),
                                fieldWithPath("name").description("Name of the requested business service").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description or the requested business service").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the requested business service").type(JsonFieldType.STRING),
                                fieldWithPath("localId").description("LocalId of the requested business service").type(JsonFieldType.STRING),
                                fieldWithPath("version").description("Version of the requested business service").type(JsonFieldType.STRING),
                                fieldWithPath("interfaces").description("Interfaces of the requested business service").type(JsonFieldType.ARRAY)
                        ))).andReturn();
    }

    @Test
    public void deleteBusinessService() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final DeleteBusinessServiceCommand command = DeleteBusinessServiceCommand.crete(jwt, 1L);
        command.getEvent().setData(DTOBoolean.TRUE);
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(delete("/api/v1/close/referential/business/services/{id}", 1L)
                .header("jwt-auth", jwt))
                .andDo(print()).andExpect(status().isOk());
        ArgumentCaptor<DeleteBusinessServiceCommand> argumentCaptor = ArgumentCaptor.forClass(DeleteBusinessServiceCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentDeleteBusinessService() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final DeleteBusinessServiceCommand command = DeleteBusinessServiceCommand.crete(jwt, 1L);
        command.getEvent().setData(DTOBoolean.TRUE);
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/v1/close/referential/business/services/{id}", 1L)
                .header("jwt-auth", jwt))
                .andDo(document("delete-business-service",
                        requestHeaders(headerWithName("jwt-auth").description("JWT authentication token")),
                        pathParameters(
                                parameterWithName("id").description("Id of the business service to delete").attributes()
                        ),

                        responseFields(
                                fieldWithPath("result").description("True if the process documentation has been deleted").type(JsonFieldType.BOOLEAN)
                        ))).andReturn();
    }

    @Test
    public void addInterface() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final AddBusinessServiceInterfaceCommand command = AddBusinessServiceInterfaceCommand.create(jwt, 1L, "Interface3", Language.ENG);
        command.getEvent().setData(businessService());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(put("/api/v1/close/referential/business/services/{id}/interface", 1L)
                .header("jwt-auth", jwt)
                .param("service_interface", command.getServiceInterface())
                .param("language", "en"))
                .andDo(print()).andExpect(status().isOk());
        ArgumentCaptor<AddBusinessServiceInterfaceCommand> argumentCaptor = ArgumentCaptor.forClass(AddBusinessServiceInterfaceCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentAddInterface() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final AddBusinessServiceInterfaceCommand command = AddBusinessServiceInterfaceCommand.create(jwt, 1L, "Interface3", Language.ENG);
        command.getEvent().setData(businessService());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(RestDocumentationRequestBuilders.put("/api/v1/close/referential/business/services/{id}/interface", 1L)
                .header("jwt-auth", jwt)
                .param("service_interface", command.getServiceInterface())
                .param("language", "en"))
                .andDo(document("add-business-service-interface",
                        requestParameters(
                                parameterWithName("language").description("language to get the result").optional(),
                                parameterWithName("service_interface").description("Name of the requested business service")
                        ),
                        pathParameters(
                                parameterWithName("id").description("Id of the requested business service")
                        ),
                        responseFields(
                                fieldWithPath("id").description("The id of requested business service").type(JsonFieldType.NUMBER),
                                fieldWithPath("name").description("Name of the requested business service").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description or the requested business service").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the requested business service").type(JsonFieldType.STRING),
                                fieldWithPath("localId").description("LocalId of the requested business service").type(JsonFieldType.STRING),
                                fieldWithPath("version").description("Version of the requested business service").type(JsonFieldType.STRING),
                                fieldWithPath("interfaces").description("Interfaces of the requested business service").type(JsonFieldType.ARRAY)
                        ))).andReturn();
    }

    @Test
    public void removeInterface() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final RemoveBusinessServiceInterfaceCommand command = RemoveBusinessServiceInterfaceCommand.create(jwt, 1L, "Service1", Language.ENG);
        command.getEvent().setData(businessService());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(delete("/api/v1/close/referential/business/services/{id}/interface", 1L)
                .header("jwt-auth", jwt)
                .param("language","en")
                .param("service_interface", command.getServiceInterface()))
                .andDo(print()).andExpect(status().isOk());
        ArgumentCaptor<RemoveBusinessServiceInterfaceCommand> argumentCaptor = ArgumentCaptor.forClass(RemoveBusinessServiceInterfaceCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentRemoveInterface() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final RemoveBusinessServiceInterfaceCommand command = RemoveBusinessServiceInterfaceCommand.create(jwt, 1L, "Service1", Language.ENG);
        command.getEvent().setData(businessService());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/v1/close/referential/business/services/{id}/interface", 1L)
                .header("jwt-auth", jwt)
                .param("language", "en")
                .param("service_interface", command.getServiceInterface()))
                .andDo(document("remove-business-service-interface",
                        requestParameters(
                                parameterWithName("language").description("language to get the result").optional(),
                                parameterWithName("service_interface").description("Name of the requested business service")
                        ),
                        pathParameters(
                                parameterWithName("id").description("Id of the requested business service")
                        ),
                        responseFields(
                                fieldWithPath("id").description("The id of requested business service").type(JsonFieldType.NUMBER),
                                fieldWithPath("name").description("Name of the requested business service").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description or the requested business service").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the requested business service").type(JsonFieldType.STRING),
                                fieldWithPath("localId").description("LocalId of the requested business service").type(JsonFieldType.STRING),
                                fieldWithPath("version").description("Version of the requested business service").type(JsonFieldType.STRING),
                                fieldWithPath("interfaces").description("Interfaces of the requested business service").type(JsonFieldType.ARRAY)
                        ))).andReturn();
    }
}
