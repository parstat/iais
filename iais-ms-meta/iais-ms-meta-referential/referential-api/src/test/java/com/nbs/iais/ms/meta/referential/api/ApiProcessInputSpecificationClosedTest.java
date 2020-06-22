package com.nbs.iais.ms.meta.referential.api;

import com.nbs.iais.ms.meta.referential.api.controllers.ApiProcessInputSpecificationClosed;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(ApiProcessInputSpecificationClosed.class)
@AutoConfigureRestDocs
public class ApiProcessInputSpecificationClosedTest {

   /* @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAISGateway iaisGateway;

    @Test
    public void createInputSpecification() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final CreateInputSpecificationCommand command = CreateInputSpecificationCommand.create(jwt, 1L, "Name", "Description", "localId", "1.0", LocalDateTime.now(), "New version", Language.ENG);
        command.getEvent().setData(processDocumentation());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(post("/api/v1/close/referential/process/inputs/documentation/{documentation}", 1L)
                .header("jwt-auth", jwt)
                .param("language", "en")
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("local_id", command.getLocalId())
                .param("version", command.getVersion())
                .param("versionDate", command.getVersionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("versionRationale", command.getVersionRationale())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
        ArgumentCaptor<CreateInputSpecificationCommand> argumentCaptor = ArgumentCaptor.forClass(CreateInputSpecificationCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentCreateInputSpecification() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final CreateInputSpecificationCommand command = CreateInputSpecificationCommand.create(jwt, 1L, "Name", "Description", "localId", "1.0", LocalDateTime.now(), "New version", Language.ENG);
        command.getEvent().setData(processDocumentation());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(RestDocumentationRequestBuilders.post("/api/v1/close/referential/process/inputs/documentation/{documentation}", 1L)
                .header("jwt-auth", jwt)
                .param("language", "en")
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("local_id", command.getLocalId())
                .param("version", command.getVersion())
                .param("versionDate", command.getVersionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("versionRationale", command.getVersionRationale())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(document("create-input-specification",
                    requestParameters(
                        parameterWithName("language").description("language to get the result").optional(),
                        parameterWithName("name").description("Name of the requested process input specification").optional(),
                        parameterWithName("description").description("Description of the requested process input specification").optional(),
                        parameterWithName("local_id").description("LocalId of the requested process input specification"),
                        parameterWithName("version").description("Version of the requested process document").optional(),
                        parameterWithName("versionDate").description("Date version of the requested process document").optional(),
                        parameterWithName("versionRationale").description("Version rationale of the requested process document").optional()
                    ),
                    pathParameters(
                        parameterWithName("processDocumentation").description("Process documentation of the requested process input specification")
                    ),
                    responseFields(
                         fieldWithPath("id").description("The id of requested process document").type(JsonFieldType.NUMBER),
                         fieldWithPath("localId").description("LocalId of requested process document").type(JsonFieldType.STRING),
                         fieldWithPath("name").description("Name of requested process document").type(JsonFieldType.STRING),
                         fieldWithPath("version").description("Version of requested process document").type(JsonFieldType.STRING),
                         fieldWithPath("description").description("Description of requested process document").type(JsonFieldType.STRING)
                    ))).andReturn();
    }

    @Test
    public void updateInputSpecification() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final UpdateInputSpecificationCommand command = UpdateInputSpecificationCommand.create(jwt, 1L, "name", "description", "localId", "version", LocalDateTime.now(), "New version", Language.ENG);
        command.getEvent().setData(processInputSpecification());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(patch("/api/v1/close/referential/process/inputs/{id}", 1L)
                .header("jwt-auth", jwt)
                .param("language", "en")
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("local_id", command.getLocalId())
                .param("version", command.getVersion())
                .param("versionDate", command.getVersionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("versionRationale", command.getVersionRationale())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
        ArgumentCaptor<UpdateInputSpecificationCommand> argumentCaptor = ArgumentCaptor.forClass(UpdateInputSpecificationCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentUpdateInputSpecification() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final UpdateInputSpecificationCommand command = UpdateInputSpecificationCommand.create(jwt, 1L, "name", "description", "localId", "version", LocalDateTime.now(), "New version", Language.ENG);
        command.getEvent().setData(processInputSpecification());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(RestDocumentationRequestBuilders.patch("/api/v1/close/referential/process/inputs/{id}", 1L)
                .header("jwt-auth", jwt)
                .param("language", "en")
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("local_id", command.getLocalId())
                .param("version", command.getVersion())
                .param("versionDate", command.getVersionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("versionRationale", command.getVersionRationale())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(document("update-input-specification",
                        requestParameters(
                                parameterWithName("language").description("language to get the result").optional(),
                                parameterWithName("name").description("Name of the requested process input specification").optional(),
                                parameterWithName("description").description("Description of the requested process input specification").optional(),
                                parameterWithName("local_id").description("LocalId of the requested process input specification"),
                                parameterWithName("version").description("Version of the requested process document").optional(),
                                parameterWithName("versionDate").description("Date version of the requested process document").optional(),
                                parameterWithName("versionRationale").description("Version rationale of the requested process document").optional()
                        ),
                        pathParameters(
                                parameterWithName("id").description("The id of the requested process input specification")
                        ),
                        responseFields(
                                fieldWithPath("id").description("The id of requested process document").type(JsonFieldType.NUMBER),
                                fieldWithPath("localId").description("LocalId of requested process document").type(JsonFieldType.STRING),
                                fieldWithPath("name").description("Name of requested process document").type(JsonFieldType.STRING),
                                fieldWithPath("version").description("Version of requested process document").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description of requested process document").type(JsonFieldType.STRING)
                        ))).andReturn();
    }

    @Test
    public void addInputSpecificationType() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final AddInputSpecificationTypeCommand command = AddInputSpecificationTypeCommand.create(jwt, 1L, ProcessInputType.PARAMETER_INPUT, Language.ENG);
        command.getEvent().setData(processInputSpecification());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(put("/api/v1/close/referential/process/inputs/{id}/type/{type}", 1L, ProcessInputType.PARAMETER_INPUT)
                .header("jwt-auth", jwt)
                .param("language", "en")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
        ArgumentCaptor<AddInputSpecificationTypeCommand> argumentCaptor = ArgumentCaptor.forClass(AddInputSpecificationTypeCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentAddInputSpecificationType() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final AddInputSpecificationTypeCommand command = AddInputSpecificationTypeCommand.create(jwt, 1L, ProcessInputType.PARAMETER_INPUT, Language.ENG);
        command.getEvent().setData(processInputSpecification());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(RestDocumentationRequestBuilders.put("/api/v1/close/referential/process/inputs/{id}/type/{type}", 1L, ProcessInputType.PARAMETER_INPUT)
                .header("jwt-auth", jwt)
                .param("language", "en")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(document("add-input-specification",
                        requestParameters(
                                parameterWithName("language").description("language to get the result").optional()
                        ),
                        pathParameters(
                                parameterWithName("id").description("The id of the requested process input specification"),
                                parameterWithName("type").description("The type of the requested process input specification")
                        ),
                        responseFields(
                                fieldWithPath("id").description("The id of requested process document").type(JsonFieldType.NUMBER),
                                fieldWithPath("localId").description("LocalId of requested process document").type(JsonFieldType.STRING),
                                fieldWithPath("name").description("Name of requested process document").type(JsonFieldType.STRING),
                                fieldWithPath("version").description("Version of requested process document").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description of requested process document").type(JsonFieldType.STRING)
                        ))).andReturn();
    }

    @Test
    public void removeInputSpecificationType() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final RemoveInputSpecificationTypeCommand command = RemoveInputSpecificationTypeCommand.create(jwt, 1L, ProcessInputType.PARAMETER_INPUT, Language.ENG);
        command.getEvent().setData(processInputSpecification());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(delete("/api/v1/close/referential/process/inputs/{id}/type/{type}",1L, ProcessInputType.PARAMETER_INPUT)
                .header("jwt-auth", jwt)
                .param("language", "en")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
        ArgumentCaptor<RemoveInputSpecificationTypeCommand> argumentCaptor = ArgumentCaptor.forClass(RemoveInputSpecificationTypeCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentRemoveInputSpecification() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final RemoveInputSpecificationTypeCommand command = RemoveInputSpecificationTypeCommand.create(jwt, 1L, ProcessInputType.PARAMETER_INPUT, Language.ENG);
        command.getEvent().setData(processInputSpecification());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/v1/close/referential/process/inputs/{id}/type/{type}", 1L, ProcessInputType.PARAMETER_INPUT)
                .header("jwt-auth", jwt)
                .param("language", "en")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(document("remove-input-specification",
                        requestHeaders(headerWithName("jwt-auth").description("JWT authentication token")),
                        pathParameters(
                                parameterWithName("id").description("Id of the process input specification to delete").attributes(),
                                parameterWithName("type").description("Type of the process input specification to delete").attributes()
                        ),

                        responseFields(
                                fieldWithPath("id").description("The id of requested process document").type(JsonFieldType.NUMBER),
                                fieldWithPath("localId").description("LocalId of requested process document").type(JsonFieldType.STRING),
                                fieldWithPath("name").description("Name of requested process document").type(JsonFieldType.STRING),
                                fieldWithPath("version").description("Version of requested process document").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description of requested process document").type(JsonFieldType.STRING)
                        ))).andReturn();
    }

    @Test
    public void deleteInputSpecification() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final DeleteInputSpecificationCommand command = DeleteInputSpecificationCommand.create(jwt, 1L);
        command.getEvent().setData(DTOBoolean.TRUE);
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(delete("/api/v1/close/referential/process/inputs/{id}", 1L)
                .header("jwt-auth", jwt))
                .andDo(print()).andExpect(status().isOk());
        ArgumentCaptor<DeleteInputSpecificationCommand> argumentCaptor = ArgumentCaptor.forClass(DeleteInputSpecificationCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentDeleteInputSpecification() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final DeleteInputSpecificationCommand command = DeleteInputSpecificationCommand.create(jwt, 1L);
        command.getEvent().setData(DTOBoolean.TRUE);
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/v1/close/referential/process/inputs/{id}", 1L)
                .header("jwt-auth", jwt))
                .andDo(document("delete-input-specification",
                        requestHeaders(headerWithName("jwt-auth").description("JWT authentication token")),
                        pathParameters(
                                parameterWithName("id").description("Id of the process input specification to delete").attributes()
                        ),

                        responseFields(
                                fieldWithPath("result").description("True if the process input specification has been deleted").type(JsonFieldType.BOOLEAN)
                        ))).andReturn();
    }*/
}
