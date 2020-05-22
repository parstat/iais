package com.nbs.iais.ms.common.messaging.channels;

public interface Channels {

    String SERVICE_INPUT = "serviceInput";

    String SERVICE_ENRICH = "serviceEnrich";

    String SERVICE_OUTPUT = "serviceOutput";

    String QUERY_INPUT = "query.input";

    String COMMAND_INPUT = "command.input";

    String SECURITY_QUERY_INPUT = "security.query.input";

    String SECURITY_COMMAND_INPUT = "security.command.input";

    String AGENT_QUERY_INPUT = "agent.query.input";

    String AGENT_COMMAND_INPUT = "agent.command.input";

    String BUSINESS_FUNCTION_QUERY_INPUT = "business.function.query.input";

    String BUSINESS_FUNCTION_COMMAND_INPUT = "business.function.command.input";

    String BUSINESS_SERVICE_QUERY_INPUT = "business.service.query.input";

    String BUSINESS_SERVICE_COMMAND_INPUT = "business.service.command.input";

    String STATISTICAL_PROGRAM_QUERY_INPUT = "statistical.program.query.input";

    String STATISTICAL_PROGRAM_COMMAND_INPUT = "statistical.program.command.input";

    String LEGISLATIVE_REFERENCE_QUERY_INPUT = "legislative.reference.query.input";

    String LEGISLATIVE_REFERENCE_COMMAND_INPUT = "legislative.reference.command.input";

    String STATISTICAL_STANDARD_QUERY_INPUT = "statistical.standard.query.input";

    String STATISTICAL_STANDARD_COMMAND_INPUT = "statistical.standard.command.input";

    String PROCESS_DOCUMENTATION_QUERY_INPUT = "process.documentation.query.input";

    String PROCESS_DOCUMENTATION_COMMAND_INPUT = "process.documentation.command.input";
    
    String PROCESS_DOCUMENT_QUERY_INPUT = "process.document.query.input";

    String PROCESS_DOCUMENT_COMMAND_INPUT = "process.document.command.input";
    
    String PROCESS_QUALITY_QUERY_INPUT = "process.quality.query.input";

    String PROCESS_QUALITY_COMMAND_INPUT = "process.quality.command.input";

    String PROCESS_INPUT_SPECIFICATION_QUERY_INPUT = "process.input.specification.query.input";

    String PROCESS_INPUT_SPECIFICATION_COMMAND_INPUT = "process.input.specification.command.input";

    String PROCESS_OUTPUT_SPECIFICATION_QUERY_INPUT = "process.output.specification.query.input";

    String PROCESS_OUTPUT_SPECIFICATION_COMMAND_INPUT = "process.output.specification.command.input";

}
