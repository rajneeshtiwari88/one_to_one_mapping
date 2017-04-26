
    alter table employee 
       drop 
       foreign key fk_emp_profile;

    drop table if exists employee;

    drop table if exists employee_profile;

    create table employee (
       id varchar(255) not null,
        emp_name varchar(25),
        emp_salary decimal(7,2),
        emp_profile_id varchar(255),
        primary key (id)
    );

    create table employee_profile (
       id varchar(255) not null,
        emp_gender varchar(1),
        emp_qualfication varchar(25),
        primary key (id)
    );

    alter table employee 
       add constraint fk_emp_profile 
       foreign key (emp_profile_id) 
       references employee_profile (id);
