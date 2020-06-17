package com.nbs.iais.ms.meta.referential.common.messageing.commands.process.documentation;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.RoleType;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.process.documentation.RemoveProcessDocumentationAdministratorEvent;

public class  RemoveProcessDocumentationAdministratorCommand extends AbstractCommand<RemoveProcessDocumentationAdministratorEvent> {

    private static final long serialVersionUID = 22200L;

    private Long processDocumentation;

    private Long agent;

    private RoleType role;

    private RemoveProcessDocumentationAdministratorCommand() {
        super(new RemoveProcessDocumentationAdministratorEvent());
    }

    private RemoveProcessDocumentationAdministratorCommand(final String jwt, final Long processDocumentation,
                                                           final Long agent, final RoleType role,
                                                           final Language language) {

        super(new RemoveProcessDocumentationAdministratorEvent());
        setClosed(true);
        setLanguage(language);
        setJwt(jwt);
        this.processDocumentation = processDocumentation;
        this.agent = agent;
        this.role = role;

    }

    public static RemoveProcessDocumentationAdministratorCommand create(final String jwt, final Long processDocumentation,
                                                                        final Long agent, final RoleType role,
                                                                        final Language language) {
        return new RemoveProcessDocumentationAdministratorCommand(jwt, processDocumentation, agent, role, language);
    }

    public Long getProcessDocumentation() {
        return processDocumentation;
    }

    public void setProcessDocumentation(final Long processDocumentation) {
        this.processDocumentation = processDocumentation;
    }

    public Long getAgent() {
        return agent;
    }

    public void setAgent(final Long agent) {
        this.agent = agent;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(final RoleType role) {
        this.role = role;
    }
}
