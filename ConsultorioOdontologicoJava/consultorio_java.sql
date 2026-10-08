-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-10-2026 a las 05:11:44
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
-- Base de datos: `consultorio_java`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_clinico`
--

CREATE TABLE `historial_clinico` (
  `id` int(11) NOT NULL,
  `paciente_id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `consulta` varchar(200) NOT NULL,
  `tratamiento` varchar(200) DEFAULT NULL,
  `observaciones` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `historial_clinico`
--

INSERT INTO `historial_clinico` (`id`, `paciente_id`, `fecha`, `consulta`, `tratamiento`, `observaciones`) VALUES
(1, 1, '2026-10-02', 'Dolor dental', 'Restauración', 'Se realizó evaluación de la pieza dental.'),
(3, 1, '2026-10-02', 'Limpieza dental', 'Profilaxis', 'Se realizó limpieza general'),
(4, 4, '2026-06-10', ' Limpieza dental', '', ''),
(5, 4, '2026-10-07', 'Desgaste dental por bruxismo', 'Fabricación de placa de relajación', ''),
(6, 4, '2026-10-07', 'Caries', 'Resina', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `odontograma`
--

CREATE TABLE `odontograma` (
  `id` int(11) NOT NULL,
  `historial_id` int(11) NOT NULL,
  `numero_diente` varchar(3) NOT NULL,
  `estado` varchar(30) NOT NULL,
  `observacion` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `odontograma`
--

INSERT INTO `odontograma` (`id`, `historial_id`, `numero_diente`, `estado`, `observacion`) VALUES
(1, 6, '18', 'Caries', ''),
(2, 6, '17', 'Sano', ''),
(3, 6, '16', 'Sano', ''),
(4, 6, '15', 'Sano', ''),
(5, 6, '14', 'Sano', ''),
(6, 6, '13', 'Sano', ''),
(7, 6, '12', 'Sano', ''),
(8, 6, '11', 'Sano', ''),
(9, 6, '21', 'Sano', ''),
(10, 6, '22', 'Sano', ''),
(11, 6, '23', 'Sano', ''),
(12, 6, '24', 'Sano', ''),
(13, 6, '25', 'Sano', ''),
(14, 6, '26', 'Sano', ''),
(15, 6, '27', 'Sano', ''),
(16, 6, '28', 'Sano', ''),
(17, 6, '48', 'Sano', ''),
(18, 6, '47', 'Sano', ''),
(19, 6, '46', 'Sano', ''),
(20, 6, '45', 'Sano', ''),
(21, 6, '44', 'Sano', ''),
(22, 6, '43', 'Sano', ''),
(23, 6, '42', 'Sano', ''),
(24, 6, '41', 'Sano', ''),
(25, 6, '31', 'Sano', ''),
(26, 6, '32', 'Sano', ''),
(27, 6, '33', 'Sano', ''),
(28, 6, '34', 'Sano', ''),
(29, 6, '35', 'Sano', ''),
(30, 6, '36', 'Sano', ''),
(31, 6, '37', 'Sano', ''),
(32, 6, '38', 'Sano', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pacientes`
--

CREATE TABLE `pacientes` (
  `id` int(11) NOT NULL,
  `cedula` varchar(20) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `direccion` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pacientes`
--

INSERT INTO `pacientes` (`id`, `cedula`, `nombre`, `apellido`, `telefono`, `fecha_nacimiento`, `direccion`) VALUES
(1, '12345678', 'María', 'González', '04121234567', '1995-05-15', 'Av. Principal'),
(4, '27084929', 'Marialis', 'Marzana', '0424461779', '1998-07-07', 'San Diego'),
(5, '31487291', 'Javier', 'Mendoza', '04244179135', '2006-02-18', 'Valencia');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `historial_clinico`
--
ALTER TABLE `historial_clinico`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_historial_paciente` (`paciente_id`);

--
-- Indices de la tabla `odontograma`
--
ALTER TABLE `odontograma`
  ADD PRIMARY KEY (`id`),
  ADD KEY `historial_id` (`historial_id`);

--
-- Indices de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cedula` (`cedula`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `historial_clinico`
--
ALTER TABLE `historial_clinico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `odontograma`
--
ALTER TABLE `odontograma`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `historial_clinico`
--
ALTER TABLE `historial_clinico`
  ADD CONSTRAINT `fk_historial_paciente` FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`id`);

--
-- Filtros para la tabla `odontograma`
--
ALTER TABLE `odontograma`
  ADD CONSTRAINT `odontograma_ibfk_1` FOREIGN KEY (`historial_id`) REFERENCES `historial_clinico` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
