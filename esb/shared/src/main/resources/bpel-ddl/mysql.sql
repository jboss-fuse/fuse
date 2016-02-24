create table ODE_SCHEMA_VERSION(VERSION integer);
insert into ODE_SCHEMA_VERSION values (220);

DROP TABLE IF EXISTS ODE_JOB;

CREATE TABLE ODE_JOB (
  jobid CHAR(64)  NOT NULL DEFAULT '',
  ts BIGINT  NOT NULL DEFAULT 0,
  nodeid varchar(64),
  scheduled bit  NOT NULL,
  transacted bit  NOT NULL,

  instanceId BIGINT,
  mexId varchar(255),
  processId varchar(255),
  type varchar(255),
  channel varchar(255),
  correlatorId varchar(255),
  correlationKeySet varchar(255),
  retryCount integer,
  inMem bit,
  detailsExt blob,

  PRIMARY KEY(jobid),
  INDEX IDX_ODE_JOB_TS(ts),
  INDEX IDX_ODE_JOB_NODEID(nodeid)
)
ENGINE=InnoDB;

COMMIT;


create table BPEL_ACTIVITY_RECOVERY (
    ID bigint not null auto_increment,
    ACTIONS varchar(255),
    ACTIVITY_ID bigint,
    CHANNEL varchar(255),
    DATE_TIME datetime,
    DETAILS longtext,
    INSTANCE_ID bigint,
    REASON varchar(255),
    RETRIES integer,
    primary key (ID)
) ENGINE=InnoDB;

create table BPEL_CORRELATION_SET (
    CORRELATION_SET_ID bigint not null auto_increment,
    CORRELATION_KEY varchar(255),
    NAME varchar(255),
    SCOPE_ID bigint,
    primary key (CORRELATION_SET_ID)
) ENGINE=InnoDB;

create table BPEL_CORRELATOR (
    CORRELATOR_ID bigint not null auto_increment,
    CORRELATOR_KEY varchar(255),
    PROC_ID bigint,
    primary key (CORRELATOR_ID)
) ENGINE=InnoDB;

create table BPEL_CORSET_PROP (
    ID bigint not null auto_increment,
    CORRSET_ID bigint,
    PROP_KEY varchar(255),
    PROP_VALUE varchar(255),
    primary key (ID)
) ENGINE=InnoDB;

create table BPEL_EVENT (
    EVENT_ID bigint not null auto_increment,
    DETAIL varchar(255),
    DATA longblob,
    SCOPE_ID bigint,
    TSTAMP datetime,
    TYPE varchar(255),
    INSTANCE_ID bigint,
    PROCESS_ID bigint,
    primary key (EVENT_ID)
) ENGINE=InnoDB;

create table BPEL_FAULT (
    FAULT_ID bigint not null auto_increment,
    ACTIVITY_ID integer,
    DATA longtext,
    MESSAGE longtext,
    LINE_NUMBER integer,
    NAME varchar(255),
    primary key (FAULT_ID)
) ENGINE=InnoDB;

create table BPEL_MESSAGE (
    MESSAGE_ID bigint not null auto_increment,
    DATA longtext,
    HEADER longtext,
    TYPE varchar(255),
    MESSAGE_EXCHANGE_ID varchar(255),
    primary key (MESSAGE_ID)
) ENGINE=InnoDB;

create table BPEL_MESSAGE_EXCHANGE (
    MESSAGE_EXCHANGE_ID varchar(255) not null,
    CALLEE varchar(255),
    CHANNEL varchar(255),
    CORRELATION_ID varchar(255),
    CORRELATION_KEYS varchar(255),
    CORRELATION_STATUS varchar(255),
    CREATE_TIME datetime,
    DIRECTION char(1),
    EPR longtext,
    FAULT varchar(255),
    FAULT_EXPLANATION varchar(255),
    OPERATION varchar(255),
    PARTNER_LINK_MODEL_ID integer,
    PATTERN varchar(255),
    PIPED_ID varchar(255),
    PORT_TYPE varchar(255),
    PROPAGATE_TRANS bit,
    STATUS varchar(255),
    SUBSCRIBER_COUNT integer,
    CORR_ID bigint,
    PARTNER_LINK_ID bigint,
    PROCESS_ID bigint,
    PROCESS_INSTANCE_ID bigint,
    REQUEST_MESSAGE_ID bigint,
    RESPONSE_MESSAGE_ID bigint,
    primary key (MESSAGE_EXCHANGE_ID)
) ENGINE=InnoDB;

