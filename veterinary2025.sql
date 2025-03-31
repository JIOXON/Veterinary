-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-03-2025 a las 19:23:34
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `veterinary2025`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clinical_history`
--

CREATE TABLE `clinical_history` (
  `history_id` bigint(20) NOT NULL,
  `details` varchar(255) DEFAULT NULL,
  `pet_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `invoice`
--

CREATE TABLE `invoice` (
  `invoice_id` bigint(20) NOT NULL,
  `amount` bigint(20) DEFAULT NULL,
  `date_invoice` date DEFAULT NULL,
  `total_cost` double DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `pet_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orders`
--

CREATE TABLE `orders` (
  `order_id` bigint(20) NOT NULL,
  `medicine` varchar(255) DEFAULT NULL,
  `order_generation` date DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  `pet_id` bigint(20) DEFAULT NULL,
  `veterinarian_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `person`
--

CREATE TABLE `person` (
  `person_id` bigint(20) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `document` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `person`
--

INSERT INTO `person` (`person_id`, `age`, `document`, `name`) VALUES
(1, 20, 1011393244, 'Camilo'),
(2, 25, 10119, 'Camilo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pet`
--

CREATE TABLE `pet` (
  `pet_id` bigint(20) NOT NULL,
  `breed` varchar(255) DEFAULT NULL,
  `characteristics` varchar(255) DEFAULT NULL,
  `pet_age` int(11) DEFAULT NULL,
  `pet_name` varchar(255) DEFAULT NULL,
  `species` varchar(255) DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `petowner_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pet_owner`
--

CREATE TABLE `pet_owner` (
  `id_owner` bigint(20) NOT NULL,
  `person_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product`
--

CREATE TABLE `product` (
  `product_id` bigint(20) NOT NULL,
  `price` double DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `person_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`user_id`, `password`, `role`, `user_name`, `person_id`) VALUES
(1, '1234', 'Admin', 'Juan', 1),
(2, '1234', 'Veterinarian', 'camilo', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clinical_history`
--
ALTER TABLE `clinical_history`
  ADD PRIMARY KEY (`history_id`),
  ADD UNIQUE KEY `UK4bqqyk9jptpb6cq8njgyuboqu` (`pet_id`);

--
-- Indices de la tabla `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`invoice_id`),
  ADD UNIQUE KEY `UKinhb3r2ihxf7p68x9r87h4f36` (`owner_id`),
  ADD UNIQUE KEY `UKgnfabg6rvhoc3c9o4deqb1hn4` (`order_id`),
  ADD UNIQUE KEY `UK94wdo688wnnpkpfvnje9c9cp9` (`pet_id`);

--
-- Indices de la tabla `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD UNIQUE KEY `UKiy415fsyvymv9u2qj1tsuliv2` (`owner_id`),
  ADD UNIQUE KEY `UKc0uxbwl3i5o2lmj78vvhrei8b` (`pet_id`),
  ADD UNIQUE KEY `UKclmbpiyietqj5o3s723ygqibj` (`veterinarian_id`);

--
-- Indices de la tabla `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`person_id`);

--
-- Indices de la tabla `pet`
--
ALTER TABLE `pet`
  ADD PRIMARY KEY (`pet_id`),
  ADD UNIQUE KEY `UKdvuc84ouoev9rwk3a5qurcxx7` (`petowner_id`);

--
-- Indices de la tabla `pet_owner`
--
ALTER TABLE `pet_owner`
  ADD PRIMARY KEY (`id_owner`),
  ADD UNIQUE KEY `UK865ytdklrsfd2n8datp8slv03` (`person_id`);

--
-- Indices de la tabla `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `UK97ih1g5lcdf1s3fg7oo4e18jw` (`person_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clinical_history`
--
ALTER TABLE `clinical_history`
  MODIFY `history_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `invoice`
--
ALTER TABLE `invoice`
  MODIFY `invoice_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `person`
--
ALTER TABLE `person`
  MODIFY `person_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `pet`
--
ALTER TABLE `pet`
  MODIFY `pet_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pet_owner`
--
ALTER TABLE `pet_owner`
  MODIFY `id_owner` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `product`
--
ALTER TABLE `product`
  MODIFY `product_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `clinical_history`
--
ALTER TABLE `clinical_history`
  ADD CONSTRAINT `FKlrsrjwejt4dywwy3lu7tcywo3` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`pet_id`);

--
-- Filtros para la tabla `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `FKdwqj0cnwb43237x54ql3ene8v` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`pet_id`),
  ADD CONSTRAINT `FKea0evpdue9h3fp1a0pekwns0h` FOREIGN KEY (`owner_id`) REFERENCES `pet_owner` (`id_owner`),
  ADD CONSTRAINT `FKthf5w8xuexpjinfl7xheakhqn` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`);

--
-- Filtros para la tabla `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK8tk5rwfv7ag4q8qbd0p4jw6jd` FOREIGN KEY (`veterinarian_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FKeyrir43ubbpxym3ubd3p10beo` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`pet_id`),
  ADD CONSTRAINT `FKqfuc6cu0vhwujii887mfryfes` FOREIGN KEY (`owner_id`) REFERENCES `pet_owner` (`id_owner`);

--
-- Filtros para la tabla `pet`
--
ALTER TABLE `pet`
  ADD CONSTRAINT `FKmev0v0cb8fiu6t3jfibw5b1i0` FOREIGN KEY (`petowner_id`) REFERENCES `pet_owner` (`id_owner`);

--
-- Filtros para la tabla `pet_owner`
--
ALTER TABLE `pet_owner`
  ADD CONSTRAINT `FKojwgd4qjwt4ygin2d2biudajk` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`);

--
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FKd21kkcigxa21xuby5i3va9ncs` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
