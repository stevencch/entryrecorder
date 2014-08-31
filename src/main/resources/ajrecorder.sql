-- phpMyAdmin SQL Dump
-- version 4.1.4
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 28, 2014 at 09:30 AM
-- Server version: 5.6.15-log
-- PHP Version: 5.4.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ajrecorder`
--

-- --------------------------------------------------------

--
-- Table structure for table `additive`
--

CREATE TABLE IF NOT EXISTS `additive` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Company` varchar(50) DEFAULT NULL,
  `Grade` varchar(25) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `additive`
--

INSERT INTO `additive` (`Id`, `Company`, `Grade`, `Description`) VALUES
(4, 'A Company 1', 'Grade A', 'A1'),
(5, 'A Company 2', 'Grade B ', 'a2');

-- --------------------------------------------------------

--
-- Table structure for table `checkitem`
--

CREATE TABLE IF NOT EXISTS `checkitem` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `checkitem`
--

INSERT INTO `checkitem` (`Id`, `Description`) VALUES
(3, 'New'),
(4, 'New'),
(5, 'New'),
(6, 'New'),
(7, 'New'),
(8, 'New33'),
(9, 'New'),
(10, 'New'),
(11, 'New'),
(12, 'New'),
(13, 'ssss'),
(14, 'ssswwwee'),
(15, 'eewewrrr'),
(16, 'qwew');

-- --------------------------------------------------------

--
-- Table structure for table `entry`
--

CREATE TABLE IF NOT EXISTS `entry` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Shift` varchar(25) NOT NULL,
  `MachineId` int(11) DEFAULT NULL,
  `MouldId` int(11) DEFAULT NULL,
  `ProductId` int(11) DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL,
  `WeightMin` float DEFAULT NULL,
  `WeightMax` float DEFAULT NULL,
  `TapPositionMin` float DEFAULT NULL,
  `TapPositionMax` float DEFAULT NULL,
  `ThreadBoreMin` float DEFAULT NULL,
  `ThreadBoreMax` float DEFAULT NULL,
  `ThreadNeckMin` float DEFAULT NULL,
  `ThreadNeckMax` float DEFAULT NULL,
  `InUse` varchar(25) DEFAULT 'YES',
  `WallUnderHandleMin` float DEFAULT NULL,
  `WallUnderHandleMax` float DEFAULT NULL,
  `WallBaseMin` float DEFAULT NULL,
  `WallBaseMax` float DEFAULT NULL,
  `WallClosureMin` float DEFAULT NULL,
  `WallClosureMax` float DEFAULT NULL,
  `WallHandleBungMin` float DEFAULT NULL,
  `WallHandleBungMax` float DEFAULT NULL,
  `WallHandleLeftMin` float DEFAULT NULL,
  `WallHandleLeftMax` float DEFAULT NULL,
  `WallHandleRightMin` float DEFAULT NULL,
  `WallHandleRightMax` float DEFAULT NULL,
  `Supervisor1` int(11) DEFAULT NULL,
  `Supervisor2` int(11) DEFAULT NULL,
  `Supervisor3` int(11) DEFAULT NULL,
  `Technician1` int(11) DEFAULT NULL,
  `Technician2` int(11) DEFAULT NULL,
  `Technician3` int(11) DEFAULT NULL,
  `Worker1` int(11) DEFAULT NULL,
  `Worker2` int(11) DEFAULT NULL,
  `Worker3` int(11) DEFAULT NULL,
  `PolymerId` int(11) DEFAULT NULL,
  `AdditiveAId` int(11) DEFAULT NULL,
  `AdditiveBId` int(11) DEFAULT NULL,
  `AdditiveCId` int(11) DEFAULT NULL,
  `AdditiveABatchA` varchar(25) DEFAULT NULL,
  `AdditiveBBatchA` varchar(25) DEFAULT NULL,
  `AdditiveCBatchA` varchar(25) DEFAULT NULL,
  `AdditiveABatchB` varchar(25) DEFAULT NULL,
  `AdditiveBBatchB` varchar(25) DEFAULT NULL,
  `AdditiveCBatchB` varchar(25) DEFAULT NULL,
  `PolymerBatchA` varchar(25) DEFAULT NULL,
  `PolymerBatchB` varchar(25) DEFAULT NULL,
  `PalletQuantity` int(11) DEFAULT NULL,
  `PalletProducedA` int(11) DEFAULT NULL,
  `PalletProducedB` int(11) DEFAULT NULL,
  `OtherQuantity` int(11) DEFAULT NULL,
  `IsChecked` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `MachineId` (`MachineId`),
  KEY `MouldId` (`MouldId`),
  KEY `ProductId` (`ProductId`),
  KEY `Supervisor1` (`Supervisor1`),
  KEY `Supervisor2` (`Supervisor2`),
  KEY `Supervisor3` (`Supervisor3`),
  KEY `Technician1` (`Technician1`),
  KEY `Technician2` (`Technician2`),
  KEY `Technician3` (`Technician3`),
  KEY `Worker1` (`Worker1`),
  KEY `Worker2` (`Worker2`),
  KEY `Worker3` (`Worker3`),
  KEY `PolymerId` (`PolymerId`),
  KEY `AdditiveAId` (`AdditiveAId`),
  KEY `AdditiveBId` (`AdditiveBId`),
  KEY `AdditiveCId` (`AdditiveCId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `entry`
