package com.nbs.iais.ms.meta.referential.common.messageing.commands.business.service;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.events.business.service.CreateBusinessServiceEvent;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class CreateBusinessServiceCommand extends AbstractCommand<CreateBusinessServiceEvent> {

    private static final long serialVersionUID = 200L;

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

    private CreateBusinessServiceCommand() {
        super(new CreateBusinessServiceEvent());
    }

    private CreateBusinessServiceCommand(final String jwt, final String name, final String description,
                                         final String localId, final String version, final String versionRationale,
                                         final LocalDateTime versionDate, final Language language) {
        super(new CreateBusinessServiceEvent());
        this.name = name;
        this.description = description;
        if(StringTools.isNotEmpty(localId)) {
            this.localId = localId;
        } else {
            this.localId = UUID.randomUUID().toString();
        }

        if(StringTools.isNotEmpty(version)) {
            this.version = version;
        } else {
            this.version = "1.0";
        }

        if(StringTools.isNotEmpty(versionRationale)) {
            this.versionRationale = versionRationale;
        } else {
            this.versionRationale = "First version";
        }

        this.versionDate = Objects.requireNonNullElseGet(versionDate, LocalDateTime::now);

        this.setJwt(jwt);
        this.setLanguage(language);
    }

    public static CreateBusinessServiceCommand create(final String jwt, final String name, final String description,
                                                      final String localId, final String version, final String versionRationale,
                                                      final LocalDateTime versionDate, final Language language) {

        return new CreateBusinessServiceCommand(jwt, name, description, localId, version, versionRationale, versionDate,
                language);
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
