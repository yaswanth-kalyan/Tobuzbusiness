# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table address (
  id                            bigserial not null,
  is_active                     boolean,
  detailed_address              TEXT,
  city_id                       bigint,
  country_id                    bigint,
  state_id                      bigint,
  latitude                      float,
  longitude                     float,
  zip_code                      varchar(255),
  google_address                TEXT,
  business_advert_id            bigint,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_address primary key (id)
);

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

create table app_user (
  id                            bigserial not null,
  is_active                     boolean,
  name                          varchar(255) not null,
  email                         varchar(255) not null,
  mobile_number                 varchar(255),
  country_code                  varchar(255),
  mobile_derived_country_id     bigint,
  password                      varchar(255),
  salt                          varchar(255),
  user_default_role             varchar(16),
  address_id                    bigint,
  login_type                    varchar(9),
  profile_image_id              bigint,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint ck_app_user_user_default_role check (user_default_role in ('BROKER','TOBUZ_ADMIN','BUYER','BUSINESS_SERVICE','FRANCHISOR','SELLER')),
  constraint ck_app_user_login_type check (login_type in ('FACEBOOK','TOBUZ','TWITTER','GOOGLE','LINKED_IN')),
  constraint uq_app_user_email unique (email),
  constraint uq_app_user_address_id unique (address_id),
  constraint uq_app_user_profile_image_id unique (profile_image_id),
  constraint pk_app_user primary key (id)
);

create table app_user_log (
  id                            bigserial not null,
  is_active                     boolean,
  logged_in_on                  timestamp,
  logged_out_on                 timestamp,
  auth_token                    varchar(255),
  ip_address                    varchar(255),
  app_user_id                   bigint,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_app_user_log primary key (id)
);

create table area_metrics (
  id                            bigserial not null,
  is_active                     boolean,
  country_id                    bigint,
  metric                        varchar(255),
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_area_metrics primary key (id)
);

create table broker (
  id                            bigserial not null,
  is_active                     boolean,
  user_id                       bigint,
  role_id                       bigint,
  share_broker_contact_to_buyer boolean,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint uq_broker_user_id unique (user_id),
  constraint pk_broker primary key (id)
);

create table business_advert (
  id                            bigserial not null,
  is_active                     boolean,
  adverted_by_user_id           bigint,
  role_id                       bigint,
  listing_type                  varchar(10),
  investment_range_from         float,
  investment_range_to           float,
  advert_description            TEXT,
  title                         varchar(255),
  space_size                    float,
  company_type                  varchar(255),
  source_of_business            varchar(255),
  time_line_in_days             integer,
  reference_url                 varchar(255),
  allow_contact_details_to_users boolean,
  business_advert_status        varchar(12),
  search_index                  varchar(255),
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint ck_business_advert_listing_type check (listing_type in ('COMMERCIAL','BUSINESS','FRANCHISE')),
  constraint ck_business_advert_business_advert_status check (business_advert_status in ('SOLD','COMPLETED','PUBLISHED','DRAFTED','APPROVED','UNDER_REVIEW','REJECTED')),
  constraint pk_business_advert primary key (id)
);

create table business_advert_subcategory_info (
  id                            bigserial not null,
  is_active                     boolean,
  business_advert_id            bigint,
  sub_category_id               bigint,
  category_id                   bigint,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_business_advert_subcategory_info primary key (id)
);

create table business_category_advert_info (
  id                            bigserial not null,
  is_active                     boolean,
  category_id                   bigint,
  business_advert_id            bigint,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_business_category_advert_info primary key (id)
);

create table business_feature (
  id                            bigserial not null,
  is_active                     boolean,
  name                          varchar(255),
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_business_feature primary key (id)
);

create table business_listing (
  id                            bigserial not null,
  is_active                     boolean,
  listed_by_user_id             bigint,
  role_id                       bigint,
  listing_id                    varchar(255),
  franchise_for                 varchar(6),
  listing_type                  varchar(10),
  listing_for                   varchar(13),
  title                         varchar(255),
  listing_description           varchar(255),
  category_id                   bigint,
  seo_keyword                   TEXT,
  business_address_country_id   bigint,
  slug_title                    varchar(255),
  meta_title                    varchar(255),
  meta_description              varchar(255),
  meta_url                      varchar(255),
  business_listing_status       varchar(12),
  name                          varchar(255),
  email                         varchar(255),
  country_code                  varchar(255),
  contact_number                varchar(255),
  website_url                   varchar(255),
  show_contact_details_on_listing boolean,
  posted_on                     timestamp,
  sold_on                       timestamp,
  sold_marked_by_id             bigint,
  business_listing_out_let_id   bigint,
  is_distress_sale              boolean,
  description                   varchar(255),
  search_index                  varchar(255),
  is_recomended_by_admin        boolean,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint ck_business_listing_franchise_for check (franchise_for in ('SALE','RESALE')),
  constraint ck_business_listing_listing_type check (listing_type in ('COMMERCIAL','BUSINESS','FRANCHISE')),
  constraint ck_business_listing_listing_for check (listing_for in ('SALE','OTHERS','RENT_OR_LEASE')),
  constraint ck_business_listing_business_listing_status check (business_listing_status in ('SOLD','COMPLETED','PUBLISHED','DRAFTED','APPROVED','UNDER_REVIEW','REJECTED')),
  constraint uq_business_listing_business_listing_out_let_id unique (business_listing_out_let_id),
  constraint pk_business_listing primary key (id)
);

