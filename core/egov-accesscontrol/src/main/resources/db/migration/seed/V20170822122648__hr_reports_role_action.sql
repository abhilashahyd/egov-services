insert into service (id, code, name, enabled, displayname, ordernumber, tenantId) 
values (nextval('SEQ_SERVICE'), 'HRPT', 'HR Report', true, 'HR Reports', 1, 'default');


INSERT INTO eg_action (id, name, url, servicecode, queryparams, parentmodule, ordernumber, displayname, enabled, createdby, createddate, lastmodifiedby, lastmodifieddate)  
VALUES (nextval('SEQ_EG_ACTION'), 'HR Report MetaData', '/report/hr/metadata/_get', 'HRPT',null,   (select id from service where name='Reports' and tenantid='default'), 1,   'Get HR Report Metadata', false, 1, now(), 1, now());

INSERT INTO eg_action (id, name, url, servicecode, queryparams, parentmodule, ordernumber, displayname, enabled, createdby, createddate, lastmodifiedby, lastmodifieddate)  
VALUES (nextval('SEQ_EG_ACTION'), 'HR Report', '/report/hr/_get', 'HRPT',null,   (select id from service where name='Reports' and tenantid='default'), 1,   'Get HR Report', false, 1, now(), 1, now());

INSERT INTO eg_action (id, name, url, servicecode, queryparams, parentmodule, ordernumber, displayname, enabled, createdby, createddate, lastmodifiedby, lastmodifieddate)  
VALUES (nextval('SEQ_EG_ACTION'), 'HR Report Reload', '/report/hr/_reload', 'HRPT',null,   (select id from service where name='HR Report' and tenantid='default'), 1,   'HR Report Reload', false, 1, now(), 1, now());


insert into eg_roleaction(roleCode,actionid,tenantId)values('EMPLOYEE ADMIN',(select id from eg_action where name='HR Report MetaData'),'default');
insert into eg_roleaction(roleCode,actionid,tenantId)values('EMPLOYEE ADMIN',(select id from eg_action where name='HR Report'),'default');
insert into eg_roleaction(roleCode,actionid,tenantId)values('EMPLOYEE ADMIN',(select id from eg_action where name='HR Report Reload'),'default');