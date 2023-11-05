DROP TABLE IF EXISTS `member_profile`;
DROP TABLE IF EXISTS `auth_social`;
DROP TABLE IF EXISTS `auth_email`;
DROP TABLE IF EXISTS `todo`;
DROP TABLE IF EXISTS `member`;

CREATE TABLE member (
                        id BIGINT AUTO_INCREMENT,
                        login_type TINYINT NOT NULL COMMENT '0: email, 1: social-login',
                        PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE member_profile (
                                id BIGINT AUTO_INCREMENT,
                                member_id BIGINT NOT NULL,
                                nickname VARCHAR(20) DEFAULT NULL,
                                created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                deleted_at TIMESTAMP NULL DEFAULT NULL,
                                PRIMARY KEY (id),
                                FOREIGN KEY (member_id) REFERENCES member (id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE auth_social (
                             id BIGINT AUTO_INCREMENT,
                             member_id BIGINT NOT NULL,
                             social_code TINYINT NOT NULL COMMENT '1: google, ...',
                             social_id VARCHAR(256) NOT NULL COMMENT 'encrypt',
                             email VARCHAR(256) NOT NULL COMMENT 'encrypt',
                             created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             deleted_at TIMESTAMP NULL DEFAULT NULL,
                             PRIMARY KEY (id),
                             FOREIGN KEY (member_id) REFERENCES member (id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE auth_email (
                            id BIGINT AUTO_INCREMENT,
                            member_id BIGINT NOT NULL,
                            email VARCHAR(256) NOT NULL COMMENT 'encrypt',
                            password VARCHAR(256) NOT NULL COMMENT 'encrypt',
                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            deleted_at TIMESTAMP NULL DEFAULT NULL,
                            PRIMARY KEY (id),
                            FOREIGN KEY (member_id) REFERENCES member (id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE todo (
                      id BIGINT AUTO_INCREMENT,
                      member_id BIGINT NOT NULL,
                      content VARCHAR(100) NOT NULL,
                      is_done TINYINT NOT NULL DEFAULT 0 COMMENT '0: not done, 1: done',
                      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                      deleted_at TIMESTAMP NULL DEFAULT NULL,
                      PRIMARY KEY (id),
                      FOREIGN KEY (member_id) REFERENCES member (id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
