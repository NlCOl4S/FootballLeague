# ⚽ Football League API (V1.0)

API REST backend desarrollada para la gestión de una liga de fútbol, construida con Java y el ecosistema de Spring Boot. El proyecto cuenta con arquitectura segura basada en JWT, manejo centralizado de excepciones y documentación interactiva mediante OpenAPI/Swagger.

---

## 🚀 Tecnologías y Herramientas

* **Java 17+**
* **Spring Boot**
* **Spring Security** (con autenticación por tokens JWT)
* **Spring Data JPA / Hibernate**
* **MySQL Database**
* **OpenAPI / Swagger UI** (Documentación de API)
* **Maven** (Gestor de dependencias)

---

## 📌 Características Principales

* **Autenticación y Seguridad:** Sistema de login y registro con generación y validación de tokens JWT, control de sesiones stateless y revocación de tokens en el logout.
* **Roles y Permisos:** 
  * Endpoints protegidos según rol (`USER` y `ADMIN`).
  * Rutas de creación, actualización y eliminación restringidas a administradores.
* **Gestión de Recursos:** Endpoints RESTful completos para la administración de jugadores y equipos de la liga.
* **Manejo Global de Excepciones:** Respuestas de error personalizadas y estructuradas (como códigos `404 Not Found`) para una mejor depuración.
* **Documentación Viva:** Interfaz gráfica integrada para probar la API directamente desde el navegador.

---

## 📦 Documentación de la API (Swagger UI)

Una vez que ejecutes la aplicación de manera local, puedes acceder a la interfaz interactiva de Swagger para probar los endpoints y autenticarte con el token JWT:

* **URL de Swagger:** `http://localhost:8080/swagger-ui/index.html`

---

## ⚙️ Cómo Ejecutar el Proyecto Localmente

1. **Clona el repositorio:**
   ```bash
   git clone [https://github.com/NlCOl4S/FootballLeague.git](https://github.com/NlCOl4S/FootballLeague.git)
