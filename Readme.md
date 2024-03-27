# Catalog Service
Este es un servicio de catálogo que gestiona información sobre películas y series.
Realizado en el marco de la carrera - Certified Tech Developer - especialización Backend, para la asignatura Backend I.

- Profesora: Vanina Godoy

## Índice

- [Descripción](#descripción)
- [Implementación](#implementación)
- [Explicación del patrón de Circuit Breaker](#explicación-del-patrón-de-circuit-breaker)
- [Endpoints](#endpoints)
- [Uso](#uso)
- [Contribuciones](#contribuciones)
- [Autores](#autores)

## Descripción
El servicio de catálogo permite a los usuarios acceder a información sobre películas y series. Utiliza microservicios separados para gestionar películas y series, y proporciona una interfaz única para acceder a esta información.

## Implementación
El servicio de catálogo se implementa utilizando JAVA Spring Boot y Feign para comunicarse con los microservicios de películas y series. Se ha utilizado el patrón de Circuit Breaker para manejar posibles fallas en los servicios remotos de películas y series y así prevenir que los errores en estos servicios afecten negativamente la funcionalidad general del sistema. Este enfoque ayuda a garantizar una experiencia de usuario más estable y confiable.

## Explicación del patrón de Circuit Breaker
El circuit breaker se implementó en los microservicios de serie y película porque estas operaciones son críticas para la funcionalidad del sistema. Si la recuperación de datos de estos servicios falla, podría haber una degradación en la calidad o completitud de la información mostrada en el catálogo. Por lo tanto, el circuit breaker proporciona un mecanismo para manejar de manera controlada posibles fallas en estas áreas clave del sistema, evitando que los errores en los servicios remotos impacten negativamente en la funcionalidad general. Por ejemplo, si los servicios de películas o series experimentan problemas de conectividad o degradación del rendimiento, el circuit breaker permite al sistema responder de manera controlada, evitando la propagación de errores y garantizando una experiencia de usuario más estable y consistente.

## Endpoints
A continuación se detallan los endpoints disponibles en este servicio de catálogo:

- /api/v1/catalog/movies/{genre}: Obtiene una lista de películas por género.
- /api/v1/catalog/series/{genre}: Obtiene una lista de series por género.
- /api/v1/catalog/movies/save: Guarda una nueva película.
- /api/v1/catalog/series/save: Guarda una nueva serie.

## Uso
Para utilizar este servicio de catálogo, simplemente envía solicitudes HTTP a los endpoints correspondientes.

## Contribuciones
Las contribuciones son bienvenidas. Si desea contribuir a este proyecto, siga estos pasos:

1. Haga un fork del repositorio.
2. Cree una nueva rama (`git checkout -b feature`).
3. Realice sus cambios.
4. Haga commit de sus cambios (`git commit -am 'Add new feature'`).
5. Haga push de su rama (`git push origin feature`).
6. Abra un pull request.

## Autores
Este servicio de catálogo fue desarrollado por:
- Fabricio González
- Juan Pablo Muttoni
