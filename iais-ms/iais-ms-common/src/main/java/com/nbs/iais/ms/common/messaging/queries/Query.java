package com.nbs.iais.ms.common.messaging.queries;

import com.nbs.iais.ms.common.RequestMessage;
import com.nbs.iais.ms.common.messaging.reads.Read;

public interface Query<R extends Read<?>> extends RequestMessage<R> {

    R getRead();

}