create table business_listing_feature_info (
  id                            bigserial not null,
  is_active                     boolean,
  business_listing_id           bigint,
  business_feature_id           bigint,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_business_listing_feature_info primary key (id)
);

create table business_listing_out_let (
  id                            bigserial not null,
  is_active                     boolean,
  business_address_id           bigint,
  company_type                  varchar(255),
  is_company_active             boolean,
  business_turnover             float,
  business_turnover_per         varchar(6),
  business_total_expenses       float,
  business_expenses_per         varchar(6),
  gross_profit                  float,
  net_profit                    float,
  is_plant_fixtures_fittings_included boolean,
  is_estimated_stock_included   boolean,
  listing_sale_price_type       varchar(21),
  total_business_sale_price     float,
  logo_id                       bigint,
  document_id                   bigint,
  video_url                     varchar(255),
  size                          float,
  year_of_establishment         integer,
  no_of_employees               integer,
  no_of_trading_hours           integer,
  is_business_supporting_and_trading boolean,
  business_description          TEXT,
  business_link_type            varchar(9),
  business_url                  varchar(255),
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint ck_business_listing_out_let_business_turnover_per check (business_turnover_per in ('MONTH','ANNUAL','WEEK')),
  constraint ck_business_listing_out_let_business_expenses_per check (business_expenses_per in ('MONTH','ANNUAL','WEEK')),
  constraint ck_business_listing_out_let_listing_sale_price_type check (listing_sale_price_type in ('SALE_PRICE','AVAILABLE_ON_APPROACH')),
  constraint ck_business_listing_out_let_business_link_type check (business_link_type in ('FACEBOOK','TOBUZ','TWITTER','GOOGLE','LINKED_IN')),
  constraint uq_business_listing_out_let_business_address_id unique (business_address_id),
  constraint pk_business_listing_out_let primary key (id)
);

create table business_listing_subcategory_info (
  id                            bigserial not null,
  is_active                     boolean,
  business_listing_id           bigint,
  sub_category_id               bigint,
  category_id                   bigint,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_business_listing_subcategory_info primary key (id)
);

create table business_listing_visit_info (
  id                            bigserial not null,
  is_active                     boolean,
  user_visited_id               bigint,
  role_id                       bigint,
  business_listing_id           bigint,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_business_listing_visit_info primary key (id)
);

create table business_type (
  id                            bigserial not null,
  is_active                     boolean,
  name                          varchar(255),
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_business_type primary key (id)
);

create table buyer (
  id                            bigserial not null,
  is_active                     boolean,
  user_id                       bigint,
  role_id                       bigint,
  share_contact_details         boolean,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint uq_buyer_user_id unique (user_id),
  constraint pk_buyer primary key (id)
);

create table category (
  id                            bigserial not null,
  is_active                     boolean,
  name                          varchar(255),
  image_id                      bigint,
  is_commercial_category        boolean,
  sequence                      integer,
  is_featured_category          boolean,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_category primary key (id)
);

create table city (
  id                            bigserial not null,
  is_active                     boolean,
  name                          varchar(255),
  state_id                      bigint,
  country_id                    bigint,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_city primary key (id)
);

create table company_type (
  id                            bigserial not null,
  is_active                     boolean,
  name                          varchar(255),
  country_id                    bigint,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_company_type primary key (id)
);

create table country (
  id                            bigserial not null,
  is_active                     boolean,
  name                          varchar(255),
  dialing_code                  varchar(255),
  iso_code                      varchar(255),
  inet_code                     varchar(255),
  currency_code                 varchar(255),
  flag_name                     varchar(255),
  time_zone                     varchar(255),
  time_offset_in_mins           integer,
  sequence                      integer,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_country primary key (id)
);

create table favourite_business_listing (
  id                            bigserial not null,
  is_active                     boolean,
  user_id                       bigint,
  role_id                       bigint,
  business_listing_id           bigint,
  business_advert_id            bigint,
  added_on                      timestamp,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_favourite_business_listing primary key (id)
);

create table file_entity (
  id                            bigserial not null,
  is_active                     boolean,
  file_name                     varchar(255),
  mime_type                     varchar(255),
  file_path                     varchar(255),
  uploaded_by_id                bigint,
  byte_content                  bytea,
  uploaded_on                   timestamp,
  comment                       TEXT,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_file_entity primary key (id)
);

create table franchiser (
  id                            bigserial not null,
  is_active                     boolean,
  user_id                       bigint,
  role_id                       bigint,
  allow_franchisor_contact_access boolean,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint uq_franchiser_user_id unique (user_id),
  constraint pk_franchiser primary key (id)
);

create table listing_keyword (
  id                            bigserial not null,
  is_active                     boolean,
  business_listing_id           bigint,
  keyword                       varchar(255),
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_listing_keyword primary key (id)
);

