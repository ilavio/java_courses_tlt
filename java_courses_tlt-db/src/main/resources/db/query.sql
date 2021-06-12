create user usr with password 'pass' CREATEDB inherit;
create database epamcourses WITH OWNER 'usr';
create schema IF NOT EXISTS potemkin authorization usr;
grant ALL PRIVILEGES ON ALL TABLES IN SCHEMA potemkin TO "usr";