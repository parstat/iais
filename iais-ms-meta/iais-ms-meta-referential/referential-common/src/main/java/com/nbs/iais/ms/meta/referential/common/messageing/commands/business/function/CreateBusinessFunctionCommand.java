package com.nbs.iais.ms.meta.referential.common.messageing.commands.business.function;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.business.function.CreateBusinessFunctionEvent;

import java.time.LocalDateTime;

public class CreateBusinessFunctionCommand extends AbstractCommand<CreateBusinessFunctionEvent> {

    private static final long serialVersionUID = 200L;

    /**
     * The name will be added in the command selected language
     */
    private String name;

    /**
     * The description will be added in the command selected language
     */
    private String description;

    /**
     * localId will be the GSBPM sub-phase number
     * i.e: 1.1
     */
    private String localId;

    /**
     * will be the GSBPM version
     * default 5.1
     */
    private String version = "5.1";

    /**
     * will be the GSBPM version 5.1 data
     * default: '01/01/2019T00:00'
     */
    private LocalDateTime versionDate = LocalDateTime.of(2019,1, 1, 0, 0);

    private CreateBusinessFunctionCommand() {
        super(new CreateBusinessFunctionEvent());
    }

    private CreateBusinessFunctionCommand(final String jwt, final String name, final String description,
                                          final String localId, final Language language) {
        super(new CreateBusinessFunctionEvent());
        this.name = name;
        this.description = description;
        this.localId = localId;
        setLanguage(language);
        setJwt(jwt);
    }

    public static CreateBusinessFunctionCommand create(final String jwt, final String name, final String description,
                                                       final String localId, final Language language) {
        return new CreateBusinessFunctionCommand(jwt, name, description, localId, language);
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
