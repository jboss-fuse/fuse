 
create table ODE_SCHEMA_VERSION(VERSION integer);
insert into ODE_SCHEMA_VERSION values (220);

CREATE TABLE ODE_JOB (
  jobid varchar(64)  NOT NULL,
  ts numeric(19,0)  NOT NULL,
  nodeid varchar(64) null,
  scheduled tinyint  NOT NULL,
  transacted tinyint  NOT NULL,

  instanceId numeric(19,0) null,
  mexId varchar(255) null,
  processId varchar(255) null,
  type varchar(255) null,
  channel varchar(255) null,
  correlatorId varchar(255) null,
  correlationKeySet varchar(255) null,
  retryCount int null,
  inMem tinyint null,
  detailsExt image null,

  PRIMARY KEY(jobid));

CREATE INDEX IDX_ODE_JOB_TS ON ODE_JOB(ts);
CREATE INDEX IDX_ODE_JOB_NODEID ON ODE_JOB(nodeid);

create table BPEL_ACTIVITY_RECOVERY (
    ID numeric(19,0) identity not null,
    ACTIONS varchar(255) null,
    ACTIVITY_ID numeric(19,0) null,
    CHANNEL varchar(255) null,
    DATE_TIME datetime null,
    DETAILS text null,
    INSTANCE_ID numeric(19,0) null,
    REASON varchar(255) null,
    RETRIES int null,
    primary key (ID)
);

create table BPEL_CORRELATION_SET (
    CORRELATION_SET_ID numeric(19,0) identity not null,
    CORRELATION_KEY varchar(255) null,
    NAME varchar(255) null,
    SCOPE_ID numeric(19,0) null,
    primary key (CORRELATION_SET_ID)
);

create table BPEL_CORRELATOR (
    CORRELATOR_ID numeric(19,0) identity not null,
    CORRELATOR_KEY varchar(255) null,
    PROC_ID numeric(19,0) null,
    primary key (CORRELATOR_ID)
);

create table BPEL_CORSET_PROP (
    ID numeric(19,0) identity not null,
    CORRSET_ID numeric(19,0) null,
    PROP_KEY varchar(255) null,
    PROP_VALUE varchar(255) null,
    primary key (ID)
);

create table BPEL_EVENT (
    EVENT_ID numeric(19,0) identity not null,
    DETAIL varchar(255) null,
    DATA image null,
    SCOPE_ID numeric(19,0) null,
    TSTAMP datetime null,
    TYPE varchar(255) null,
    INSTANCE_ID numeric(19,0) null,
    PROCESS_ID numeric(19,0) null,
    primary key (EVENT_ID)
);

create table BPEL_FAULT (
    FAULT_ID numeric(19,0) identity not null,
    ACTIVITY_ID int null,
    DATA text null,
    MESSAGE varchar(max) null,
    LINE_NUMBER int null,
    NAME varchar(255) null,
    primary key (FAULT_ID)
);

create table BPEL_MESSAGE (
    MESSAGE_ID numeric(19,0) identity not null,
    DATA text null,
    HEADER text null,
    TYPE varchar(255) null,
    MESSAGE_EXCHANGE_ID varchar(255) null,
    primary key (MESSAGE_ID)
);

create table BPEL_MESSAGE_EXCHANGE (
    MESSAGE_EXCHANGE_ID varchar(255) not null,
    CALLEE varchar(255) null,
    CHANNEL varchar(255) null,
    CORRELATION_ID varchar(255) null,
    CORRELATION_KEYS varchar(255) null,
    CORRELATION_STATUS varchar(255) null,
    CREATE_TIME datetime null,
    DIRECTION char(1) null,
    EPR text null,
    FAULT varchar(255) null,
    FAULT_EXPLANATION varchar(255) null,
    OPERATION varchar(255) null,
    PARTNER_LINK_MODEL_ID int null,
    PATTERN varchar(255) null,
    PIPED_ID varchar(255) null,
    PORT_TYPE varchar(255) null,
    PROPAGATE_TRANS tinyint null,
    STATUS varchar(255) null,
    SUBSCRIBER_COUNT int null,
    CORR_ID numeric(19,0) null,
    PARTNER_LINK_ID numeric(19,0) null,
    PROCESS_ID numeric(19,0) null,
    PROCESS_INSTANCE_ID numeric(19,0) null,
    REQUEST_MESSAGE_ID numeric(19,0) null,
    RESPONSE_MESSAGE_ID numeric(19,0) null,
    primary key (MESSAGE_EXCHANGE_ID)
);

create table BPEL_MESSAGE_ROUTE (
    MESSAGE_ROUTE_ID numeric(19,0) identity not null,
    CORRELATION_KEY varchar(255) null,
    GROUP_ID varchar(255) null,
    ROUTE_INDEX int null,
    PROCESS_INSTANCE_ID numeric(19,0) null,
    ROUTE_POLICY varchar(16) null,
    CORR_ID numeric(19,0) null,
    primary key (MESSAGE_ROUTE_ID)
);