--

INSERT INTO `entry` (`Id`, `Shift`, `MachineId`, `MouldId`, `ProductId`, `CreateDate`, `WeightMin`, `WeightMax`, `TapPositionMin`, `TapPositionMax`, `ThreadBoreMin`, `ThreadBoreMax`, `ThreadNeckMin`, `ThreadNeckMax`, `InUse`, `WallUnderHandleMin`, `WallUnderHandleMax`, `WallBaseMin`, `WallBaseMax`, `WallClosureMin`, `WallClosureMax`, `WallHandleBungMin`, `WallHandleBungMax`, `WallHandleLeftMin`, `WallHandleLeftMax`, `WallHandleRightMin`, `WallHandleRightMax`, `Supervisor1`, `Supervisor2`, `Supervisor3`, `Technician1`, `Technician2`, `Technician3`, `Worker1`, `Worker2`, `Worker3`, `PolymerId`, `AdditiveAId`, `AdditiveBId`, `AdditiveCId`, `AdditiveABatchA`, `AdditiveBBatchA`, `AdditiveCBatchA`, `AdditiveABatchB`, `AdditiveBBatchB`, `AdditiveCBatchB`, `PolymerBatchA`, `PolymerBatchB`, `PalletQuantity`, `PalletProducedA`, `PalletProducedB`, `OtherQuantity`, `IsChecked`) VALUES
(3, 'shift', 15, 8, 21, '2014-08-23 11:40:10', 2, 6, 5, 10, 4, 8, 4, 8, 'YES', 2, 22, 3, 33, 4, 44, 5, 55, 6, 66, 7, 77, 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4, 4, 4, NULL, '11', '22', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
(4, 'shift', 17, 8, 20, '2014-08-23 11:40:58', 1, 7, 5, 10, 4, 8, 4, 8, 'YES', 1, 12, 2, 13, 3, 14, 4, 15, 5, 16, 6, 17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, 4, 4, 5, '11', '22', '33', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `machine`
--

CREATE TABLE IF NOT EXISTS `machine` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `MachineNo` varchar(25) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Year` varchar(25) DEFAULT NULL,
  `SerialNo` varchar(25) DEFAULT NULL,
  `Capacity` varchar(25) DEFAULT NULL,
  `Manufacturer` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

--
-- Dumping data for table `machine`
--

INSERT INTO `machine` (`Id`, `MachineNo`, `Description`, `Year`, `SerialNo`, `Capacity`, `Manufacturer`) VALUES
(15, 'Machine 1', 'Machine 1', '2000', 'aaa1', '18', 'Machine 1 manufacturer'),
(16, 'Machine 2', 'Machine 2', '2010', 'a2', '34', 'Machine 2 manufacturer'),
(17, '3', 'sdd', '2000', 'asasdas', '100', 'ds');

-- --------------------------------------------------------

--
-- Table structure for table `mould`
--

CREATE TABLE IF NOT EXISTS `mould` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(25) DEFAULT NULL,
  `Code` varchar(25) NOT NULL,
  `Volumn` varchar(25) DEFAULT NULL,
  `Manufacturer` varchar(25) DEFAULT NULL,
  `Year` varchar(25) DEFAULT NULL,
  `ImageDrawing` varchar(50) DEFAULT NULL,
  `ImageNonDg` varchar(50) DEFAULT NULL,
  `ImageDg` varchar(50) DEFAULT NULL,
  `ImageBoreA` varchar(50) DEFAULT NULL,
  `ImageBoreB` varchar(50) DEFAULT NULL,
  `ImageNeck` varchar(50) DEFAULT NULL,
  `ImageTap` varchar(50) DEFAULT NULL,
  `WeightNonDgMin` float DEFAULT NULL,
  `WeightNonDgMax` float DEFAULT NULL,
  `WeightDgMin` float DEFAULT NULL,
  `WeightDgMax` float DEFAULT NULL,
  `TapPositionMin` float DEFAULT NULL,
  `TapPositionMax` float DEFAULT NULL,
  `WallNonDgUnderHandleMin` float DEFAULT NULL,
  `WallNonDgUnderHandleMax` float DEFAULT NULL,
  `WallNonDgBaseMin` float DEFAULT NULL,
  `WallNonDgBaseMax` float DEFAULT NULL,
  `WallNonDgClosureMin` float DEFAULT NULL,
  `WallNonDgClosureMax` float DEFAULT NULL,
  `WallNonDgHandleBungMin` float DEFAULT NULL,
  `WallNonDgHandleBungMax` float DEFAULT NULL,
  `WallNonDgHandleLeftMin` float DEFAULT NULL,
  `WallNonDgHandleLeftMax` float DEFAULT NULL,
  `WallNonDgHandleRightMin` float DEFAULT NULL,
  `WallNonDgHandleRightMax` float DEFAULT NULL,
  `WallDgUnderHandleMin` float DEFAULT NULL,
  `WallDgUnderHandleMax` float DEFAULT NULL,
  `WallDgBaseMin` float DEFAULT NULL,
  `WallDgBaseMax` float DEFAULT NULL,
  `WallDgClosureMin` float DEFAULT NULL,
  `WallDgClosureMax` float DEFAULT NULL,
  `WallDgHandleBungMin` float DEFAULT NULL,
  `WallDgHandleBungMax` float DEFAULT NULL,
  `WallDgHandleLeftMin` float DEFAULT NULL,
  `WallDgHandleLeftMax` float DEFAULT NULL,
  `WallDgHandleRightMin` float DEFAULT NULL,
  `WallDgHandleRightMax` float DEFAULT NULL,
  `ThreadBoreASize1` varchar(25) DEFAULT NULL,
  `ThreadBoreASize2` varchar(25) DEFAULT NULL,
  `ThreadBoreASize3` varchar(25) DEFAULT NULL,
  `ThreadBoreBSize1` varchar(25) DEFAULT NULL,
  `ThreadBoreBSize2` varchar(25) DEFAULT NULL,
  `ThreadBoreBSize3` varchar(25) DEFAULT NULL,
  `ThreadBoreAMin1` float DEFAULT NULL,
  `ThreadBoreAMin2` float DEFAULT NULL,
  `ThreadBoreAMin3` float DEFAULT NULL,
  `ThreadBoreAMax1` float DEFAULT NULL,
  `ThreadBoreAMax2` float DEFAULT NULL,
  `ThreadBoreAMax3` float DEFAULT NULL,
  `ThreadBoreBMin1` float DEFAULT NULL,
  `ThreadBoreBMin2` float DEFAULT NULL,
  `ThreadBoreBMin3` float DEFAULT NULL,
  `ThreadBoreBMax1` float DEFAULT NULL,
  `ThreadBoreBMax2` float DEFAULT NULL,
  `ThreadBoreBMax3` float DEFAULT NULL,
  `ThreadNeckSize1` varchar(25) DEFAULT NULL,
  `ThreadNeckSize2` varchar(25) DEFAULT NULL,
  `ThreadNeckSize3` varchar(25) DEFAULT NULL,
  `ThreadNeckMin1` float DEFAULT NULL,
  `ThreadNeckMin2` float DEFAULT NULL,
  `ThreadNeckMin3` float DEFAULT NULL,
  `ThreadNeckMax1` float DEFAULT NULL,
  `ThreadNeckMax2` float DEFAULT NULL,
  `ThreadNeckMax3` float DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `mould`
--

INSERT INTO `mould` (`Id`, `Name`, `Code`, `Volumn`, `Manufacturer`, `Year`, `ImageDrawing`, `ImageNonDg`, `ImageDg`, `ImageBoreA`, `ImageBoreB`, `ImageNeck`, `ImageTap`, `WeightNonDgMin`, `WeightNonDgMax`, `WeightDgMin`, `WeightDgMax`, `TapPositionMin`, `TapPositionMax`, `WallNonDgUnderHandleMin`, `WallNonDgUnderHandleMax`, `WallNonDgBaseMin`, `WallNonDgBaseMax`, `WallNonDgClosureMin`, `WallNonDgClosureMax`, `WallNonDgHandleBungMin`, `WallNonDgHandleBungMax`, `WallNonDgHandleLeftMin`, `WallNonDgHandleLeftMax`, `WallNonDgHandleRightMin`, `WallNonDgHandleRightMax`, `WallDgUnderHandleMin`, `WallDgUnderHandleMax`, `WallDgBaseMin`, `WallDgBaseMax`, `WallDgClosureMin`, `WallDgClosureMax`, `WallDgHandleBungMin`, `WallDgHandleBungMax`, `WallDgHandleLeftMin`, `WallDgHandleLeftMax`, `WallDgHandleRightMin`, `WallDgHandleRightMax`, `ThreadBoreASize1`, `ThreadBoreASize2`, `ThreadBoreASize3`, `ThreadBoreBSize1`, `ThreadBoreBSize2`, `ThreadBoreBSize3`, `ThreadBoreAMin1`, `ThreadBoreAMin2`, `ThreadBoreAMin3`, `ThreadBoreAMax1`, `ThreadBoreAMax2`, `ThreadBoreAMax3`, `ThreadBoreBMin1`, `ThreadBoreBMin2`, `ThreadBoreBMin3`, `ThreadBoreBMax1`, `ThreadBoreBMax2`, `ThreadBoreBMax3`, `ThreadNeckSize1`, `ThreadNeckSize2`, `ThreadNeckSize3`, `ThreadNeckMin1`, `ThreadNeckMin2`, `ThreadNeckMin3`, `ThreadNeckMax1`, `ThreadNeckMax2`, `ThreadNeckMax3`) VALUES
(8, 'Mould 1', 'Mould-1', '100', 'mould manufacturer', '2000', '\\images\\Koala.jpg', '\\images\\Desert.jpg', '\\images\\Chrysanthemum.jpg', '\\images\\Tulips.jpg', '\\images\\Tulips.jpg', NULL, '\\images\\Penguins.jpg', 10, 50, 20, 60, 5, 10, 1, 12, 2, 13, 3, 14, 4, 15, 5, 16, 6, 17, 2, 22, 3, 33, 4, 44, 5, 55, 6, 66, 7, 77, 'A1', '20', '30', 'B1', '50', '60', 5, 2, 3, 9, 6, 7, 4, 5, 1, 8, 9, 5, 'aaa1', '22', '33', 2, 3, 4, 6, 7, 8),
(9, 'Mould 2', 'Mould-2', '11', 'mould 2 manufacturer', '2001', '\\images\\Chrysanthemum.jpg', '\\images\\Koala.jpg', '\\images\\Jellyfish.jpg', NULL, NULL, NULL, NULL, 5, 14, 3, 9, 1, 9, 4, 7, 5, 8, 6, 9, 3, 6, 2, 5, 3, 6, 4, 6, 5, 8, 6, 9, 2, 5, 3, 6, 4, 7, '11', '22', '33', '44', '55', '66', 1, 2, 3, 6, 7, 8, 4, 2, 3, 7, 5, 7, '77', '88', '99', 8, 3, 4, 9, 5, 8);

-- --------------------------------------------------------

--
-- Table structure for table `polymer`
--

CREATE TABLE IF NOT EXISTS `polymer` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Company` varchar(50) DEFAULT NULL,
  `Grade` varchar(25) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `polymer`
--

INSERT INTO `polymer` (`Id`, `Company`, `Grade`, `Description`) VALUES
(4, 'P Company 1', 'Grade A', 'p1'),
(5, 'P Company 2', 'Grade B', 'p2');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Code` varchar(25) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `MouldId` int(11) DEFAULT NULL,
  `WeightMin` float DEFAULT NULL,
  `WeightMax` float DEFAULT NULL,
  `Bung` varchar(25) DEFAULT NULL,
  `Pierced` varchar(25) DEFAULT NULL,
  `PolymerId` int(11) DEFAULT NULL,
  `AdditiveAId` int(11) DEFAULT NULL,
  `AdditiveBId` int(11) DEFAULT NULL,
  `AdditiveCId` int(11) DEFAULT NULL,
  `AdditiveAPercentage` varchar(25) DEFAULT NULL,
  `AdditiveBPercentage` varchar(25) DEFAULT NULL,
  `AdditiveCPercentage` varchar(25) DEFAULT NULL,
  `ThreadBoreA` int(11) DEFAULT NULL,
  `ThreadBoreB` int(11) DEFAULT NULL,
  `ThreadNeck` int(11) DEFAULT NULL,
  `DGNONDG` int(11) DEFAULT NULL,
  `ViewLine` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `MouldId` (`MouldId`),
  KEY `PolymerId` (`PolymerId`),
  KEY `AdditiveAId` (`AdditiveAId`),
  KEY `AdditiveBId` (`AdditiveBId`),
  KEY `AdditiveCId` (`AdditiveCId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`Id`, `Code`, `Description`, `MouldId`, `WeightMin`, `WeightMax`, `Bung`, `Pierced`, `PolymerId`, `AdditiveAId`, `AdditiveBId`, `AdditiveCId`, `AdditiveAPercentage`, `AdditiveBPercentage`, `AdditiveCPercentage`, `ThreadBoreA`, `ThreadBoreB`, `ThreadNeck`, `DGNONDG`, `ViewLine`) VALUES
(20, 'Product-1-2', 'Product-1-2', 8, 1, 7, 'NO', 'NO', 5, 4, 4, 5, '11', '22', '33', 1, 1, 3, 1, NULL),
(21, 'Product-1-1', 'Product-1-1', 8, 2, 6, 'YES', 'NO', 4, 4, 4, NULL, '11', '22', NULL, 1, 1, 3, 0, NULL),
(22, 'Product-2-1', 'Product-2-1', 9, 3, 8, 'NO', 'NO', 4, 4, 4, 4, '55', '22', '28', 1, 1, 2, 0, NULL),
(23, 'Product Code3', NULL, 8, NULL, NULL, 'YES', 'YES', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `productcheck`
--

CREATE TABLE IF NOT EXISTS `productcheck` (
  `Product` int(11) NOT NULL,
  `CheckItem` int(11) NOT NULL,
  PRIMARY KEY (`Product`,`CheckItem`),
  KEY `CheckItem` (`CheckItem`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `productcheck`
--

INSERT INTO `productcheck` (`Product`, `CheckItem`) VALUES
(20, 3),
(21, 3),
(20, 5),
(21, 5),
(20, 6),
(21, 6),
(20, 15),
(21, 15);

-- --------------------------------------------------------

--
-- Table structure for table `record`
--

CREATE TABLE IF NOT EXISTS `record` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `RecordKey` varchar(25) DEFAULT NULL,
  `NumberValue` float DEFAULT NULL,
  `StringValue` varchar(25) DEFAULT NULL,
  `CreatedTime` datetime DEFAULT NULL,
  `EntryId` int(11) DEFAULT NULL,
  `Staff` varchar(25) DEFAULT NULL,
  `IsPass` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `EntryId` (`EntryId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `record`
--

INSERT INTO `record` (`Id`, `RecordKey`, `NumberValue`, `StringValue`, `CreatedTime`, `EntryId`, `Staff`, `IsPass`) VALUES
(1, 'LEAK_FILL', 0, 'aaa', '2014-08-28 10:53:50', 3, '', ''),
(2, 'LEAK_CHECK', 0, 'rrr', '2014-08-28 10:53:59', 3, '', ''),
(3, 'ANY_LEAK', 0, 'YES', '2014-08-28 10:53:59', 3, '', ''),
(4, 'LEAK_NOTES', 0, 'sdfdsf', '2014-08-28 10:53:59', 3, '', '');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE IF NOT EXISTS `staff` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(25) NOT NULL,
  `JobType` varchar(25) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`Id`, `Name`, `JobType`) VALUES
(1, 'WORKER A', 'PROCESS WORKER'),
(2, 'WORK B', 'PROCESS WORKER'),
(3, 'TECHNICIAN A', 'TECHNICIAN'),
(4, 'TECHNICIAN B', 'TECHNICIAN'),
(5, 'SUPERVISOR A', 'SUPERVISOR'),
(6, 'SUPERVISOR B', 'SUPERVISOR');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `entry`
--
ALTER TABLE `entry`
  ADD CONSTRAINT `entry_ibfk_1` FOREIGN KEY (`MachineId`) REFERENCES `machine` (`Id`),
  ADD CONSTRAINT `entry_ibfk_10` FOREIGN KEY (`Worker1`) REFERENCES `staff` (`Id`),
  ADD CONSTRAINT `entry_ibfk_11` FOREIGN KEY (`Worker2`) REFERENCES `staff` (`Id`),
  ADD CONSTRAINT `entry_ibfk_12` FOREIGN KEY (`Worker3`) REFERENCES `staff` (`Id`),
  ADD CONSTRAINT `entry_ibfk_13` FOREIGN KEY (`PolymerId`) REFERENCES `polymer` (`Id`),
  ADD CONSTRAINT `entry_ibfk_14` FOREIGN KEY (`AdditiveAId`) REFERENCES `additive` (`Id`),
  ADD CONSTRAINT `entry_ibfk_15` FOREIGN KEY (`AdditiveBId`) REFERENCES `additive` (`Id`),
  ADD CONSTRAINT `entry_ibfk_16` FOREIGN KEY (`AdditiveCId`) REFERENCES `additive` (`Id`),
  ADD CONSTRAINT `entry_ibfk_2` FOREIGN KEY (`MouldId`) REFERENCES `mould` (`Id`),
  ADD CONSTRAINT `entry_ibfk_3` FOREIGN KEY (`ProductId`) REFERENCES `product` (`Id`),
  ADD CONSTRAINT `entry_ibfk_4` FOREIGN KEY (`Supervisor1`) REFERENCES `staff` (`Id`),
  ADD CONSTRAINT `entry_ibfk_5` FOREIGN KEY (`Supervisor2`) REFERENCES `staff` (`Id`),
  ADD CONSTRAINT `entry_ibfk_6` FOREIGN KEY (`Supervisor3`) REFERENCES `staff` (`Id`),
  ADD CONSTRAINT `entry_ibfk_7` FOREIGN KEY (`Technician1`) REFERENCES `staff` (`Id`),
  ADD CONSTRAINT `entry_ibfk_8` FOREIGN KEY (`Technician2`) REFERENCES `staff` (`Id`),
  ADD CONSTRAINT `entry_ibfk_9` FOREIGN KEY (`Technician3`) REFERENCES `staff` (`Id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`MouldId`) REFERENCES `mould` (`Id`),
  ADD CONSTRAINT `product_ibfk_2` FOREIGN KEY (`PolymerId`) REFERENCES `polymer` (`Id`),
  ADD CONSTRAINT `product_ibfk_3` FOREIGN KEY (`AdditiveAId`) REFERENCES `additive` (`Id`),
  ADD CONSTRAINT `product_ibfk_4` FOREIGN KEY (`AdditiveBId`) REFERENCES `additive` (`Id`),
  ADD CONSTRAINT `product_ibfk_5` FOREIGN KEY (`AdditiveCId`) REFERENCES `additive` (`Id`);

--
-- Constraints for table `productcheck`
--
ALTER TABLE `productcheck`
  ADD CONSTRAINT `productcheck_ibfk_1` FOREIGN KEY (`Product`) REFERENCES `product` (`Id`),
  ADD CONSTRAINT `productcheck_ibfk_2` FOREIGN KEY (`CheckItem`) REFERENCES `checkitem` (`Id`);

--
-- Constraints for table `record`
--
ALTER TABLE `record`
  ADD CONSTRAINT `record_ibfk_2` FOREIGN KEY (`EntryId`) REFERENCES `entry` (`Id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
