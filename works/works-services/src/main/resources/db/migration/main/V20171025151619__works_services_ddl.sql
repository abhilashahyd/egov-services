CREATE TABLE egw_estimate_appropriation
(
  id character varying(256),
  tenantId character varying(250) NOT NULL,
  objecttype character varying(50),
  objectnumber character varying(100),
  budgetusage character varying(50),
  createdby bigint NOT NULL,
  createddate bigint NOT NULL,
  lastmodifiedby bigint,
  lastmodifieddate bigint,
  CONSTRAINT tenantid_detailedEstimate_unique UNIQUE (tenantId, objectnumber)
);

CREATE TABLE egw_documentdetail
(
  id character varying(256),
  tenantId character varying(250) NOT NULL,
  objectid character varying(50) NOT NULL,
  objecttype character varying(100) NOT NULL,
  documenttype character varying(50),
  filestore character varying(250) NOT NULL,
  latitude double precision,
  longitude double precision,
  description character varying(1024),
  dateofcapture bigint,
  workprogress character varying(50),
  createdby bigint NOT NULL,
  createddate bigint NOT NULL,
  lastmodifiedby bigint,
  lastmodifieddate bigint,
  CONSTRAINT tenantid_document_unique UNIQUE (tenantId, objectid)
);
