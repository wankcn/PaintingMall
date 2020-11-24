/*
SQLyog v10.2 
MySQL - 5.5.27 : Database - shop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shop` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `shop`;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(20) DEFAULT NULL,
  `cdesc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`cid`,`cname`,`cdesc`) values (1,'人物画','描述人物的画'),(2,'风景画','风景画'),(3,'动物画','描述动物的画');

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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`pid`,`pname`,`author`,`price`,`description`,`filename`,`path`,`cid`) values (5,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/1.jpg',1),(6,'bbb','作者1',1000,'人物的画','2.jpg','/shop/upload/2.jpg',2),(7,'ccc','作者1',1000,'人物的画','3.jpg','/shop/upload/3.jpg',3),(8,'ddd','作者1',1000,'人物的画','4.jpg','/shop/upload/4.jpg',1),(9,'eee','作者1',1000,'人物的画','5.jpg','/shop/upload/5.jpg',2),(10,'fff','作者1',1000,'人物的画','1.jpg','/shop/upload/6.jpg',3),(11,'abc','作者1',1000,'人物的画','1.jpg','/shop/upload/1.jpg',1),(12,'bcd','作者1',1000,'人物的画','1.jpg','/shop/upload/2.jpg',2),(13,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/3.jpg',3),(14,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/4.jpg',1),(15,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/2.jpg',2),(16,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/1.jpg',3),(17,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/4.jpg',1),(18,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/5.jpg',2),(19,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/6.jpg',3),(20,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/1.jpg',1),(21,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/3.jpg',2),(22,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/1.jpg',3),(23,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/1.jpg',1),(24,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/1.jpg',2),(25,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/1.jpg',3),(26,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/1.jpg',1),(27,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/1.jpg',1),(28,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/1.jpg',1),(29,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/1.jpg',1),(30,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/1.jpg',1),(31,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/1.jpg',1),(32,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/1.jpg',1),(33,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/1.jpg',1),(34,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/1.jpg',1),(35,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/1.jpg',1),(36,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/1.jpg',1),(37,'aaa','作者1',1000,'人物的画','1.jpg','/shop/upload/1.jpg',1);

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

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
