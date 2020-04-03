package com.nbs.iais.ms.referential.common.messageing.queries;



import com.nbs.iais.ms.common.messaging.queries.abstracts.AbstractQuery;
import com.nbs.iais.ms.referential.common.messageing.reads.GetStatisticalProcessesRead;



public class GetStatisticalProcessesQuery extends AbstractQuery<GetStatisticalProcessesRead> {

	private static final long serialVersionUID = 20320L;
	
    private GetStatisticalProcessesQuery() {
		super(new GetStatisticalProcessesRead());
		// TODO Auto-generated constructor stub
	}

	public GetStatisticalProcessesQuery(GetStatisticalProcessesRead read) {
		super(read);
		// TODO Auto-generated constructor stub
	}

	 public static GetStatisticalProcessesQuery create() {
	        return new GetStatisticalProcessesQuery();
	    }


}

