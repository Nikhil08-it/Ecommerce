-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 09, 2023 at 10:26 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inventoryms`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `adminId` int(11) NOT NULL,
  `adminFname` varchar(255) DEFAULT NULL,
  `adminLname` varchar(255) DEFAULT NULL,
  `adminUsername` varchar(50) DEFAULT NULL,
  `adminPass` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`adminId`, `adminFname`, `adminLname`, `adminUsername`, `adminPass`) VALUES
(1, 'Haifa', 'Nasuha', 'admin', 'admin'),
(2, 'Aliaa', 'Aqilah', 'admin2', 'admin2');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `catId` int(11) NOT NULL,
  `catName` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`catId`, `catName`) VALUES
(1, 'Fruit'),
(2, 'Dairy'),
(3, 'Meat'),
(4, 'Seafood'),
(5, 'Vegetable');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `orderId` int(11) NOT NULL,
  `orderDate` date DEFAULT current_timestamp(),
  `orderCreatedby` int(11) DEFAULT NULL,
  `orderStatus` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`orderId`, `orderDate`, `orderCreatedby`, `orderStatus`) VALUES
(1, '2023-01-01', 1, 'Paid'),
(2, '2023-01-01', 2, 'Paid'),
(3, '2023-01-02', 1, 'Paid'),
(7, '2023-01-02', 2, 'Paid'),
(8, '2023-01-03', 1, 'Paid'),
(9, '2023-01-03', 1, 'Paid'),
(10, '2023-01-04', 3, 'Paid'),
(11, '2023-01-05', 2, 'Paid'),
(12, '2023-01-05', 3, 'Paid'),
(13, '2023-01-06', 1, 'Paid'),
(14, '2023-01-06', 1, 'Paid'),
(15, '2023-01-07', 1, 'Unpaid'),
(16, '2023-01-07', 1, 'Paid'),
(17, '2023-01-08', 1, 'Paid'),
(18, '2023-01-08', 1, 'Paid'),
(19, '2023-01-09', 1, 'Paid'),
(20, '2023-01-09', 1, 'Unpaid');

-- --------------------------------------------------------

--
-- Table structure for table `orders_product`
--

CREATE TABLE `orders_product` (
  `orderprodId` int(11) NOT NULL,
  `orderId` int(11) DEFAULT NULL,
  `prodId` int(11) DEFAULT NULL,
  `orderQty` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders_product`
--

