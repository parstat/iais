package com.nbs.iais.common.db.domains.interfaces.group.concept;

import com.nbs.iais.common.db.domains.interfaces.group.base.IdentifiableArtefact;

import java.util.List;

public interface NodeSet extends IdentifiableArtefact {

    List<Node> getNodes();

    void setNodes(List<Node> nodes);

    List<Concept> getConcepts();

    void setConcepts(List<Concept> concepts);


}
