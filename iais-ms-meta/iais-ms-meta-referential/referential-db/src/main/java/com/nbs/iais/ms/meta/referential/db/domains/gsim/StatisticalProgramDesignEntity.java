package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractIdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AdministrativeDetails;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AgentInRole;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.ChangeEvent;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.*;
import com.nbs.iais.ms.common.enums.ProgramStatus;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "StatisticalProgramDesign")
@Table(name = "statistical_program_design")
public class StatisticalProgramDesignEntity extends AbstractIdentifiableArtefact implements StatisticalProgramDesign {


    @Override
    public List<String> getConceptualFrameworks() {
        return null;
    }

    @Override
    public void setConceptualFrameworks(List<String> conceptualFrameworks) {

    }

    @Override
    public LocalDateTime getDateEnded() {
        return null;
    }

    @Override
    public void setDateEnded(LocalDateTime dateEnded) {

    }

    @Override
    public LocalDateTime getDateInitiated() {
        return null;
    }

    @Override
    public void setDateInitiated(LocalDateTime dateStarted) {

    }

    @Override
    public ProgramStatus getProgramDesignStatus() {
        return null;
    }

    @Override
    public void setProgramDesignStatus(ProgramStatus programDesignStatus) {

    }

    @Override
    public List<StatisticalProgram> getStatisticalPrograms() {
        return null;
    }

    @Override
    public void setStatisticalPrograms(List<StatisticalProgram> statisticalPrograms) {

    }

    @Override
    public List<BusinessService> getBusinessServices() {
        return null;
    }

    @Override
    public void setBusinessServices(List<BusinessService> businessServices) {

    }

    @Override
    public List<ProcessDesign> getProcessDesigns() {
        return null;
    }

    @Override
    public void setProcessDesigns(List<ProcessDesign> processDesigns) {

    }

    @Override
    public List<BusinessCase> getBasedBusinessCases() {
        return null;
    }

    @Override
    public void setBasedBusinessCases(List<BusinessCase> basedBusinessCases) {

    }

    @Override
    public MultilingualText getName() {
        return null;
    }

    @Override
    public void setName(MultilingualText name) {

    }

    @Override
    public MultilingualText getDescription() {
        return null;
    }

    @Override
    public void setDescription(MultilingualText description) {

    }

    @Override
    public ChangeEvent getChangeEvent() {
        return null;
    }

    @Override
    public void setChangeEvent(ChangeEvent changeEvent) {

    }

    @Override
    public List<AgentInRole> getAdministrators() {
        return null;
    }

    @Override
    public void setAdministrators(List<AgentInRole> administrators) {

    }

    @Override
    public AdministrativeDetails getAdministrativeDetails() {
        return null;
    }

    @Override
    public void setAdministrativeDetails(AdministrativeDetails administrativeDetails) {

    }
}
