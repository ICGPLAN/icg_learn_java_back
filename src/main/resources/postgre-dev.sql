#CREATE DATABASE icgdevdb;
#CREATE USER javaadmin WITH PASSWORD 'asd8751A52s';
CREATE TABLE contact (
  contact_id VARCHAR(30) PRIMARY KEY,
  name VARCHAR(30),
  inquirySubject VARCHAR(100),
  email VARCHAR(80),
  content VARCHAR(4000),
  creation_time TIMESTAMP,
  deleted_flag BOOLEAN,
  read_flag BOOLEAN
);