create table message (
  id                            bigserial not null,
  is_active                     boolean,
  from_user_id                  bigint,
  from_user_role_id             bigint,
  to_app_user_id                bigint,
  to_user_role_id               bigint,
  subject                       TEXT,
  bodytext                      TEXT,
  is_receiver_verified          boolean,
  business_listing_id           bigint,
  business_advert_id            bigint,
  message_sent_on               timestamp,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_message primary key (id)
);

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

create table payment (
  id                            bigserial not null,
  is_active                     boolean,
  app_user_id                   bigint,
  paid_amount                   float,
  transaction_id                varchar(255),
  transaction_status            varchar(255),
  transaction_description       TEXT,
  transaction_done_on           timestamp,
  business_listing_id           bigint,
  user_tobuz_service_package_info_id bigint,
  is_distress_sale_payment      boolean,
  is_pckage_sale_payment        boolean,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_payment primary key (id)
);

create table role (
  id                            bigserial not null,
  is_active                     boolean,
  user_role                     varchar(16),
  app_user_id                   bigint,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint ck_role_user_role check (user_role in ('BROKER','TOBUZ_ADMIN','BUYER','BUSINESS_SERVICE','FRANCHISOR','SELLER')),
  constraint pk_role primary key (id)
);

create table seller (
  id                            bigserial not null,
  is_active                     boolean,
  user_id                       bigint,
  role_id                       bigint,
  allow_buyer_to_contact        boolean,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint uq_seller_user_id unique (user_id),
  constraint pk_seller primary key (id)
);

create table state (
  id                            bigserial not null,
  is_active                     boolean,
  name                          varchar(255),
  country_id                    bigint,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_state primary key (id)
);

create table sub_category (
  id                            bigserial not null,
  is_active                     boolean,
  name                          varchar(255),
  category_id                   bigint,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_sub_category primary key (id)
);

create table tobuz_package (
  id                            bigserial not null,
  is_active                     boolean,
  tobuz_package_type            varchar(6),
  description                   TEXT,
  cost                          float,
  country_id                    bigint,
  expiry_period_in_months       integer,
  is_free_package               boolean,
  sequence                      integer,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint ck_tobuz_package_tobuz_package_type check (tobuz_package_type in ('GOLD','BRONZE','SILVER','FREE')),
  constraint pk_tobuz_package primary key (id)
);

create table tobuz_package_service (
  id                            bigserial not null,
  is_active                     boolean,
  title                         varchar(255),
  description                   TEXT,
  country_id                    bigint,
  user_role                     varchar(16),
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint ck_tobuz_package_service_user_role check (user_role in ('BROKER','TOBUZ_ADMIN','BUYER','BUSINESS_SERVICE','FRANCHISOR','SELLER')),
  constraint pk_tobuz_package_service primary key (id)
);

create table tobuz_package_service_info (
  id                            bigserial not null,
  is_active                     boolean,
  tobuz_package_id              bigint,
  tobuz_package_service_id      bigint,
  country_id                    bigint,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_tobuz_package_service_info primary key (id)
);

create table user_contact_info (
  id                            bigserial not null,
  is_active                     boolean,
  user_name                     varchar(255),
  email                         varchar(255),
  mobile_number                 varchar(255),
  country_code                  varchar(255),
  mobile_derived_country_id     bigint,
  message                       TEXT,
  business_listing_id           bigint,
  enable_sms_email_alerts       boolean,
  enable_similar_property_alert boolean,
  enable_business_provider_advise boolean,
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint pk_user_contact_info primary key (id)
);

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

create table user_tobuz_service_package_info (
  id                            bigserial not null,
  is_active                     boolean,
  user_id                       bigint,
  role_id                       bigint,
  tobuz_package_id              bigint,
  activated_on                  timestamp,
  expired_on                    timestamp,
  package_active_for            varchar(10),
  user_package_status           varchar(7),
  created_on                    timestamp not null,
  last_update                   timestamp not null,
  constraint ck_user_tobuz_service_package_info_package_active_for check (package_active_for in ('COMMERCIAL','BUSINESS','FRANCHISE')),
  constraint ck_user_tobuz_service_package_info_user_package_status check (user_package_status in ('ACTIVE','EXPIRED')),
  constraint pk_user_tobuz_service_package_info primary key (id)
);

alter table address add constraint fk_address_city_id foreign key (city_id) references city (id) on delete restrict on update restrict;
create index ix_address_city_id on address (city_id);

alter table address add constraint fk_address_country_id foreign key (country_id) references country (id) on delete restrict on update restrict;
create index ix_address_country_id on address (country_id);

alter table address add constraint fk_address_state_id foreign key (state_id) references state (id) on delete restrict on update restrict;
create index ix_address_state_id on address (state_id);

alter table address add constraint fk_address_business_advert_id foreign key (business_advert_id) references business_advert (id) on delete restrict on update restrict;
create index ix_address_business_advert_id on address (business_advert_id);

alter table app_user add constraint fk_app_user_mobile_derived_country_id foreign key (mobile_derived_country_id) references country (id) on delete restrict on update restrict;
create index ix_app_user_mobile_derived_country_id on app_user (mobile_derived_country_id);

alter table app_user add constraint fk_app_user_address_id foreign key (address_id) references address (id) on delete restrict on update restrict;

