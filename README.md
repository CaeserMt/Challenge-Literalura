# Proyecto Gutendex API

## Descripción
Este proyecto tiene como objetivo interactuar con la API **Gutendex**, una herramienta que proporciona metadatos sobre más de 70,000 libros disponibles en Project Gutenberg, una biblioteca en línea gratuita. La API permite explorar, filtrar y analizar información detallada sobre los libros.

El desarrollo de este proyecto forma parte de las actividades del programa ONE (Oracle Next Education), demostrando las habilidades adquiridas en manejo de APIs y desarrollo de soluciones basadas en datos.

---

## Autor
- **Cesar Raul Morales Ticona**  
  Especialista en Ingeniería de Sistemas e Informática, con sólida experiencia en tecnología y automatización. Apasionado por la creación de soluciones prácticas que aprovechan herramientas digitales avanzadas.

---

## Objetivo del Proyecto
Desarrollar un programa que permita a los usuarios realizar consultas específicas a la API Gutendex y explorar información relevante como:
- Títulos de libros
- Autores
- Idiomas
- Temas
- Fechas de publicación

Además, se busca:
- Mostrar los resultados en una interfaz clara y funcional.
- Proveer funcionalidades de exportación de datos en formato JSON para análisis posterior.
- Fomentar el aprendizaje sobre consumo de APIs RESTful y manipulación de datos JSON.

---

## Requisitos
### Software Necesario
- [Node.js](https://nodejs.org) (Recomendado para ejecutar el programa).
- Un editor de texto o IDE, como Visual Studio Code.

### Librerías
Las siguientes herramientas pueden ser útiles para el proyecto:
- [Axios](https://axios-http.com/) o Fetch API para manejar solicitudes HTTP.
- Herramientas para manipulación de datos JSON, como `fs` (en Node.js).

### Acceso a la API
La API **Gutendex** no requiere autenticación ni claves de acceso. Puedes comenzar a consumirla directamente desde la URL base:
- **Base URL:** [https://gutendex.com/](https://gutendex.com/)
- **Endpoint principal:** `/books`

---

## Funcionalidades del Proyecto
1. Consultar el catálogo completo de libros.
2. Filtrar resultados por:
   - Autor
   - Idioma
   - Temas
   - Fecha de publicación
3. Ordenar libros por popularidad.
4. Exportar resultados de consultas en formato JSON para análisis posterior.

---

## Estructura del Proyecto
```
├── src/
│   ├── api.js          # Lógica para consumir la API Gutendex
│   ├── utils.js        # Funciones auxiliares (e.g., formateo de datos)
│   └── index.js        # Punto de entrada principal
├── data/
│   └── results.json    # Archivo de resultados exportados
├── README.md           # Descripción del proyecto
├── package.json        # Configuración de dependencias
└── LICENSE             # Licencia del proyecto
```

---

## Ejemplo de Uso
### Consulta Básica
Obtener el catálogo completo de libros:
```bash
GET https://gutendex.com/books/
```
**Respuesta esperada:**
```json
{
  "count": 70943,
  "next": "https://gutendex.com/books/?page=2",
  "previous": null,
  "results": [
    {
      "id": 1,
      "title": "Pride and Prejudice",
      "authors": [
        {
          "name": "Jane Austen",
          "birth_year": 1775,
          "death_year": 1817
        }
      ],
      "languages": ["en"],
      "download_count": 45678
    }
  ]
}
```

### Filtrado por Autor
Buscar libros de **Jane Austen**:
```bash
GET https://gutendex.com/books/?search=Jane%20Austen
```

---

## Instalación y Ejecución
1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/usuario/proyecto-gutendex.git
   ```
2. **Instalar dependencias**:
   ```bash
   npm install
   ```
3. **Ejecutar el programa**:
   ```bash
   node src/index.js
   ```

---

## Mejoras Futuras
- Implementar una interfaz gráfica con herramientas como React o Vue.js.
- Añadir soporte para búsquedas avanzadas por múltiples parámetros combinados.
- Conectar el proyecto con una base de datos para almacenar los resultados localmente.
- Generar gráficos interactivos para analizar tendencias en el uso de los libros.

---

## Recursos Adicionales
- [Documentación de Gutendex](https://gutendex.com/)
- [Project Gutenberg](https://www.gutenberg.org/)
- [Guía de Axios](https://axios-http.com/docs/intro)

---

## Contacto
¿Tienes preguntas o sugerencias? ¡Contáctame!  
- **Cesar Raul Morales Ticona** 
- **LinkedIn:**
- [Cesar Morales](https://www.linkedin.com/in/cesar-raul-morales-ticona/)  

---

## Licencia
Este proyecto está licenciado bajo la **Licencia MIT**. Puedes consultar el archivo `LICENSE` para más información.

