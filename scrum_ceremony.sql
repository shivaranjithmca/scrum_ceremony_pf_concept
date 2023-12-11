DROP TABLE IF EXISTS retrospective_t;

CREATE TABLE IF NOT EXISTS retrospective_t(id bigint(20) NOT NULL AUTO_INCREMENT, name varchar(100) NOT NULL, summary varchar(200) default NULL, date datetime NOT NULL, row_created_at DATETIME DEFAULT CURRENT_TIMESTAMP, row_updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, PRIMARY KEY (id), UNIQUE KEY name (name)) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS participant_t;

CREATE TABLE IF NOT EXISTS participant_t(id bigint(20) NOT NULL AUTO_INCREMENT, name varchar(100) NOT NULL, retrospective_id bigint(20) NOT NULL, row_created_at DATETIME DEFAULT CURRENT_TIMESTAMP, row_updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, PRIMARY KEY (id), CONSTRAINT participant_t_ibfk_1 FOREIGN KEY (retrospective_id) REFERENCES retrospective_t (id)) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS feed_back_t;

CREATE TABLE IF NOT EXISTS feed_back_t(id bigint(20) NOT NULL AUTO_INCREMENT, retrospective_id bigint(20) NOT NULL, row_created_at DATETIME DEFAULT CURRENT_TIMESTAMP, row_updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, PRIMARY KEY (id), CONSTRAINT feed_back_t_ibfk_1 FOREIGN KEY (retrospective_id) REFERENCES retrospective_t (id)) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS feed_back_type_t;

CREATE TABLE IF NOT EXISTS feed_back_type_t(id bigint(20) NOT NULL AUTO_INCREMENT, type varchar(50) DEFAULT NULL, row_created_at DATETIME DEFAULT CURRENT_TIMESTAMP, row_updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, PRIMARY KEY (id)) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS feed_back_item_t;

CREATE TABLE IF NOT EXISTS feed_back_item_t(id bigint(20) NOT NULL AUTO_INCREMENT, feed_back_id bigint(20) NOT NULL, name varchar(100) NOT NULL, body varchar(200) default NULL, type_id bigint(20) NOT NULL, row_created_at DATETIME DEFAULT CURRENT_TIMESTAMP, row_updated_at DATETIME DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY (id), CONSTRAINT feed_back_item_t_ibfk_1 FOREIGN KEY (feed_back_id) REFERENCES feed_back_t (id), CONSTRAINT feed_back_item_t_ibfk_2 FOREIGN KEY (type_id) REFERENCES feed_back_type_t (id)) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;

INSERT into feed_back_type_t(type) values("positive"), ("negative"), ("praise"), ("idea");