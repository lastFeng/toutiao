DROP DATABASE IF EXISTS toutiao;
CREATE DATABASE toutiao DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

use toutiao;
DELETE FROM user WHERE User='toutiao';
flush PRIVILEGES;

grant all privileges on wsp.* to 'toutiao'@'%' identified by 'guoweifeng';
grant all privileges on wsp.* to 'toutiao'@'localhost' identified by 'guoweifeng';
grant all privileges on wsp.* to 'toutiao'@'127.0.0.1' identified by 'guoweifeng';
flush privileges;