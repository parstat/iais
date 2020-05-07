package com.nbs.iais.ms.meta.referential.api.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.Views;
import com.nbs.iais.ms.common.dto.impl.AgentDTO;
import com.nbs.iais.ms.common.dto.impl.BusinessFunctionDTO;
import com.nbs.iais.ms.common.dto.impl.LegislativeReferenceDTO;
import com.nbs.iais.ms.common.dto.impl.ProcessDocumentationDTO;
import com.nbs.iais.ms.common.dto.impl.StatisticalProgramDTO;
import com.nbs.iais.ms.common.dto.impl.StatisticalStandardDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.AgentType;
import com.nbs.iais.ms.common.enums.Frequency;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.LegislativeType;
import com.nbs.iais.ms.common.enums.StatisticalStandardType;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.agent.GetAgentsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.business.function.GetBusinessFunctionsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.legislative.reference.GetLegislativeReferenceQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.legislative.reference.GetLegislativeReferencesQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.documentation.GetProcessDocumentationQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.process.documentation.GetProcessDocumentationsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard.GetStatisticalStandardQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statisical.standard.GetStatisticalStandardsQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramQuery;
import com.nbs.iais.ms.meta.referential.common.messageing.queries.statistical.program.GetStatisticalProgramsQuery;

/**
 * All API method in this controller does not require being registered
 * <p>
 * Open methods
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/referential/process/documentations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiProcessDocumentationOpen extends AbstractController {

	/**
	 * Method to get many process documentations in the selected language
	 * 
	 * @param name     the name to search the process documentations
	  * @param language the language to present the returned DTO (en, ro, ru)
	 * @return a list of filtered s in the selected language all
	 *         process documentations if no filter parameter has been provided
	 */
	@JsonView(Views.Extended.class)
	@GetMapping
	public DTOList<ProcessDocumentationDTO> getProcessDocumentationsQuery(
			@RequestParam(name = "name", required = false) final String name,
			@RequestParam(name = "frequency", required = false) final Frequency frequency,
			@RequestParam(name = "language") final String language) {

		final GetProcessDocumentationsQuery getProcessDocumentationsQuery = GetProcessDocumentationsQuery.create(name,frequency, Language.getLanguage(language));
		return sendQuery(getProcessDocumentationsQuery, "referential").getRead().getData();

	}

	/**
	 * Method to get the process documentations by id
	 * 
	 * @param id       the id of the process documentations
	 * @param language the language to present the returned DTO (en, ro, ru)
	 * @return ProcessDocumentationDTO in the selected language
	 */
	@JsonView(Views.Extended.class)
	@GetMapping("/{id}")
	public ProcessDocumentationDTO getProcessDocumentationQuery(
			@PathVariable(name = "id") final Long id,
			@RequestParam(name = "language") final String language) {

		final GetProcessDocumentationQuery getProcessDocumentationQuery = GetProcessDocumentationQuery.create(id);
		getProcessDocumentationQuery.setLanguage(Language.getLanguage(language));
		return sendQuery(getProcessDocumentationQuery, "referential").getRead().getData();
	}
	
}
