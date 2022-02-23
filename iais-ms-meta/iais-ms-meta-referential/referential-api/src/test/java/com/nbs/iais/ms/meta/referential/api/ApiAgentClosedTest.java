package com.nbs.iais.ms.meta.referential.api;

import com.nbs.iais.ms.common.api.messaging.gateway.IAISGateway;
import com.nbs.iais.ms.common.dto.wrappers.DTOBoolean;
import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.api.controllers.ApiAgentClosed;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.CreateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.DeleteAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.agent.UpdateAgentCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentQuery;
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

import static com.nbs.iais.ms.common.utils.DTOMocks.agent;
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
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ApiAgentClosed.class)
@AutoConfigureRestDocs
public class ApiAgentClosedTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAISGateway iaisGateway;

    @Test
    public void createAgent() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final CreateAgentCommand command = CreateAgentCommand.create(jwt, "Name", "Description", AgentType.INDIVIDUAL, "florian.nika@email.com", null, 1L, Language.ENG);
        command.getEvent().setData(agent());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(post("/api/v1/close/referential/agents/")
                .header("jwt-auth", jwt)
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("account", String.valueOf(command.getAccount()))
                .param("type", command.getType().toString())
                .param("local_id", command.getLocalId())
                .param("language", "en")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<CreateAgentCommand> argumentCaptor = ArgumentCaptor.forClass(CreateAgentCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentCreateAgent() throws Exception{
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final CreateAgentCommand command = CreateAgentCommand.create(jwt, "Name", "Description", AgentType.INDIVIDUAL, "florian.nika@email.com", null, 1L, Language.ENG);
        command.getEvent().setData(agent());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(RestDocumentationRequestBuilders.post("/api/v1/close/referential/agents")
                .header("jwt-auth", jwt)
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("account", String.valueOf(command.getAccount()))
                .param("type", command.getType().toString())
                .param("local_id", command.getLocalId())
                .param("language", "en"))
                .andDo(document("create-agent",
                        requestHeaders(
                                headerWithName("jwt-auth").description("authentication token")
                        ),
                        requestParameters(
                                parameterWithName("name").description("Name of the agent").optional(),
                                parameterWithName("description").description("Description of the agent").optional(),
                                parameterWithName("account").description("Agent mapping account").optional(),
                                parameterWithName("type").description("Type of agent: ORGANIZATION, DIVISION or INDIVIDUAL"),
                                parameterWithName("parent").description("Parent of the agent").optional(),
                                parameterWithName("local_id").description("Usually the email address when agent is INDIVIDUAL").optional(),
                                parameterWithName("language").description("language to get the result")),
                        responseFields(
                                fieldWithPath("id").description("The id of requested agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("name").description("Name of the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description or the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("localId").description("Local id of the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("type").description("Type of agent: ORGANIZATION, DIVISION or INDIVIDUAL").type(JsonFieldType.STRING),
                                fieldWithPath("parent").description("Parent agent of the requested agent, INDIVIDUAL can have DIVISION parents, DIVISION can have ORGANIZATION parent, ORGANIZATION can not have parent").type(JsonFieldType.OBJECT),
                                fieldWithPath("parent.id").description("Id of the parent agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("parent.link").description("Link of the parent agent").type(JsonFieldType.STRING),
                                fieldWithPath("parent.name").description("Name of the parent agent").type(JsonFieldType.STRING),
                                fieldWithPath("parent.type").description("Type of the parent agent").type(JsonFieldType.STRING)


                        ))).andReturn();
    }

    @Test
    public void updateAgent() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final UpdateAgentCommand command = UpdateAgentCommand.create(jwt, 2L,"Name", "Description", AgentType.INDIVIDUAL, "florian.nika@email.com", null, 1L, null, null, null, Language.ENG);
        command.getEvent().setData(agent());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(patch("/api/v1/close/referential/agents/{id}", 2L)
                .header("jwt-auth", jwt)
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("account", String.valueOf(command.getAccount()))
                .param("type", command.getType().toString())
                .param("local_id", command.getLocalId())
                .param("language", "en")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<UpdateAgentCommand> argumentCaptor = ArgumentCaptor.forClass(UpdateAgentCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentUpdateAgent() throws Exception{
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final UpdateAgentCommand command = UpdateAgentCommand.create(jwt, 1L,"Name", "Description", AgentType.INDIVIDUAL, "florian.nika@email.com", null, 1L, null, null, null, Language.ENG);
        command.getEvent().setData(agent());
        when(iaisGateway.sendCommand(any(), anyString())).thenReturn(command);
        mockMvc.perform(RestDocumentationRequestBuilders.patch("/api/v1/close/referential/agents/{id}", 1L)
                .header("jwt-auth", jwt)
                .param("name", command.getName())
                .param("description", command.getDescription())
                .param("account", String.valueOf(command.getAccount()))
                .param("type", command.getType().toString())
                .param("local_id", command.getLocalId())
                .param("language", "en"))
                .andDo(document("update-agent",
                        pathParameters(parameterWithName("id").description("Id of agent that is being modified")),
                        requestParameters(
                                parameterWithName("name").description("Name of the agent").optional(),
                                parameterWithName("description").description("Description of the agent").optional(),
                                parameterWithName("account").description("Agent mapping account").optional(),
                                parameterWithName("type").description("Type of agent: ORGANIZATION, DIVISION or INDIVIDUAL"),
                                parameterWithName("parent").description("Parent of the agent").optional(),
                                parameterWithName("local_id").description("Usually the email address when agent is INDIVIDUAL").optional(),
                                parameterWithName("version").description("Version of the agent").optional(),
                                parameterWithName("version_date").description("The date of the version").optional(),
                                parameterWithName("version_rationale").description("Rationale for version").optional(),
                                parameterWithName("language").description("language to get the result")),
                        responseFields(
                                fieldWithPath("id").description("The id of requested agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("name").description("Name of the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description or the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("localId").description("Local id of the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("type").description("Type of agent: ORGANIZATION, DIVISION or INDIVIDUAL").type(JsonFieldType.STRING),
                                fieldWithPath("parent").description("Parent agent of the requested agent, INDIVIDUAL can have DIVISION parents, DIVISION can have ORGANIZATION parent, ORGANIZATION can not have parent").type(JsonFieldType.OBJECT),
                                fieldWithPath("parent.id").description("Id of the parent agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("parent.link").description("Link of the parent agent").type(JsonFieldType.STRING),
                                fieldWithPath("parent.name").description("Name of the parent agent").type(JsonFieldType.STRING),
                                fieldWithPath("parent.type").description("Type of the parent agent").type(JsonFieldType.STRING)


                        ))).andReturn();
    }

    @Test
    public void deleteAgent() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final DeleteAgentCommand command = DeleteAgentCommand.create(jwt, 2L);
        command.getEvent().setData(DTOBoolean.TRUE);
        when(iaisGateway.sendCommand(any(DeleteAgentCommand.class), anyString())).thenReturn(command);
        mockMvc.perform(delete("/api/v1/close/referential/agents/{id}", command.getId().toString())
                .header("jwt-auth", jwt))
                .andDo(print()).andExpect(status().isOk());
        ArgumentCaptor<DeleteAgentCommand> argumentCaptor = ArgumentCaptor.forClass(DeleteAgentCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentDeleteAgent() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final DeleteAgentCommand command = DeleteAgentCommand.create(jwt, 2L);
        command.getEvent().setData(DTOBoolean.TRUE);

        when(iaisGateway.sendCommand(any(DeleteAgentCommand.class), anyString())).thenReturn(command);

        mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/v1/close/referential/agents/{id}", command.getId())
                .header("jwt-auth", jwt))
                .andDo(document("delete-agent",
                        requestHeaders(headerWithName("jwt-auth").description("JWT authentication token")),
                        pathParameters(
                                parameterWithName("id").description("Id of the new created agent").attributes()
                        ),

                        responseFields(
                                fieldWithPath("result").description("True if agent has been deleted").type(JsonFieldType.BOOLEAN)
                        ))).andReturn();
    }

    @Test
    public void getAgentQueryByAccount() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final GetAgentQuery query = GetAgentQuery.create(Language.ENG);
        query.setAccountId(1L);
        query.getRead().setData(agent());
        when(iaisGateway.sendQuery(any(), anyString())).thenReturn(query);
        mockMvc.perform(get("/api/v1/close/referential/agents/account/{account}", 1L)
                .header("jwt-auth", jwt)
                .param("language", "en")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<GetAgentQuery> argumentCaptor = ArgumentCaptor.forClass(GetAgentQuery.class);
        verify(iaisGateway).sendQuery(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentGetAgentQueryByAccount() throws Exception {
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final GetAgentQuery query = GetAgentQuery.create(Language.ENG);
        query.setAccountId(1L);
        query.getRead().setData(agent());
        when(iaisGateway.sendQuery(any(), anyString())).thenReturn(query);
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/close/referential/agents/account/{account}", 1L)
                .header("jwt-auth", jwt)
                .param("language", "en")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(document("get-agent-by-account",
                        requestParameters(parameterWithName("language").description("language to get the result")),
                        pathParameters(
                                parameterWithName("account").description("account of the requested agent").attributes()
                        ),
                        responseFields(
                                fieldWithPath("id").description("The id of requested agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("name").description("Name of the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description or the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("localId").description("Local id of the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("type").description("Type of agent: ORGANIZATION, DIVISION or INDIVIDUAL").type(JsonFieldType.STRING),
                                fieldWithPath("parent").description("Parent agent of the requested agent, INDIVIDUAL can have DIVISION parents, DIVISION can have ORGANIZATION parent, ORGANIZATION can not have parent").type(JsonFieldType.OBJECT),
                                fieldWithPath("parent.id").description("Id of the parent agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("parent.link").description("Link of the parent agent").type(JsonFieldType.STRING),
                                fieldWithPath("parent.name").description("Name of the parent agent").type(JsonFieldType.STRING),
                                fieldWithPath("parent.type").description("Type of the parent agent").type(JsonFieldType.STRING)
                        )
                )).andReturn();
    }
}
