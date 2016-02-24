
create table ODE_SCHEMA_VERSION(VERSION integer);
insert into ODE_SCHEMA_VERSION values (220);

  create table ODE_JOB (
      jobid varchar(64) not null,
      ts int8 not null,
  	  nodeid varchar(64),
      scheduled bool not null,
      transacted bool not null,
      instanceId int8,
  	  mexId varchar(255),
  	  processId varchar(255),
      type varchar(255),
      channel varchar(255),
      correlatorId varchar(255),
      correlationKeySet varchar(255),
      retryCount int4,
      inMem bool,
      detailsExt oid,

      primary key (jobid)
  );

CREATE INDEX IDX_ODE_JOB_TS ON ODE_JOB(ts);
CREATE INDEX IDX_ODE_JOB_NODEID ON ODE_JOB(nodeid);

create table BPEL_ACTIVITY_RECOVERY (
    ID int8 not null,
    ACTIONS varchar(255),
    ACTIVITY_ID int8,
    CHANNEL varchar(255),
    DATE_TIME timestamp,
    DETAILS text,
    INSTANCE_ID int8,
    REASON varchar(255),
    RETRIES int4,
    primary key (ID)
);

create table BPEL_CORRELATION_SET (
    CORRELATION_SET_ID int8 not null,
    CORRELATION_KEY varchar(255),
    NAME varchar(255),
    SCOPE_ID int8,
    primary key (CORRELATION_SET_ID)
);

create table BPEL_CORRELATOR (
    CORRELATOR_ID int8 not null,
    CORRELATOR_KEY varchar(255),
    PROC_ID int8,
    primary key (CORRELATOR_ID)
);

create table BPEL_CORSET_PROP (
    ID int8 not null,
    CORRSET_ID int8,
    PROP_KEY varchar(255),
    PROP_VALUE varchar(255),
    primary key (ID)
);

create table BPEL_EVENT (
    EVENT_ID int8 not null,
    DETAIL varchar(255),
    DATA oid,
    SCOPE_ID int8,
    TSTAMP timestamp,
    TYPE varchar(255),
    INSTANCE_ID int8,
    PROCESS_ID int8,
    primary key (EVENT_ID)
);

create table BPEL_FAULT (
    FAULT_ID int8 not null,
    ACTIVITY_ID int4,
    DATA text,
    MESSAGE varchar(102400),
    LINE_NUMBER int4,
    NAME varchar(255),
    primary key (FAULT_ID)
);

create table BPEL_MESSAGE (
    MESSAGE_ID int8 not null,
    DATA text,
    HEADER text,
    TYPE varchar(255),
    MESSAGE_EXCHANGE_ID varchar(255),
    primary key (MESSAGE_ID)
);

create table BPEL_MESSAGE_EXCHANGE (
    MESSAGE_EXCHANGE_ID varchar(255) not null,
    CALLEE varchar(255),
    CHANNEL varchar(255),
    CORRELATION_ID varchar(255),
    CORRELATION_KEYS varchar(255),
    CORRELATION_STATUS varchar(255),
    CREATE_TIME timestamp,
    DIRECTION char(1),
    EPR text,
    FAULT varchar(255),
    FAULT_EXPLANATION varchar(255),
    OPERATION varchar(255),
    PARTNER_LINK_MODEL_ID int4,
    PATTERN varchar(255),
    PIPED_ID varchar(255),
    PORT_TYPE varchar(255),
    PROPAGATE_TRANS bool,
    STATUS varchar(255),
    SUBSCRIBER_COUNT int4,
    CORR_ID int8,
    PARTNER_LINK_ID int8,
    PROCESS_ID int8,
    PROCESS_INSTANCE_ID int8,
    REQUEST_MESSAGE_ID int8,
    RESPONSE_MESSAGE_ID int8,
    primary key (MESSAGE_EXCHANGE_ID)
);

create table BPEL_MESSAGE_ROUTE (
    MESSAGE_ROUTE_ID int8 not null,
    CORRELATION_KEY varchar(255),
    GROUP_ID varchar(255),
    ROUTE_INDEX int4,
    PROCESS_INSTANCE_ID int8,
    ROUTE_POLICY varchar(16),
    CORR_ID int8,
    primary key (MESSAGE_ROUTE_ID)
);

