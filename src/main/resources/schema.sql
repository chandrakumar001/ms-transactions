create SCHEMA transactions;
create sequence transactions.hibernate_sequence increment 1 start 1 minvalue 1;
create table transactions.parent_transaction (id bigint not null, receiver varchar(255), sender varchar(255), total_amount double not null, primary key (id));
create table transactions.child_transaction (id bigint not null, paid_amount double not null, parent_id bigint not null, primary key (id));
alter table transactions.child_transaction add constraint child_transaction_parent_id_fk foreign key (parent_id) references parent_transaction on delete cascade;

