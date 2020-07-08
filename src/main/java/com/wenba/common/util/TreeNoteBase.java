package com.wenba.common.util;

import java.io.Serializable;
import java.util.List;

public interface TreeNoteBase<E> extends Serializable {

    Long getId();

    Long getParentId();

    void setChildren(List<E> childList);
}
