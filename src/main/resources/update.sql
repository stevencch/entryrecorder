ALTER TABLE `entry` ADD `Material` VARCHAR(2000) NULL ;

ALTER TABLE `product` ADD `ThreadBoreA1` INT NULL , ADD `ThreadBoreB1` INT NULL , ADD `ThreadNeck1` INT NULL , ADD `ClosureType` VARCHAR(25) NULL , ADD `ProductImage` VARCHAR(50) NULL ;

ALTER TABLE `entry` CHANGE `ThreadBoreMin` `ThreadBoreBMin` FLOAT NULL DEFAULT NULL;

ALTER TABLE `entry` CHANGE `ThreadBoreMax` `ThreadBoreBMax` FLOAT NULL DEFAULT NULL;

ALTER TABLE `entry` ADD `ThreadBoreAMin` FLOAT NULL , ADD `ThreadBoreAMax` FLOAT NULL , ADD `ThreadBoreAMin1` FLOAT NULL , ADD `ThreadBoreAMax1` FLOAT NULL , ADD `ThreadBoreBMin1` FLOAT NULL , ADD `ThreadBoreBMax1` FLOAT NULL , ADD `ThreadNeckMin1` FLOAT NULL , ADD `ThreadNeckMax1` FLOAT NULL ;











