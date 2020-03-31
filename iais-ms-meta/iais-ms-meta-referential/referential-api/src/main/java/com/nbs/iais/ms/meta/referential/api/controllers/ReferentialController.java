package com.nbs.iais.ms.meta.referential.api.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.impl.StatisticalProcessDTO;
import com.nbs.iais.ms.referential.common.messageing.commands.StatisticalProcessQueryCommand;


@RestController
@RequestMapping(value = "/referential", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReferentialController extends AbstractController  {
 
    @GetMapping("/statprocess/{id}")
    public StatisticalProcessDTO StatisticalProcessfindById(@PathVariable("id") final Long id) {
        final StatisticalProcessQueryCommand statisticalProcessQueryCommand = new StatisticalProcessQueryCommand();
        statisticalProcessQueryCommand.setId(id);
       
        return send(statisticalProcessQueryCommand, "referential").getEvent().getData();    	
    }
}
