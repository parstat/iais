package com.nbs.iais.ms.meta.referential.db.service.process.method;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.ProcessMethod;
import com.nbs.iais.ms.common.db.service.tests.ServiceTest;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.method.CreateProcessMethodCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.method.DeleteProcessMethodCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.commands.process.method.UpdateProcessMethodCommand;
import com.nbs.iais.ms.meta.referential.db.domains.gsim.ProcessMethodEntity;
import com.nbs.iais.ms.meta.referential.db.repositories.ProcessMethodRepository;
import com.nbs.iais.ms.meta.referential.db.services.ProcessMethodCommandService;
import com.nbs.iais.ms.meta.referential.db.utils.CommandTranslator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


public class ProcessMethodCommandServiceTest extends ServiceTest {

    @Mock
    private ProcessMethodRepository repository;

    @InjectMocks
    private ProcessMethodCommandService service;

    final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpc3MiOiJpYWlzIiwibmFtZSI6IkZsb3JpYW4gTmlrYSIsImV4cCI6MTU4NjE4MzUwNSwiaWF0IjoxNTg2MTc5OTA1LCJ1c2VyIjoxfQ.xs5EaJie5DsmrUBRoUSHysKUoKXuuuKJ-8YPGIk1OqU";

    @Test
    public void createProcessMethodTest() {

        final CreateProcessMethodCommand command = CreateProcessMethodCommand.create(jwt, null,"Name",
                "Description", "1.0", "Rationale", LocalDateTime.now(), Language.ENG);

        final ProcessMethodEntity processMethod = CommandTranslator.translate(command);

        when(repository.save(any(ProcessMethodEntity.class))).thenReturn(processMethod);

        //call
        service.createProcessMethod(command);

        //verify
        verify(repository).save(any(ProcessMethodEntity.class));
    }

    @Test
    public void updateProcessMethodTest() {

        final UpdateProcessMethodCommand command = UpdateProcessMethodCommand.create(jwt, 1L,null,
                "Description", "1.0", Language.ENG);

        final ProcessMethodEntity processMethod = new ProcessMethodEntity();
        CommandTranslator.translate(command, processMethod);

        when(repository.findById(eq(command.getId()))).thenReturn(Optional.of(processMethod));
        when(repository.save(any(ProcessMethodEntity.class))).thenReturn(processMethod);

        //call
        service.updateProcessMethod(command);

        //verify
        verify(repository).findById(eq(command.getId()));
        verify(repository).save(any(ProcessMethodEntity.class));
    }

    @Test
    public void deleteProcessMethodTest() {
        //setup
        final DeleteProcessMethodCommand command = DeleteProcessMethodCommand.create(jwt, 1L);

        doNothing().when(repository).deleteById(eq(command.getId()));

        //call
        service.deleteProcessMethod(command);

        //verify
        verify(repository).deleteById(eq(command.getId()));
    }
}
