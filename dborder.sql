-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 15, 2017 at 09:19 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dborder`
--

-- --------------------------------------------------------

--
-- Table structure for table `beverages`
--

CREATE TABLE `beverages` (
  `Id` varchar(8) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Type` varchar(25) NOT NULL,
  `Price` varchar(15) NOT NULL,
  `Description` varchar(60) NOT NULL,
  `Main Menu` varchar(25) NOT NULL,
  `Sub_Menu` varchar(20) NOT NULL,
  `Picture` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `beverages`
--

INSERT INTO `beverages` (`Id`, `Name`, `Type`, `Price`, `Description`, `Main Menu`, `Sub_Menu`, `Picture`) VALUES
('76604', 'Ginger tea', 'Not Applicable', '10', '1 cup', 'Beverages', 'Refreshing Teas', 'http://192.168.43.49/ProjectHungerBhagao/images/76604'),
('19276', 'Masala tea', 'Not Applicable', '15', '1 cup', 'Beverages', 'Refreshing Teas', 'http://192.168.43.49/ProjectHungerBhagao/images/19276'),
('62547', 'Coffee latte', 'Not Applicable', '55', '1 cup', 'Beverages', 'Coffee', 'http://192.168.43.49/ProjectHungerBhagao/images/62547'),
('98732', 'Cappuccino coffee', 'Not Applicable', '65', '1 cup', 'Beverages', 'Coffee', 'http://192.168.43.49/ProjectHungerBhagao/images/98732'),
('46239', 'Lemonade', 'Not Applicable', '25', 'medium sized glass', 'Beverages', 'Fruiteezers', 'http://192.168.43.49/ProjectHungerBhagao/images/46239'),
('81178', 'Berries feast', 'Not Applicable', '40', 'made up of pure berries', 'Beverages', 'Fruiteezers', 'http://192.168.43.49/ProjectHungerBhagao/images/81178'),
('36825', 'Orange juice', 'Not Applicable', '50', 'fresh Orange extract', 'Beverages', 'Fruiteezers', 'http://192.168.43.49/ProjectHungerBhagao/images/36825'),
('29904', 'Pepsi', 'Not Applicable', '25', '600 ml bottle', 'Beverages', 'Summer Chillers', 'http://192.168.43.49/ProjectHungerBhagao/images/29904'),
('34337', 'Cold coffee', 'Not Applicable', '45', '1 mug', 'Beverages', 'Summer Chillers', 'http://192.168.43.49/ProjectHungerBhagao/images/34337'),
('42029', 'Pineapple shake', 'Not Applicable', '40', '1 glass', 'Beverages', 'Summer Chillers', 'http://192.168.43.49/ProjectHungerBhagao/images/42029'),
('25502', 'Chocolate Shake', 'Not Applicable', '30', '1 glass', 'Beverages', 'Summer Chillers', 'http://192.168.43.49/ProjectHungerBhagao/images/25502'),
('25215', 'Kiwi shake', 'Not Applicable', '50', '1 glass', 'Beverages', 'Summer Chillers', 'http://192.168.43.49/ProjectHungerBhagao/images/25215');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `Name` varchar(25) NOT NULL,
  `User_Name` varchar(20) NOT NULL,
  `Email_Id` varchar(25) NOT NULL,
  `Password` varchar(25) NOT NULL,
  `Confirm_Password` varchar(25) NOT NULL,
  `Mobile` varchar(15) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`Name`, `User_Name`, `Email_Id`, `Password`, `Confirm_Password`, `Mobile`) VALUES
('rashika shukla', 'rashi057', 'rashika059@gmail.com', '123', '123', '9952741270'),
('hello', 'saw', 'ee@gmail.com', 'cc', 'cc', '7845968271'),
('rrr', 'qq', 'ssd@gmail.com', 'aa', 'aa', '5874123605'),
('Nikita khanna', 'nikki12', 'nik@gmail.com', 'yes', 'yes', '8574109246'),
('Harry potter', 'Harry2', 'harry@gmail.com', 'magic', 'magic', '9985360574'),
('nn', 'vbb', 'aa@gmail.com', 'aa', 'aa', '8514726905'),
('ddddccc', 'qqqw', 'ff@gg.com', 'aa', 'aa', '4512783690'),
('Pragya shukla', 'Prague12', 'rr@gmail.com', 'car', 'car', '9985147236');

-- --------------------------------------------------------

--
-- Table structure for table `desserts`
--

