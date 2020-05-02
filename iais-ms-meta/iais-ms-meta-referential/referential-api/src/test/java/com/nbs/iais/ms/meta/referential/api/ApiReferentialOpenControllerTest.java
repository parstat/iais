package com.nbs.iais.ms.meta.referential.api;

import com.nbs.iais.ms.common.api.messaging.gateway.IAISGateway;
import com.nbs.iais.ms.common.dto.impl.AgentDTO;
import com.nbs.iais.ms.common.dto.impl.mini.AgentMiniDTO;
import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.meta.referential.api.controllers.ApiReferentialOpen;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentQuery;
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
        AgentMiniDTO agentMiniDTO = new AgentMiniDTO(1L);
        agentMiniDTO.setName("Organization");
        agentMiniDTO.setType(AgentType.ORGANIZATION);
        agentMiniDTO.setLink("/agent/1");
        return agentMiniDTO;
    }

    private AgentDTO agent() {
        AgentDTO agentDTO = new AgentDTO(2L);
        agentDTO.setName("Division");
        agentDTO.setType(AgentType.DIVISION);
        agentDTO.setDescription("description");
        agentDTO.setLink("/agents/" + agentDTO.getId().toString());
        agentDTO.setParent(agentMini());
        return agentDTO;
    }


    @Test
    public void getAgent() throws Exception {
        final GetAgentQuery query = GetAgentQuery.create(1L);
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
        final GetAgentQuery query = GetAgentQuery.create(1L);
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
}

