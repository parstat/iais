package com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.RoleType;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.statistical.program.AddStatisticalProgramAdministratorEvent;

public class AddStatisticalProgramAdministratorCommand extends AbstractCommand<AddStatisticalProgramAdministratorEvent> {

    private static final long serialVersionUID = 200L;

    private Long statisticalProgram;
    private Long agent;
    private RoleType role;

    private AddStatisticalProgramAdministratorCommand() {
        super(new AddStatisticalProgramAdministratorEvent());
    }

    private AddStatisticalProgramAdministratorCommand(final String jwt, final Long statisticalProgram, final Long agent,
                                                      final RoleType role, final Language language) {
        super(new AddStatisticalProgramAdministratorEvent());
        this.statisticalProgram = statisticalProgram;
        this.agent = agent;
        this.role = role;
        setLanguage(language);
        setJwt(jwt);
        setClosed(true);
    }

    public static AddStatisticalProgramAdministratorCommand create(final String jwt, final Long statisticalProgram, final Long agent,
                                                            final RoleType role, final Language language) {

        return new AddStatisticalProgramAdministratorCommand(jwt, statisticalProgram,agent, role,
                language);
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

    public Long getStatisticalProgram() {
        return statisticalProgram;
    }

    public void setStatisticalProgram(final Long statisticalProgram) {
        this.statisticalProgram = statisticalProgram;
    }
}
