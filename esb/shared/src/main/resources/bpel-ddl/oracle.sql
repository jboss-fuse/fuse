create table ODE_SCHEMA_VERSION(VERSION integer);
insert into ODE_SCHEMA_VERSION values (220);

CREATE TABLE ODE_JOB (
  jobid VARCHAR2(64)  NOT NULL,
  ts number(19,0)  NOT NULL,
  nodeid varchar2(64),
  scheduled number(1,0)  NOT NULL,
  transacted number(1,0)  NOT NULL,
  
  instanceId number(37),
  mexId varchar(255),
  processId varchar2(255),
  type varchar2(255),
  channel varchar2(255),
  correlatorId varchar2(255),
  correlationKeySet varchar2(255),
  retryCount number(10,0),
  inMem number(1,0),
  detailsExt blob,

  PRIMARY KEY(jobid));

CREATE INDEX IDX_ODE_JOB_TS ON ODE_JOB(ts);
CREATE INDEX IDX_ODE_JOB_NODEID ON ODE_JOB(nodeid);

create table BPEL_ACTIVITY_RECOVERY (
    ID number(19,0) not null,
    ACTIONS varchar2(255),
    ACTIVITY_ID number(19,0),
    CHANNEL varchar2(255),
    DATE_TIME date,
    DETAILS clob,
    INSTANCE_ID number(19,0),
    REASON varchar2(255),
    RETRIES number(10,0),
    primary key (ID)
);

create table BPEL_CORRELATION_SET (
    CORRELATION_SET_ID number(19,0) not null,
    CORRELATION_KEY varchar2(255),
    NAME varchar2(255),
    SCOPE_ID number(19,0),
    primary key (CORRELATION_SET_ID)
);

create table BPEL_CORRELATOR (
    CORRELATOR_ID number(19,0) not null,
    CORRELATOR_KEY varchar2(255),
    PROC_ID number(19,0),
    primary key (CORRELATOR_ID)
);

create table BPEL_CORSET_PROP (
    ID number(19,0) not null,
    CORRSET_ID number(19,0),
    PROP_KEY varchar2(255),
    PROP_VALUE varchar2(255),
    primary key (ID)
);

create table BPEL_EVENT (
    EVENT_ID number(19,0) not null,
    DETAIL varchar2(255),
    DATA blob,
    SCOPE_ID number(19,0),
    TSTAMP date,
    TYPE varchar2(255),
    INSTANCE_ID number(19,0),
    PROCESS_ID number(19,0),
    primary key (EVENT_ID)
);

create table BPEL_FAULT (
    FAULT_ID number(19,0) not null,
    ACTIVITY_ID number(10,0),
    DATA clob,
    MESSAGE clob,
    LINE_NUMBER number(10,0),
    NAME varchar2(255),
    primary key (FAULT_ID)
);

create table BPEL_MESSAGE (
    MESSAGE_ID number(19,0) not null,
    DATA clob,
    HEADER clob,
    TYPE varchar2(255),
    MESSAGE_EXCHANGE_ID varchar2(255),
    primary key (MESSAGE_ID)
);

create table BPEL_MESSAGE_EXCHANGE (
    MESSAGE_EXCHANGE_ID varchar2(255) not null,
    CALLEE varchar2(255),
    CHANNEL varchar2(255),
    CORRELATION_ID varchar2(255),
    CORRELATION_KEYS varchar2(255),
    CORRELATION_STATUS varchar2(255),
    CREATE_TIME date,
    DIRECTION char(1),
    EPR clob,
    FAULT varchar2(255),
    FAULT_EXPLANATION varchar2(255),
    OPERATION varchar2(255),
    PARTNER_LINK_MODEL_ID number(10,0),
    PATTERN varchar2(255),
    PIPED_ID varchar2(255),
    PORT_TYPE varchar2(255),
    PROPAGATE_TRANS number(1,0),
    STATUS varchar2(255),
    SUBSCRIBER_COUNT number(10,0),
    CORR_ID number(19,0),
    PARTNER_LINK_ID number(19,0),
    PROCESS_ID number(19,0),
    PROCESS_INSTANCE_ID number(19,0),
    REQUEST_MESSAGE_ID number(19,0),
    RESPONSE_MESSAGE_ID number(19,0),
    primary key (MESSAGE_EXCHANGE_ID)
);

create table BPEL_MESSAGE_ROUTE (
    MESSAGE_ROUTE_ID number(19,0) not null,
    CORRELATION_KEY varchar2(255),
    GROUP_ID varchar2(255),
    ROUTE_INDEX number(10,0),
    PROCESS_INSTANCE_ID number(19,0),
    ROUTE_POLICY varchar2(16),
    CORR_ID number(19,0),
    primary key (MESSAGE_ROUTE_ID)
);

