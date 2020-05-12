package com.nbs.iais.ms.meta.referential.common.messageing.commands.statistical.program;

import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.RoleType;
import com.nbs.iais.ms.common.messaging.commands.abstracts.AbstractCommand;
import com.nbs.iais.ms.meta.referential.common.messageing.events.statistical.program.RemoveStatisticalProgramAdministratorEvent;

public class RemoveStatisticalProgramAdministratorCommand extends AbstractCommand<RemoveStatisticalProgramAdministratorEvent> {

    private static final long serialVersionUID = 200L;

    /**
     * id of Statistical Program
     */
    private Long statisticalProgramId;

    /**
     * Id of Agent
     */
    private Long agentId;

    /**
     * Role of agent
     */
    private RoleType roleType;

    private RemoveStatisticalProgramAdministratorCommand() {
        super(new RemoveStatisticalProgramAdministratorEvent());
    }

    private RemoveStatisticalProgramAdministratorCommand(final String jwt, final Long statisticalProgramId,
                                                         final Long agentId, final RoleType roleType,
                                                         final Language language) {
        super(new RemoveStatisticalProgramAdministratorEvent());
        this.statisticalProgramId = statisticalProgramId;
        this.agentId = agentId;
        this.roleType = roleType;
        setJwt(jwt);
        setLanguage(language);
        setClosed(true);
    }

    public static RemoveStatisticalProgramAdministratorCommand create(final String jwt, final Long statisticalProgramId,
                                                               final Long agentId, final RoleType roleType,
                                                               final Language language) {

        return new RemoveStatisticalProgramAdministratorCommand(jwt, statisticalProgramId, agentId, roleType, language);
    }

    public Long getStatisticalProgramId() {
        return statisticalProgramId;
    }

    public void setStatisticalProgramId(final Long statisticalProgramId) {
        this.statisticalProgramId = statisticalProgramId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(final Long agentId) {
        this.agentId = agentId;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(final RoleType roleType) {
        this.roleType = roleType;
    }
}
