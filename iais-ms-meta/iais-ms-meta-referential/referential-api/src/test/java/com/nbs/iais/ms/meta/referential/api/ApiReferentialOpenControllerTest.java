package com.nbs.iais.ms.meta.referential.api;

import com.nbs.iais.ms.common.api.messaging.gateway.IAISGateway;
import com.nbs.iais.ms.common.dto.impl.AgentDTO;
import com.nbs.iais.ms.common.dto.impl.BusinessFunctionDTO;
import com.nbs.iais.ms.common.dto.impl.mini.AgentMiniDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.api.controllers.ApiReferentialOpen;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionsQuery;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(ApiReferentialOpen.class)
@AutoConfigureRestDocs
public class ApiReferentialOpenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAISGateway iaisGateway;

    private AgentMiniDTO agentMini() {
        final AgentMiniDTO agentMiniDTO = new AgentMiniDTO(1L);
        agentMiniDTO.setName("Organization");
        agentMiniDTO.setType(AgentType.ORGANIZATION);
        agentMiniDTO.setLink("/agent/1");
        return agentMiniDTO;
    }

    private AgentDTO agent() {
        final AgentDTO agentDTO = new AgentDTO(2L);
        agentDTO.setName("Division");
        agentDTO.setType(AgentType.DIVISION);
        agentDTO.setDescription("description");
        agentDTO.setLink("/agents/" + agentDTO.getId().toString());
        agentDTO.setParent(agentMini());
        return agentDTO;
    }

    private BusinessFunctionDTO businessFunction() {
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


    @Test
    public void getAgent() throws Exception {
        final GetAgentQuery query = GetAgentQuery.create(1L, Language.ENG);
        query.getRead().setData(agent());
        when(iaisGateway.sendQuery(any(), anyString())).thenReturn(query);
        mockMvc.perform(get("/api/v1/referential/agents/{id}", 1)
                .param("language", "en")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<GetAgentQuery> argumentCaptor = ArgumentCaptor.forClass(GetAgentQuery.class);
        verify(iaisGateway).sendQuery(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentGetAgent() throws Exception{
        final GetAgentQuery query = GetAgentQuery.create(1L, Language.ENG);
        query.getRead().setData(agent());
        when(iaisGateway.sendQuery(any(), anyString())).thenReturn(query);
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/referential/agents/{id}", 1)
                .param("language", "en"))
                .andDo(document("get-agent",
                        requestParameters(parameterWithName("language").description("language to get the result")),
                        pathParameters(
                                parameterWithName("id").description("id of the requested agent").attributes()
                        ),
                        responseFields(
                                fieldWithPath("id").description("The id of requested agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("name").description("Name of the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description or the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("type").description("Type of agent: ORGANIZATION, DIVISION or INDIVIDUAL").type(JsonFieldType.STRING),
                                fieldWithPath("parent").description("Parent agent of the requested agent, INDIVIDUAL can have DIVISION parents, DIVISION can have ORGANIZATION parent, ORGANIZATION can not have parent").type(JsonFieldType.OBJECT),
                                fieldWithPath("parent.id").description("Id of the parent agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("parent.link").description("Link of the parent agent").type(JsonFieldType.STRING),
                                fieldWithPath("parent.name").description("Name of the parent agent").type(JsonFieldType.STRING),
                                fieldWithPath("parent.type").description("Type of the parent agent").type(JsonFieldType.STRING)


                        ))).andReturn();
    }

    @Test
    public void getAgents() throws Exception {
        final GetAgentsQuery query = GetAgentsQuery.create(null, "Division", null, Language.ENG);
        query.getRead().setData(DTOList.create(agent()));
        when(iaisGateway.sendQuery(any(), anyString())).thenReturn(query);
        mockMvc.perform(get("/api/v1/referential/agents")
                .param("language", "en")
                .param("name", "Division")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<GetAgentsQuery> argumentCaptor = ArgumentCaptor.forClass(GetAgentsQuery.class);
        verify(iaisGateway).sendQuery(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentGetAgents() throws Exception{
        final GetAgentsQuery query = GetAgentsQuery.create(null, "Division", null, Language.ENG);
        query.getRead().setData(DTOList.create(agent()));
        when(iaisGateway.sendQuery(any(), anyString())).thenReturn(query);
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/referential/agents")
                .param("language", "en")
                .param("name", "Division"))
                .andDo(document("get-agents",
                        requestParameters(parameterWithName("language").description("language to get the result"),
                                parameterWithName("type").description("Get the agents of a type").optional(),
                                parameterWithName("name").description("Search agent by name").optional(),
                                parameterWithName("parent").description("Get all children agents of parent").optional()),
                        responseFields(
                                fieldWithPath("[]").description("List of agents").type(JsonFieldType.ARRAY),
                                fieldWithPath("[].id").description("The id of requested agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("[].name").description("Name of the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("[].description").description("Description or the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("[].link").description("Client link of the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("[].type").description("Type of agent: ORGANIZATION, DIVISION or INDIVIDUAL").type(JsonFieldType.STRING),
                                fieldWithPath("[].parent").description("Parent agent of the requested agent, INDIVIDUAL can have DIVISION parents, DIVISION can have ORGANIZATION parent, ORGANIZATION can not have parent").type(JsonFieldType.OBJECT),
                                fieldWithPath("[].parent.id").description("Id of the parent agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("[].parent.link").description("Link of the parent agent").type(JsonFieldType.STRING),
                                fieldWithPath("[].parent.name").description("Name of the parent agent").type(JsonFieldType.STRING),
                                fieldWithPath("[].parent.type").description("Type of the parent agent").type(JsonFieldType.STRING)


                        ))).andReturn();
    }

    @Test
    public void getBusinessFunction() throws Exception {
        final GetBusinessFunctionQuery query = GetBusinessFunctionQuery.create(1L, Language.ENG);
        query.getRead().setData(businessFunction());
        when(iaisGateway.sendQuery(any(), anyString())).thenReturn(query);
        mockMvc.perform(get("/api/v1/referential/business/functions/{id}", 1)
                .param("language", "en")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<GetBusinessFunctionQuery> argumentCaptor = ArgumentCaptor.forClass(GetBusinessFunctionQuery.class);
        verify(iaisGateway).sendQuery(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentGetBusinessFunction() throws Exception{
        final GetBusinessFunctionQuery query = GetBusinessFunctionQuery.create(1L, Language.ENG);
        query.getRead().setData(businessFunction());
        when(iaisGateway.sendQuery(any(), anyString())).thenReturn(query);
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/referential/business/functions/{id}", 1)
                .param("language", "en"))
                .andDo(document("get-business-function",
                        requestParameters(parameterWithName("language").description("language to get the result")),
                        pathParameters(
                                parameterWithName("id").description("id of the requested agent").attributes()
                        ),
                        responseFields(
                                fieldWithPath("id").description("The id of requested agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("name").description("Name of the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description or the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("phase").description("GSBPM phase").type(JsonFieldType.STRING),
                                fieldWithPath("phaseId").description("GSBPM phase id").type(JsonFieldType.NUMBER),
                                fieldWithPath("localId").description("GSBPM sub-phase").type(JsonFieldType.STRING),
                                fieldWithPath("version").description("Version of GSBPM, 5.1").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the entity").type(JsonFieldType.STRING)

                        ))).andReturn();
    }

    @Test
    public void getBusinessFunctions() throws Exception {
        final GetBusinessFunctionsQuery query = GetBusinessFunctionsQuery.create("Specify", 1, null, Language.ENG);
        query.getRead().setData(DTOList.create(businessFunction()));
        when(iaisGateway.sendQuery(any(), anyString())).thenReturn(query);
        mockMvc.perform(get("/api/v1/referential/business/functions", 1)
                .param("language", "en")
                .param("phase", "1")
                .param("name", "Specify")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<GetBusinessFunctionsQuery> argumentCaptor = ArgumentCaptor.forClass(GetBusinessFunctionsQuery.class);
        verify(iaisGateway).sendQuery(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentGetBusinessFunctions() throws Exception{
        final GetBusinessFunctionsQuery query = GetBusinessFunctionsQuery.create("Specify", 1, null, Language.ENG);
        query.getRead().setData(DTOList.create(businessFunction()));
        when(iaisGateway.sendQuery(any(), anyString())).thenReturn(query);
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/referential/business/functions", 1)
                .param("language", "en")
                .param("phase", "1")
                .param("name", "Specify"))
                .andDo(document("get-business-functions",
                        requestParameters(parameterWithName("language").description("language to get the result"),
                                parameterWithName("phase").description("GSBPM phase").optional(),
                                parameterWithName("name").description("The name to search with").optional()),
                        responseFields(
                                fieldWithPath("[]").description("List of business functions").type(JsonFieldType.ARRAY),
                                fieldWithPath("[].id").description("The id of requested agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("[].name").description("Name of the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("[].description").description("Description or the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("[].link").description("Client link of the requested agent").type(JsonFieldType.STRING),
                                fieldWithPath("[].phase").description("GSBPM phase").type(JsonFieldType.STRING),
                                fieldWithPath("[].phaseId").description("GSBPM phase id").type(JsonFieldType.NUMBER),
                                fieldWithPath("[].localId").description("GSBPM sub-phase").type(JsonFieldType.STRING),
                                fieldWithPath("[].version").description("Version of GSBPM, 5.1").type(JsonFieldType.STRING),
                                fieldWithPath("[].link").description("Client link of the entity").type(JsonFieldType.STRING)

                        ))).andReturn();
    }
}

