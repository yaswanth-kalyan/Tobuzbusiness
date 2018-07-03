--- author  : Lakshmi

create table admin_data (
  id                            bigserial not null,
  is_active                     boolean,
  email_from_id                 varchar(255),
  email_smtp_user_name          varchar(255),
  email_host                    varchar(255),
  email_port                    integer,
  email_smtp_password           varchar(255),
  sms_auth_key                  varchar(255),
  sms_sender_id                 varchar(255),
  operations_team_email_id      varchar(255),
  google_api_key                varchar(255),
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_admin_data primary key (id)
);