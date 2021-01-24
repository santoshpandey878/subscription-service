\connect subscriptionservice;

CREATE TYPE subscription_status AS ENUM ('ACTIVE','CANCEL','PAUSE');
CREATE TYPE subscription_type AS ENUM ('MONTHLY','QUARTERLY','HALF_YEARLY','YEARLY');
CREATE TYPE voucher_type AS ENUM ('FIXED_AMOUNT','PERCENTAGE');

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

create table voucher(
	id bigserial PRIMARY KEY,
	name varchar(100),
	voucher_type voucher_type,
	active boolean,
	fixed_amount decimal,
	percentage int
);

create table product_voucher(
	product_id integer references product(id),
	voucher_id integer references voucher(id)
);

create table subscription(
	id bigserial PRIMARY KEY,
	start_date timestamp,
	end_date timestamp,
	subscription_type subscription_type,
	subscription_status subscription_status,
	base_price decimal,
	discount_amount decimal,
	tax_amount decimal,
	total_price decimal,
	trial_start_date timestamp,
	trial_end_date timestamp,
	subscription_plan_id bigint references subscription_plan(id),
	product_id bigint references product(id),
	user_id bigint references users(id)
);

INSERT INTO subscription_plan(price_per_month, subscription_type) VALUES 
(6, 'MONTHLY'),
(8, 'MONTHLY');

INSERT INTO product(name, description, active, subscription_plan_id) VALUES 
('PRODUCT 1', 'DESCRIPTION FOR PRODUCT 1', true, 1),
('PRODUCT 2', 'DESCRIPTION FOR PRODUCT 2', true, 2);

INSERT INTO voucher(name, voucher_type, active, fixed_amount, percentage) VALUES 
('Voucher 1', 'FIXED_AMOUNT', true, 20, 0),
('Voucher 2', 'PERCENTAGE', true, 0, 3),
('Voucher 3', 'PERCENTAGE', true, 0, 2),
('Voucher 4', 'FIXED_AMOUNT', true, 10, 0);

INSERT INTO product_voucher(product_id, voucher_id) VALUES 
(1,1),
(1,2),
(2,4),
(2,1);