create table BPEL_MESSAGE_ROUTE (
    MESSAGE_ROUTE_ID bigint not null auto_increment,
    CORRELATION_KEY varchar(255),
    GROUP_ID varchar(255),
    ROUTE_INDEX integer,
    PROCESS_INSTANCE_ID bigint,
    ROUTE_POLICY varchar(16),
    CORR_ID bigint,
    primary key (MESSAGE_ROUTE_ID)
) ENGINE=InnoDB;

create table BPEL_MEX_PROP (
    ID bigint not null auto_increment,
    MEX_ID varchar(255),
    PROP_KEY varchar(255),
    PROP_VALUE longtext,
    primary key (ID)
) ENGINE=InnoDB;

create table BPEL_PARTNER_LINK (
    PARTNER_LINK_ID bigint not null auto_increment,
    MY_EPR longtext,
    MY_ROLE_NAME varchar(255),
    MY_ROLE_SERVICE_NAME varchar(255),
    MY_SESSION_ID varchar(255),
    PARTNER_EPR longtext,
    PARTNER_LINK_MODEL_ID integer,
    PARTNER_LINK_NAME varchar(255),
    PARTNER_ROLE_NAME varchar(255),
    PARTNER_SESSION_ID varchar(255),
    SCOPE_ID bigint,
    primary key (PARTNER_LINK_ID)
) ENGINE=InnoDB;

create table BPEL_PROCESS (
    ID bigint not null auto_increment,
    GUID varchar(255),
    PROCESS_ID varchar(255),
    PROCESS_TYPE varchar(255),
    VERSION bigint,
    primary key (ID)
) ENGINE=InnoDB;

create table BPEL_PROCESS_INSTANCE (
    ID bigint not null auto_increment,
    DATE_CREATED datetime,
    EXECUTION_STATE longblob,
    FAULT_ID bigint,
    LAST_ACTIVE_TIME datetime,
    LAST_RECOVERY_DATE datetime,
    PREVIOUS_STATE smallint,
    SEQUENCE bigint,
    INSTANCE_STATE smallint,
    INSTANTIATING_CORRELATOR_ID bigint,
    PROCESS_ID bigint,
    ROOT_SCOPE_ID bigint,
    primary key (ID)
) ENGINE=InnoDB;

create table BPEL_SCOPE (
    SCOPE_ID bigint not null auto_increment,
    MODEL_ID integer,
    SCOPE_NAME varchar(255),
    SCOPE_STATE varchar(255),
    PARENT_SCOPE_ID bigint,
    PROCESS_INSTANCE_ID bigint,
    primary key (SCOPE_ID)
) ENGINE=InnoDB;

create table BPEL_XML_DATA (
    XML_DATA_ID bigint not null auto_increment,
    DATA longtext,
    IS_SIMPLE_TYPE bit,
    NAME varchar(255),
    SCOPE_ID bigint,
    primary key (XML_DATA_ID)
) ENGINE=InnoDB;

create table BPEL_XML_DATA_PROP (
    ID bigint not null auto_increment,
    XML_DATA_ID bigint,
    PROP_KEY varchar(255),
    PROP_VALUE varchar(255),
    primary key (ID)
) ENGINE=InnoDB;

create table STORE_DU (
    NAME varchar(255) not null,
    DEPLOYDT datetime,
    DEPLOYER varchar(255),
    DIR varchar(255)
) ENGINE=InnoDB;

create table STORE_PROCESS (
    PID varchar(255) not null,
    STATE varchar(255),
    TYPE varchar(255),
    VERSION bigint,
    DU varchar(255),
    primary key (PID)
) ENGINE=InnoDB;

create table STORE_PROCESS_PROP (
    ID bigint not null auto_increment,
    PROP_KEY varchar(255),
    PROP_VAL varchar(255),
    primary key (ID)
) ENGINE=InnoDB;

create table STORE_PROC_TO_PROP (
    STORE_PROCESS_PID varchar(255) not null,
    STORE_PROPERTY_ID bigint not null,
    primary key (STORE_PROCESS_PID, STORE_PROPERTY_ID),
    unique (STORE_PROPERTY_ID)
) ENGINE=InnoDB;

create table STORE_VERSIONS (
    ID bigint not null auto_increment,
    VERSION bigint,
    primary key (ID)
) ENGINE=InnoDB;

create table BPAF_EVENT (
        EID bigint not null auto_increment,
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
    ) ENGINE=InnoDB;;

create table BPAF_EVENT_DATA (
    TID bigint not null auto_increment,
    NAME varchar(255),
    VALUE longtext,
    EVENT_ID bigint,
    primary key (TID)
) ENGINE=InnoDB;;

alter table BPAF_EVENT_DATA
    add index FK3E83D1BCDA848C8 (EVENT_ID),
    add constraint FK3E83D1BCDA848C8
    foreign key (EVENT_ID)
    references BPAF_EVENT (EID);

