--- author : Lakshmi

alter table category add column sequence integer,add column is_commercial_category boolean default false,add column is_featured_category boolean default false;

 alter table favourite_business_listing add column role_id bigint;