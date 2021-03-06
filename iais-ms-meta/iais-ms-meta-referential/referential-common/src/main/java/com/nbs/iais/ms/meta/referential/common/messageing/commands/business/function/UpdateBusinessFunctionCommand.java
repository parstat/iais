package com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.business.function.UpdateBusinessFunctionEvent;

import java.time.LocalDateTime;

public class UpdateBusinessFunctionCommand extends AbstractCommand<UpdateBusinessFunctionEvent> {

    private static final long serialVersionUID = 200L;

    /**
     * id of the business function is being updated
     */
    private Long id;

    /**
     * new name, if null or empty will not change the previous one
     * also adding a name in a new language will be considered an update
     * if the name on that language exist is going to be replaced by this one
     * if not will be added a new name in the selected language
     */
    private String name;

    /**
     * new description, if null or empty will not change the previous one
     * also adding a description in a new language will be considered an update
     * if the description on that language exist is going to be replaced by this one
     * if not will be added a new description in the selected language
     */
    private String description;


    private UpdateBusinessFunctionCommand() {
        super(new UpdateBusinessFunctionEvent());
    }

    private UpdateBusinessFunctionCommand(final String jwt, final Long id, final String name, final String description,
                                          final Language language) {
        super(new UpdateBusinessFunctionEvent());
        this.id = id;
        this.name = name;
        this.description = description;
        setLanguage(language);
        setJwt(jwt);

    }


    public static UpdateBusinessFunctionCommand create(final String jwt, final Long id, final String name, final String description,
                                                       final Language language) {
        return new UpdateBusinessFunctionCommand(jwt, id, name, description, language);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }


}
