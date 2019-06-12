CREATE TABLE `child_address` (
  `idchild` int(11) NOT NULL,
  `idaddr` int(11) NOT NULL,
  KEY `foreign_idchild_idx` (`idchild`),
  KEY `foreign_idaddr_idx` (`idaddr`),
  CONSTRAINT `foreign_idaddr` FOREIGN KEY (`idaddr`) REFERENCES `addr` (`idaddr`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `foreign_idchild` FOREIGN KEY (`idchild`) REFERENCES `child` (`idchild`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
