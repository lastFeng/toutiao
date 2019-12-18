
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` varchar(32) NOT NULL,
    `name` varchar(64),
    `password` varchar(128),
    `salt` varchar(32),
    `head_url` varchar(256)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`(
    `id` varchar(32) NOT NULL,
    `title` varchar(128),
    `link` varchar(256),
    `image` varchar(256),
    `like_count` int NOT NULL DEFAULT 0,
    `comment_count` int DEFAULT 0,
    `create_date` datetime,
    `user_id` varchar(32)
)ENGINE=InnoDB;