alter table app_user add constraint fk_app_user_profile_image_id foreign key (profile_image_id) references file_entity (id) on delete restrict on update restrict;

alter table app_user_log add constraint fk_app_user_log_app_user_id foreign key (app_user_id) references app_user (id) on delete restrict on update restrict;
create index ix_app_user_log_app_user_id on app_user_log (app_user_id);

alter table area_metrics add constraint fk_area_metrics_country_id foreign key (country_id) references country (id) on delete restrict on update restrict;
create index ix_area_metrics_country_id on area_metrics (country_id);

alter table broker add constraint fk_broker_user_id foreign key (user_id) references app_user (id) on delete restrict on update restrict;

alter table broker add constraint fk_broker_role_id foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_broker_role_id on broker (role_id);

alter table business_advert add constraint fk_business_advert_adverted_by_user_id foreign key (adverted_by_user_id) references app_user (id) on delete restrict on update restrict;
create index ix_business_advert_adverted_by_user_id on business_advert (adverted_by_user_id);

alter table business_advert add constraint fk_business_advert_role_id foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_business_advert_role_id on business_advert (role_id);

alter table business_advert_subcategory_info add constraint fk_business_advert_subcategory_info_business_advert_id foreign key (business_advert_id) references business_advert (id) on delete restrict on update restrict;
create index ix_business_advert_subcategory_info_business_advert_id on business_advert_subcategory_info (business_advert_id);

alter table business_advert_subcategory_info add constraint fk_business_advert_subcategory_info_sub_category_id foreign key (sub_category_id) references sub_category (id) on delete restrict on update restrict;
create index ix_business_advert_subcategory_info_sub_category_id on business_advert_subcategory_info (sub_category_id);

alter table business_advert_subcategory_info add constraint fk_business_advert_subcategory_info_category_id foreign key (category_id) references category (id) on delete restrict on update restrict;
create index ix_business_advert_subcategory_info_category_id on business_advert_subcategory_info (category_id);

alter table business_category_advert_info add constraint fk_business_category_advert_info_category_id foreign key (category_id) references category (id) on delete restrict on update restrict;
create index ix_business_category_advert_info_category_id on business_category_advert_info (category_id);

alter table business_category_advert_info add constraint fk_business_category_advert_info_business_advert_id foreign key (business_advert_id) references business_advert (id) on delete restrict on update restrict;
create index ix_business_category_advert_info_business_advert_id on business_category_advert_info (business_advert_id);

alter table business_listing add constraint fk_business_listing_listed_by_user_id foreign key (listed_by_user_id) references app_user (id) on delete restrict on update restrict;
create index ix_business_listing_listed_by_user_id on business_listing (listed_by_user_id);

alter table business_listing add constraint fk_business_listing_role_id foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_business_listing_role_id on business_listing (role_id);

alter table business_listing add constraint fk_business_listing_category_id foreign key (category_id) references category (id) on delete restrict on update restrict;
create index ix_business_listing_category_id on business_listing (category_id);

alter table business_listing add constraint fk_business_listing_business_address_country_id foreign key (business_address_country_id) references country (id) on delete restrict on update restrict;
create index ix_business_listing_business_address_country_id on business_listing (business_address_country_id);

alter table business_listing add constraint fk_business_listing_sold_marked_by_id foreign key (sold_marked_by_id) references app_user (id) on delete restrict on update restrict;
create index ix_business_listing_sold_marked_by_id on business_listing (sold_marked_by_id);

alter table business_listing add constraint fk_business_listing_business_listing_out_let_id foreign key (business_listing_out_let_id) references business_listing_out_let (id) on delete restrict on update restrict;

alter table business_listing_feature_info add constraint fk_business_listing_feature_info_business_listing_id foreign key (business_listing_id) references business_listing (id) on delete restrict on update restrict;
create index ix_business_listing_feature_info_business_listing_id on business_listing_feature_info (business_listing_id);

alter table business_listing_feature_info add constraint fk_business_listing_feature_info_business_feature_id foreign key (business_feature_id) references business_feature (id) on delete restrict on update restrict;
create index ix_business_listing_feature_info_business_feature_id on business_listing_feature_info (business_feature_id);

alter table business_listing_out_let add constraint fk_business_listing_out_let_business_address_id foreign key (business_address_id) references address (id) on delete restrict on update restrict;

alter table business_listing_subcategory_info add constraint fk_business_listing_subcategory_info_business_listing_id foreign key (business_listing_id) references business_listing (id) on delete restrict on update restrict;
create index ix_business_listing_subcategory_info_business_listing_id on business_listing_subcategory_info (business_listing_id);

alter table business_listing_subcategory_info add constraint fk_business_listing_subcategory_info_sub_category_id foreign key (sub_category_id) references sub_category (id) on delete restrict on update restrict;
create index ix_business_listing_subcategory_info_sub_category_id on business_listing_subcategory_info (sub_category_id);

alter table business_listing_subcategory_info add constraint fk_business_listing_subcategory_info_category_id foreign key (category_id) references category (id) on delete restrict on update restrict;
create index ix_business_listing_subcategory_info_category_id on business_listing_subcategory_info (category_id);

