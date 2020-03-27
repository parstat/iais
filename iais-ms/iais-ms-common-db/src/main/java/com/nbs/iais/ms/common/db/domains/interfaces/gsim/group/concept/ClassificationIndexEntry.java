package com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.concept;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.base.IdentifiableArtefact;

import java.util.List;

public interface ClassificationIndexEntry extends IdentifiableArtefact {

    List<String> getCodingInstructions();

    void setCodingInstructions(List<String> codingInstructions);

    List<String> getTexts();

    void setTexts(List<String> texts);

    List<ClassificationIndex> getClassificationIndexes();

    void setClassificationIndexes(List<ClassificationIndex> classificationIndexes);

    List<ClassificationFamily> getClassificationFamilies();

    void setClassificationFamilies(List<ClassificationFamily> classificationFamilies);

    List<Node> getNodes();

    void setNodes(List<Node> nodes);

}
