-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost:3306
-- Thời gian đã tạo: Th10 05, 2021 lúc 03:00 AM
-- Phiên bản máy phục vụ: 10.4.19-MariaDB
-- Phiên bản PHP: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `dbo`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `departments`
--

CREATE TABLE `departments` (
  `dept_no` int(11) NOT NULL,
  `dept_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `departments`
--

INSERT INTO `departments` (`dept_no`, `dept_name`, `description`) VALUES
(5, 'FIT 1', 'Teach technology 1'),
(6, 'FIT 2', 'Teach technology 2'),
(7, 'FIT 3', 'Teach technology 3'),
(8, 'FIT 4', 'Teach technology 4'),
(9, 'FIT 5', 'Teach technology 5'),
(10, 'FIT 6', 'Teach technology 6'),
(11, 'FIT 7', 'Teach technology 7'),
(12, 'FIT 8', 'Teach technology 8'),
(13, 'FIT 9', 'Teach technology 9');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employees`
--

CREATE TABLE `employees` (
  `emp_no` int(11) NOT NULL,
  `birth_date` date DEFAULT NULL,
  `first_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` char(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `hire_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `employees`
--

INSERT INTO `employees` (`emp_no`, `birth_date`, `first_name`, `last_name`, `gender`, `hire_date`) VALUES
(6, '2001-03-24', 'DO', 'PHUC DAI', 'M', '2020-03-24'),
(7, '2001-03-24', 'NGUYEN', 'PHUC DAI ', 'M', '2018-03-24'),
(8, '2001-03-24', 'NGO', 'PHUC DAI ', 'M', '2019-03-24'),
(9, '2001-03-24', 'BUI', 'PHUC DAI ', 'M', '2017-03-24'),
(10, '2001-03-24', 'PHAN', 'PHUC DAI ', 'M', '2017-03-24'),
(11, '2001-03-24', 'TRAN', 'PHUC DAI ', 'M', '2017-03-24');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `working_history`
--

CREATE TABLE `working_history` (
  `dept_no` int(11) NOT NULL,
  `emp_no` int(11) NOT NULL,
  `from_date` date DEFAULT NULL,
  `to_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `working_history`
--

INSERT INTO `working_history` (`dept_no`, `emp_no`, `from_date`, `to_date`) VALUES
(6, 6, '2010-03-24', '2011-03-24');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `departments`
--
ALTER TABLE `departments`
  ADD PRIMARY KEY (`dept_no`);

--
-- Chỉ mục cho bảng `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`emp_no`);

--
-- Chỉ mục cho bảng `working_history`
--
ALTER TABLE `working_history`
  ADD KEY `emp_no` (`emp_no`),
  ADD KEY `dept_no` (`dept_no`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `departments`
--
ALTER TABLE `departments`
  MODIFY `dept_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `employees`
--
ALTER TABLE `employees`
  MODIFY `emp_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `working_history`
--
ALTER TABLE `working_history`
  ADD CONSTRAINT `working_history_ibfk_1` FOREIGN KEY (`emp_no`) REFERENCES `employees` (`emp_no`),
  ADD CONSTRAINT `working_history_ibfk_2` FOREIGN KEY (`dept_no`) REFERENCES `departments` (`dept_no`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