INSERT INTO `orders_product` (`orderprodId`, `orderId`, `prodId`, `orderQty`) VALUES
(2, 1, 14, 6),
(3, 2, 11, 10),
(4, 2, 10, 5),
(8, 3, 3, 10),
(9, 1, 7, 5),
(10, 1, 3, 5),
(13, 3, 2, 5),
(15, 3, 5, 3),
(16, 1, 9, 3),
(19, 7, 5, 5),
(20, 7, 7, 5),
(22, 9, 14, 3),
(23, 9, 11, 3),
(24, 10, 4, 4),
(25, 10, 6, 5),
(26, 11, 10, 6),
(27, 11, 9, 10),
(28, 11, 4, 7),
(29, 12, 6, 4),
(30, 12, 11, 2),
(31, 12, 1, 10),
(32, 11, 1, 10),
(43, 2, 1, 4),
(45, 8, 3, 5),
(46, 8, 5, 1),
(50, 7, 1, 1),
(53, 13, 7, 3),
(54, 13, 4, 3),
(55, 14, 6, 5),
(56, 14, 1, 1),
(57, 14, 5, 12),
(58, 14, 13, 4),
(59, 7, 3, 4),
(61, 15, 1, 7),
(62, 15, 3, 5),
(63, 15, 11, 5),
(64, 15, 9, 4),
(65, 16, 1, 6),
(66, 16, 9, 7),
(67, 16, 8, 8),
(68, 1, 1, 10),
(69, 17, 8, 2),
(70, 17, 11, 5),
(71, 17, 17, 5),
(72, 18, 9, 6),
(73, 18, 8, 6),
(74, 19, 15, 11);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `prodId` int(11) NOT NULL,
  `prodName` varchar(50) DEFAULT NULL,
  `prodQty` int(11) DEFAULT NULL,
  `prodPrice` float NOT NULL,
  `prodUnit` varchar(30) NOT NULL,
  `prodDateAdded` date DEFAULT current_timestamp(),
  `prodAdded` int(11) DEFAULT NULL,
  `catId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`prodId`, `prodName`, `prodQty`, `prodPrice`, `prodUnit`, `prodDateAdded`, `prodAdded`, `catId`) VALUES
(1, 'Oranges', 0, 1.2, 'pcs', '2022-12-25', 1, 1),
(2, 'Apples', 0, 1.5, 'pcs', '2022-12-25', 1, 1),
(3, 'Bananas', 0, 2.5, 'kg', '2022-12-25', 1, 1),
(4, 'Lettuce', 14, 2, 'pack', '2022-12-25', 1, 5),
(5, 'Tomatoes', 6, 3, 'pack', '2022-12-25', 1, 5),
(6, 'Celery', 0, 1.5, 'pack', '2022-12-25', 1, 5),
(7, 'Cucumber', 10, 0.6, 'kg', '2022-12-25', 1, 5),
(8, 'Milk', 134, 5.5, 'pcs', '2022-12-25', 1, 2),
(9, 'Cheese', 163, 7.9, 'pcs', '2022-12-25', 1, 2),
(10, 'Eggs', 250, 4.8, 'pack', '2022-12-25', 1, 2),
(11, 'Yogurt', 110, 4.5, 'pcs', '2022-12-25', 1, 2),
(12, 'Salmon', 0, 18.9, 'kg', '2022-12-25', 1, 4),
(13, 'Beef', 196, 25.5, 'kg', '2022-12-25', 1, 3),
(14, 'Crab', 239, 31, 'kg', '2022-12-25', 1, 4),
(15, 'Tuna', 489, 12.5, 'kg', '2022-12-25', 1, 4),
(16, 'Mackerel', 220, 13, 'kg', '2022-12-25', 1, 4),
(17, 'Chicken', 195, 9, 'kg', '2022-12-25', 1, 3),
(34, 'Mango', 90, 5, 'kg', '2023-01-08', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `staffId` int(11) NOT NULL,
  `staffFname` varchar(50) DEFAULT NULL,
  `staffLname` varchar(50) DEFAULT NULL,
  `staffAddress` varchar(255) DEFAULT NULL,
  `staffPass` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staffId`, `staffFname`, `staffLname`, `staffAddress`, `staffPass`) VALUES
(1, 'Muhamad', 'Ashraff', 'No 6, Jalan Pelangi 32, Taman Pelangi, 51500, Cheras, WP Kuala Lumpur', 'Test1'),
(2, 'Muhammad', 'Hadif', '1016, Jalan Sultan Ismail, 51500, WP Kuala Lumpur', 'Test2'),
(3, 'Ahmad', 'Ali', 'TRG', 'Test789');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`adminId`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`catId`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderId`),
  ADD KEY `orderCreated` (`orderCreatedby`);

--
-- Indexes for table `orders_product`
--
ALTER TABLE `orders_product`
  ADD PRIMARY KEY (`orderprodId`),
  ADD KEY `product` (`prodId`),
  ADD KEY `order` (`orderId`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`prodId`),
  ADD KEY `category` (`catId`),
  ADD KEY `add` (`prodAdded`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`staffId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `adminId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `catId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `orderId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `orders_product`
--
ALTER TABLE `orders_product`
  MODIFY `orderprodId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=75;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `prodId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `staffId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orderCreated` FOREIGN KEY (`orderCreatedby`) REFERENCES `staff` (`staffId`);

--
-- Constraints for table `orders_product`
--
ALTER TABLE `orders_product`
  ADD CONSTRAINT `order` FOREIGN KEY (`orderId`) REFERENCES `orders` (`orderId`),
  ADD CONSTRAINT `product` FOREIGN KEY (`prodId`) REFERENCES `product` (`prodId`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `add` FOREIGN KEY (`prodAdded`) REFERENCES `admin` (`adminId`),
  ADD CONSTRAINT `category` FOREIGN KEY (`catId`) REFERENCES `category` (`catId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
