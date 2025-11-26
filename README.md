

API REST para detectar mutantes analizando secuencias de ADN, desarrollada como examen técnico para MercadoLibre.

## Características del proyecto

* **Detección de Mutantes:** Algoritmo optimizado para detectar más de una secuencia de 4 letras iguales (Horizontal, Vertical, Diagonal).
* **API REST:** Endpoints para verificación y estadísticas.
* **Base de Datos:** H2 (En memoria) para almacenar resultados y evitar re-procesar ADNs conocidos.
* **Arquitectura Sólida:** Implementación de Patrones de Diseño (**Strategy**, **Chain of Responsibility**) para un código limpio y escalable.
* **Testing:** Cobertura de pruebas unitarias e integración.

## Cómo ejecutar el proyecto

### Prerrequisitos
* Java 17 JDK instalado.
* Gradle (incluido en el wrapper).

### Pasos
1.  **Clonar el repositorio:**
    ```bash
    git clone <[TU_URL_DEL_REPO](https://github.com/Matias3096/ExamenMercado-DS2025.git)>
    cd ExamenMercado-DS2025
    ```

2.  **Ejecutar la aplicación:**
    ```bash
    # En Windows
    ./gradlew.bat bootRun

    # En Mac/Linux
    ./gradlew bootRun
    ```

3.  **Acceder a la API:**
    * La aplicación iniciará en `http://localhost:8080`.
    * **Swagger UI (Documentación interactiva):** `http://localhost:8080/swagger-ui.html`
    * **Consola H2:** `http://localhost:8080/h2-console`

## Endpoints de la API

### 1. Detectar Mutante
Este es el endpoint principal donde envías el ADN para analizar.

URL: /mutant

Método: POST (porque estás enviando datos para procesar).

Qué recibe: Un JSON con la secuencia de ADN.
```bash
   {
  "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
}
```
Respuesta esperada: 

* 200 OK: Si el ADN es de un Mutante.

* 403 Forbidden: Si el ADN es de un Humano.

* 400 Bad Request: Si se enviaron datos inválidos (ej. una matriz que no es cuadrada o letras raras).

  
### 2. Endpoint de Estadísticas (GET /stats)
Este endpoint sirve para consultar el reporte de cuántos análisis has hecho.

URL: /stats

Método: GET (porque solo se pide información, no se envia nada).

Qué recibe: Nada (cuerpo vacío).

Qué responde: Un JSON con el conteo total.

```bash
  {
  "count_mutant_dna": 40,
  "count_human_dna": 100,
  "ratio": 0.4
}
```
### ¿Dónde se escriben los endpoints en el código?
Se encuentran exclusivamente en la clase MutantController.java.
Esa clase tendrá métodos con anotaciones como @PostMapping("/mutant") y @GetMapping("/stats"), que le dicen a Spring Boot: "Cuando alguien llame a esta dirección, ejecuta este pedazo de código".

### Arquitectura y Diseño
El proyecto destaca por el uso de Patrones de Diseño para desacoplar la lógica:

Controller (/controller): Maneja las peticiones HTTP.

Service (/service): Orquesta la lógica, conecta con BD y verifica duplicados (Hashing).

Repository (/repository): Capa de persistencia con JPA/H2.

Domain (/domain):

Strategy Pattern (/strategy): Separamos la lógica de búsqueda (HorizontalDetector, VerticalDetector, etc.) para facilitar la extensión y el testing.

Chain of Responsibility (/validator): Validaciones en cadena (NotNull, NxN, Regex) para asegurar la integridad de los datos.

### Testing
Ejecutar todos los tests y generar reporte de cobertura:

### Autores - Grupo 5



BATTELLA VERCESI,Luisina F (persistencia)

FERNANDEZ,Matias L (Logica core)

GONZALEZ PUJADO,M.Candela (testing)

PONCE, Alma Yasmin (controller)

VILLARREAL,Maria Jose (QA)












