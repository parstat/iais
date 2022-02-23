package com.nbs.iais.ms.meta.referential.db.service.legislative.reference;

import com.nbs.iais.ms.common.db.service.tests.ServiceTest;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.LegislativeType;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.CreateLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.DeleteLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.legislative.reference.UpdateLegislativeReferenceCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.LegislativeReferenceEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.LegislativeReferenceRepository;
import com.nbs.iais.ms.meta.referential.db.services.LegislativeReferenceCommandService;
import com.nbs.iais.ms.meta.referential.db.utils.CommandTranslator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class LegislativeReferenceCommandServiceTest extends ServiceTest {

    @Mock
    private LegislativeReferenceRepository repository;

    @InjectMocks
    private LegislativeReferenceCommandService service;

    final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";

    @Test
    public void createLegislativeReferenceTest() {

        //Setup
        final CreateLegislativeReferenceCommand command = CreateLegislativeReferenceCommand.create(jwt, "name",
                "description", "101/2", LocalDateTime.now(), "link", LegislativeType.LAW, "2",
                LocalDateTime.now(), "test", Language.ENG);

        final LegislativeReferenceEntity legislativeReference = CommandTranslator.translate(command);
        legislativeReference.setId(1L);

        when(repository.save(any(LegislativeReferenceEntity.class))).thenReturn(legislativeReference);

        //call
        service.createLegislativeReference(command);

        //verify
        verify(repository).save(any(LegislativeReferenceEntity.class));
    }

    @Test
    public void updateLegislativeReferenceTest() {

        //Setup
        final UpdateLegislativeReferenceCommand command = UpdateLegislativeReferenceCommand.create(jwt, 1L,"name",
                "description", LocalDateTime.now(), "link", LegislativeType.LAW, "2",
                "101/2", LocalDateTime.now(), "test", Language.ENG);

        final LegislativeReferenceEntity legislativeReference = new LegislativeReferenceEntity();
        legislativeReference.setId(1L);
        legislativeReference.setLegislativeType(command.getType());
        legislativeReference.setName(command.getName(), command.getLanguage());
        legislativeReference.setDescription(command.getDescription(), command.getLanguage());
        legislativeReference.setLink(command.getLink(), command.getLanguage());

        when(repository.findById(eq(command.getId()))).thenReturn(Optional.of(legislativeReference));
        when(repository.save(any(LegislativeReferenceEntity.class))).thenReturn(legislativeReference);

        //call
        service.updateLegislativeReference(command);

        //verify
        verify(repository).findById(eq(command.getId()));
        verify(repository).save(any(LegislativeReferenceEntity.class));
    }

    @Test
    public void deleteLegislativeReferenceTest() {
        //Setup
        final DeleteLegislativeReferenceCommand command = DeleteLegislativeReferenceCommand.create(jwt, 1L);

        doNothing().when(repository).deleteById(eq(command.getId()));

        //call
        service.deleteLegislativeReference(command);

        //verify
        verify(repository).deleteById(eq(command.getId()));
    }
}
