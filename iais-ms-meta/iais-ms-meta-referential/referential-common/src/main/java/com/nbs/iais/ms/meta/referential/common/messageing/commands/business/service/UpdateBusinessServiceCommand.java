package com.nbs.iais.ms.meta.referential.common.messageing.commands.business.service;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.events.business.service.CreateBusinessServiceEvent;
import com.nbs.iais.ms.meta.referential.common.messageing.events.business.service.UpdateBusinessServiceEvent;

import java.time.LocalDateTime;
import java.util.UUID;

public class UpdateBusinessServiceCommand extends AbstractCommand<UpdateBusinessServiceEvent> {

    private static final long serialVersionUID = 200L;

    /**
     * Id of the Business Service to update
     */
    private Long id;

    /**
     * Name of Business Service
     */
    private String name;

    /**
     * Description of Business Service
     */
    private String description;

    /**
     * Local id of the Business Service
     */
    private String localId;

    /**
     * Version of Business Service
     */
    private String version;

    /**
     * Reason why this version exits
     */
    private String versionRationale;

    /**
     * Version released date
     */
    private LocalDateTime versionDate;

    private UpdateBusinessServiceCommand() {
        super(new UpdateBusinessServiceEvent());
    }

    private UpdateBusinessServiceCommand(final String jwt, final Long id, final String name, final String description,
                                         final String localId, final String version, final String versionRationale,
                                         final LocalDateTime versionDate, final Language language) {
        super(new UpdateBusinessServiceEvent());
        this.id = id;
        this.name = name;
        this.description = description;
        this.localId = localId;
        this.version = version;
        this.versionRationale = versionRationale;
        this.versionDate = versionDate;

        this.setJwt(jwt);
        this.setLanguage(language);
    }

    public static UpdateBusinessServiceCommand create(final String jwt, final Long id, final String name, final String description,
                                                      final String localId, final String version, final String versionRationale,
                                                      final LocalDateTime versionDate, final Language language) {

        return new UpdateBusinessServiceCommand(jwt, id, name, description, localId, version, versionRationale, versionDate,
                language);
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

    public String getVersionRationale() {
        return versionRationale;
    }

    public void setVersionRationale(final String versionRationale) {
        this.versionRationale = versionRationale;
    }

    public LocalDateTime getVersionDate() {
        return versionDate;
    }

    public void setVersionDate(final LocalDateTime versionDate) {
        this.versionDate = versionDate;
    }
}
