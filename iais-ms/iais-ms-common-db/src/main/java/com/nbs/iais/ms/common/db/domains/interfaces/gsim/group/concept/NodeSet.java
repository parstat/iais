package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.concept;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;

import java.util.List;

public interface NodeSet extends IdentifiableArtefact {

    List<Node> getNodes();

    void setNodes(List<Node> nodes);

    List<Concept> getConcepts();

    void setConcepts(List<Concept> concepts);


}
