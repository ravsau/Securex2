use securex;

set foreign_key_checks=0;



INSERT INTO role (role)
VALUES ('USER');
INSERT INTO role (role)
VALUES ('USER');





INSERT INTO user(enabled,password,username) values (1,'password','bina');
INSERT INTO user(enabled,password,username) values (1,'password','mina');
INSERT INTO user(enabled,password,username) values (1,'password','tina');


INSERT INTO role (role)
VALUES ('USER'); 

INSERT INTO role (role)
VALUES ('ADMIN');

INSERT INTO user_roles (user_id,role_id) values (1,1);
INSERT INTO user_roles (user_id,role_id) values (2,2);
INSERT INTO user_roles (user_id,role_id) values (3,1);