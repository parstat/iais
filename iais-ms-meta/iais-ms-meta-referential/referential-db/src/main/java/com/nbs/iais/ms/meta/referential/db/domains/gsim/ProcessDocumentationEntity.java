package com.nbs.iais.ms.meta.referential.db.domains.gsim;

import com.nbs.iais.ms.common.db.domains.abstracts.AbstractIdentifiableArtefact;
import com.nbs.iais.ms.common.db.domains.interfaces.MultilingualText;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AdministrativeDetails;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.AgentInRole;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.*;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocument;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessDocumentation;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.ProcessQuality;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.gsbpm.StatisticalStandardReference;
import com.nbs.iais.ms.common.enums.Frequency;
import com.nbs.iais.ms.common.enums.Language;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "ProcessDocumentation")
@Table(name = "process_documentation", uniqueConstraints = @UniqueConstraint(columnNames = { "business_function_id",
		"statistical_program_id", "version" }))
public class ProcessDocumentationEntity extends AbstractIdentifiableArtefact implements ProcessDocumentation {

	@OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL, targetEntity = MultiLanguageTextEntity.class)
	@JoinColumn(name = "key_name")
	private MultilingualText name;

	@OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL, targetEntity = MultiLanguageTextEntity.class)
	@JoinColumn(name = "key_description")
	private MultilingualText description;

	@ManyToMany(targetEntity = AgentInRoleEntity.class)
	@JoinTable(name = "pd_agent_in_role", joinColumns = @JoinColumn(name = "pd_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "agent_in_role_id", referencedColumnName = "id"))
	private List<AgentInRole> administrators = new ArrayList<>();

	@ManyToMany(targetEntity = ProcessMethodEntity.class)
	@JoinTable(name = "process_documentation_methods", joinColumns = @JoinColumn(name = "process_documentation_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "method_id", referencedColumnName = "id"))
	private List<ProcessMethod> processMethods = new ArrayList<>();

	@ManyToMany(targetEntity = StatisticalStandardReferenceEntity.class)
	@JoinTable(name = "process_documentation_standards", joinColumns = @JoinColumn(name = "process_documentation_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "standard_id", referencedColumnName = "id"))
	private List<StatisticalStandardReference> standardsUsed = new ArrayList<>();

	@OneToMany(targetEntity = ProcessQualityEntity.class, mappedBy = "processDocumentation", orphanRemoval = true)
	// @JoinTable(name = "process_documentation_quality_indicators",
	// joinColumns = @JoinColumn(name = "process_documentation_id",
	// referencedColumnName = "id"),
	// inverseJoinColumns = @JoinColumn(name = "quality_indicator_id",
	// referencedColumnName = "id" ))
	private List<ProcessQuality> processQualityList = new ArrayList<>();

	@OneToMany(targetEntity = ProcessInputSpecificationEntity.class, mappedBy = "processDocumentation", orphanRemoval = true)
	private List<ProcessInputSpecification> processInputSpecifications = new ArrayList<>();

	@OneToMany(targetEntity = ProcessOutputSpecificationEntity.class, mappedBy = "processDocumentation", orphanRemoval = true)
	private List<ProcessOutputSpecification> processOutputSpecifications = new ArrayList<>();

	@OneToMany(targetEntity = ProcessDocumentEntity.class, mappedBy = "processDocumentation", orphanRemoval = true)
	private List<ProcessDocument> processDocuments = new ArrayList<>();

	@OneToOne(orphanRemoval = true, targetEntity = AdministrativeDetailsEntity.class)
	@JoinColumn(name = "administrative_details_id", referencedColumnName = "id")
	private AdministrativeDetails administrativeDetails;

	@ManyToOne(targetEntity = BusinessFunctionEntity.class)
	@JoinColumn(name = "business_function_id", referencedColumnName = "id")
	private BusinessFunction businessFunction;

	@ManyToOne(targetEntity = BusinessFunctionEntity.class)
	@JoinColumn(name = "next_business_function_id", referencedColumnName = "id")
	private BusinessFunction nextBusinessFunction;

	@ManyToOne(targetEntity = StatisticalProgramEntity.class)
	@JoinColumn(name = "statistical_program_id", referencedColumnName = "id")
	private StatisticalProgram statisticalProgram;

	@Enumerated(EnumType.STRING)
	@Column(name = "frequency")
	private Frequency frequency;

	public ProcessDocumentationEntity() {
		super();
	}

	
	public ProcessDocumentationEntity(Long id) {
		super();
		setId(id);
	}

	@Override
	public void setName(final String name, final Language language) {
		name().addText(language.getShortName(), name);
	}

	@Override
	public String getName(final Language language) {
		return name().getText(language.getShortName());
	}

	private MultilingualText name() {
		if (getName() == null) {
			setName(new MultiLanguageTextEntity());
		}
		return getName();
	}

	private MultilingualText description() {
		if (getDescription() == null) {
			setDescription(new MultiLanguageTextEntity());
		}
		return getDescription();
	}

	@Override
	public String getDescription(final Language language) {
		return description().getText(language.getShortName());
	}

	@Override
	public void setDescription(final String description, final Language language) {
		description().addText(language.getShortName(), description);
	}

	@Override
	public MultilingualText getName() {
		return name;
	}

	@Override
	public void setName(final MultilingualText name) {
		this.name = name;
	}

	@Override
	public MultilingualText getDescription() {
		return description;
	}

	@Override
	public void setDescription(final MultilingualText description) {
		this.description = description;
	}

	@Override
	public AdministrativeDetails getAdministrativeDetails() {
		return administrativeDetails;
	}

	@Override
	public void setAdministrativeDetails(final AdministrativeDetails administrativeDetails) {
		this.administrativeDetails = administrativeDetails;
	}

	@Override
	public List<BusinessService> getBusinessServices() {
		return null;
	}

	@Override
	public void setBusinessServices(final List<BusinessService> businessServices) {

	}

	@Override
	public List<ProcessInputSpecification> getProcessInputs() {
		return processInputSpecifications;
	}

	@Override
	public void setProcessInputs(final List<ProcessInputSpecification> processInputs) {
		this.processInputSpecifications = processInputs;
	}

	@Override
	public List<ProcessOutputSpecification> getProcessOutputs() {
		return processOutputSpecifications;
	}

	@Override
	public void setProcessOutputs(final List<ProcessOutputSpecification> processOutputs) {
		this.processOutputSpecifications = processOutputs;
	}

	@Override
	public List<ProcessMethod> getProcessMethods() {
		return processMethods;
	}

	@Override
	public void setProcessMethods(final List<ProcessMethod> processMethods) {
		this.processMethods = processMethods;
	}

	@Override
	public List<ProcessDocument> getProcessDocuments() {
		return processDocuments;
	}

	@Override
	public void setProcessDocuments(final List<ProcessDocument> processDocuments) {
		this.processDocuments = processDocuments;
	}

	@Override
	public List<ProcessQuality> getProcessQualityIndicators() {
		return processQualityList;
	}

	@Override
	public void setProcessQualityIndicators(final List<ProcessQuality> processQualityList) {
		this.processQualityList = processQualityList;
	}

	@Override
	public BusinessFunction getBusinessFunction() {
		return businessFunction;
	}

	@Override
	public void setBusinessFunction(final BusinessFunction businessFunctions) {
		this.businessFunction = businessFunctions;
	}

	@Override
	public BusinessFunction getNextBusinessFunction() {
		return nextBusinessFunction;
	}

	@Override
	public void setNextBusinessFunction(final BusinessFunction businessFunction) {
		this.nextBusinessFunction = businessFunction;
	}

	@Override
	public StatisticalProgram getStatisticalProgram() {
		return statisticalProgram;
	}

	@Override
	public void setStatisticalProgram(final StatisticalProgram statisticalProgram) {
		this.statisticalProgram = statisticalProgram;
	}

	@Override
	public Frequency getFrequency() {
		return frequency;
	}

	@Override
	public void setFrequency(final Frequency frequency) {
		this.frequency = frequency;
	}

	@Override
	public List<StatisticalStandardReference> getStandardsUsed() {
		return standardsUsed;
	}

	@Override
	public void setStandardsUsed(final List<StatisticalStandardReference> standardsUsed) {
		this.standardsUsed = standardsUsed;
	}

	@Override
	public List<AgentInRole> getAdministrators() {
		return administrators;
	}

	@Override
	public void setAdministrators(final List<AgentInRole> administrators) {
		this.administrators = administrators;
	}

}
