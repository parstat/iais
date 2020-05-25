package com.nbs.iais.ms.meta.referential.api;

import com.nbs.iais.ms.common.api.messaging.gateway.IAISGateway;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.Frequency;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.api.controllers.ApiProcessDocumentationClosed;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.AddProcessDocumentationVersionCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.CreateProcessDocumentationCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.UpdateProcessDocumentationCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.documentation.GetProcessDocumentationQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.documentation.GetProcessDocumentationsQuery;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.nbs.iais.ms.common.utils.DTOMocks.processDocumentation;
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
@WebMvcTest(ApiProcessDocumentationClosed.class)
@AutoConfigureRestDocs
public class ApiProcessDocumentationClosedTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAISGateway iaisGateway;

    @Test
    public void createProcessDocumentation() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final CreateProcessDocumentationCommand command = CreateProcessDocumentationCommand.create(jwt, "name", "description", "localId", "1.0", LocalDateTime.of(2020,1,1, 0, 0),"First version", 1L, 1L, Frequency.YEARLY, 1L, "1.2", Language.ENG);
        command.getEvent().setData(processDocumentation());

        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);

        mockMvc.perform(put("/api/v1/close/referential/process/documentations/program/{statistical_program}/function/{business_function}", command.getStatisticalProgram(), command.getBusinessFunction())
                .header("jwt-auth", jwt)
                .param("language", "en")
                .param("name", "Name")
                .param("description", command.getDescription())
                .param("local_id", command.getLocalId())
                .param("version", command.getVersion())
                .param("versionDate", command.getVersionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("versionRationale", command.getVersionRationale())
                .param("frequency", command.getFrequency().toString())
                .param("maintainer", command.getMaintainer().toString())
                .param("nextSubPhase", command.getNextSubPhase())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<CreateProcessDocumentationCommand> argumentCaptor = ArgumentCaptor.forClass(CreateProcessDocumentationCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void docuemntCreateProcessDocumentation() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final CreateProcessDocumentationCommand command = CreateProcessDocumentationCommand.create(jwt, "name", "description", "localId", "1.0", LocalDateTime.of(2020,1,1, 0, 0),"First version", 1L, 1L, Frequency.YEARLY, 1L, "1.2", Language.ENG);
        command.getEvent().setData(processDocumentation());

        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);

        mockMvc.perform(RestDocumentationRequestBuilders.put("/api/v1/close/referential/process/documentations/program/{statistical_program}/function/{business_function}", command.getStatisticalProgram(), command.getBusinessFunction())
                .header("jwt-auth", jwt)
                .param("language", "en")
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("local_id", command.getLocalId())
                .param("version", command.getVersion())
                .param("versionDate", command.getVersionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("versionRationale", command.getVersionRationale())
                .param("frequency", command.getFrequency().toString())
                .param("maintainer", command.getMaintainer().toString())
                .param("nextSubPhase", command.getNextSubPhase()))
                .andDo(document("create-process-documentation",
                        requestHeaders(headerWithName("jwt-auth").description("JWT authentication token")),
                        requestParameters(
                                parameterWithName("language").description("language to get the result"),
                                parameterWithName("name").description("Name of the new process documentation"),
                                parameterWithName("description").description("Description of the new process documentation"),
                                parameterWithName("local_id").description("LocalId of the new process documentation"),
                                parameterWithName("version").description("Version of the new process documentation"),
                                parameterWithName("versionDate").description("Date version of the new process documentation"),
                                parameterWithName("versionRationale").description("rationale version of the new process documentation"),
                                parameterWithName("frequency").description("Frequency of the new process documentation"),
                                parameterWithName("maintainer").description("Maintainer of the new process documentation"),
                                parameterWithName("nextSubPhase").description("Next sub phase of the new process documentation")
                        ),
                        pathParameters(
                                parameterWithName("statistical_program").description("Statistica program of the new process documentation"),
                                parameterWithName("business_function").description("Business function of the new process documentation")
                        ),
                        responseFields(
                                fieldWithPath("id").description("The id of requested process documentation"),
                                fieldWithPath("description").description("Description of new process documentation"),
                                fieldWithPath("businessFunction").description("Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.OBJECT),
                                fieldWithPath("businessFunction.id").description("Id of Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.NUMBER),
                                fieldWithPath("businessFunction.name").description("Name of Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.STRING),
                                fieldWithPath("businessFunction.link").description("Link of Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.STRING),
                                fieldWithPath("businessFunction.localId").description("Local id of Business Function (GSBPM sub-phase number) being documented").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalProgram").description("Statistical Program being documented").type(JsonFieldType.OBJECT),
                                fieldWithPath("statisticalProgram.id").description("Id of Statistical Program being documented").type(JsonFieldType.NUMBER),
                                fieldWithPath("statisticalProgram.name").description("Name of Statistical Program being documented").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalProgram.acronym").description("Acronym of Statistical Program being documented").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalProgram.description").description("Description of Statistical Program being documented").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalProgram.link").description("Link of Statistical Program being documented").type(JsonFieldType.STRING),
                                fieldWithPath("frequency").description("Frequency of requested process documentation").type(JsonFieldType.STRING)

                        )
                        )).andReturn();
    }

    @Test
    public void addProcessDocumentationVersion() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final AddProcessDocumentationVersionCommand command = AddProcessDocumentationVersionCommand.create(jwt, "name", "description", "localId", "1.0", LocalDateTime.of(2020,1,1, 0, 0),"First version", 1L, 1L, Frequency.YEARLY, 1L, "1.2", Language.ENG);
        command.getEvent().setData(processDocumentation());

        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);

        mockMvc.perform(put("/api/v1/close/referential/process/documentations/program/{statistical_program}/function/{business_function}/version/{version}", command.getStatisticalProgram(), command.getBusinessFunction(), command.getVersion())
                .header("jwt-auth", jwt)
                .param("language", "en")
                .param("name", "Name")
                .param("description", command.getDescription())
                .param("local_id", command.getLocalId())
                .param("versionDate", command.getVersionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("versionRationale", command.getVersionRationale())
                .param("frequency", command.getFrequency().toString())
                .param("maintainer", command.getMaintainer().toString())
                .param("nextSubPhase", command.getNextSubPhase())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<AddProcessDocumentationVersionCommand> argumentCaptor = ArgumentCaptor.forClass(AddProcessDocumentationVersionCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentAddProcessDocumentationVersion() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final AddProcessDocumentationVersionCommand command = AddProcessDocumentationVersionCommand.create(jwt, "name", "description", "localId", "1.0", LocalDateTime.of(2020,1,1, 0, 0),"First version", 1L, 1L, Frequency.YEARLY, 1L, "1.2", Language.ENG);
        command.getEvent().setData(processDocumentation());

        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);

        mockMvc.perform(RestDocumentationRequestBuilders.put("/api/v1/close/referential/process/documentations/program/{statistical_program}/function/{business_function}/version/{version}", command.getStatisticalProgram(), command.getBusinessFunction(), command.getVersion())
                .header("jwt-auth", jwt)
                .param("language", "en")
                .param("name", "Name")
                .param("description", command.getDescription())
                .param("local_id", command.getLocalId())
                .param("versionDate", command.getVersionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("versionRationale", command.getVersionRationale())
                .param("frequency", command.getFrequency().toString())
                .param("maintainer", command.getMaintainer().toString())
                .param("nextSubPhase", command.getNextSubPhase()))
                .andDo(document("add-process-documentation-version",
                        requestHeaders(headerWithName("jwt-auth").description("JWT authentication token")),
                        requestParameters(
                                parameterWithName("language").description("language to get the result"),
                                parameterWithName("name").description("Name of the new process documentation"),
                                parameterWithName("description").description("Description of the new process documentation"),
                                parameterWithName("local_id").description("LocalId of the new process documentation"),
                                parameterWithName("versionDate").description("Date version of the new process documentation"),
                                parameterWithName("versionRationale").description("rationale version of the new process documentation"),
                                parameterWithName("frequency").description("Frequency of the new process documentation"),
                                parameterWithName("maintainer").description("Maintainer of the new process documentation"),
                                parameterWithName("nextSubPhase").description("Next sub phase of the new process documentation")
                        ),
                        pathParameters(
                                parameterWithName("statistical_program").description("Statistica program of the new process documentation"),
                                parameterWithName("business_function").description("Business function of the new process documentation"),
                                parameterWithName("version").description("Version of the new process documentation")
                        ),
                        responseFields(
                                fieldWithPath("id").description("The id of requested process documentation"),
                                fieldWithPath("description").description("Description of new process documentation"),
                                fieldWithPath("businessFunction").description("Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.OBJECT),
                                fieldWithPath("businessFunction.id").description("Id of Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.NUMBER),
                                fieldWithPath("businessFunction.name").description("Name of Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.STRING),
                                fieldWithPath("businessFunction.link").description("Link of Business Function (GSBPM sub-phase) being documented").type(JsonFieldType.STRING),
                                fieldWithPath("businessFunction.localId").description("Local id of Business Function (GSBPM sub-phase number) being documented").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalProgram").description("Statistical Program being documented").type(JsonFieldType.OBJECT),
                                fieldWithPath("statisticalProgram.id").description("Id of Statistical Program being documented").type(JsonFieldType.NUMBER),
                                fieldWithPath("statisticalProgram.name").description("Name of Statistical Program being documented").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalProgram.acronym").description("Acronym of Statistical Program being documented").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalProgram.description").description("Description of Statistical Program being documented").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalProgram.link").description("Link of Statistical Program being documented").type(JsonFieldType.STRING),
                                fieldWithPath("frequency").description("Frequency of requested process documentation").type(JsonFieldType.STRING)

                        )
                        )).andReturn();
    }

    @Test
    public void updateProcessDocumentation() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final UpdateProcessDocumentationCommand command = UpdateProcessDocumentationCommand.create(jwt, "name", "description", "localId", "1.0", LocalDateTime.of(2020,1,1, 0, 0),"First version", 1L, 1L, Frequency.YEARLY, 1L, "1.2", Language.ENG);
        command.getEvent().setData(processDocumentation());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(patch("/api/v1/close/referential/process/documentations/{id}", 1L)
                .header("jwt-auth", jwt)
                .param())
    }
}
