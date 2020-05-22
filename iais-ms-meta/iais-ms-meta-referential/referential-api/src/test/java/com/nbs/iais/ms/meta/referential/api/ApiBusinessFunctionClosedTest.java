package com.nbs.iais.ms.meta.referential.api;

import com.nbs.iais.ms.common.api.messaging.gateway.IAISGateway;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.api.controllers.ApiBusinessFunctionClosed;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.CreateBusinessFunctionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function.UpdateBusinessFunctionCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.nbs.iais.ms.common.utils.DTOMocks.businessFunction;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ApiBusinessFunctionClosed.class)
@AutoConfigureRestDocs
public class ApiBusinessFunctionClosedTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAISGateway iaisGateway;

    @Test
    public void createBusinessFunction() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final CreateBusinessFunctionCommand command = CreateBusinessFunctionCommand.create(jwt, "name", "Description", "1.1", Language.ENG);
        command.getEvent().setData(businessFunction());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(put("/api/v1/referential/business/functions/{local_id}", "1.1")
                .header("jwt-auth", jwt)
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("language", "em")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<CreateBusinessFunctionCommand> argumentCaptor = ArgumentCaptor.forClass(CreateBusinessFunctionCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentCreateBusinessFunction() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final CreateBusinessFunctionCommand command = CreateBusinessFunctionCommand.create(jwt, "name", "Description", "1.1", Language.ENG);
        command.getEvent().setData(businessFunction());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(RestDocumentationRequestBuilders.put("/api/v1/referential/business/functions/{local_id}", "1.1")
                .header("jwt-auth", jwt)
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("language", "em")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(document("create-business-function-by-localId",
                        requestParameters(
                                parameterWithName("language").description("language to get the result"),
                                parameterWithName("name").description("Name of the requested business function"),
                                parameterWithName("description").description("Description or the requested business function")
                        ),
                        pathParameters(parameterWithName("local_id").description("localId to get the result")),
                        responseFields(
                                fieldWithPath("id").description("The id of requested business function").type(JsonFieldType.NUMBER),
                                fieldWithPath("name").description("Name of the requested business function").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description or the requested business function").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the requested business function").type(JsonFieldType.STRING),
                                fieldWithPath("phase").description("GSBPM phase").type(JsonFieldType.STRING),
                                fieldWithPath("phaseId").description("GSBPM phase id").type(JsonFieldType.NUMBER),
                                fieldWithPath("localId").description("GSBPM sub-phase").type(JsonFieldType.STRING),
                                fieldWithPath("version").description("Version of GSBPM, 5.1").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the entity").type(JsonFieldType.STRING)
                        ))).andReturn();
    }

    @Test
    public void updateBusinessFunction() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final UpdateBusinessFunctionCommand command = UpdateBusinessFunctionCommand.create(jwt, 1L, "name", "description", Language.ENG);
        command.getEvent().setData(businessFunction());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(patch("/api/v1/referential/business/functions/{id}", 1L)
                .header("jwt-auth",jwt)
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("language", "en")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
        ArgumentCaptor<UpdateBusinessFunctionCommand> argumentCaptor = ArgumentCaptor.forClass(UpdateBusinessFunctionCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentUpdateBusinessFunction() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final UpdateBusinessFunctionCommand command = UpdateBusinessFunctionCommand.create(jwt, 1L, "name", "description", Language.ENG);
        command.getEvent().setData(businessFunction());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(RestDocumentationRequestBuilders.patch("/api/v1/referential/business/functions/{id}", 1L)
                .header("jwt-auth", jwt)
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("language", "en")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(document("update-business-function",
                        requestParameters(
                                parameterWithName("language").description("language to get the result"),
                                parameterWithName("name").description("Name of the requested business function"),
                                parameterWithName("description").description("Description or the requested business function")
                        ),
                        pathParameters(parameterWithName("id").description("id to get the result")),
                        responseFields(
                                fieldWithPath("id").description("The id of requested business function").type(JsonFieldType.NUMBER),
                                fieldWithPath("name").description("Name of the requested business function").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description or the requested business function").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the requested business function").type(JsonFieldType.STRING),
                                fieldWithPath("phase").description("GSBPM phase").type(JsonFieldType.STRING),
                                fieldWithPath("phaseId").description("GSBPM phase id").type(JsonFieldType.NUMBER),
                                fieldWithPath("localId").description("GSBPM sub-phase").type(JsonFieldType.STRING),
                                fieldWithPath("version").description("Version of GSBPM, 5.1").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the entity").type(JsonFieldType.STRING)
                        ))).andReturn();
    }
}
