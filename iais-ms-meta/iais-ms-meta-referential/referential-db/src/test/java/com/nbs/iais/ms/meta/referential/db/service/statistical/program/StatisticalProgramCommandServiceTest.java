package com.nbs.iais.ms.meta.referential.db.service.statistical.program;

import com.nbs.iais.ms.common.db.service.tests.ServiceTest;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.ProgramStatus;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.CreateStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.DeleteStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program.UpdateStatisticalProgramCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalProgramEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.*;
import com.nbs.iais.ms.meta.referential.db.services.StatisticalProgramCommandService;
import org.junit.Test;
import org.mockito.*;
import org.mockito.stubbing.Answer;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class StatisticalProgramCommandServiceTest extends ServiceTest {

    @Mock
    private StatisticalProgramRepository statisticalProgramRepository;

    @Mock
    private AgentRepository agentRepository;

    @Mock
    private AgentInRoleRepository agentInRoleRepository;

    @Mock
    private LegislativeReferenceRepository legislativeReferenceRepository;

    @Mock
    private StatisticalStandardReferenceRepository statisticalStandardReferenceRepository;

    @InjectMocks
    private StatisticalProgramCommandService service;


    @Test
    public void createStatisticalProgramTest() {
        //Setup
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final CreateStatisticalProgramCommand command = CreateStatisticalProgramCommand.create(jwt, "Statistical Program",
                "Description", "Acronym", "lid001", LocalDateTime.now().minusDays(365),
                null, 1000D, "Source", ProgramStatus.CURRENT, null, null,
                null, Language.ENG);

        Mockito.when(statisticalProgramRepository.save(any(StatisticalProgramEntity.class))).thenAnswer(
                (Answer<StatisticalProgramEntity>) invocation -> {
                    Object[] args = invocation.getArguments();
                    final StatisticalProgramEntity statisticalProgram = (StatisticalProgramEntity) args[0];
                    statisticalProgram.setId(1L);
                    return statisticalProgram;
                });

        //Call
        service.createStatisticalProgram(command);

        //Verify
        ArgumentCaptor<StatisticalProgramEntity> captor = ArgumentCaptor.forClass(StatisticalProgramEntity.class);
        verify(statisticalProgramRepository).existsByLocalId(eq(command.getLocalId()));
        verify(statisticalProgramRepository).save(captor.capture());
        assertThat(captor.getValue().getId()).isNotNull();


    }

    @Test
    public void updateStatisticalProgramTest() {

        //Setup
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final UpdateStatisticalProgramCommand command = UpdateStatisticalProgramCommand.create(jwt, 1L, "Statistical Program",
                "Description", "Acronym", "lid001", LocalDateTime.now().minusDays(365),
                null, null,1000D, "Source", ProgramStatus.CURRENT, Language.ENG);

        final StatisticalProgramEntity statisticalProgram = new StatisticalProgramEntity();
        statisticalProgram.setId(command.getId());
        statisticalProgram.setName(command.getName(), command.getLanguage());
        statisticalProgram.setDescription(command.getDescription(), command.getLanguage());
        statisticalProgram.setAcronym(command.getAcronym(), command.getLanguage());
        statisticalProgram.setProgramStatus(command.getStatus());
        statisticalProgram.setSourceOfFunding(command.getSourceOfFunding());
        statisticalProgram.setDateEnded(command.getDateEnded());
        statisticalProgram.setDateInitiated(command.getDateInitiated());
        statisticalProgram.setBudget(command.getBudget());

        when(statisticalProgramRepository.findById(eq(command.getId()))).thenReturn(Optional.of(statisticalProgram));
        when(statisticalProgramRepository.save(any(StatisticalProgramEntity.class))).thenReturn(statisticalProgram);

        //Call
        service.updateStatisticalProgram(command);

        //Verify
        ArgumentCaptor<StatisticalProgramEntity> captor = ArgumentCaptor.forClass(StatisticalProgramEntity.class);
        verify(statisticalProgramRepository).findById(eq(command.getId()));
        verify(statisticalProgramRepository).save(captor.capture());
        assertThat(captor.getValue().getId()).isNotNull();

    }

    @Test
    public void deleteStatisticalProgramTest() {
        //Setup
        final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";
        final DeleteStatisticalProgramCommand command = DeleteStatisticalProgramCommand.create(jwt, 1L);

        doNothing().when(statisticalProgramRepository).deleteById(eq(command.getId()));

        //call
        service.deleteStatisticalProgram(command);

        //verify
        verify(statisticalProgramRepository).deleteById(eq(command.getId()));

    }


}
