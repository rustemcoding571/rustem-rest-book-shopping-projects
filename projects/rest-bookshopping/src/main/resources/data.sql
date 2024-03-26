
insert into users
(username,password,enabled)
values('librarian1','$2a$12$XYdHW6MDTpTaVJjyogjY/umSHm3IppO9QleeNZb2j/VcwwRozG4Ve',1);

insert into users
(username,password,enabled)
values('student1','$2a$12$So0oM3O5hu.TRbwU.dmjNOes1JjI3nJwUo31oaQcN0JqWkEVbjf/.',1);

insert into authorities
(username,authority)
values 
('student1','ROLE_ADMIN');

insert into authorities
(username,authority)
values 
('librarian1','ROLE_ADMIN');