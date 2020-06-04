package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.method;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.method.CreateProcessMethodEvent;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateProcessMethodCommand extends AbstractCommand<CreateProcessMethodEvent> {

    private static final long serialVersionUID = 2300L;

    /**
     * Name of the method
     */
    private String name;

    /**
     * Description of the method
     */
    private String description;

    /**
     * Local id of the method default random uuid
     */
    private String localId = UUID.randomUUID().toString();

    /**
     * Version being created default 1.0
     */
    private String version = "1.0";

    /**
     * Version description default "First Version"
     */
    private String versionRationale = "First Version";

    /**
     * Date of the version Default current time
     */
    private LocalDateTime versionDate = LocalDateTime.now();

    private CreateProcessMethodCommand() {
        super(new CreateProcessMethodEvent());
    }

    private CreateProcessMethodCommand(final String jwt, final String localId, final String name, final String description,
                                       final String version, final String versionRationale,
                                       final LocalDateTime versionDate, final Language language) {

        super(new CreateProcessMethodEvent());
        setJwt(jwt);
        this.name = name;
        this.description = description;
        if(StringTools.isNotEmpty(localId)) {
            this.localId = localId;
        }
        if(StringTools.isNotEmpty(version)) {
            this.version = version;
        }
        if(StringTools.isNotEmpty(versionRationale)) {
            this.versionRationale = versionRationale;
        }
        if(versionDate != null) {
            this.versionDate = versionDate;
        }
        setLanguage(language);
        setClosed(true);

    }

    public static CreateProcessMethodCommand create(final String jwt, final String localId, final String name, final String description,
                                                    final String version, final String versionRationale,
                                                    final LocalDateTime versionDate, final Language language) {

        return new CreateProcessMethodCommand(jwt, localId, name, description, version, versionRationale, versionDate, language);

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
