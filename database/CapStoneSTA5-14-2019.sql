CREATE DATABASE `capstonesta` /*!40100 DEFAULT CHARACTER SET latin1 */;
CREATE TABLE `child` (
	  `idchild` int(101) NOT NULL DEFAULT '1',
	  `first_name` varchar(15) DEFAULT 'test',
	  `last_name` varchar(20) DEFAULT 'test',
	  `city` varchar(20) DEFAULT 'test',
	  `street` varchar(20) DEFAULT 'test',
	  `zip` int(5) DEFAULT '425',
	  `state` varchar(2) DEFAULT 'te',
	  `housenum` int(6) DEFAULT '1234',
	  `dir` varchar(2) DEFAULT NULL,
	  PRIMARY KEY (`idchild`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
