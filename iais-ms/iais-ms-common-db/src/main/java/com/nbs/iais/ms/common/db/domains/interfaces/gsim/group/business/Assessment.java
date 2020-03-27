package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;

import java.time.LocalDateTime;
import java.util.List;

public interface Assessment extends IdentifiableArtefact {

    List<LocalDateTime> getDateAssessed();

    void setDateAssessed(List<LocalDateTime> dateAssessed);

    List<String> getIssues();

    void setIssues(List<String> issues);

    List<String> getRecommendations();

    void setRecommendations(List<String> recommendations);

    List<String> getResults();

    void setResults(final List<String> results);
}