CREATE TABLE `desserts` (
  `Id` varchar(8) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Type` varchar(20) NOT NULL,
  `Price` varchar(15) NOT NULL,
  `Description` varchar(60) NOT NULL,
  `Main Menu` varchar(15) NOT NULL,
  `Sub_Menu` varchar(20) NOT NULL,
  `Picture` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `desserts`
--

INSERT INTO `desserts` (`Id`, `Name`, `Type`, `Price`, `Description`, `Main Menu`, `Sub_Menu`, `Picture`) VALUES
('82339', 'Black forest pastry', 'Not Applicable', '65', '1 piece', 'Desserts', 'Pastries', 'http://192.168.43.49/ProjectHungerBhagao/images/82339'),
('95985', 'Pineapple pastry', 'Not Applicable', '65', '1 piece', 'Desserts', 'Pastries', 'http://192.168.43.49/ProjectHungerBhagao/images/95985'),
('76428', 'Birth by chocolate pastry', 'Not Applicable', '50', '1 piece', 'Desserts', 'Donuts', 'http://192.168.43.49/ProjectHungerBhagao/images/76428'),
('91696', 'Donut feast', 'Not Applicable', '60', '1 piece', 'Desserts', 'Donuts', 'http://192.168.43.49/ProjectHungerBhagao/images/91696'),
('55925', 'Chocolate chip ice cream', 'Not Applicable', '35', '1 scoop', 'Desserts', 'Frozen deserts', 'http://192.168.43.49/ProjectHungerBhagao/images/55925'),
('36081', 'Strawberry ice cream', 'Not Applicable', '20', '1 scoop', 'Desserts', 'Frozen deserts', 'http://192.168.43.49/ProjectHungerBhagao/images/36081'),
('96943', 'Tutti frutti ice cream', 'Not Applicable', '40', '1 scoop', 'Desserts', 'Frozen deserts', 'http://192.168.43.49/ProjectHungerBhagao/images/96943'),
('37870', 'Rasgulla', 'Not Applicable', '10', '1 piece', 'Desserts', 'Indian Sweets', 'http://192.168.43.49/ProjectHungerBhagao/images/37870'),
('44053', 'Rice kheer', 'Not Applicable', '30', '1 bowl', 'Desserts', 'Indian Sweets', 'http://192.168.43.49/ProjectHungerBhagao/images/44053'),
('82607', 'Gulab jamun', 'Not Applicable', '15', '1 piece', 'Desserts', 'Indian Sweets', 'http://192.168.43.49/ProjectHungerBhagao/images/82607'),
('13172', 'Indian thali', 'Not Applicable', '30', 'best', 'Desserts', 'Indian Sweets', 'http://192.168.43.49/ProjectHungerBhagao/images/13172');

-- --------------------------------------------------------

--
-- Table structure for table `orderlist`
--

CREATE TABLE `orderlist` (
  `Name` varchar(25) NOT NULL,
  `Address` varchar(70) NOT NULL,
  `OrderKey` varchar(25) NOT NULL,
  `Bill_Amount` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orderlist`
--

INSERT INTO `orderlist` (`Name`, `Address`, `OrderKey`, `Bill_Amount`) VALUES
('rashika', 'lko', 'saw', 'Rs.455'),
('saurabh', 'Hajratganj', 'saw', 'Rs.670');

-- --------------------------------------------------------

--
-- Table structure for table `prague12`
--

CREATE TABLE `prague12` (
  `id` int(6) UNSIGNED NOT NULL,
  `itemname` varchar(30) NOT NULL,
  `itemid` varchar(15) NOT NULL,
  `type` varchar(20) DEFAULT NULL,
  `quantity` varchar(15) DEFAULT NULL,
  `totalprice` varchar(15) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prague12`
--

INSERT INTO `prague12` (`id`, `itemname`, `itemid`, `type`, `quantity`, `totalprice`) VALUES
(1, 'Veggie Delight', '97299', 'Veg', '2', '90'),
(2, 'Veggie Delight', '97299', 'Veg', '2', '90');

-- --------------------------------------------------------

--
-- Table structure for table `saw`
--

CREATE TABLE `saw` (
  `id` int(6) UNSIGNED NOT NULL,
  `itemname` varchar(30) NOT NULL,
  `itemid` varchar(15) NOT NULL,
  `type` varchar(20) DEFAULT NULL,
  `quantity` varchar(15) DEFAULT NULL,
  `totalprice` varchar(15) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `saw`
--

INSERT INTO `saw` (`id`, `itemname`, `itemid`, `type`, `quantity`, `totalprice`) VALUES
(1, 'Birth by chocolate pastry', '76428', 'Not Applicable', '2', '100');

-- --------------------------------------------------------

--
-- Table structure for table `snacks`
--

CREATE TABLE `snacks` (
  `Id` varchar(8) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Type` varchar(20) NOT NULL,
  `Price` varchar(10) NOT NULL,
  `Description` varchar(60) NOT NULL,
  `Main Menu` varchar(25) NOT NULL,
  `Sub_Menu` varchar(20) NOT NULL,
  `Picture` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `snacks`
--

INSERT INTO `snacks` (`Id`, `Name`, `Type`, `Price`, `Description`, `Main Menu`, `Sub_Menu`, `Picture`) VALUES
('73748', 'Chilli maggi', 'Veg', '30', 'vegetarian, spicy Maggi', 'Snacks', 'Maggi and Noodles', 'http://192.168.43.49/ProjectHungerBhagao/images/73748'),
('52376', 'Paneer tikka burger', 'Veg', '85', 'grilled paneer filling', 'Snacks', 'Burgers', 'http://192.168.43.49/ProjectHungerBhagao/images/52376'),
('97299', 'Veggie Delight', 'Veg', '45', 'healthy and tasty burger, full of veggies', 'Snacks', 'Burgers', 'http://192.168.43.49/ProjectHungerBhagao/images/97299'),
('74595', 'Cheese burger', 'Veg', '65', 'mozilla cheese layers', 'Snacks', 'Burgers', 'http://192.168.43.49/ProjectHungerBhagao/images/74595'),
('9276', 'Chicken burger', 'Non Veg', '115', 'grilled chicken tikki', 'Snacks', 'Burgers', 'http://192.168.43.49/ProjectHungerBhagao/images/9276'),
('41544', 'Country Special', 'Veg', '199', 'pan pizza tossed with onion,capsicum,alpeno,corn,etc.', 'Snacks', 'Pizzas', 'http://192.168.43.49/ProjectHungerBhagao/images/41544'),
('41901', 'Cheese maggi', 'Veg', '55', 'maggi cooked in fat free cheese', 'Snacks', 'Maggi and Noodles', 'http://192.168.43.49/ProjectHungerBhagao/images/41901'),
('185', 'Veg. Chop Suey', 'Veg', '75', 'Cabbage,carrot ,capsicum,onion, pineapple in sweet and sour', 'Snacks', 'Maggi and Noodles', 'http://192.168.43.49/ProjectHungerBhagao/images/185'),
('98607', 'Hakka noodles', 'Veg', '90', 'Noodles and vegetable', 'Snacks', 'Maggi and Noodles', 'http://192.168.43.49/ProjectHungerBhagao/images/98607'),
('88333', 'Chicken Schezwan Noodles', 'Non Veg', '105', 'Noodles with chicken , baby corn,mushroom and schezwan', 'Snacks', 'Maggi and Noodles', 'http://192.168.43.49/ProjectHungerBhagao/images/88333'),
('55376', 'Burnt pepper garlic noodles', 'Non Veg', '117', 'shredded chicken tossed with butter and noodles', 'Snacks', 'Maggi and Noodles', 'http://192.168.43.49/ProjectHungerBhagao/images/55376'),
('95299', 'Pasta Alfredo', 'Veg', '190', 'Boiled pasta sauteed with Italian herbs,pamesan cheese', 'Snacks', 'Pastas', 'http://192.168.43.49/ProjectHungerBhagao/images/95299'),
('1685', 'Red sauce pasta', 'Veg', '65', 'spicy pasta sauteed in red tomato sauce', 'Snacks', 'Pastas', 'http://192.168.43.49/ProjectHungerBhagao/images/1685'),
('46751', 'Farmhouse pizza', 'Veg', '175', 'Hand tossed pizza with cheese, capsicum and much more', 'Snacks', 'Pizzas', 'http://192.168.43.49/ProjectHungerBhagao/images/46751'),
('816', 'Paneer tikka rolls', 'Veg', '65', 'grilled paneer stuffing', 'Snacks', 'Rolls', 'http://192.168.43.49/ProjectHungerBhagao/images/816'),
('36221', 'Spring roll', 'Veg', '65', 'spicy ', 'Snacks', 'Rolls', 'http://192.168.43.49/ProjectHungerBhagao/images/36221'),
('13395', 'Hot and Sour soup', 'Veg', '85', 'served hot and Sour', 'Snacks', 'Soups', 'http://192.168.43.49/ProjectHungerBhagao/images/13395'),
('38940', 'Manchow soup', 'Veg', '115', 'perfect winter treat', 'Snacks', 'Soups', 'http://192.168.43.49/ProjectHungerBhagao/images/38940'),
('61227', 'Pani puri', 'Veg', '25', 'Indian fast food,spicy and refreshing juice', 'Snacks', 'Specialities', 'http://192.168.43.49/ProjectHungerBhagao/images/61227'),
('2712', 'Pav bhaji', 'Veg', '40', 'spicy and delicious', 'Snacks', 'Specialities', 'http://192.168.43.49/ProjectHungerBhagao/images/2712'),
('72025', 'Aloo tikki burger', 'Veg', '45', 'yummy and delicious', 'Snacks', 'Burgers', 'http://192.168.43.49/ProjectHungerBhagao/images/72025'),
('19928', 'Pizza', 'Veg', '200', 'large', 'Snacks', 'Burgers', 'http://192.168.43.49/ProjectHungerBhagao/images/19928');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `beverages`
--
ALTER TABLE `beverages`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Name` (`Name`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`User_Name`);

--
-- Indexes for table `desserts`
--
ALTER TABLE `desserts`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `UNIQUE` (`Name`);

--
-- Indexes for table `prague12`
--
ALTER TABLE `prague12`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `saw`
--
ALTER TABLE `saw`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `snacks`
--
ALTER TABLE `snacks`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Name` (`Name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `prague12`
--
ALTER TABLE `prague12`
  MODIFY `id` int(6) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `saw`
--
ALTER TABLE `saw`
  MODIFY `id` int(6) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
