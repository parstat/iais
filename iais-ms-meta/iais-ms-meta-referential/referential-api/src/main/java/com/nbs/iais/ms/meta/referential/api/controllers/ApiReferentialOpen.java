package com.nbs.iais.ms.meta.referential.api.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.AgentDTO;
import com.nbs.iais.ms.common.dto.impl.BusinessFunctionDTO;
import com.nbs.iais.ms.common.dto.impl.LegislativeReferenceDTO;
import com.nbs.iais.ms.common.dto.impl.StatisticalProgramDTO;
import com.nbs.iais.ms.common.dto.impl.StatisticalStandardDTO;
import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.LegislativeType;
import com.nbs.iais.ms.common.enums.StatisticalStandardType;
import com.nbs.iais.ms.common.utils.StringTools;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.legislative.reference.GetLegislativeReferenceQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.legislative.reference.GetLegislativeReferencesQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard.GetStatisticalStandardQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard.GetStatisticalStandardsQuery;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramQuery;

/**
 * All API method in this controller does not require being registered
 * <p>Open methods</p>
 */
@RestController
@RequestMapping(value = "/api/v1/referential", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiReferentialOpen extends AbstractController {


	/**
	 * Method to get statistical programs (survey) by different filters
	 * @param name the name to search the statistical surveys
	 *             if this parameter has value the other filter parameters will be ignored
	 * @param maintainer the division id, to get all statistical surveys by division id
	 * @param language the language to present the DTOs (en, ro, ru)
	 *                 also the query will search only in the selected language name of the statistical survey
	 * @return List of all  Surveys in selected language if not parameter provided or will return only filtered
	 * surveys
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/statistical/programs")
	public DTOList<StatisticalProgramDTO> getStatisticalPrograms(
			@RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "maintainer", required = false) final Long maintainer,
			@RequestParam(name = "language") final String language) {

		final GetStatisticalProgramsQuery getStatisticalProgramsQuery = GetStatisticalProgramsQuery.create(name, maintainer, Language.getLanguage(language));
		return sendQuery(getStatisticalProgramsQuery, "referential").getRead().getData();

	}

	/**
	 * Method to get a statistical program (survey) by id
	 * @param id the id of the requested statistical program (survey)
	 * @param language the language to present the returned DTO (en, ro, ru)
	 * @return StatisticalProgramDTO (the requested survey presented in the selected language)
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/statistical/programs/{id}")
	public StatisticalProgramDTO getStatisticalProgram(
			@PathVariable(name = "id") final Long id,
			@RequestParam(name = "language") final String language) {

		final GetStatisticalProgramQuery getStatisticalProcessQuery = GetStatisticalProgramQuery.create(id,
				Language.getLanguage(language));
		return sendQuery(getStatisticalProcessQuery, "referential").getRead().getData();
	}

	/**
	 * Method to get a statistical program (survey) by local id and version
	 * @param localId local id of the requested statistical program (survey)
	 * @param version the version of the statistical program (survey)
	 * @param language to present the returned DTO (en, ro, ru)
	 * @return StatisticalProgramDTO (the requested survey presented in the selected language)
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/statistical/programs/{local_id}/versions/{version}")
	public StatisticalProgramDTO getStatisticalProgramByVersion(
			@PathVariable(name = "local_id") final String localId,
			@PathVariable(name = "version") final String version,
			@RequestParam(name = "language") final String language) {

		final GetStatisticalProgramQuery getStatisticalProcessQuery = GetStatisticalProgramQuery.create(localId, version,
				Language.getLanguage(language));

		return sendQuery(getStatisticalProcessQuery, "referential").getRead().getData();
	}

	/**
	 * Method to get latest versions of a statistical program (survey)
	 * @param localId the local id of the statistical program (survey)
	 * @param language the language to present the returned DTO (en, ro, ru)
	 * @return StatisticalProgramDTO (the latest versions of the survey presented in the selected language)
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/statistical/programs/{local_id}/latest")
	public StatisticalProgramDTO getLatestVersionStatisticalProgram(
			@PathVariable(name = "local_id") final String localId,
			@RequestParam(name = "language") final String language) {

		final GetStatisticalProgramQuery getStatisticalProgramQuery = GetStatisticalProgramQuery.create(localId,
				Language.getLanguage(language));

		return sendQuery(getStatisticalProgramQuery, "referential").getRead().getData();
	}

	/**
	 * Method to get all versions of a statistical program (survey)
	 * @param localId of the statistical program (survey)
	 * @param language to present the returned DTO (en, ro, ru)
	 * @return (the requested versions of the survey presented in the selected language)
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/statistical/programs/{local_id}/versions")
	public DTOList<StatisticalProgramDTO> getAllVersionsOfStatisticalProgram(
			@PathVariable(name = "local_id") final String localId,
			@RequestParam(name = "language") final String language) {

		final GetStatisticalProgramsQuery getStatisticalProgramsQuery = GetStatisticalProgramsQuery
				.create(Language.getLanguage(language));
		getStatisticalProgramsQuery.setLocalId(localId);
		return sendQuery(getStatisticalProgramsQuery, "referential").getRead().getData();
	}

	/**
	 * Method to get a business function (gsbpm sub-phase) by id
	 * @param id sub-phase id
	 * @param language the language to present the returned DTO (en, ro, ru)
	 * @return BusinessFunctionDTO (the requested gsbpm sub-phase in the selected language)
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/business/functions/{id}")
	public BusinessFunctionDTO getBusinessFunction(
			@PathVariable(name = "id") final Long id,
			@RequestParam(name = "language") final String language) {

		final GetBusinessFunctionQuery getBusinessFunctionQuery = GetBusinessFunctionQuery.create(id,
				Language.getLanguage(language));

		return sendQuery(getBusinessFunctionQuery, "referential").getRead().getData();
	}

	/**
	 * Method to get a business function (gsbpm sub-phase) by local id and version
	 * <p> Sub-phase of gsbpm can have different version, current is 5.1
	 * if the user is interested in another version can use this method </p>
	 * @param localId the id of sub-phase
	 * @param version version of sub-phase (default 5.1)
	 * @param language the language to present the returned DTO (en, ro, ru)
	 * @return BusinessFunctionDTO (gsbpm sub-phase in the requested language)
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/business/functions/sub-phase/{localId}/versions/{version}")
	public BusinessFunctionDTO getBusinessFunction(
			@PathVariable(name = "sub_phase") final String localId,
			@PathVariable(name = "version") final String version,
			@RequestParam(name = "language") final String language) {

		final GetBusinessFunctionQuery getBusinessFunctionQuery = GetBusinessFunctionQuery.create(localId,
					version, Language.getLanguage(language));
		return sendQuery(getBusinessFunctionQuery, "referential").getRead().getData();
	}

	/**
	 * FIXME FLORIAN
	 * Method to get the current version of gsbpm sub-phse (business function)
	 * <p>Current version is hardcoded to 5.1 method should be changed to get always the latest version</p>
	 * @param localId the id of gsbpm sub-phase
	 * @param language the language to present the returned DTO (en, ro, ru)
	 * @return BusinessFunctionDTO the requested business function (gsbpm sub-phase) in the selected language
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/business/functions/sub-phase/{localId}")
	public BusinessFunctionDTO getBusinessFunction(
			@PathVariable(name = "localId") final String localId,
			@RequestParam(name = "language") final String language) {

		final GetBusinessFunctionQuery getBusinessFunctionQuery = GetBusinessFunctionQuery.create(localId,
				Language.getLanguage(language));
		return sendQuery(getBusinessFunctionQuery, "referential").getRead().getData();
	}

	/**
	 * Method to get business functions using different filters
	 * @param name the string to search on the name of the selected language
	 * @param phase to get all business function of this phase
	 * @param language the selected language to return the result
	 * @return a list of BusinessFunctionDTO in the selected language
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/business/functions")
	public DTOList<BusinessFunctionDTO> getBusinessFunctions(
			@RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "phase", required = false) final int phase,
			@RequestParam(name = "language") final String language) {

		final GetBusinessFunctionsQuery query = GetBusinessFunctionsQuery.create(name, phase,null, Language.getLanguage(language));

		return sendQuery(query, "referential").getRead().getData();
	}

	/**
	 * Method to get many agents in the selected language
	 * @param name the name to search the agents
	 * @param type the type of agent: DIVISION, ORGANIZATION, DEPARTMENT, INDIVIDUAL
	 * @param parent the agent id to return all children
	 * @param language the language to present the returned DTO (en, ro, ru)
	 * @return a list of filtered agents in the selected language
	 * all agents if no filter parameter has been provided
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/agents")
	public DTOList<AgentDTO> getAgentsQuery(
			@RequestParam(name = "type", required = false) final AgentType type,
			@RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "parent", required = false) final Long parent,
			@RequestParam(name = "language") final String language) {

		final GetAgentsQuery getAgentsQuery = GetAgentsQuery.create(type,name,parent, Language.getLanguage(language));
		return sendQuery(getAgentsQuery, "referential").getRead().getData();

	}
	
	
	/**
	 * Method to get the agent by id
	 * @param id the id of the agent
	 * @param language the language to present the returned DTO (en, ro, ru)
	 * @return AgentDTO in the selected language
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/agents/{id}")
	public AgentDTO getAgentQuery(@PathVariable(name = "id") final Long id,
			@RequestParam(name = "language") final String language) {

		final GetAgentQuery getAgentQuery = GetAgentQuery.create(id, Language.getLanguage(language));
		return sendQuery(getAgentQuery, "referential").getRead().getData();
	}
	
	/**
	 * FIXME not sure wee need this method
	 * Method to get the agent by localId
	 * @param localId the local id of the agent
	 * @param language the language to present the returned DTO (en, ro, ru)
	 * @return AgentDTO in the selected language
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/agents/localid/{localId}")
	public AgentDTO getAgentQueryByLocalId(@PathVariable(name = "localId") final String localId,
			@RequestParam(name = "language") final String language) {

		final GetAgentQuery getAgentQuery = GetAgentQuery.create();
		getAgentQuery.setLocalId(localId);
		getAgentQuery.setLanguage(Language.getLanguage(language));
		return sendQuery(getAgentQuery, "referential").getRead().getData();
	}
	
	/**
	 * FIXME not sure this method should be open (the user requesting this info must be ADMIN or self)
	 * Method to get the agent by account
	 * @param account id of registered account that is mapped with an agent
	 * @param language the language to present the returned DTO (en, ro, ru)
	 * @return AgentDTO in the selected language
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/agents/account/{account}")
	public AgentDTO getAgentQueryByAccount(@PathVariable(name = "account") final Long account,
			@RequestParam(name = "language") final String language) {

		final GetAgentQuery getAgentQuery = GetAgentQuery.create();
		getAgentQuery.setAccountId(account);
		getAgentQuery.setLanguage(Language.getLanguage(language));
		return sendQuery(getAgentQuery, "referential").getRead().getData();
	}

	/**
	 * Method to get many statistical standards in the selected language
	 * @param name the name to search the statistical standards
	 * @param type the type of Statistical Standard: CLASSIFICATIONS, CONCEPTS,
	 *                          DEFINITIONS, METHODOLOGIES, PROCEDURES,
	 *                         RECOMMENDATIONS, FRAMEWORK
	 * @param language the language to present the returned DTO (en, ro, ru)
	 * @return a list of filtered statistical standards in the selected language
	 * all statistical standards if no filter parameter has been provided
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/statistical/standards")
	public DTOList<StatisticalStandardDTO> getStatisticalStandardsQuery(
			@RequestParam(name = "type", required = false) final StatisticalStandardType type,
			@RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "language") final String language) {

		final GetStatisticalStandardsQuery getStatisticalStandardsQuery = GetStatisticalStandardsQuery.create(type,name, Language.getLanguage(language));
		return sendQuery(getStatisticalStandardsQuery, "referential").getRead().getData();

	}
	

	/**
	 * Method to get the statistical standards by id
	 * @param id the id of the statistical standards
	 * @param language the language to present the returned DTO (en, ro, ru)
	 * @return StatisticalStandardDTO in the selected language
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/statistical/standards/{id}")
	public StatisticalStandardDTO getStatisticalStandardQuery(@PathVariable(name = "id") final Long id,
			@RequestParam(name = "language") final String language) {

		final GetStatisticalStandardQuery getStatisticalStandardQuery = GetStatisticalStandardQuery.create(id);
		getStatisticalStandardQuery.setLanguage(Language.getLanguage(language));
		return sendQuery(getStatisticalStandardQuery, "referential").getRead().getData();
	}
	
	/**  FIXME it required unique local_Id
	 * Method to get the agent by statistical standards
	 * @param localId the local id of the statistical standards
	 * @param language the language to present the returned DTO (en, ro, ru)
	 * @return StatisticalStandardDTO in the selected language
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/statistical/standards/localid/{localId}")
	public StatisticalStandardDTO getStatisticalStandardQueryByLocalId(@PathVariable(name = "localId") final String localId,
			@RequestParam(name = "language") final String language) {

		final GetStatisticalStandardQuery getStatisticalStandardQuery = GetStatisticalStandardQuery.create();
		getStatisticalStandardQuery.setLocalId(localId);
		getStatisticalStandardQuery.setLanguage(Language.getLanguage(language));
		return sendQuery(getStatisticalStandardQuery, "referential").getRead().getData();
	}


	/** FIXME FRancesco
	 * Method to get many statistical standards in the selected language
	 * @param name the name to search the statistical standards
	 * @param type the type of Statistical Standard: CLASSIFICATIONS, CONCEPTS,
	 *                          DEFINITIONS, METHODOLOGIES, PROCEDURES,
	 *                         RECOMMENDATIONS, FRAMEWORK
	 * @param language the language to present the returned DTO (en, ro, ru)
	 * @return a list of filtered statistical standards in the selected language
	 * all statistical standards if no filter parameter has been provided
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/legislative/references")
	public DTOList<LegislativeReferenceDTO> getLegislativeReferencesQuery(
			@RequestParam(name = "type", required = false) final LegislativeType type,
			@RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "number", required = false) final Integer number,
			@RequestParam(name = "language") final String language) {

		final GetLegislativeReferencesQuery getLegislativeReferencesQuery = GetLegislativeReferencesQuery.create(type,name,number, Language.getLanguage(language));
		return sendQuery(getLegislativeReferencesQuery, "referential").getRead().getData();

	}
	


	/**  FIXME FRancesco
	 * Method to get the legislative reference by id
	 * @param id the id of the legislative reference
	 * @param language the language to present the returned DTO (en, ro, ru)
	 * @return LegislativeReferenceDTO in the selected language
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/legislative/references/{id}")
	public LegislativeReferenceDTO getLegislativeReferenceQuery(@PathVariable(name = "id") final Long id,
			@RequestParam(name = "language") final String language) {

		final GetLegislativeReferenceQuery getLegislativeReferenceQuery = GetLegislativeReferenceQuery.create(id);
		getLegislativeReferenceQuery.setLanguage(Language.getLanguage(language));
		return sendQuery(getLegislativeReferenceQuery, "referential").getRead().getData();
	}
	
	/**   FIXME FRancesco
	 *  FIXME it required unique local_Id
	 * Method to get the agent by legislative references
	 * @param localId the local id of the legislative references
	 * @param language the language to present the returned DTO (en, ro, ru)
	 * @return LegislativeReferenceDTO in the selected language
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/legislative/references/localid/{localId}")
	public LegislativeReferenceDTO getLegislativeReferenceQueryByLocalId(@PathVariable(name = "localId") final String localId,
			@RequestParam(name = "language") final String language) {

		final GetLegislativeReferenceQuery getLegislativeReferenceQuery = GetLegislativeReferenceQuery.create();
		getLegislativeReferenceQuery.setLocalId(localId);
		getLegislativeReferenceQuery.setLanguage(Language.getLanguage(language));
		return sendQuery(getLegislativeReferenceQuery, "referential").getRead().getData();
	}
}
