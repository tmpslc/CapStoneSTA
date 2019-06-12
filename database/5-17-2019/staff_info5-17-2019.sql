CREATE TABLE `staff_info` (
  `idstaff` int(101) NOT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `phonenumber` float DEFAULT NULL,
  `username` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idstaff`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
