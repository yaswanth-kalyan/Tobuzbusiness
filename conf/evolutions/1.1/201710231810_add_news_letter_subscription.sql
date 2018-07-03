--- author : Lakshmi

create table news_letter_subscription (
  id                            bigserial not null,
  is_active                     boolean,
  email                         varchar(255),
  app_user_id                   bigint,
  role_id                       bigint,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_news_letter_subscription primary key (id)
);


alter table if exists news_letter_subscription drop constraint if exists fk_news_letter_subscription_app_user_id;
drop index if exists ix_news_letter_subscription_app_user_id;

alter table if exists news_letter_subscription drop constraint if exists fk_news_letter_subscription_role_id;
drop index if exists ix_news_letter_subscription_role_id;
