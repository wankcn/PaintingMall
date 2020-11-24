
CREATE DATABASE  IF NOT EXISTS `shop`  DEFAULT CHARACTER SET utf8 ;

USE `shop`;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(20) DEFAULT NULL,
  `cdesc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;



/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(20) DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `filename` varchar(30) DEFAULT NULL,
  `path` varchar(200) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `fk_0001` (`cid`),
  CONSTRAINT `fk_0001` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;



/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`uid`,`username`,`password`) values (1,'aaa','123'),(2,'imooc','123456');