create table BPEL_MEX_PROP (
    ID number(19,0) not null,
    MEX_ID varchar2(255),
    PROP_KEY varchar2(255),
    PROP_VALUE varchar2(2000),
    primary key (ID)
);

create table BPEL_PARTNER_LINK (
    PARTNER_LINK_ID number(19,0) not null,
    MY_EPR clob,
    MY_ROLE_NAME varchar2(255),
    MY_ROLE_SERVICE_NAME varchar2(255),
    MY_SESSION_ID varchar2(255),
    PARTNER_EPR clob,
    PARTNER_LINK_MODEL_ID number(10,0),
    PARTNER_LINK_NAME varchar2(255),
    PARTNER_ROLE_NAME varchar2(255),
    PARTNER_SESSION_ID varchar2(255),
    SCOPE_ID number(19,0),
    primary key (PARTNER_LINK_ID)
);

create table BPEL_PROCESS (
    ID number(19,0) not null,
    GUID varchar2(255),
    PROCESS_ID varchar2(255),
    PROCESS_TYPE varchar2(255),
    VERSION number(19,0),
    primary key (ID)
);

create table BPEL_PROCESS_INSTANCE (
    ID number(19,0) not null,
    DATE_CREATED date,
    EXECUTION_STATE blob,
    FAULT_ID number(19,0),
    LAST_ACTIVE_TIME date,
    LAST_RECOVERY_DATE date,
    PREVIOUS_STATE number(5,0),
    SEQUENCE number(19,0),
    INSTANCE_STATE number(5,0),
    INSTANTIATING_CORRELATOR_ID number(19,0),
    PROCESS_ID number(19,0),
    ROOT_SCOPE_ID number(19,0),
    primary key (ID)
);

create table BPEL_SCOPE (
    SCOPE_ID number(19,0) not null,
    MODEL_ID number(10,0),
    SCOPE_NAME varchar2(255),
    SCOPE_STATE varchar2(255),
    PARENT_SCOPE_ID number(19,0),
    PROCESS_INSTANCE_ID number(19,0),
    primary key (SCOPE_ID)
);

create table BPEL_XML_DATA (
    XML_DATA_ID number(19,0) not null,
    DATA clob,
    IS_SIMPLE_TYPE number(1,0),
    NAME varchar2(255),
    SCOPE_ID number(19,0),
    primary key (XML_DATA_ID)
);

create table BPEL_XML_DATA_PROP (
    ID number(19,0) not null,
    XML_DATA_ID number(19,0),
    PROP_KEY varchar2(255),
    PROP_VALUE varchar2(255),
    primary key (ID)
);

create table STORE_DU (
    NAME varchar2(255) not null,
    DEPLOYDT date,
    DEPLOYER varchar2(255),
    DIR varchar2(255)
);

create table STORE_PROCESS (
    PID varchar2(255) not null,
    STATE varchar2(255),
    TYPE varchar2(255),
    VERSION number(19,0),
    DU varchar2(255),
    primary key (PID)
);

create table STORE_PROCESS_PROP (
    ID number(19,0) not null,
    PROP_KEY varchar2(255),
    PROP_VAL varchar2(255),
    primary key (ID)
);

create table STORE_PROC_TO_PROP (
    STORE_PROCESS_PID varchar2(255) not null,
    STORE_PROPERTY_ID number(19,0) not null,
    primary key (STORE_PROCESS_PID, STORE_PROPERTY_ID),
    unique (STORE_PROPERTY_ID)
);

create table STORE_VERSIONS (
    ID number(19,0) not null,
    VERSION number(19,0),
    primary key (ID)
);

create sequence bpel_sequence;

create table BPAF_EVENT (
    EID number(19,0) not null,
    ACTIVITY_DEFINITION_ID varchar2(255 char),
    ACTIVITY_INSTANCE_ID varchar2(255 char),
    ACTIVITY_NAME varchar2(255 char),
    CURRENT_STATE varchar2(255 char),
    PREVIOUS_STATE varchar2(255 char),
    PROCESS_DEFINITION_ID varchar2(255 char),
    PROCESS_INSTANCE_ID varchar2(255 char),
    PROCESS_NAME varchar2(255 char),
    SERVER_ID varchar2(255 char),
    TIMESTAMP number(19,0),
    primary key (EID)
    );

create table BPAF_EVENT_DATA (
    TID number(19,0) not null,
    NAME varchar2(255 char),
    VALUE clob,
    EVENT_ID number(19,0),
    primary key (TID)
);

alter table BPAF_EVENT_DATA
    add constraint FK3E83D1BCDA848C8
    foreign key (EVENT_ID)
    references BPAF_EVENT;