alter table business_listing_visit_info add constraint fk_business_listing_visit_info_user_visited_id foreign key (user_visited_id) references app_user (id) on delete restrict on update restrict;
create index ix_business_listing_visit_info_user_visited_id on business_listing_visit_info (user_visited_id);

alter table business_listing_visit_info add constraint fk_business_listing_visit_info_role_id foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_business_listing_visit_info_role_id on business_listing_visit_info (role_id);

alter table business_listing_visit_info add constraint fk_business_listing_visit_info_business_listing_id foreign key (business_listing_id) references business_listing (id) on delete restrict on update restrict;
create index ix_business_listing_visit_info_business_listing_id on business_listing_visit_info (business_listing_id);

alter table buyer add constraint fk_buyer_user_id foreign key (user_id) references app_user (id) on delete restrict on update restrict;

alter table buyer add constraint fk_buyer_role_id foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_buyer_role_id on buyer (role_id);

alter table city add constraint fk_city_state_id foreign key (state_id) references state (id) on delete restrict on update restrict;
create index ix_city_state_id on city (state_id);

alter table city add constraint fk_city_country_id foreign key (country_id) references country (id) on delete restrict on update restrict;
create index ix_city_country_id on city (country_id);

alter table company_type add constraint fk_company_type_country_id foreign key (country_id) references country (id) on delete restrict on update restrict;
create index ix_company_type_country_id on company_type (country_id);

alter table favourite_business_listing add constraint fk_favourite_business_listing_user_id foreign key (user_id) references app_user (id) on delete restrict on update restrict;
create index ix_favourite_business_listing_user_id on favourite_business_listing (user_id);

alter table favourite_business_listing add constraint fk_favourite_business_listing_role_id foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_favourite_business_listing_role_id on favourite_business_listing (role_id);

alter table favourite_business_listing add constraint fk_favourite_business_listing_business_listing_id foreign key (business_listing_id) references business_listing (id) on delete restrict on update restrict;
create index ix_favourite_business_listing_business_listing_id on favourite_business_listing (business_listing_id);

alter table favourite_business_listing add constraint fk_favourite_business_listing_business_advert_id foreign key (business_advert_id) references business_advert (id) on delete restrict on update restrict;
create index ix_favourite_business_listing_business_advert_id on favourite_business_listing (business_advert_id);

alter table file_entity add constraint fk_file_entity_uploaded_by_id foreign key (uploaded_by_id) references app_user (id) on delete restrict on update restrict;
create index ix_file_entity_uploaded_by_id on file_entity (uploaded_by_id);

alter table franchiser add constraint fk_franchiser_user_id foreign key (user_id) references app_user (id) on delete restrict on update restrict;

alter table franchiser add constraint fk_franchiser_role_id foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_franchiser_role_id on franchiser (role_id);

alter table listing_keyword add constraint fk_listing_keyword_business_listing_id foreign key (business_listing_id) references business_listing (id) on delete restrict on update restrict;
create index ix_listing_keyword_business_listing_id on listing_keyword (business_listing_id);

alter table message add constraint fk_message_from_user_id foreign key (from_user_id) references app_user (id) on delete restrict on update restrict;
create index ix_message_from_user_id on message (from_user_id);

alter table message add constraint fk_message_from_user_role_id foreign key (from_user_role_id) references role (id) on delete restrict on update restrict;
create index ix_message_from_user_role_id on message (from_user_role_id);

alter table message add constraint fk_message_to_app_user_id foreign key (to_app_user_id) references app_user (id) on delete restrict on update restrict;
create index ix_message_to_app_user_id on message (to_app_user_id);

alter table message add constraint fk_message_to_user_role_id foreign key (to_user_role_id) references role (id) on delete restrict on update restrict;
create index ix_message_to_user_role_id on message (to_user_role_id);

alter table message add constraint fk_message_business_listing_id foreign key (business_listing_id) references business_listing (id) on delete restrict on update restrict;
create index ix_message_business_listing_id on message (business_listing_id);

alter table message add constraint fk_message_business_advert_id foreign key (business_advert_id) references business_advert (id) on delete restrict on update restrict;
create index ix_message_business_advert_id on message (business_advert_id);

alter table news_letter_subscription add constraint fk_news_letter_subscription_app_user_id foreign key (app_user_id) references app_user (id) on delete restrict on update restrict;
create index ix_news_letter_subscription_app_user_id on news_letter_subscription (app_user_id);

alter table news_letter_subscription add constraint fk_news_letter_subscription_role_id foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_news_letter_subscription_role_id on news_letter_subscription (role_id);

alter table payment add constraint fk_payment_app_user_id foreign key (app_user_id) references app_user (id) on delete restrict on update restrict;
create index ix_payment_app_user_id on payment (app_user_id);

alter table payment add constraint fk_payment_business_listing_id foreign key (business_listing_id) references business_listing (id) on delete restrict on update restrict;
create index ix_payment_business_listing_id on payment (business_listing_id);

alter table payment add constraint fk_payment_user_tobuz_service_package_info_id foreign key (user_tobuz_service_package_info_id) references user_tobuz_service_package_info (id) on delete restrict on update restrict;
create index ix_payment_user_tobuz_service_package_info_id on payment (user_tobuz_service_package_info_id);

