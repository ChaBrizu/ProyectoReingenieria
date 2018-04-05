-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 05-04-2018 a las 19:38:46
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `puntoventa`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE IF NOT EXISTS `productos` (
  `id_producto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `costo` float NOT NULL,
  `cantidad` int(3) NOT NULL,
  `descripcion` varchar(140) NOT NULL,
  `codigo_producto` varchar(16) NOT NULL,
  PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id_producto`, `nombre`, `costo`, `cantidad`, `descripcion`, `codigo_producto`) VALUES
(1, 'Leche', 25.5, 10, 'Lacteos', '549076'),
(2, 'Pan', 3.5, 20, 'Bolillos', '123456789'),
(3, 'Sabritas', 12, 60, 'Papas fritas', '234561'),
(4, 'Doritos', 6, 34, 'Papas fritas sabor queso', '521346'),
(5, 'Maruchan', 11.5, 10, 'Sopa instantanea', '123000'),
(6, 'Coca Cola', 25, 100, 'Bebida gasificada', '50001002'),
(8, 'Chocolate', 3, 100, 'Dulce sabor cacao, marca cremino', '00009879');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro`
--

CREATE TABLE IF NOT EXISTS `registro` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(20) NOT NULL,
  `contraseña` varchar(20) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `registro`
--

INSERT INTO `registro` (`id_user`, `usuario`, `contraseña`) VALUES
(2, 'emmanuel', '1234'),
(3, 'martin', 'hola'),
(4, 'Joel', '1234');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registrousers`
--

CREATE TABLE IF NOT EXISTS `registrousers` (
  `id_admin` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(20) NOT NULL,
  `contraseña` varchar(20) NOT NULL,
  `tipoUser` enum('admin','usuario') NOT NULL,
  PRIMARY KEY (`id_admin`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `registrousers`
--

INSERT INTO `registrousers` (`id_admin`, `usuario`, `contraseña`, `tipoUser`) VALUES
(1, 'carlos', 'barrio', 'admin'),
(2, 'emma', 'insite', 'usuario'),
(4, 'Jana', 'hola', 'admin');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
