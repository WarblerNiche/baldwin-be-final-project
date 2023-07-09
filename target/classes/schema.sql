drop table if exists game_collection;
drop table if exists player_game;
drop table if exists game;
drop table if exists player_memberships;
drop table if exists membership;
drop table if exists player;

CREATE TABLE player(
	player_id int not null auto_increment,
	name varchar(50) not null,
	email varchar(60) not null,
	phone varchar(30) not null,
	PRIMARY KEY (player_id)
);

Create table membership(
	membership_id int not null auto_increment,
	player_id int not null,
	membership_type varchar(64) not null,
	membership_renewal_freq varchar(24) not null,
	start_date date not null,
	is_active boolean,
	Primary key (membership_id),
	foreign key (player_id) references player (player_id) on delete cascade
);

create table game(
	game_id int not null auto_increment,
	title varchar(128) not null,
	genre varchar(124),
	platform varchar(64),
	esrb_rating varchar(64),
	primary key (game_id)
);

create table game_collection(
	player_id int not null,
	game_id int not null,
	foreign key (player_id) references player (player_id) on delete cascade,
	foreign key (game_id) references game (game_id) on delete cascade
);