alter table role add constraint fk_role_app_user_id foreign key (app_user_id) references app_user (id) on delete restrict on update restrict;
create index ix_role_app_user_id on role (app_user_id);

alter table seller add constraint fk_seller_user_id foreign key (user_id) references app_user (id) on delete restrict on update restrict;

alter table seller add constraint fk_seller_role_id foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_seller_role_id on seller (role_id);

alter table state add constraint fk_state_country_id foreign key (country_id) references country (id) on delete restrict on update restrict;
create index ix_state_country_id on state (country_id);

alter table sub_category add constraint fk_sub_category_category_id foreign key (category_id) references category (id) on delete restrict on update restrict;
create index ix_sub_category_category_id on sub_category (category_id);

alter table tobuz_package add constraint fk_tobuz_package_country_id foreign key (country_id) references country (id) on delete restrict on update restrict;
create index ix_tobuz_package_country_id on tobuz_package (country_id);

alter table tobuz_package_service add constraint fk_tobuz_package_service_country_id foreign key (country_id) references country (id) on delete restrict on update restrict;
create index ix_tobuz_package_service_country_id on tobuz_package_service (country_id);

alter table tobuz_package_service_info add constraint fk_tobuz_package_service_info_tobuz_package_id foreign key (tobuz_package_id) references tobuz_package (id) on delete restrict on update restrict;
create index ix_tobuz_package_service_info_tobuz_package_id on tobuz_package_service_info (tobuz_package_id);

alter table tobuz_package_service_info add constraint fk_tobuz_package_service_info_tobuz_package_service_id foreign key (tobuz_package_service_id) references tobuz_package_service (id) on delete restrict on update restrict;
create index ix_tobuz_package_service_info_tobuz_package_service_id on tobuz_package_service_info (tobuz_package_service_id);

alter table tobuz_package_service_info add constraint fk_tobuz_package_service_info_country_id foreign key (country_id) references country (id) on delete restrict on update restrict;
create index ix_tobuz_package_service_info_country_id on tobuz_package_service_info (country_id);

alter table user_contact_info add constraint fk_user_contact_info_mobile_derived_country_id foreign key (mobile_derived_country_id) references country (id) on delete restrict on update restrict;
create index ix_user_contact_info_mobile_derived_country_id on user_contact_info (mobile_derived_country_id);

alter table user_contact_info add constraint fk_user_contact_info_business_listing_id foreign key (business_listing_id) references business_listing (id) on delete restrict on update restrict;
create index ix_user_contact_info_business_listing_id on user_contact_info (business_listing_id);

alter table user_search add constraint fk_user_search_app_user_id foreign key (app_user_id) references app_user (id) on delete restrict on update restrict;
create index ix_user_search_app_user_id on user_search (app_user_id);

alter table user_search add constraint fk_user_search_role_id foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_user_search_role_id on user_search (role_id);

alter table user_tobuz_service_package_info add constraint fk_user_tobuz_service_package_info_user_id foreign key (user_id) references app_user (id) on delete restrict on update restrict;
create index ix_user_tobuz_service_package_info_user_id on user_tobuz_service_package_info (user_id);

alter table user_tobuz_service_package_info add constraint fk_user_tobuz_service_package_info_role_id foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_user_tobuz_service_package_info_role_id on user_tobuz_service_package_info (role_id);

alter table user_tobuz_service_package_info add constraint fk_user_tobuz_service_package_info_tobuz_package_id foreign key (tobuz_package_id) references tobuz_package (id) on delete restrict on update restrict;
create index ix_user_tobuz_service_package_info_tobuz_package_id on user_tobuz_service_package_info (tobuz_package_id);


# --- !Downs

alter table if exists address drop constraint if exists fk_address_city_id;
drop index if exists ix_address_city_id;

alter table if exists address drop constraint if exists fk_address_country_id;
drop index if exists ix_address_country_id;

alter table if exists address drop constraint if exists fk_address_state_id;
drop index if exists ix_address_state_id;

alter table if exists address drop constraint if exists fk_address_business_advert_id;
drop index if exists ix_address_business_advert_id;

alter table if exists app_user drop constraint if exists fk_app_user_mobile_derived_country_id;
drop index if exists ix_app_user_mobile_derived_country_id;

alter table if exists app_user drop constraint if exists fk_app_user_address_id;

alter table if exists app_user drop constraint if exists fk_app_user_profile_image_id;

alter table if exists app_user_log drop constraint if exists fk_app_user_log_app_user_id;
drop index if exists ix_app_user_log_app_user_id;

alter table if exists area_metrics drop constraint if exists fk_area_metrics_country_id;
drop index if exists ix_area_metrics_country_id;

alter table if exists broker drop constraint if exists fk_broker_user_id;

alter table if exists broker drop constraint if exists fk_broker_role_id;
drop index if exists ix_broker_role_id;

alter table if exists business_advert drop constraint if exists fk_business_advert_adverted_by_user_id;
drop index if exists ix_business_advert_adverted_by_user_id;

alter table if exists business_advert drop constraint if exists fk_business_advert_role_id;
drop index if exists ix_business_advert_role_id;

