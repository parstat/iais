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

    /**
     * new localId, if null or empty will not change the previous one
     */
    private String localId;

    /**
     * new version, if null or empty will not change the previous one
     */
    private String version;

    /**
     * new version date, if null or empty will not change the previous one
     */
    private LocalDateTime versionDate;

    private UpdateBusinessFunctionCommand() {
        super(new UpdateBusinessFunctionEvent());
    }

    private UpdateBusinessFunctionCommand(final String jwt, final Long id, final String name, final String description,
                                          final String localId, final String version, final LocalDateTime versionDate,
                                          final Language language) {
        super(new UpdateBusinessFunctionEvent());
        this.id = id;
        this.name = name;
        this.description = description;
        this.localId = localId;
        this.version = version;
        this.versionDate = versionDate;
        setLanguage(language);
        setJwt(jwt);

    }

    public static UpdateBusinessFunctionCommand create(final String jwt, final Long id, final String name, final String description,
                                                       final String localId, final String version, final LocalDateTime versionDate,
                                                       final Language language) {
        return new UpdateBusinessFunctionCommand(jwt, id, name, description, localId, version, versionDate, language);
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

    public String getVersion() {
        return version;
    }

    public void setVersion(final String version) {
        this.version = version;
    }

    public LocalDateTime getVersionDate() {
        return versionDate;
    }

    public void setVersionDate(final LocalDateTime versionDate) {
        this.versionDate = versionDate;
    }
}
