alter table student
    add constraint age_constraint check (age >= 16);


alter table student
    add constraint name_unique unique (name);
alter table student
    alter column name set not null;



alter table faculty
    add constraint color_name_unique unique (color, name);



alter table student
    alter column age set default '20';
