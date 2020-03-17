package com.nbs.iais.common.db.domains.interfaces.group.concept;

import com.nbs.iais.common.db.domains.interfaces.MultilingualText;

import java.util.List;

public interface ClassificationItem extends Node {

    List<MultilingualText> getCaseLaws();
}
