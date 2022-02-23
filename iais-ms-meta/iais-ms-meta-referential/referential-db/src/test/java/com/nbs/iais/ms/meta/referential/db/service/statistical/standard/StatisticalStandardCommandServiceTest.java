package com.nbs.iais.ms.meta.referential.db.service.statistical.standard;

import com.nbs.iais.ms.common.db.service.tests.ServiceTest;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.StatisticalStandardType;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.CreateStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.DeleteStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.standard.UpdateStatisticalStandardCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.StatisticalStandardReferenceEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.StatisticalStandardReferenceRepository;
import com.nbs.iais.ms.meta.referential.db.services.StatisticalStandardCommandService;
import com.nbs.iais.ms.meta.referential.db.utils.CommandTranslator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class StatisticalStandardCommandServiceTest extends ServiceTest {

    @Mock
    private StatisticalStandardReferenceRepository repository;

    @InjectMocks
    private StatisticalStandardCommandService service;

    final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";

    @Test
    public void createStatisticalStandardTest() {
        //Setup

        final CreateStatisticalStandardCommand command = CreateStatisticalStandardCommand.create(jwt, "Name", "Description", null, StatisticalStandardType.CONCEPTS, "1.0", LocalDateTime.now(), "New version", "http://the/link", Language.ENG);

        final StatisticalStandardReferenceEntity statisticalStandard = CommandTranslator.translate(command);
        statisticalStandard.setId(1L);

        when(repository.save(any(StatisticalStandardReferenceEntity.class))).thenReturn(statisticalStandard);

        //call
        service.createStatisticalStandard(command);

        //verify
        verify(repository).save(any(StatisticalStandardReferenceEntity.class));
    }

    @Test
    public void updateStatisticalStandardTest() {
        //Setup
        final UpdateStatisticalStandardCommand command = UpdateStatisticalStandardCommand.create(jwt, 1L, "Name", "Description",  StatisticalStandardType.CONCEPTS, null,"1.0", LocalDateTime.now(), "New version", "http://the/link", Language.ENG);

        final StatisticalStandardReferenceEntity statisticalStandard = new StatisticalStandardReferenceEntity();
        statisticalStandard.setId(1L);
        statisticalStandard.setName(command.getName(), command.getLanguage());
        statisticalStandard.setDescription(command.getDescription(), command.getLanguage());
        statisticalStandard.setVersion(command.getVersion());
        statisticalStandard.setType(command.getType());
        statisticalStandard.setLink(command.getLink(), command.getLanguage());


        when(repository.findById(eq(command.getId()))).thenReturn(Optional.of(statisticalStandard));
        when(repository.save(any(StatisticalStandardReferenceEntity.class))).thenReturn(statisticalStandard);

        //call
        service.updateStatisticalStandard(command);

        //verify
        verify(repository).findById(eq(command.getId()));
        verify(repository).save(any(StatisticalStandardReferenceEntity.class));
    }

    @Test
    public void deleteStatisticalStandardTest() {
        //setup
        final DeleteStatisticalStandardCommand command = DeleteStatisticalStandardCommand.create(jwt, 1L);

        doNothing().when(repository).deleteById(eq(command.getId()));

        //call
        service.deleteStatisticalStandard(command);

        //verify
        verify(repository).deleteById(eq(command.getId()));
    }
}