alter table if exists business_advert_subcategory_info drop constraint if exists fk_business_advert_subcategory_info_business_advert_id;
drop index if exists ix_business_advert_subcategory_info_business_advert_id;

alter table if exists business_advert_subcategory_info drop constraint if exists fk_business_advert_subcategory_info_sub_category_id;
drop index if exists ix_business_advert_subcategory_info_sub_category_id;

alter table if exists business_advert_subcategory_info drop constraint if exists fk_business_advert_subcategory_info_category_id;
drop index if exists ix_business_advert_subcategory_info_category_id;

alter table if exists business_category_advert_info drop constraint if exists fk_business_category_advert_info_category_id;
drop index if exists ix_business_category_advert_info_category_id;

alter table if exists business_category_advert_info drop constraint if exists fk_business_category_advert_info_business_advert_id;
drop index if exists ix_business_category_advert_info_business_advert_id;

alter table if exists business_listing drop constraint if exists fk_business_listing_listed_by_user_id;
drop index if exists ix_business_listing_listed_by_user_id;

alter table if exists business_listing drop constraint if exists fk_business_listing_role_id;
drop index if exists ix_business_listing_role_id;

alter table if exists business_listing drop constraint if exists fk_business_listing_category_id;
drop index if exists ix_business_listing_category_id;

alter table if exists business_listing drop constraint if exists fk_business_listing_business_address_country_id;
drop index if exists ix_business_listing_business_address_country_id;

alter table if exists business_listing drop constraint if exists fk_business_listing_sold_marked_by_id;
drop index if exists ix_business_listing_sold_marked_by_id;

alter table if exists business_listing drop constraint if exists fk_business_listing_business_listing_out_let_id;

alter table if exists business_listing_feature_info drop constraint if exists fk_business_listing_feature_info_business_listing_id;
drop index if exists ix_business_listing_feature_info_business_listing_id;

alter table if exists business_listing_feature_info drop constraint if exists fk_business_listing_feature_info_business_feature_id;
drop index if exists ix_business_listing_feature_info_business_feature_id;

alter table if exists business_listing_out_let drop constraint if exists fk_business_listing_out_let_business_address_id;

alter table if exists business_listing_subcategory_info drop constraint if exists fk_business_listing_subcategory_info_business_listing_id;
drop index if exists ix_business_listing_subcategory_info_business_listing_id;

alter table if exists business_listing_subcategory_info drop constraint if exists fk_business_listing_subcategory_info_sub_category_id;
drop index if exists ix_business_listing_subcategory_info_sub_category_id;

alter table if exists business_listing_subcategory_info drop constraint if exists fk_business_listing_subcategory_info_category_id;
drop index if exists ix_business_listing_subcategory_info_category_id;

alter table if exists business_listing_visit_info drop constraint if exists fk_business_listing_visit_info_user_visited_id;
drop index if exists ix_business_listing_visit_info_user_visited_id;

alter table if exists business_listing_visit_info drop constraint if exists fk_business_listing_visit_info_role_id;
drop index if exists ix_business_listing_visit_info_role_id;

alter table if exists business_listing_visit_info drop constraint if exists fk_business_listing_visit_info_business_listing_id;
drop index if exists ix_business_listing_visit_info_business_listing_id;

alter table if exists buyer drop constraint if exists fk_buyer_user_id;

alter table if exists buyer drop constraint if exists fk_buyer_role_id;
drop index if exists ix_buyer_role_id;

alter table if exists city drop constraint if exists fk_city_state_id;
drop index if exists ix_city_state_id;

alter table if exists city drop constraint if exists fk_city_country_id;
drop index if exists ix_city_country_id;

alter table if exists company_type drop constraint if exists fk_company_type_country_id;
drop index if exists ix_company_type_country_id;

alter table if exists favourite_business_listing drop constraint if exists fk_favourite_business_listing_user_id;
drop index if exists ix_favourite_business_listing_user_id;

alter table if exists favourite_business_listing drop constraint if exists fk_favourite_business_listing_role_id;
drop index if exists ix_favourite_business_listing_role_id;

alter table if exists favourite_business_listing drop constraint if exists fk_favourite_business_listing_business_listing_id;
drop index if exists ix_favourite_business_listing_business_listing_id;

alter table if exists favourite_business_listing drop constraint if exists fk_favourite_business_listing_business_advert_id;
drop index if exists ix_favourite_business_listing_business_advert_id;

alter table if exists file_entity drop constraint if exists fk_file_entity_uploaded_by_id;
drop index if exists ix_file_entity_uploaded_by_id;

alter table if exists franchiser drop constraint if exists fk_franchiser_user_id;

alter table if exists franchiser drop constraint if exists fk_franchiser_role_id;
drop index if exists ix_franchiser_role_id;

alter table if exists listing_keyword drop constraint if exists fk_listing_keyword_business_listing_id;
drop index if exists ix_listing_keyword_business_listing_id;

alter table if exists message drop constraint if exists fk_message_from_user_id;
drop index if exists ix_message_from_user_id;

alter table if exists message drop constraint if exists fk_message_from_user_role_id;
drop index if exists ix_message_from_user_role_id;

alter table if exists message drop constraint if exists fk_message_to_app_user_id;
drop index if exists ix_message_to_app_user_id;

