package com.nbs.iais.ms.meta.referential.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nbs.iais.ms.common.api.controllers.AbstractController;
import com.nbs.iais.ms.common.dto.impl.StatisticalProcessDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.referential.common.messageing.queries.GetStatisticalProcessQuery;
import com.nbs.iais.ms.referential.common.messageing.queries.GetStatisticalProcessesQuery;

@RestController
@RequestMapping(value = "/api/v1/open/refential", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiReferentialOpen extends AbstractController {

	@GetMapping("/statprocess")
	public ResponseEntity<DTOList<StatisticalProcessDTO>> getStatisticalProcessesQuery() {

		final GetStatisticalProcessesQuery getStatisticalProcessesQuery = GetStatisticalProcessesQuery.create();
		return ResponseEntity.ok(sendQuery(getStatisticalProcessesQuery, "referential").getRead().getData());

	}

	@GetMapping("/statprocess/{id}")
	public ResponseEntity<StatisticalProcessDTO> getStatisticalProcessQuery(@PathVariable("id") Long id) {

		final GetStatisticalProcessQuery getStatisticalProcessQuery = GetStatisticalProcessQuery.create(id);
		StatisticalProcessDTO statisticalProcessDTO = sendQuery(getStatisticalProcessQuery, "referential").getRead()
				.getData();
		if (statisticalProcessDTO != null) {
			return ResponseEntity.ok(statisticalProcessDTO);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
