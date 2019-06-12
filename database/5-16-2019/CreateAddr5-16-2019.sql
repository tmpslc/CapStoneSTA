CREATE TABLE `addr` (
  `idaddr` int(101) NOT NULL,
  `housenum` int(6) DEFAULT NULL,
  `street` varchar(20) DEFAULT NULL,
  `zip` int(6) DEFAULT NULL,
  PRIMARY KEY (`idaddr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
