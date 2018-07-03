--- author : Lakshmi

create table user_search (
  id                            bigserial not null,
  is_active                     boolean,
  app_user_id                   bigint,
  role_id                       bigint,
  search_key                    varchar(255),
  searched_on                   timestamp,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_user_search primary key (id)
);

alter table user_search add constraint fk_user_search_app_user_id foreign key (app_user_id) references app_user (id) on delete restrict on update restrict;
create index ix_user_search_app_user_id on user_search (app_user_id);

alter table user_search add constraint fk_user_search_role_id foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_user_search_role_id on user_search (role_id);