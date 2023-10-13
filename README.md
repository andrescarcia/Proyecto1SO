# Proyecto1SO

# Problemática 

Cada día se acercan más los Game Awards 2023, y este año en particular muchas empresas desean simpatizar más con su público Latinoamericano para mejorar sus oportunidades de ganar el Juego del año 2023. Así que están considerando abrir sedes de desarrollo en Venezuela, un punto estratégico en la región gracias a su cercanía con los servidores estadounidenses. 

Sin embargo, dado a lo arriesgado de dicha decisión, las empresas han optado por contratar a desarrolladores de software que puedan crear una simulación y así determinar la viabilidad de la apertura de un estudio de desarrollo de videojuegos en el país. Después de todo, las interesadas, Capcom y Square Enix, no pueden darse el lujo de realizar una inversión equivocada.

El objetivo de la simulación es conocer las ganancias potenciales con la producción y venta de videojuegos, contrastadas con los gastos operativos que implican mantener al estudio de desarrollo. Además, las empresas no quieren ceder ante la competencia, así que han solicitado comparar el rendimiento de su estudio con el de una empresa rival, para así determinar si vale la pena realizar la inversión.

Para lograrlo, los desarrolladores de software deben presentar un Dashboard en donde se puedan visualizar los datos solicitados de forma dinámica y creativa, a su vez de permitir la manipulación del número de desarrolladores en cada estudio, para así conocer la combinación óptima de los mismos.

## Lineamientos generales 

### 1. Desarrolladores de los estudios

Los estudios requieren personal especializado en cada área, al que se le debe pagar un sueldo por hora. También, cada grupo de trabajadores cuenta con un espacio de Google Drive, el cual tiene una capacidad limitada para guardar elementos del videojuego ya terminados, si la sección del Drive está llena no se pueden producir más elementos.

Cada estudio cuenta con: 
- **Capcom :11 trabajadores**
- **Square Enix :17 trabajadores**

Ese número de desarrolladores abarcan las áreas que se encuentran a continuación y a los integradores finales:

#### a. Desarrollador de narrativa

- Cobran: $10 la hora
- **Capcom**: 1 guión cada 2 días 
- **Square Enix**: 1 guión cada 4 días
- Drive máximo: 25 guiones

#### b. Diseñador de niveles

- Cobran: $13 la hora
- **Capcom**: 1 nivel cada 2 días 
- **Square Enix**: 1 nivel cada 4 días
- Drive máximo: 20 niveles

#### c. Artista de sprites

- Cobran: $20 la hora
- **Capcom**: 3 sprites set cada día
- **Square Enix**: 1 sprites set cada día
- Drive máximo: 55 Sprite Sets

#### d. Programador de lógica

- Cobran: $8 la hora
- **Capcom**: 3 sistemas de juego cada día 
- **Square Enix**: 5 sistemas de juego cada día
- Drive máximo: 35 Sistemas

#### e. Desarrollador de DLC

- Cobran: $17 la hora
- **Capcom**: 1 DLC cada 3 días 
- **Square Enix**: 1 DLC cada 2 días
- Drive máximo: 10 DLC

### 2. Integradores

Este tipo de empleados accede al Drive, toma las partes de videojuegos necesarias (dadas según la empresa con la que se trabaje) y ensambla un videojuego terminado, esto le toma 2 días y cobra 25$ la hora.

### 3. Project Manager

Solo hay uno por cada estudio, y su trabajo es registrar el paso de los días. Tiene acceso a un contador con los días restantes para la entrega de los juegos terminados a las tiendas. Sin embargo, al PM se le conoce por su fanatismo a los E-sports (Among us competitivo), a tal punto que las primeras 16 horas del día logra ver streams a escondidas. Cada intervalo de 30 minutos ve streams, y los siguientes 30 minutos trabaja revisando el avance del proyecto, siguiendo el ciclo durante las primeras 16 horas del día. Las últimas 8 horas del día las invierte cambiando el contador con los días restantes para la entrega. El PM cobra $20 la hora esté trabajando o esté viendo Streams.

### 4. Director

Solo hay uno por estudio y se encarga de revisar cuántos días restan para la entrega de los videojuegos a las tiendas. El director siempre cobra $30 la hora. Funciones detalladas:

#### a. Proceso de envío

Si el contador marca 0 días restantes, el director se encarga de enviar todos los videojuegos ya creados a las tiendas, lo que le toma 24 horas. Una vez terminado registra la nueva ganancia de los juegos enviados y reinicia el contador de los días restantes para entregar los videojuegos, lo cual le toma un tiempo despreciable.

#### b. Labores diarias

Si el contador marca 1 día o más restantes, entonces el director trabajará con labores administrativas durante todo el día. Además, escogerá una hora aleatoria del día en la que revisará lo que está haciendo el PM por 25 minutos. Si durante ese intervalo el director descubre al PM viendo streams, le colocará una falta y descontará $50 de su sueldo.

## Requerimientos funcionales 

- Uso de Hilos/Threads y Semáforos/Semaphores.
- Asegurar Exclusión mutua para Drive.
- Cada estudio de desarrollo debe contar en todo momento con un desarrollador de cada tipo como mínimo.
- Cada empresa requiere conocer los datos más relevantes sobre la simulación de su estudio.
- Uso de una interfaz gráfica.
- La simulación debe permitir en tiempo de ejecución varios ajustes.

## Consideraciones 

No se permite uso de librerías para estructuras de datos, como ArrayList.

## Especificaciones de las empresas de desarrollo

### Capcom

- **Videojuego estándar**: Narrativa simple (1 guión), niveles complejos (2 niveles), gráficos realistas (6 sets de sprites), sistemas complejos (5 sistemas de juego). Ganancia al sacar el juego: $400K.
  
- **Videojuego con DLC**: Después de crear 3 videojuegos, el siguiente tendrá 1 DLC adicional. Ganancia al sacar el juego: $750K.

### Square Enix

- **Videojuego estándar**: Narrativa simple (1 guión), niveles simples (1 nivel), gráficos retro (2 sets de sprites), sistemas abundantes (4 sistemas de juego). Ganancia al sacar el juego: $350K.
  
- **Videojuego con DLC**: Después de crear 2 videojuegos, el siguiente tendrá 3 DLC´s adicionales. Ganancia al sacar el juego: $700K.