create table BPEL_MEX_PROP (
    ID int8 not null,
    MEX_ID varchar(255),
    PROP_KEY varchar(255),
    PROP_VALUE varchar(2000),
    primary key (ID)
);

create table BPEL_PARTNER_LINK (
    PARTNER_LINK_ID int8 not null,
    MY_EPR text,
    MY_ROLE_NAME varchar(255),
    MY_ROLE_SERVICE_NAME varchar(255),
    MY_SESSION_ID varchar(255),
    PARTNER_EPR text,
    PARTNER_LINK_MODEL_ID int4,
    PARTNER_LINK_NAME varchar(255),
    PARTNER_ROLE_NAME varchar(255),
    PARTNER_SESSION_ID varchar(255),
    SCOPE_ID int8,
    primary key (PARTNER_LINK_ID)
);

create table BPEL_PROCESS (
    ID int8 not null,
    GUID varchar(255),
    PROCESS_ID varchar(255),
    PROCESS_TYPE varchar(255),
    VERSION int8,
    primary key (ID)
);

create table BPEL_PROCESS_INSTANCE (
    ID int8 not null,
    DATE_CREATED timestamp,
    EXECUTION_STATE oid,
    FAULT_ID int8,
    LAST_ACTIVE_TIME timestamp,
    LAST_RECOVERY_DATE timestamp,
    PREVIOUS_STATE int2,
    SEQUENCE int8,
    INSTANCE_STATE int2,
    INSTANTIATING_CORRELATOR_ID int8,
    PROCESS_ID int8,
    ROOT_SCOPE_ID int8,
    primary key (ID)
);

create table BPEL_SCOPE (
    SCOPE_ID int8 not null,
    MODEL_ID int4,
    SCOPE_NAME varchar(255),
    SCOPE_STATE varchar(255),
    PARENT_SCOPE_ID int8,
    PROCESS_INSTANCE_ID int8,
    primary key (SCOPE_ID)
);

create table BPEL_XML_DATA (
    XML_DATA_ID int8 not null,
    DATA text,
    IS_SIMPLE_TYPE bool,
    NAME varchar(255),
    SCOPE_ID int8,
    primary key (XML_DATA_ID)
);

create table BPEL_XML_DATA_PROP (
    ID int8 not null,
    XML_DATA_ID int8,
    PROP_KEY varchar(255),
    PROP_VALUE varchar(255),
    primary key (ID)
);

create table STORE_DU (
    NAME varchar(255) not null,
    DEPLOYDT timestamp,
    DEPLOYER varchar(255),
    DIR varchar(255)
);

create table STORE_PROCESS (
    PID varchar(255) not null,
    STATE varchar(255),
    TYPE varchar(255),
    VERSION int8,
    DU varchar(255),
    primary key (PID)
);

create table STORE_PROCESS_PROP (
    ID int8 not null,
    PROP_KEY varchar(255),
    PROP_VAL varchar(255),
    primary key (ID)
);

create table STORE_PROC_TO_PROP (
    STORE_PROCESS_PID varchar(255) not null,
    STORE_PROPERTY_ID int8 not null,
    primary key (STORE_PROCESS_PID, STORE_PROPERTY_ID),
    unique (STORE_PROPERTY_ID)
);

create table STORE_VERSIONS (
    ID int8 not null,
    VERSION int8,
    primary key (ID)
);

create sequence bpel_sequence;

create table BPAF_EVENT (
        EID int8 not null,
        ACTIVITY_DEFINITION_ID varchar(255),
        ACTIVITY_INSTANCE_ID varchar(255),
        ACTIVITY_NAME varchar(255),
        CURRENT_STATE varchar(255),
        PREVIOUS_STATE varchar(255),
        PROCESS_DEFINITION_ID varchar(255),
        PROCESS_INSTANCE_ID varchar(255),
        PROCESS_NAME varchar(255),
        SERVER_ID varchar(255),
        TIMESTAMP bigint,
        primary key (EID)
    );

create table BPAF_EVENT_DATA (
    TID int8 not null,
    NAME varchar(255),
    VALUE text,
    EVENT_ID bigint,
    primary key (TID)
);

alter table BPAF_EVENT_DATA
    add constraint FK3E83D1BCDA848C8
    foreign key (EVENT_ID)
    references BPAF_EVENT;
