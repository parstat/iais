package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.quality;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.quaity.RemoveProcessDocumentationQualityEvent;

public class RemoveProcessDocumentationQualityCommand extends AbstractCommand<RemoveProcessDocumentationQualityEvent> {

    private static final long serialVersionUID = 200L;

    private Long documentation;

    private Long quality;

    private RemoveProcessDocumentationQualityCommand() {
        super(new RemoveProcessDocumentationQualityEvent());
    }

    private RemoveProcessDocumentationQualityCommand(final String jwt, final Long documentation, final Long quality,
                                                     final Language language) {
        super(new RemoveProcessDocumentationQualityEvent());
        setJwt(jwt);
        setLanguage(language);
        setClosed(true);
        this.documentation = documentation;
        this.quality = quality;
    }

    public static RemoveProcessDocumentationQualityCommand create(final String jwt, final Long documentation,
                                                                  final Long quality, final Language language) {
        return new RemoveProcessDocumentationQualityCommand(jwt, documentation, quality, language);
    }

    public Long getDocumentation() {
        return documentation;
    }

    public void setDocumentation(final Long documentation) {
        this.documentation = documentation;
    }

    public Long getQuality() {
        return quality;
    }

    public void setQuality(final Long quality) {
        this.quality = quality;
    }
}
