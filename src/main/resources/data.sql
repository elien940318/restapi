insert into users 
(id, join_date, name, password, ssn)
values
(90001, now(), 'User1', 'test1111', '701010-1111111'),
(90002, now(), 'User2', 'test2222', '801010-1111111'),
(90003, now(), 'User3', 'test3333', '901010-1111111');

insert into post
(description, user_id)
values
('my First post', 90001),
('my Second post', 90001);