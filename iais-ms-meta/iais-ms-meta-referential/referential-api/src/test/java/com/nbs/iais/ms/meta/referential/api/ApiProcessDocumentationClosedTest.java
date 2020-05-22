package com.nbs.iais.ms.meta.referential.api;

import com.nbs.iais.ms.common.api.messaging.gateway.IAISGateway;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.Frequency;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.api.controllers.ApiProcessDocumentationClosed;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation.CreateProcessDocumentationCommand;
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
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

        when(iaisGateway.sendCommand(any(CreateProcessDocumentationCommand.class), anyString())).thenReturn(command);

        mockMvc.perform(put("/api/v1/close/referential/process/documentations/program/{statistical_program}/function/{business_function}", command.getStatisticalProgram(), command.getBusinessFunction())
                .header("jwt-auth", jwt)
                .param("language", "en")
                .param("name", "Name")
                .param("description", command.getDescription())
                .param("local_id", command.getLocalId())
                .param("version", command.getVersion())
                .param("version_date", command.getVersionDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .param("versionRationale", command.getVersionRationale())
                .param("frequency", command.getFrequency().toString())
                .param("maintainer", command.getMaintainer().toString())
                .param("nextSubPhase", command.getNextSubPhase())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        ArgumentCaptor<CreateProcessDocumentationCommand> argumentCaptor = ArgumentCaptor.forClass(CreateProcessDocumentationCommand.class);
        verify(iaisGateway).sendCommand(argumentCaptor.capture(), anyString());
    }
}
