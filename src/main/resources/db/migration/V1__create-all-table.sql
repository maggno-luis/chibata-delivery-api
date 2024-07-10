CREATE TABLE IF NOT EXISTS tb_category (
                                           id SERIAL PRIMARY KEY,
                                           name VARCHAR(255) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS tb_role (
                                       role_id SERIAL PRIMARY KEY,
                                       name VARCHAR(255) DEFAULT NULL
);

INSERT INTO tb_role (role_id, name) VALUES (1, 'ADMIN'), (2, 'BASIC');

CREATE TABLE IF NOT EXISTS tb_user (
                                       user_id UUID PRIMARY KEY,
                                       email VARCHAR(255) UNIQUE,
                                       name VARCHAR(255) DEFAULT NULL,
                                       password VARCHAR(255) DEFAULT NULL,
                                       path_image VARCHAR(255) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS tb_user_role (
                                            user_id UUID NOT NULL,
                                            role_id BIGINT NOT NULL,
                                            PRIMARY KEY (user_id, role_id),
                                            CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES tb_user (user_id),
                                            CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES tb_role (role_id)
);

CREATE TABLE IF NOT EXISTS tb_order (
                                        id SERIAL PRIMARY KEY,
                                        complement VARCHAR(255) DEFAULT NULL,
                                        house_number VARCHAR(255) DEFAULT NULL,
                                        neighborhood VARCHAR(255) DEFAULT NULL,
                                        street VARCHAR(255) DEFAULT NULL,
                                        payment_method VARCHAR(255) DEFAULT NULL,
                                        status VARCHAR(255) DEFAULT NULL,
                                        user_id UUID REFERENCES tb_user (user_id),
                                        user_order_id UUID DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS tb_product (
                                          id SERIAL PRIMARY KEY,
                                          name VARCHAR(255) DEFAULT NULL,
                                          path_image VARCHAR(255) DEFAULT NULL,
                                          category_id BIGINT,
                                          price DOUBLE PRECISION DEFAULT NULL,
                                          CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES tb_category (id)
);

CREATE TABLE IF NOT EXISTS tb_item (
                                       id SERIAL PRIMARY KEY,
                                       name VARCHAR(255) DEFAULT NULL,
                                       price VARCHAR(255) DEFAULT NULL,
                                       quantity INT DEFAULT NULL,
                                       order_id BIGINT REFERENCES tb_order (id)
);
