package com.nbs.iais.ms.meta.referential.api;

import com.nbs.iais.ms.common.api.messaging.gateway.IAISGateway;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.*;
import com.nbs.iais.ms.meta.referential.api.controllers.ApiReferentialOpen;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramsQuery;
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

import static com.nbs.iais.ms.common.utils.DTOMocks.*;
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
                                fieldWithPath("[].id").description("The id of requested business functions").type(JsonFieldType.NUMBER),
                                fieldWithPath("[].name").description("Name of the requested business functions").type(JsonFieldType.STRING),
                                fieldWithPath("[].description").description("Description or the requested business functions").type(JsonFieldType.STRING),
                                fieldWithPath("[].link").description("Client link of the requested business functions").type(JsonFieldType.STRING),
                                fieldWithPath("[].phase").description("GSBPM phase").type(JsonFieldType.STRING),
                                fieldWithPath("[].phaseId").description("GSBPM phase id").type(JsonFieldType.NUMBER),
                                fieldWithPath("[].localId").description("GSBPM sub-phase").type(JsonFieldType.STRING),
                                fieldWithPath("[].version").description("Version of GSBPM, 5.1").type(JsonFieldType.STRING),
                                fieldWithPath("[].link").description("Client link of the entity").type(JsonFieldType.STRING)

                        ))).andReturn();
    }

    @Test
    public void getStatisticalProgramById() throws Exception {
        final GetStatisticalProgramQuery query = GetStatisticalProgramQuery.create(1L, Language.ENG);
        query.getRead().setData(statisticalProgram());
        when(iaisGateway.sendQuery(any(), anyString())).thenReturn(query);
        mockMvc.perform(get("/api/v1/referential/statistical/programs/{id}", 1)
                .param("language", "en")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<GetStatisticalProgramQuery> argumentCaptor = ArgumentCaptor.forClass(GetStatisticalProgramQuery.class);
        verify(iaisGateway).sendQuery(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentGetStatisticalProgramById() throws Exception{
        final GetStatisticalProgramQuery query = GetStatisticalProgramQuery.create(1L, Language.ENG);
        query.getRead().setData(statisticalProgram());
        when(iaisGateway.sendQuery(any(), anyString())).thenReturn(query);
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/referential/statistical/programs/{id}", 1)
                .param("language", "en"))
                .andDo(document("get-statistical-program",
                        requestParameters(parameterWithName("language").description("language to get the result")),
                        pathParameters(
                                        parameterWithName("id").description("id of the requested statistical program").attributes()
                                ),
                        responseFields(
                                fieldWithPath("id").description("The id of requested statistical program").type(JsonFieldType.NUMBER),
                                fieldWithPath("name").description("Name of the requested statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("Description or the requested statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("acronym").description("Acronym of statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the requested statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("version").description("Version of statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("localId").description("The local id of the statistical program"),
                                fieldWithPath("budget").description("Budget of statistical program").type(JsonFieldType.NUMBER),
                                fieldWithPath("dateInitiated").description("The initiated date of the statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("dateEnded").description("The ended date of the statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("sourceOfFunding").description("Source of Funding of the statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("link").description("Client link of the entity").type(JsonFieldType.STRING),
                                fieldWithPath("programStatus").description("Status of the statistical program: CURRENT, COMPLETED, ").type(JsonFieldType.STRING),
                                fieldWithPath("contact").description("Contact agent of the statistical program").type(JsonFieldType.OBJECT),
                                fieldWithPath("contact.id").description("id of the contact agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("contact.name").description("Name of the contact agent").type(JsonFieldType.STRING),
                                fieldWithPath("contact.type").description("Type of contact agent, normally INDIVIDUAL").type(JsonFieldType.STRING),
                                fieldWithPath("contact.link").description("Client link of contact agent").type(JsonFieldType.STRING),
                                fieldWithPath("owner").description("Owner agent of the statistical program").type(JsonFieldType.OBJECT),
                                fieldWithPath("owner.id").description("Id of the owner agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("owner.name").description("Name of the owner agent").type(JsonFieldType.STRING),
                                fieldWithPath("owner.type").description("Type of owner agent, normally ORGANIZATION").type(JsonFieldType.STRING),
                                fieldWithPath("owner.link").description("Client link of owner agent").type(JsonFieldType.STRING),
                                fieldWithPath("maintainer").description("Maintainer agent of the statistical program").type(JsonFieldType.OBJECT),
                                fieldWithPath("maintainer.id").description("Id of the maintainer agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("maintainer.name").description("Name of the maintainer agent").type(JsonFieldType.STRING),
                                fieldWithPath("maintainer.type").description("Type of maintainer agent, normally DIVISION").type(JsonFieldType.STRING),
                                fieldWithPath("maintainer.link").description("Client link of contact agent").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[]").description("List of statistical standards of the statistical program").type(JsonFieldType.ARRAY),
                                fieldWithPath("statisticalStandards[].id").description("Id of the statistical standard").type(JsonFieldType.NUMBER),
                                fieldWithPath("statisticalStandards[].name").description("Name of the statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].description").description("Description of the statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].type").description("Type of statistical standard:  CLASSIFICATIONS, CONCEPTS, DEFINITIONS, METHODOLOGIES, PROCEDURES, RECOMMENDATIONS, FRAMEWORK").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].version").description("Version of statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("statisticalStandards[].link").description("Client link of statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[]").description("List of statistical standards of the legislative reference").type(JsonFieldType.ARRAY),
                                fieldWithPath("legislativeReferences[].id").description("Id of the legislative reference").type(JsonFieldType.NUMBER),
                                fieldWithPath("legislativeReferences[].name").description("Name of the legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].description").description("Description of the legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].type").description("Type of legislative reference:   REGULATION, LAW, CODE, GOVERNMENTAL_DECISION, AMENDMENT,").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].version").description("Version of legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].link").description("Client link of legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].approval").description("First approval date of the legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("legislativeReferences[].number").description("Number of the legislative reference").type(JsonFieldType.NUMBER)

                        ))).andReturn();
    }


    @Test
    public void getStatisticalPrograms() throws Exception {
        final GetStatisticalProgramsQuery query = GetStatisticalProgramsQuery.create("Labor", null, Language.ENG);
        query.getRead().setData(DTOList.create(statisticalProgram()));
        when(iaisGateway.sendQuery(any(), anyString())).thenReturn(query);
        mockMvc.perform(get("/api/v1/referential/statistical/programs")
                .param("language", "en")
                .param("name", "Labor")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<GetBusinessFunctionsQuery> argumentCaptor = ArgumentCaptor.forClass(GetBusinessFunctionsQuery.class);
        verify(iaisGateway).sendQuery(argumentCaptor.capture(), anyString());
    }

    @Test
    public void documentGetStatisticalPrograms() throws Exception{
        final GetStatisticalProgramsQuery query = GetStatisticalProgramsQuery.create("Labor", null, Language.ENG);
        query.getRead().setData(DTOList.create(statisticalProgram()));
        when(iaisGateway.sendQuery(any(), anyString())).thenReturn(query);
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/referential/statistical/programs")
                .param("language", "en")
                .param("name", "Labor"))
                .andDo(document("get-statistical-programs",
                        requestParameters(parameterWithName("language").description("language to get the result"),
                                parameterWithName("name").description("The name to search with").optional()),
                        responseFields(
                                fieldWithPath("[]").description("List of business functions").type(JsonFieldType.ARRAY),
                                fieldWithPath("[].id").description("The id of requested statistical program").type(JsonFieldType.NUMBER),
                                fieldWithPath("[].name").description("Name of the requested statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("[].description").description("Description or the requested statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("[].acronym").description("Acronym of statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("[].link").description("Client link of the requested statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("[].version").description("Version of statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("[].localId").description("The local id of the statistical program"),
                                fieldWithPath("[].budget").description("Budget of statistical program").type(JsonFieldType.NUMBER),
                                fieldWithPath("[].dateInitiated").description("The initiated date of the statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("[].dateEnded").description("The ended date of the statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("[].sourceOfFunding").description("Source of Funding of the statistical program").type(JsonFieldType.STRING),
                                fieldWithPath("[].link").description("Client link of the entity").type(JsonFieldType.STRING),
                                fieldWithPath("[].programStatus").description("Status of the statistical program: CURRENT, COMPLETED, ").type(JsonFieldType.STRING),
                                fieldWithPath("[].contact").description("Contact agent of the statistical program").type(JsonFieldType.OBJECT),
                                fieldWithPath("[].contact.id").description("id of the contact agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("[].contact.name").description("Name of the contact agent").type(JsonFieldType.STRING),
                                fieldWithPath("[].contact.type").description("Type of contact agent, normally INDIVIDUAL").type(JsonFieldType.STRING),
                                fieldWithPath("[].contact.link").description("Client link of contact agent").type(JsonFieldType.STRING),
                                fieldWithPath("[].owner").description("Owner agent of the statistical program").type(JsonFieldType.OBJECT),
                                fieldWithPath("[].owner.id").description("Id of the owner agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("[].owner.name").description("Name of the owner agent").type(JsonFieldType.STRING),
                                fieldWithPath("[].owner.type").description("Type of owner agent, normally ORGANIZATION").type(JsonFieldType.STRING),
                                fieldWithPath("[].owner.link").description("Client link of owner agent").type(JsonFieldType.STRING),
                                fieldWithPath("[].maintainer").description("Maintainer agent of the statistical program").type(JsonFieldType.OBJECT),
                                fieldWithPath("[].maintainer.id").description("Id of the maintainer agent").type(JsonFieldType.NUMBER),
                                fieldWithPath("[].maintainer.name").description("Name of the maintainer agent").type(JsonFieldType.STRING),
                                fieldWithPath("[].maintainer.type").description("Type of maintainer agent, normally DIVISION").type(JsonFieldType.STRING),
                                fieldWithPath("[].maintainer.link").description("Client link of contact agent").type(JsonFieldType.STRING),
                                fieldWithPath("[].statisticalStandards[]").description("List of statistical standards of the statistical program").type(JsonFieldType.ARRAY),
                                fieldWithPath("[].statisticalStandards[].id").description("Id of the statistical standard").type(JsonFieldType.NUMBER),
                                fieldWithPath("[].statisticalStandards[].name").description("Name of the statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("[].statisticalStandards[].description").description("Description of the statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("[].statisticalStandards[].type").description("Type of statistical standard:  CLASSIFICATIONS, CONCEPTS, DEFINITIONS, METHODOLOGIES, PROCEDURES, RECOMMENDATIONS, FRAMEWORK").type(JsonFieldType.STRING),
                                fieldWithPath("[].statisticalStandards[].version").description("Version of statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("[].statisticalStandards[].link").description("Client link of statistical standard").type(JsonFieldType.STRING),
                                fieldWithPath("[].legislativeReferences[]").description("List of statistical standards of the legislative reference").type(JsonFieldType.ARRAY),
                                fieldWithPath("[].legislativeReferences[].id").description("Id of the legislative reference").type(JsonFieldType.NUMBER),
                                fieldWithPath("[].legislativeReferences[].name").description("Name of the legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("[].legislativeReferences[].description").description("Description of the legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("[].legislativeReferences[].type").description("Type of legislative reference:   REGULATION, LAW, CODE, GOVERNMENTAL_DECISION, AMENDMENT,").type(JsonFieldType.STRING),
                                fieldWithPath("[].legislativeReferences[].version").description("Version of legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("[].legislativeReferences[].link").description("Client link of legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("[].legislativeReferences[].approval").description("First approval date of the legislative reference").type(JsonFieldType.STRING),
                                fieldWithPath("[].legislativeReferences[].number").description("Number of the legislative reference").type(JsonFieldType.NUMBER)


                        ))).andReturn();
    }
}