create table BPEL_MEX_PROP (
    ID numeric(19,0) identity not null,
    MEX_ID varchar(255) null,
    PROP_KEY varchar(255) null,
    PROP_VALUE varchar(2000) null,
    primary key (ID)
);

create table BPEL_PARTNER_LINK (
    PARTNER_LINK_ID numeric(19,0) identity not null,
    MY_EPR text null,
    MY_ROLE_NAME varchar(255) null,
    MY_ROLE_SERVICE_NAME varchar(255) null,
    MY_SESSION_ID varchar(255) null,
    PARTNER_EPR text null,
    PARTNER_LINK_MODEL_ID int null,
    PARTNER_LINK_NAME varchar(255) null,
    PARTNER_ROLE_NAME varchar(255) null,
    PARTNER_SESSION_ID varchar(255) null,
    SCOPE_ID numeric(19,0) null,
    primary key (PARTNER_LINK_ID)
);

create table BPEL_PROCESS (
    ID numeric(19,0) identity not null,
    GUID varchar(255) null,
    PROCESS_ID varchar(255) null,
    PROCESS_TYPE varchar(255) null,
    VERSION numeric(19,0) null,
    primary key (ID)
);

create table BPEL_PROCESS_INSTANCE (
    ID numeric(19,0) identity not null,
    DATE_CREATED datetime null,
    EXECUTION_STATE image null,
    FAULT_ID numeric(19,0) null,
    LAST_ACTIVE_TIME datetime null,
    LAST_RECOVERY_DATE datetime null,
    PREVIOUS_STATE smallint null,
    SEQUENCE numeric(19,0) null,
    INSTANCE_STATE smallint null,
    INSTANTIATING_CORRELATOR_ID numeric(19,0) null,
    PROCESS_ID numeric(19,0) null,
    ROOT_SCOPE_ID numeric(19,0) null,
    primary key (ID)
);

create table BPEL_SCOPE (
    SCOPE_ID numeric(19,0) identity not null,
    MODEL_ID int null,
    SCOPE_NAME varchar(255) null,
    SCOPE_STATE varchar(255) null,
    PARENT_SCOPE_ID numeric(19,0) null,
    PROCESS_INSTANCE_ID numeric(19,0) null,
    primary key (SCOPE_ID)
);

create table BPEL_XML_DATA (
    XML_DATA_ID numeric(19,0) identity not null,
    DATA text null,
    IS_SIMPLE_TYPE tinyint null,
    NAME varchar(255) null,
    SCOPE_ID numeric(19,0) null,
    primary key (XML_DATA_ID)
);

create table BPEL_XML_DATA_PROP (
    ID numeric(19,0) identity not null,
    XML_DATA_ID numeric(19,0) null,
    PROP_KEY varchar(255) null,
    PROP_VALUE varchar(255) null,
    primary key (ID)
);

create table STORE_DU (
    NAME varchar(255) not null,
    DEPLOYDT datetime null,
    DEPLOYER varchar(255) null,
    DIR varchar(255) null
);

create table STORE_PROCESS (
    PID varchar(255) not null,
    STATE varchar(255) null,
    TYPE varchar(255) null,
    VERSION numeric(19,0) null,
    DU varchar(255) null,
    primary key (PID)
);

create table STORE_PROCESS_PROP (
    ID numeric(19,0) identity not null,
    PROP_KEY varchar(255) null,
    PROP_VAL varchar(255) null,
    primary key (ID)
);

create table STORE_PROC_TO_PROP (
    STORE_PROCESS_PID varchar(255) not null,
    STORE_PROPERTY_ID numeric(19,0) not null,
    primary key (STORE_PROCESS_PID, STORE_PROPERTY_ID),
    unique (STORE_PROPERTY_ID)
);

create table STORE_VERSIONS (
    ID numeric(19,0) identity not null,
    VERSION numeric(19,0) null,
    primary key (ID)
);

create table BPAF_EVENT (
        EID numeric(19,0) identity not null,
        ACTIVITY_DEFINITION_ID varchar(255) null,
        ACTIVITY_INSTANCE_ID varchar(255) null,
        ACTIVITY_NAME varchar(255) null,
        CURRENT_STATE varchar(255) null,
        PREVIOUS_STATE varchar(255) null,
        PROCESS_DEFINITION_ID varchar(255) null,
        PROCESS_INSTANCE_ID varchar(255) null,
        PROCESS_NAME varchar(255) null,
        SERVER_ID varchar(255) null,
        TIMESTAMP numeric(19,0) null,
        primary key (EID)
    );

create table BPAF_EVENT_DATA (
    TID numeric(19,0) identity not null,
    NAME varchar(255) null,
    VALUE text null,
    EVENT_ID numeric(19,0) null,
    primary key (TID)
);

alter table BPAF_EVENT_DATA
    add constraint FK3E83D1BCDA848C8
    foreign key (EVENT_ID)
    references BPAF_EVENT;

