package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.method;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.method.UpdateProcessMethodEvent;


public class UpdateProcessMethodCommand extends AbstractCommand<UpdateProcessMethodEvent> {

    private static final long serialVersionUID = 2300L;

    /**
     * Id of the process method to update
     */
    private Long id;

    /**
     * Name of the method
     */
    private String name;

    /**
     * Description of the method
     */
    private String description;

    /**
     * Local id of the method
     */
    private String localId;


    private UpdateProcessMethodCommand() {
        super(new UpdateProcessMethodEvent());
    }

    private UpdateProcessMethodCommand(final String jwt, final Long id, final String localId, final String name,
                                       final String description, final Language language) {

        super(new UpdateProcessMethodEvent());
        this.id = id;
        setJwt(jwt);
        this.localId = localId;
        this.name = name;
        this.description = description;
        setLanguage(language);
        setClosed(true);

    }

    public static UpdateProcessMethodCommand create(final String jwt, final Long id, final String localId, final String name, final String description,
                                                    final Language language) {

        return new UpdateProcessMethodCommand(jwt, id, localId, name, description,  language);

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

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(final String localId) {
        this.localId = localId;
    }

}
