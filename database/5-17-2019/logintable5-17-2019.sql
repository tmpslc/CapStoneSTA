CREATE TABLE `login` (
  `loginAttempt` int(101) NOT NULL AUTO_INCREMENT,
  `entered_name` varchar(20) DEFAULT NULL,
  `successful` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`loginAttempt`),
  KEY `foreign_username_idx` (`entered_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