alter table if exists message drop constraint if exists fk_message_to_user_role_id;
drop index if exists ix_message_to_user_role_id;

alter table if exists message drop constraint if exists fk_message_business_listing_id;
drop index if exists ix_message_business_listing_id;

alter table if exists message drop constraint if exists fk_message_business_advert_id;
drop index if exists ix_message_business_advert_id;

alter table if exists news_letter_subscription drop constraint if exists fk_news_letter_subscription_app_user_id;
drop index if exists ix_news_letter_subscription_app_user_id;

alter table if exists news_letter_subscription drop constraint if exists fk_news_letter_subscription_role_id;
drop index if exists ix_news_letter_subscription_role_id;

alter table if exists payment drop constraint if exists fk_payment_app_user_id;
drop index if exists ix_payment_app_user_id;

alter table if exists payment drop constraint if exists fk_payment_business_listing_id;
drop index if exists ix_payment_business_listing_id;

alter table if exists payment drop constraint if exists fk_payment_user_tobuz_service_package_info_id;
drop index if exists ix_payment_user_tobuz_service_package_info_id;

alter table if exists role drop constraint if exists fk_role_app_user_id;
drop index if exists ix_role_app_user_id;

alter table if exists seller drop constraint if exists fk_seller_user_id;

alter table if exists seller drop constraint if exists fk_seller_role_id;
drop index if exists ix_seller_role_id;

alter table if exists state drop constraint if exists fk_state_country_id;
drop index if exists ix_state_country_id;

alter table if exists sub_category drop constraint if exists fk_sub_category_category_id;
drop index if exists ix_sub_category_category_id;

alter table if exists tobuz_package drop constraint if exists fk_tobuz_package_country_id;
drop index if exists ix_tobuz_package_country_id;

alter table if exists tobuz_package_service drop constraint if exists fk_tobuz_package_service_country_id;
drop index if exists ix_tobuz_package_service_country_id;

alter table if exists tobuz_package_service_info drop constraint if exists fk_tobuz_package_service_info_tobuz_package_id;
drop index if exists ix_tobuz_package_service_info_tobuz_package_id;

alter table if exists tobuz_package_service_info drop constraint if exists fk_tobuz_package_service_info_tobuz_package_service_id;
drop index if exists ix_tobuz_package_service_info_tobuz_package_service_id;

alter table if exists tobuz_package_service_info drop constraint if exists fk_tobuz_package_service_info_country_id;
drop index if exists ix_tobuz_package_service_info_country_id;

alter table if exists user_contact_info drop constraint if exists fk_user_contact_info_mobile_derived_country_id;
drop index if exists ix_user_contact_info_mobile_derived_country_id;

alter table if exists user_contact_info drop constraint if exists fk_user_contact_info_business_listing_id;
drop index if exists ix_user_contact_info_business_listing_id;

alter table if exists user_search drop constraint if exists fk_user_search_app_user_id;
drop index if exists ix_user_search_app_user_id;

alter table if exists user_search drop constraint if exists fk_user_search_role_id;
drop index if exists ix_user_search_role_id;

alter table if exists user_tobuz_service_package_info drop constraint if exists fk_user_tobuz_service_package_info_user_id;
drop index if exists ix_user_tobuz_service_package_info_user_id;

alter table if exists user_tobuz_service_package_info drop constraint if exists fk_user_tobuz_service_package_info_role_id;
drop index if exists ix_user_tobuz_service_package_info_role_id;

alter table if exists user_tobuz_service_package_info drop constraint if exists fk_user_tobuz_service_package_info_tobuz_package_id;
drop index if exists ix_user_tobuz_service_package_info_tobuz_package_id;

drop table if exists address cascade;

drop table if exists admin_data cascade;

drop table if exists app_user cascade;

drop table if exists app_user_log cascade;

drop table if exists area_metrics cascade;

drop table if exists broker cascade;

drop table if exists business_advert cascade;

drop table if exists business_advert_subcategory_info cascade;

drop table if exists business_category_advert_info cascade;

drop table if exists business_feature cascade;

drop table if exists business_listing cascade;

drop table if exists business_listing_feature_info cascade;

drop table if exists business_listing_out_let cascade;

drop table if exists business_listing_subcategory_info cascade;

drop table if exists business_listing_visit_info cascade;

drop table if exists business_type cascade;

drop table if exists buyer cascade;

drop table if exists category cascade;

drop table if exists city cascade;

drop table if exists company_type cascade;

drop table if exists country cascade;

drop table if exists favourite_business_listing cascade;

drop table if exists file_entity cascade;

drop table if exists franchiser cascade;

drop table if exists listing_keyword cascade;

drop table if exists message cascade;

drop table if exists news_letter_subscription cascade;

drop table if exists payment cascade;

drop table if exists role cascade;

drop table if exists seller cascade;

drop table if exists state cascade;

drop table if exists sub_category cascade;

drop table if exists tobuz_package cascade;

drop table if exists tobuz_package_service cascade;

drop table if exists tobuz_package_service_info cascade;

drop table if exists user_contact_info cascade;

drop table if exists user_search cascade;

drop table if exists user_tobuz_service_package_info cascade;

