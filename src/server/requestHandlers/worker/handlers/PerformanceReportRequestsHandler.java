package server.requestHandlers.worker.handlers;

import java.sql.SQLException;

import core.worker.WorkerRequestType;
import core.worker.requests.BaseRequest;
import core.worker.responses.WorkerResponse;

public class PerformanceReportRequestsHandler extends BaseRequestsHandler {

	@Override
	protected WorkerRequestType getHandlerRequestsType() {
		return WorkerRequestType.PERFORMENCE_REPORT;
	}

	@Override
	protected WorkerResponse HandleSpecificRequest(BaseRequest specificRequest) throws SQLException {
		return createUnsupportedFeatureResponse();
		
//		WorkerBaseResponse response = WorkerResponseFactory.CreateReportResponse(reportItems, getHandlerRequestsType());
//		return CreateWorkerResponse(response);
	}
}
