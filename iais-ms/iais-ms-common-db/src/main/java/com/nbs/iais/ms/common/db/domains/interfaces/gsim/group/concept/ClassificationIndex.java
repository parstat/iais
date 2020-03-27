package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.concept;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;
import com.nbs.iais.ms.common.enums.Language;

import java.util.List;

public interface ClassificationIndex<N extends NodeSet> extends IdentifiableArtefact {

    List<N> getNodeSets();

    void setNodeSets(List<N> nodeSets);

    List<String> getCodingInstructions();

    void setCodingInstructions(List<String> codingInstructions);

    String getCorrections();

    void setCorrections(String corrections);

    List<Language> getAvailableLanguages();

    void setAvailableLanguages(List<Language> availableLanguages);


}
