-- We making sure that no table with those name exist before we start creating a new tables
DROP TABLE ActiveCart, Customer, Desserts, Drinks, Product;
-- Create Customer table
CREATE TABLE Customer (
    customerId INT PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255)
);
-- Create Drinks table
CREATE TABLE Drinks (
    productId INT PRIMARY KEY,
    name VARCHAR(255),
    size INT
);
-- Create Desserts table
CREATE TABLE Desserts (
    productId INT PRIMARY KEY,
    name VARCHAR(255),
    isGlutenFree BOOLEAN
);
-- Create ActiveCart table
CREATE TABLE ActiveCart (
    orderId INT PRIMARY KEY,
    customerId INT,
    productId INT,
    FOREIGN KEY (customerId) REFERENCES Customer(customerId)
);
-- We need to change a foreign key so we can delete this table from the java application when we want to save new database
ALTER TABLE `ActiveCart` DROP FOREIGN KEY `activecart_ibfk_1`;
ALTER TABLE `ActiveCart` ADD CONSTRAINT `activecart_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`) ON DELETE CASCADE;