CREATE TYPE subscription_status AS ENUM ('ACTIVE','CANCEL','PAUSE');
CREATE TYPE subscription_type AS ENUM ('MONTHLY','QUARTERLY','YEARLY');

create table users(
	id bigserial PRIMARY KEY,
	first_name varchar(50),
	last_name varchar(50),
	email varchar(100),
	active boolean
);

create table subscription_plan(
	id bigserial PRIMARY KEY,
	price_per_month decimal,
	subscription_type subscription_type
);

create table product(
	id bigserial PRIMARY KEY,
	name varchar(100),
	description varchar(255),
	active boolean,
	subscription_plan_id bigint references subscription_plan(id)
);

create table subscription(
	id bigserial PRIMARY KEY,
	start_date timestamp,
	end_date timestamp,
	subscription_status subscription_status,
	price_before_discount decimal,
	discount_amount decimal,
	price_after_discount decimal,
	subscription_plan_id bigint references subscription_plan(id),
	user_id bigint references users(id)
);